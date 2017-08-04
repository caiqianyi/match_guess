package com.ct.caipiao.lol.job;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;

public abstract class AbstractLoLMatchJob implements Job{
	
	public String doGet(String url, Map<String,String> header){
		
        /*通过httpclient来获取http请求的响应信息*/  
        HttpClient client = new DefaultHttpClient();  
        HttpGet httpGet = new HttpGet(url);  
        
        if(header != null){
        	for(String key : header.keySet()){
        		httpGet.setHeader(key,header.get(key));
        	}
        }
        
        /*这里用的是httpclient的HttpResponse，与WebCollector中的HttpResponse无关*/  
        org.apache.http.HttpResponse httpClientResponse;
		try {
			
			httpClientResponse = client.execute(httpGet);
			  
	        HttpEntity entity = httpClientResponse.getEntity();  
	        
	        String body = new String(EntityUtils.toByteArray(entity));
	        
	        return body;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
