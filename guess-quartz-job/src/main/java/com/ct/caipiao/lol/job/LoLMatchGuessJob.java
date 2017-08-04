package com.ct.caipiao.lol.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ct.commons.utils.Assert;
import com.ct.soa.amqp.core.sender.IRabbitmqSender;
import com.ct.soa.guess.core.constants.AmqpDirectQueue;

@Component
@Scope("prototype")
public class LoLMatchGuessJob extends AbstractLoLMatchJob{
	
	Logger logger = LoggerFactory.getLogger(LoLMatchGuessJob.class);
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	public void visit(String body) {
		
		Assert.notNull(body);
		
		try{
			JSONObject datas = JSONObject.parseObject(body);
			String retCode = datas.getString("retCode"),errCode=datas.getString("errCode"),result=datas.getString("result");
			
			if(!("0".equals(retCode) && "0".equals(errCode) && "0".equals(result))){
				logger.error("retCode={},errCode={},result={}",retCode,errCode,result);
				return ;
			}
			JSONObject data = (JSONObject) datas.get("data");
			rabbitmqSender.sendContractDirect(AmqpDirectQueue.LOL_GUESS_PULL, data.toJSONString());
		}catch(Exception e){
			logger.error("body={}",body,e);
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
		
		// TODO Auto-generated method stub
		String url = "https://qs.888.qq.com/node_pool2/?d=es&c=esLOL&m=getTopicInfoLoop&ajax=true&cms_where=603005&vb2ctag=1_64_1_5475&bc_web=484664209&t=1500691597438&g_tk=5381&_=1500691597439";
		
        visit(request(url));
	}
	
	public static void main(String[] args) {
		String url = "https://qs.888.qq.com/node_pool2/?d=es&c=esLOL&m=getTopicInfoLoop&ajax=true&cms_where=603005&vb2ctag=1_64_1_5475&bc_web=484664209&t=1500691597438&g_tk=5381&_=1500691597439";
		LoLMatchGuessJob lmgj = new LoLMatchGuessJob();
		String body = lmgj.request(url);
		lmgj.visit(body);
	}

}