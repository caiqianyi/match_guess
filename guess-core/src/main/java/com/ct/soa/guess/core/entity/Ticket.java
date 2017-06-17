package com.ct.soa.guess.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞猜订单表
 * @author caiqianyi
 *
 */
public class Ticket implements Serializable{
	
	private static final long serialVersionUID = -6340856489772774125L;
	
	private String id;
	private String userid;//用户ID
	private String lottery;//投注code
	private Long money;//金额
	private String catId;//类型
	private String issue;//期号
	private String matchId;//比赛ID
	private String openLottery;//开奖号
	private Integer status;//状态
	private Date createTime;//购买时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLottery() {
		return lottery;
	}
	public void setLottery(String lottery) {
		this.lottery = lottery;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getOpenLottery() {
		return openLottery;
	}
	public void setOpenLottery(String openLottery) {
		this.openLottery = openLottery;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
}
