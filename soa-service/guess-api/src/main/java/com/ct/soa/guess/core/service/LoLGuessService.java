package com.ct.soa.guess.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ct.soa.guess.core.service.hystrix.LolGuessServiceHystrix;

@FeignClient(value="guess-lol-service",fallback=LolGuessServiceHystrix.class)
public interface LoLGuessService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/lol/matchs/{league}")
	List<Map> matchs(@PathVariable(value="league") String league);
	
	@RequestMapping(method = RequestMethod.GET, value = "/lol/guess/{league}/{play_type}")
	Map<String, Map<String, Map>> guess(@PathVariable(value="league") String league,@PathVariable(value="play_type") String play_type);
	
	@RequestMapping(method=RequestMethod.POST,value="/lol/guess/pull")
	boolean pullMatchGuess(@RequestBody String body);
	
	@RequestMapping(method=RequestMethod.POST,value="/lol/match/pull")
	boolean pullMatchInfo(@RequestBody String body,@RequestParam(value="p8") Integer p8);
}