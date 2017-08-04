package com.ct.soa.guess.core.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ct.soa.guess.core.service.ILoLMatchGuessService;

@RestController
@RequestMapping("/lol/")
public class LpLMatchController {
	
	private Logger logger = LoggerFactory.getLogger(LpLMatchController.class);
	
	@Resource
	private ILoLMatchGuessService lolMatchGuessService;
	
	@RequestMapping(value="/matchs/{league}", method=RequestMethod.GET)
	public List<Map> matchs(@PathVariable String league){
		return lolMatchGuessService.matchs(league);
	}
	
	@RequestMapping(value="/guess/{league}/{play_type}", method=RequestMethod.GET)
	public Map<String, Map<String, Map>> guess(@PathVariable String league,@PathVariable String play_type){
		return lolMatchGuessService.guess(league, play_type);
	}
	
	@RequestMapping(value="/guess/pull", method=RequestMethod.POST)
	public boolean pullMatchGuess(@RequestBody String body){
		JSONObject data = JSONObject.parseObject(body);
		return lolMatchGuessService.pullMatchGuess(data);
	}
	
	@RequestMapping(value="/match/pull", method=RequestMethod.POST)
	public boolean pullMatchInfo(@RequestBody String body,Integer p8){
		JSONArray matchs = JSONArray.parseArray(body);
		return lolMatchGuessService.pullMatchInfo(matchs,p8);
	}
}
