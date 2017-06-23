package com.ct.soa.guess.core.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单表
 * 
CREATE TABLE `t_pay_orders` (
  `id` varchar(36) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `userid` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `order_no` varchar(200) DEFAULT NULL COMMENT '平台订单号',
  `item_id` varchar(50) DEFAULT NULL COMMENT '商品ID',
  `buy_way` varchar(50) DEFAULT NULL COMMENT '购买方式、用途',
  `pay_way` varchar(50) DEFAULT NULL COMMENT '支付渠道 ZFB、WX',
  `pay_no` varchar(200) DEFAULT NULL COMMENT '第三方支付订单号',
  `merc_no` varchar(50) DEFAULT NULL COMMENT '第三方支付商户号',
  `refer_id` varchar(36) DEFAULT NULL COMMENT '涉及内部表关联ID',
  `money` float(10,2) DEFAULT '0.00' COMMENT '支付金额',
  `status` varchar(50) DEFAULT NULL COMMENT '订单状态 0 未支付 1已支付未到账 2交易成功',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '到账时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`),
  KEY `create_time_index` (`create_time`),
  KEY `userid_index` (`userId`),
  KEY `buy_way_index` (`buy_way`),
  KEY `refer_id_index` (`refer_id`),
  KEY `pay_time_index` (`pay_time`),
  KEY `pay_way_index` (`pay_way`),
  KEY `status_index` (`status`),
  KEY `pay_no` (`pay_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付订单表';
 * @author caiqianyi
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
