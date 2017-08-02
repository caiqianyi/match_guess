package com.ct.soa.guess.core.service;

import java.util.Date;
import java.util.List;

import com.ct.soa.guess.core.entity.Ticket;

public interface ITicketService {
	
	public Ticket buyGuessTicket(Ticket ticket);
	
	public List<Ticket> findTicketByUserid(String userid,Integer status,String catId,Date start,Date end);
	
	public Ticket findTicketById(String userid,String id);
}