package com.ct.soa.guess.core.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ct.commons.utils.DateUtil;

@RestController
@RequestMapping("/lol/")
public class LpLMatchController {
	
	private Logger logger = LoggerFactory.getLogger(LpLMatchController.class);
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	private String lpl_match_guess_list = "lpl_match_guess_list";
	
	public final static String lol_matchs_cn = "lol_matchs";
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/matchs/{league}", method=RequestMethod.GET)
	public List<Map> matchs(@PathVariable String league){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long start = c.getTimeInMillis();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
		long end = c.getTimeInMillis();
		logger.info("start={},end={},league={}",DateUtil.formatDatetime(new Date(start), "yyyy-MM-dd HH:mm:ss"),DateUtil.formatDatetime(new Date(end), "yyyy-MM-dd HH:mm:ss"),league);
		Query query = new Query().addCriteria(Criteria.where("match_date").lte(end).gte(start).and("league").is(league));
		return mongoTemplate.find(query, Map.class, lol_matchs_cn);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/guess/{league}/{play_type}", method=RequestMethod.GET)
	public Map<String, Map<String, Map>> guess(@PathVariable String league,@PathVariable String play_type){
		Map<String,Map<String,Map>> result = new HashMap<String,Map<String,Map>>();
		
		Query query = new Query().addCriteria(Criteria.where("bet_state").is("2").and("play_type").is(play_type));
		List<Map> guess = mongoTemplate.find(query, Map.class, lpl_match_guess_list);
		if(!guess.isEmpty()){
			
			Collections.sort(guess,new Comparator<Map>(){
				@Override
				public int compare(Map o1, Map o2) {
					Long t1 = Long.parseLong((String) o1.get("guess_end_time"));
					Long t2 = Long.parseLong((String) o2.get("guess_end_time"));
					return t1.compareTo(t2);
				}
				
			});
			
			List<String> matchIds = new ArrayList<String>();
			for(Map item : guess){
				matchIds.add((String)item.get("match_id"));
			}
			
			List<Map> matchs = mongoTemplate.find(new Query().addCriteria(Criteria.where("match_id").in(matchIds).and("league").is(league)), Map.class, lol_matchs_cn);
			Collections.sort(matchs,new Comparator<Map>(){
				@Override
				public int compare(Map o1, Map o2) {
					Long t1 = (Long) o1.get("match_date");
					Long t2 = (Long) o2.get("match_date");
					return t1.compareTo(t2);
				}
				
			});
			Map<String,Map> m = new TreeMap<String,Map>();
			for(Map item : matchs){
				m.put((String)item.get("match_id"), item);
			}
			result.put("matchs", m);
		}
		
		
		Map<String,Map> m = new TreeMap<String,Map>();
		for(Map item : guess){
			m.put((String)item.get("match_id"), item);
		}
		result.put("guess", m);
		return result;
		//return mongoTemplate.findAll(Map.class, lpl_match_guess_list);
	}
	
	public static void main(String[] args) {
		System.out.println(new Date(1501332180000l));
		System.out.println(new Date(1501322400000l));
	}
}
