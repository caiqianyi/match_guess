package com.ct.lol.guess.amqp.listener.direct;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ct.soa.guess.core.constants.AmqpDirectQueue;
import com.ct.soa.guess.core.service.ILoLGuessService;


/**
 * 竞彩赔率消费者处理
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = AmqpDirectQueue.LOL_MATCH_PULL)
public class LoLMatchPullListener {
	
	private Logger logger = LoggerFactory.getLogger(LoLMatchPullListener.class);
	
	@Resource
	private ILoLGuessService lolGuessService;

	@Bean
    public Queue queueLoLMatchPull() {
        return new Queue(AmqpDirectQueue.LOL_MATCH_PULL);
    }

    @Bean
    Binding bindingDirectExchangeLoLMatchPull(Queue queueLoLMatchPull, DirectExchange directExchange) {
        return BindingBuilder.bind(queueLoLMatchPull).to(directExchange).with(AmqpDirectQueue.LOL_MATCH_PULL);
    }
	
	@RabbitHandler
    public void receive(String body) {
		JSONObject data = JSONObject.parseObject(body);
		Integer p8 = data.getInteger("p8");
		JSONArray result = data.getJSONArray("result");
		try{
			lolGuessService.pullMatchInfo(result.toJSONString(), p8);
		}catch(Exception e){
			logger.info("result.size={},p8={}",result.size(),p8);
		}
    }
}
