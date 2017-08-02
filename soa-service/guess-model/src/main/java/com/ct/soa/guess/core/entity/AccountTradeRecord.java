package com.ct.soa.guess.core.entity;

import java.util.Date;

/**
 * 账户交易流水表
 * 
CREATE TABLE `t_account_trade_records` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userid` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `refer_id` varchar(36) DEFAULT NULL COMMENT '涉及内部表关联ID',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '交易金额',
  `current_money` int(11) NOT NULL DEFAULT '0' COMMENT '交易时余额',
  `descr` varchar(200) DEFAULT NULL COMMENT '详情说明',
  `hostip` varchar(200) DEFAULT NULL COMMENT '交易ip',
  `status` int(11) NOT NULL COMMENT '交易状态 0 失败 1成功 2回滚',
  `trade_type` int(11) NOT NULL COMMENT '交易状态 0 失败 1成功 2回滚',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`create_time`),
  KEY `userid_index` (`userId`),
  KEY `refer_id_index` (`refer_id`),
  KEY `hostip_index` (`hostip`),
  KEY `status_index` (`status`),
  KEY `trade_type_index` (`trade_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户交易流水表';
 * @author caiqianyi
 *
 */
public class AccountTradeRecord {
	
	private String id;//交易流水号
	private String userid;//交易账户
	private String referId;//涉及ID
	private Long money;//交易金额
	private Long currentMoney;//交易时余额
	private String descr;//详情说明
	private String hostip;//交易ip
	private Integer status;//交易状态 0 失败 1成功 2回滚
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
