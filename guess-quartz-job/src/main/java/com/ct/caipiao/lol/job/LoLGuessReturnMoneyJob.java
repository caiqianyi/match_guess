package com.ct.caipiao.lol.job;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.ct.soa.amqp.core.sender.IRabbitmqSender;
import com.ct.soa.guess.core.constants.AmqpDirectQueue;

/**
 * 竞猜结算任务
 * @author user
 *
 */
@Component
public class LoLGuessReturnMoneyJob extends AbstractLoLMatchJob {
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		rabbitmqSender.sendContractDirect(AmqpDirectQueue.LOL_GUESS_TICKET_BONUS, "");
	}

}
