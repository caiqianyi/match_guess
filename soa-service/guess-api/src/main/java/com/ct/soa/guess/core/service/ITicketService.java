package com.ct.soa.guess.core.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ct.soa.guess.core.entity.Ticket;
import com.ct.soa.guess.core.service.hystrix.LolGuessServiceHystrix;

//@FeignClient(value="guess-account-service",fallback=LolGuessServiceHystrix.class)
public interface ITicketService {

	@RequestMapping(method = RequestMethod.GET, value = "/ticket/lolGuessSyncBonusForAll")
	List<Ticket> lolGuessSyncBonusForAll();
}
