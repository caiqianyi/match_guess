package com.ct.soa.guess.core.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<Map> guess(@PathVariable String league,@PathVariable String play_type){
		Query query = new Query().addCriteria(Criteria.where("bet_state").is("2").and("play_type").is(play_type));
		return mongoTemplate.find(query, Map.class, lpl_match_guess_list);
		//return mongoTemplate.findAll(Map.class, lpl_match_guess_list);
	}
}
