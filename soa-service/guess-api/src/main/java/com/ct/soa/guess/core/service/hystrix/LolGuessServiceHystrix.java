package com.ct.soa.guess.core.service.hystrix;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ct.soa.guess.core.service.ILoLGuessService;

@Component
public class LolGuessServiceHystrix implements ILoLGuessService {
	
	private Logger logger = LoggerFactory.getLogger(LolGuessServiceHystrix.class);

	@Override
	public List<Map> matchs(String league) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public Map<String, Map<String, Map>> guess(String league, String play_type) {
		// TODO Auto-generated method stub
		return Collections.emptyMap();
	}

	@Override
	public boolean pullMatchGuess(String body) {
		
		logger.debug("body={}",body);
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean pullMatchInfo(String body, Integer p8) {
		logger.debug("body={},p8={}",body,p8);
		return false;
	}
}
