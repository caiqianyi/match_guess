package com.ct.soa.guess.core.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ct.soa.guess.core.constants.CatId;
import com.ct.soa.guess.core.entity.Ticket;
import com.ct.soa.guess.core.service.ITicketService;

@RestController
@RequestMapping("/ticket/")
public class TicketController {

	@Resource
	private ITicketService ticketService;
	
	@RequestMapping(value="/lolGuessSyncBonusForAll", method=RequestMethod.GET)
	public List<Ticket> lolGuessSyncBonusForAll(){
		List<Ticket> tickets = ticketService.findTicketByOpening(CatId.LOL_LPL);
		for(Ticket ticket:tickets){
			ticketService.lolGuessSyncBonus(ticket);
		}
		return tickets;
	}
}
