package com.ct.soa.guess.core.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单表
 * CREATE TABLE `pay_order` (
  `id` varchar(50) NOT NULL DEFAULT UUID(),
  `orderNo` varchar(200) DEFAULT NULL COMMENT '订单号',
  `money` float(10,2) DEFAULT '0.00' COMMENT '支付金额',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `buyWay` char(10) DEFAULT 'ON' COMMENT '',
  `userId` varchar(50) NOT NULL COMMENT '游戏显示玩家id',
  `agentId` int(11) DEFAULT NULL COMMENT 'agentId 只有vip充卡的时候会存',
  `playerType` char(10) DEFAULT NULL COMMENT '玩家类型 P:普通玩家 V：VIP',
  `chargeUser` varchar(20) DEFAULT 'S' COMMENT '充卡人：如果线上买卡，默认为S，否则记录admin中的account',
  `unitPrice` float(6,2) DEFAULT '0.00' COMMENT '房卡单价（只有线下的时候会有或者线上支付购买的时候会有）',
  `serverCode` char(20) DEFAULT NULL COMMENT '区服',
  `buyTime` int(11) DEFAULT NULL COMMENT '购买时间',
  `payWay` char(10) DEFAULT NULL COMMENT '当buyWay=ONPAY时，payWay有值 (ZFB、WX)',
  `orderStatus` char(10) DEFAULT NULL COMMENT '订单状态',
  `activitySend` int(11) DEFAULT '0' COMMENT '活动额外赠送',
  `payNo` varchar(100) DEFAULT NULL COMMENT '第三方支付订单号',
  `loginNo` varchar(64) DEFAULT NULL,
  `mercNo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderNo` (`orderNo`),
  KEY `createTime_index` (`createTime`),
  KEY `agentId_index` (`agentId`),
  KEY `userId_index` (`userId`),
  KEY `buyWay_index` (`buyWay`),
  KEY `playerType_index` (`playerType`),
  KEY `chargeUser_index` (`chargeUser`),
  KEY `serverCode_index` (`serverCode`),
  KEY `buyTime_index` (`buyTime`),
  KEY `payWay_index` (`payWay`),
  KEY `orderStatus_index` (`orderStatus`),
  KEY `payNo_index` (`payNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付订单表';

 * @author user
 *
 */
public class PayOrder {
	
	private String id;
	private String userid;//用户ID
	private String orderNo;//平台订单号
	private String itemId;//商品ID
	private String buyWay;//购买方式、用途
	private String payWay;//支付渠道 ZFB、WX
	private String payNo;//第三方订单号
	private String mercNo;//支付商户号
	private String referId;//涉及ID
	private BigDecimal money;//支付金额
	private Integer status;//0 未支付 1已支付未到账 2交易成功
	private Date payTime;//到账时间
	private Date createTime;//创建时间
	
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getBuyWay() {
		return buyWay;
	}
	public void setBuyWay(String buyWay) {
		this.buyWay = buyWay;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getMercNo() {
		return mercNo;
	}
	public void setMercNo(String mercNo) {
		this.mercNo = mercNo;
	}
	public String getReferId() {
		return referId;
	}
	public void setReferId(String referId) {
		this.referId = referId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
