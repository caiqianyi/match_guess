package com.ct.soa.guess.core.bo;

import java.util.HashMap;
import java.util.Map;

import com.ct.pay.alipay.AlipayConfig;
import com.ct.pay.alipay.AlipaySubmit;

public class AliPayBo {
	
	private String service = "alipay.wap.create.direct.pay.by.user";//调用的接口名
	private String partner;//合作身份者ID 商户号
	private String seller_id;//收款支付宝账号 商户号
	private String _input_charset = "utf-8";//字符编码格式 目前支持utf-8
	private String payment_type = "1";//支付类型
	private String notify_url;//异步回调地址
	private String return_url;//同步回调地址
	private String out_trade_no;//商户订单号，商户网站订单系统中唯一订单号
	private String subject;//订单名称
	private String total_fee;//付款金额
	private String show_url;//支付宝--收银台页面上，商品展示的超链接
	private String app_pay = "Y";//启用此参数可唤起钱包APP支付
	private String body;//商品描述，可空
	
	public AliPayBo(String partner,String seller_id,String notify_url,String return_url,
			String out_trade_no,String subject,String total_fee,String show_url,String body) {
		// TODO Auto-generated constructor stub
		this.partner = partner;
		this.seller_id = seller_id;
		this.notify_url = notify_url;
		this.return_url = return_url;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.total_fee = total_fee;
		this.show_url = show_url;
		this.body = body;
	}
	
	public synchronized static String getPayFormHtml(AliPayBo apb){
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.getService());
        sParaTemp.put("partner", apb.getPartner());
        sParaTemp.put("seller_id", apb.getSeller_id());
        sParaTemp.put("_input_charset", AlipayConfig.getInputCharset());
		sParaTemp.put("payment_type", AlipayConfig.getPaymentType());
		sParaTemp.put("notify_url", AlipayConfig.getNotifyUrl());
		sParaTemp.put("return_url", AlipayConfig.getReturnUrl());
		sParaTemp.put("out_trade_no", apb.getOut_trade_no());
		sParaTemp.put("subject", apb.getSubject());
		sParaTemp.put("total_fee", apb.getTotal_fee());
		sParaTemp.put("show_url", AlipayConfig.getShowUrl());
		sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		sParaTemp.put("body", apb.getBody());
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认","");
		return sHtmlText;
	}
	
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getService() {
		return service;
	}
	public String get_input_charset() {
		return _input_charset;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public String getApp_pay() {
		return app_pay;
	}
}
