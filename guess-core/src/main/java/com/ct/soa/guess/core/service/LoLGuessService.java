package com.ct.soa.guess.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="lolGuess-service",value="lolGuess-service")
public interface LoLGuessService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/lpl/matchs/{league}")
	List<Map> matchs(@PathVariable String league);
	
	@RequestMapping(method = RequestMethod.GET, value = "/lpl/guess/{league}/{play_type}")
	Map<String, Map<String, Map>> guess(@PathVariable String league,@PathVariable String play_type);
}