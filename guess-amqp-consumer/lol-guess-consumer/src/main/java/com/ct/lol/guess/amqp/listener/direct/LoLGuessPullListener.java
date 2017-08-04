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

import com.ct.soa.guess.core.constants.AmqpDirectQueue;
import com.ct.soa.guess.core.service.LoLGuessService;


/**
 * 竞彩赔率消费者处理
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = AmqpDirectQueue.LOL_GUESS_PULL)
public class LoLGuessPullListener {
	
	private Logger logger = LoggerFactory.getLogger(LoLGuessPullListener.class);
	
	@Resource
	private LoLGuessService lolGuessService;

	@Bean
    public Queue queueLoLGuessPull() {
        return new Queue(AmqpDirectQueue.LOL_GUESS_PULL);
    }

    @Bean
    Binding bindingDirectExchangeLoLGuessPull(Queue queueLoLGuessPull, DirectExchange directExchange) {
        return BindingBuilder.bind(queueLoLGuessPull).to(directExchange).with(AmqpDirectQueue.LOL_GUESS_PULL);
    }
	
	@RabbitHandler
    public void receive(String body) {
		try{
			lolGuessService.pullMatchGuess(body);
		}catch(Exception e){
			logger.info("e={}",e);
		}
		
    }
}
