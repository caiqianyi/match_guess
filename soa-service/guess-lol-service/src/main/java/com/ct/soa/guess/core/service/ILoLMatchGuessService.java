package com.ct.soa.guess.core.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ILoLMatchGuessService {
	
	List<Map> matchs(String league);
	
	Map<String, Map<String, Map>> guess(String league,String play_type);
	
	boolean pullMatchGuess(JSONObject data);
	
	boolean pullMatchInfo(JSONArray matchs,Integer p8);
}
