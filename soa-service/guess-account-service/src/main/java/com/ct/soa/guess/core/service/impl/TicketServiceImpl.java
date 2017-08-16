package com.ct.soa.guess.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ct.soa.guess.core.constants.CatId;
import com.ct.soa.guess.core.constants.MongoCollections;
import com.ct.soa.guess.core.constants.TradeRecord;
import com.ct.soa.guess.core.dao.IAccountMapper;
import com.ct.soa.guess.core.dao.ITicketMapper;
import com.ct.soa.guess.core.entity.AccountTradeRecord;
import com.ct.soa.guess.core.entity.Ticket;
import com.ct.soa.guess.core.entity.User;
import com.ct.soa.guess.core.service.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {
	
	@Resource
	private IAccountMapper accountMapper;
	
	@Resource
	private ITicketMapper ticketMapper;
	
	@Resource
	private MongoTemplate mongoTemplate;


	@Override
	public Ticket buyGuessTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> findTicketByUserid(String userid, Integer status,
			String catId, Date start, Date end) {
		return ticketMapper.findTicketByUserid(userid, status, catId, start, end);
	}

	@Override
	public Ticket findTicketById(String userid, String id) {
		return ticketMapper.findTicketById(userid, id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Ticket lolGuessSyncBonus(Ticket ticket) {
		//10001=s,10002=f,10003=s|10001=s,10002=f,10003=s
		String[] lotterys = ticket.getLottery().split("\\|");
		double bonus = 0.00;boolean isover = true;
		for(String lottery : lotterys){
			String[] ops = lottery.split("\\,");
			boolean isBonus = true;
			double b = 0.00;
			for(String opt : ops){
				String match_id = opt.split("\\=")[0];
				String op = opt.split("\\=")[1];
				String play_type = "s".equals(op) || "f".equals(op)?"sf":"bf";
				Map guess = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("match_id").is(match_id).and("play_type").is(play_type)), Map.class, MongoCollections.LPL_MATCH_GUESS_NAME);
				Map match = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("match_id").is(match_id)), Map.class, MongoCollections.LOL_MATCHS_NAME);
				if(!"3".equals(match.get("match_status"))){
					isover = false;
					break;
				}
				Map<String,List<Double>> odds = (Map<String, List<Double>>) guess.get("odds");
				Double odd = odds.get(op).get(odds.size()-1);
				b = b * odd;
				if(play_type.equals("sf")){
					if("s".equals(op) && ((double)match.get("home_score")) < ((double)match.get("away_score"))){
						isBonus = false;
					}
					if("f".equals(op) && ((double)match.get("home_score")) > ((double)match.get("away_score"))){
						isBonus = false;
					}
				}
				if(play_type.equals("bf")){
					Map<String,String> m = new HashMap<String,String>();
					m.put("_1", "0:2");
					m.put("_2", "1:2");
					m.put("_3", "2:0");
					m.put("_4", "2:1");
					
					m.put("_5", "0:3");
					m.put("_6", "1:3");
					m.put("_7", "2:3");
					m.put("_8", "3:0");
					m.put("_9", "3:1");
					m.put("_10", "3:2");
					if(!m.get(op).equals(match.get("home_score")+""+match.get("away_score"))){
						isBonus = false;
					}
				}
			}
			if(!isover){
				break;
			}
			if(isBonus){
				bonus += b;
			}
		}
		if(isover){
			BigDecimal b = new BigDecimal(bonus);
			Integer status = 3;
			if(bonus == 0.00){//未中奖
				status = 4;
			}
			long money = (long)(b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()*100);
			ticket.setOpenTime(new Date());
			ticket.setBonus(money);
			ticket.setStatus(status);
			User user = accountMapper.findById(ticket.getUserid());
			ticketMapper.updateTicket(ticket);
			
			AccountTradeRecord tradeRecord = new AccountTradeRecord();
			tradeRecord.setUserid(user.getId());
			tradeRecord.setReferId(ticket.getId());
			tradeRecord.setMoney(money);
			tradeRecord.setCurrentMoney(user.getFee());
			tradeRecord.setDescr("");
			tradeRecord.setStatus(1);
			tradeRecord.setTradeType(TradeRecord.BONUS);
			tradeRecord.setCreateTime(new Date());
			
			user.setFee(user.getFee()+money);
			accountMapper.updateUserInfo(user);
			accountMapper.saveTradeRecord(tradeRecord);
			return ticket;
		}
		return null;
	}
	
	@Override
	public List<Ticket> lolGuessSyncBonusForAll() {
		// TODO Auto-generated method stub
		List<Ticket> tickets = ticketMapper.findTicketByOpening(CatId.LOL_LPL);
		for(Ticket ticket:tickets){
			this.lolGuessSyncBonus(ticket);
		}
		return tickets;
	}
	
	@Override
	public List<Ticket> findTicketByOpening(String catId) {
		// TODO Auto-generated method stub
		return ticketMapper.findTicketByOpening(CatId.LOL_LPL);
	}

}
