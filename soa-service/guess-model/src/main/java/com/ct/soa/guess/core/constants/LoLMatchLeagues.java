package com.ct.soa.guess.core.constants;

import java.util.HashMap;
import java.util.Map;

public class LoLMatchLeagues {
	
	public final static Map<String,String> leagues = new HashMap<String,String>();
	
	static{
		leagues.put("7", "lspl");//lspl甲级联赛
		
		leagues.put("4", "demaxiya");//德玛西亚杯
		leagues.put("20", "csyxzbs");//城市英雄争霸赛
		leagues.put("9", "gxls");//高校联赛
		leagues.put("39", "xjjys");//校际精英赛
		
		leagues.put("5", "lpl");
		leagues.put("68", "lms");//台港澳职业联赛
		leagues.put("51", "lck");//韩国职业联赛
		leagues.put("54", "lcs_oz");//欧洲职业联赛
		leagues.put("55", "lcs_bm");//北美职业联赛
		
		leagues.put("1", "wcs");//全球总决赛
		leagues.put("8", "msi");//MSI季中邀请赛
		leagues.put("71", "zjxls");//洲际系列赛
		leagues.put("6", "allstar");//全明星赛
	}
}
