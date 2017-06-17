package com.ct.soa.guess.core.entity;

import java.util.Date;

/**
 * 账户交易表
 * @author caiqianyi
 *
 */
public class AccountTradeRecord {
	
	private String id;//交易流水号
	private String userid;//交易账户
	private String referId;//涉及ID
	private Long money;//交易金额
	private Long currentMoney;//交易时金额
	private String descr;//详情说明
	private String hostip;//交易ip
	private Integer status;//交易状态
	private Integer tradeType;//交易类型
	private Date createTime;//交易时间
	
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
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getReferId() {
		return referId;
	}
	public void setReferId(String referId) {
		this.referId = referId;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getHostip() {
		return hostip;
	}
	public void setHostip(String hostip) {
		this.hostip = hostip;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCurrentMoney() {
		return currentMoney;
	}
	public void setCurrentMoney(Long currentMoney) {
		this.currentMoney = currentMoney;
	}
}
