package com.ct.soa.guess.core.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ct.soa.guess.core.service.LoLGuessService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/lol/")
public class LpLMatchController {
	
	private Logger logger = LoggerFactory.getLogger(LpLMatchController.class);
	
	@Resource
	private LoLGuessService lolGuessService;
	
	
	@RequestMapping(value="/matchs/{league}", method=RequestMethod.GET)
	public List<Map> matchs(@PathVariable String league){
		return lolGuessService.matchs(league);
	}
	
	@RequestMapping(value="/guess/{league}/{play_type}", method=RequestMethod.GET)
	public Map<String, Map<String, Map>> guess(@PathVariable String league,@PathVariable String play_type){
		return lolGuessService.guess(league, play_type);
	}
	
}
