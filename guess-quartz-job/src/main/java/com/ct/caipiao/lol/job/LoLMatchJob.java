package com.ct.caipiao.lol.job;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ct.commons.utils.Assert;
import com.ct.commons.utils.DateUtil;
import com.ct.soa.amqp.core.sender.IRabbitmqSender;
import com.ct.soa.guess.core.constants.AmqpDirectQueue;

@Component
@Scope("prototype")
public class LoLMatchJob extends AbstractLoLMatchJob{
	
	Logger logger = LoggerFactory.getLogger(LoLMatchJob.class);
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	public void visit(String body,Integer p8) {
		
		Assert.notNull(body);
		
		String json = body.replace("var retObj=", "").replace(";", "");
		JSONObject datas = JSONObject.parseObject(json);
		String status = datas.getString("status");
		
		if("0".equals(status)){
			JSONObject msg = (JSONObject) datas.get("msg");
			String total = msg.getString("total"),totalpage=msg.getString("totalpage"),page = msg.getString("page");
			logger.info("total={},totalpage={},page={}",total,totalpage,page);
			
			JSONArray result = msg.getJSONArray("result");
			logger.info("result.size={}",result.size());
			
			
			JSONObject data = new JSONObject();
			data.put("p8", p8);
			data.put("result", result);
			rabbitmqSender.sendContractDirect(AmqpDirectQueue.LOL_MATCH_PULL, data.toJSONString());
		}
	}
	
	public String request(String url){
		
        Map<String,String> header = new HashMap<String,String>();
		header.put("accept-language", "zh-CN,zh;q=0.8");
		header.put("referer", "https://qs.888.qq.com/m_qq/es/es.lol.html?channelName=landing");
		header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		header.put("x-requested-with", "XMLHttpRequest");
		header.put("Content-Type","text/html;charset=UTF-8"); 
		
		return doGet(url, header);
	}
	

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		String dataJson = (String) context.getJobDetail().getJobDataMap().get("dataJson");
		
		if(StringUtils.isBlank(dataJson)){//
			return ;
		}
		
		run(dataJson);
	}
	
	public void run(String body){
		//系统配置
		
		JSONObject json = JSONObject.parseObject(body);
		Long s = json.getLong("start"),e = json.getLong("end");
		Integer p8 = json.getInteger("p8"),pageSize = json.getInteger("pageSize");
		
		if(s == null){//更新最新的
			s = new Date().getTime();
		}
		if(e == null){//更新最新的
			e = new Date().getTime();
		}
		if(pageSize == null){
			pageSize = 500;
		}
		
		Date start = new Date(s),end = new Date(e);
		
		// TODO Auto-generated method stub
		String url = "http://apps.game.qq.com/lol/match/apis/searchBMatchInfo.php?p8="+p8+"&p1=&p9="+DateUtil.formatDatetime(start, "yyyy-MM-dd")+"%2000:00:00&p10="+DateUtil.formatDatetime(end, "yyyy-MM-dd")+"%2023:59:59&p6=3&pagesize="+pageSize+"&r1=retObj&_=1500702932923";
		
        visit(request(url),p8);
	}
	
	public static void main(String[] args) throws ParseException {
		long start = DateUtils.parseDate("2017-01-01 00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"}).getTime(),end = DateUtils.parseDate("2018-01-01 00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"}).getTime();
		String json = "{\"start\":"+start+",\"end\":"+end+",\"p8\":51}";
		System.out.println(json);
		//new LoLMatchJob().run(json);
		
	}

}