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
import com.ct.soa.guess.core.service.ITicketService;


/**
 * 竞彩赔率消费者处理
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = AmqpDirectQueue.LOL_GUESS_TICKET_BONUS)
public class LoLGuessTicketBonusListener {
	
	private Logger logger = LoggerFactory.getLogger(LoLGuessTicketBonusListener.class);
	
	@Resource
	private ITicketService ticketService;

	@Bean
    public Queue queueLoLGuessTicketBonus() {
        return new Queue(AmqpDirectQueue.LOL_GUESS_TICKET_BONUS);
    }

    @Bean
    Binding bindingDirectExchangeLoLGuessTicketBonus(Queue queueLoLGuessTicketBonus, DirectExchange directExchange) {
        return BindingBuilder.bind(queueLoLGuessTicketBonus).to(directExchange).with(AmqpDirectQueue.LOL_GUESS_TICKET_BONUS);
    }
	
	@RabbitHandler
    public void receive(String body) {
		ticketService.lolGuessSyncBonusForAll();
    }
}
