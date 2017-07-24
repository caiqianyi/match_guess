package com.ct.soa.guess.core.service;

import com.ct.soa.guess.core.entity.PayOrder;

public interface IPayOrderService {
	
	public PayOrder createZfbPayOrder(PayOrder order);
	
	public boolean zfbNotify(String inputString,String out_trade_no);
}
