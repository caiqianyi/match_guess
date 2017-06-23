package com.ct.soa.guess.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author caiqianyi
CREATE TABLE `users` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `nick_name` varchar(50) NOT NULL COMMENT '用户昵称',
  `source` varchar(200) NOT NULL COMMENT '注册来源',
  `type` varchar(200) DEFAULT NULL COMMENT '账号类型',
  `wx_appid` varchar(200) DEFAULT NULL COMMENT '微信appid',
  `openid` varchar(200) DEFAULT NULL COMMENT '微信openid',
  `unionid` varchar(200) DEFAULT NULL COMMENT '微信UnionId',
  `status` varchar(50) DEFAULT NULL COMMENT 'Y 正常 N 禁用  F 异常冻结',
  `first_pay_order_no` varchar(200) DEFAULT NULL COMMENT '第一次支付订单号',
  `first_ticket_id` varchar(36) DEFAULT NULL COMMENT '第一次购票ID',
  `count_pay_order` int(11) NOT NULL DEFAULT 0 COMMENT '充值次数',
  `count_ticket` int(11) NOT NULL DEFAULT 0 COMMENT '购票次数',
  `last_login_host` varchar(50) DEFAULT NULL COMMENT '登录信息',
  `level` int(11) NOT NULL DEFAULT 2 COMMENT '账户等级',
  `fee` bigint(20) NOT NULL DEFAULT '0' COMMENT '账户金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `last_buy_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后购买时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `nick_name` (`nick_name`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `unionid` (`unionid`),
  KEY `source_index` (`source`),
  KEY `type_index` (`type`),
  KEY `status_index` (`status`),
  KEY `level_index` (`level`),
  KEY `openid_index` (`openid`),
  KEY `create_time_index` (`create_time`),
  KEY `last_login_time_index` (`last_login_time`),
  KEY `last_buy_time_index` (`last_buy_time`),
  KEY `first_pay_order_no_index` (`first_pay_order_no`),
  KEY `first_ticket_id_index` (`first_ticket_id`),
  KEY `count_pay_order_index` (`count_pay_order`),
  KEY `count_ticket_index` (`count_ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 840319899504317101L;
	
	private String id;
	private String account;//账号
	private String mobile;//手机号
	private String password;//密码
	private String nickName;//昵称
	private String source;//注册来源
	private String type;//账号类型
	private String wxAppid;//注册微信appid
	private String openid;//微信openid
	private String unionid;//微信UnionId
	private String status;//Y 正常 N 禁用  F 冻结 
	private String firstPayOrderNo;//第一次支付订单号
	private String firstTicketId;//第一次购票Id
	private Integer countPayOrder;//充值次数
	private Integer countTicket;//购票次数
	private String lastLoginHost;//登录信息
	private Date createTime;//注册时间
	private Date lastLoginTime;//最后登录时间
	private Date lastBuyTime;//最后购买时间
	private Integer level;//等级
	private Long fee;//账户余额
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastBuyTime() {
		return lastBuyTime;
	}
	public void setLastBuyTime(Date lastBuyTime) {
		this.lastBuyTime = lastBuyTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getLastLoginHost() {
		return lastLoginHost;
	}
	public void setLastLoginHost(String lastLoginHost) {
		this.lastLoginHost = lastLoginHost;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getWxAppid() {
		return wxAppid;
	}
	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}
	public String getFirstPayOrderNo() {
		return firstPayOrderNo;
	}
	public void setFirstPayOrderNo(String firstPayOrderNo) {
		this.firstPayOrderNo = firstPayOrderNo;
	}
	public String getFirstTicketId() {
		return firstTicketId;
	}
	public void setFirstTicketId(String firstTicketId) {
		this.firstTicketId = firstTicketId;
	}
	public Integer getCountPayOrder() {
		return countPayOrder;
	}
	public void setCountPayOrder(Integer countPayOrder) {
		this.countPayOrder = countPayOrder;
	}
	public Integer getCountTicket() {
		return countTicket;
	}
	public void setCountTicket(Integer countTicket) {
		this.countTicket = countTicket;
	}
}
