package com.ct.soa.guess.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞猜订单表
 * 
drop TABLE if EXISTS `t_tickets`;
CREATE TABLE `t_tickets` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userid` varchar(36) NOT NULL COMMENT '用户ID',
  `lottery` longtext NOT NULL COMMENT '投注code',
  `money` bigint(20) NOT NULL DEFAULT '0' COMMENT '交易金额',
  `cat_id` varchar(50) NOT NULL COMMENT '竞猜类型',
  `issue` varchar(200) NOT NULL COMMENT '竞猜期号',
  `match_id` varchar(200) NOT NULL COMMENT '比赛ID',
  `open_lottery` longtext DEFAULT NULL COMMENT '开奖号',
  `status` int(11) NOT NULL DEFAULT 2 COMMENT '订单状态  0出票失败 1出票成功 2出票中  3已返奖 4未中奖',
  `bonus` bigint(20) NOT NULL DEFAULT '0' COMMENT '奖金',
  `open_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开奖时间',
  `last_open_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后开奖时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '交易时间',
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`create_time`),
  KEY `userid_index` (`userid`),
  KEY `status_index` (`status`),
  KEY `match_id_index` (`match_id`),
  KEY `issue_index` (`issue`),
  KEY `cat_id_index` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜订单表';

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
	private Long bonus;//奖金
	private Date openTime;//开奖时间
	private Date lastOpenTime;//最后开奖时间
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
	public Date getLastOpenTime() {
		return lastOpenTime;
	}
	public void setLastOpenTime(Date lastOpenTime) {
		this.lastOpenTime = lastOpenTime;
	}
	public Long getBonus() {
		return bonus;
	}
	public void setBonus(Long bonus) {
		this.bonus = bonus;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
}
