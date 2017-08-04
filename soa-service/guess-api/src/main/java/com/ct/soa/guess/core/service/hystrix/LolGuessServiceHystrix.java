package com.ct.soa.guess.core.service.hystrix;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ct.soa.guess.core.service.LoLGuessService;

@Component
public class LolGuessServiceHystrix implements LoLGuessService {

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

}
