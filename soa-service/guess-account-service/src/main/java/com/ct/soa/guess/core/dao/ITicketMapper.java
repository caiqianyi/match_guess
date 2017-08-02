package com.ct.soa.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ct.soa.guess.core.entity.Ticket;

@Mapper
public interface ITicketMapper {
	
	int saveTicket(Ticket ticket);
	
	public List<Ticket> findTicketByUserid(@Param("userid")String userid,
			@Param("status")Integer status,@Param("catId")String catId,
			@Param("start")Date start,@Param("end")Date end);
	
	public Ticket findTicketById(@Param("userid")String userid,@Param("id")String id);
}
