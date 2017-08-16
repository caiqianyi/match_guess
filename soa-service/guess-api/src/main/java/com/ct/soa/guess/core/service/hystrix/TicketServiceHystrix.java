package com.ct.soa.guess.core.service.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ct.soa.guess.core.entity.Ticket;
import com.ct.soa.guess.core.service.ITicketService;

//@Component
public class TicketServiceHystrix implements ITicketService {

	@Override
	public List<Ticket> lolGuessSyncBonusForAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
