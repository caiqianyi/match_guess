package com.ct.soa.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ct.soa.guess.core.entity.PayOrder;

@Mapper
public interface IOrderMapper {
	
	int savePayOrder(PayOrder order);
	
	int updatePayOrderStatus(@Param("userid")Integer status,@Param("userid")String orderNo);
	
	List<PayOrder> findPayOrderByUserid(@Param("userid")String userid,@Param("userid")Integer status,@Param("userid")Date start,@Param("userid")Date end);		
	
	PayOrder findPayOrderByOrderNo(@Param("userid")String order_no);
}
