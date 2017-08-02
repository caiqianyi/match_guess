package com.ct.soa.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ct.soa.guess.core.entity.PayOrder;

@Mapper
public interface IOrderMapper {
	
	int savePayOrder(PayOrder order);
	
	int updatePayOrderStatus(@Param("userid")Integer status,@Param("payTime")Date payTime,@Param("orderNo")String orderNo);
	
	List<PayOrder> findPayOrderByUserid(@Param("userid")String userid,@Param("status")Integer status,@Param("start")Date start,@Param("end")Date end);		
	
	PayOrder findPayOrderByOrderNo(@Param("order_no")String order_no);
}
