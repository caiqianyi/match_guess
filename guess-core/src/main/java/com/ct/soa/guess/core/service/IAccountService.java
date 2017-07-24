package com.ct.soa.guess.core.service;

import java.util.Date;
import java.util.List;

import com.ct.soa.guess.core.bo.UserBo;
import com.ct.soa.guess.core.entity.AccountTradeRecord;
import com.ct.soa.guess.core.entity.User;

/**
 * 账户操作
 * @author caiqianyi
 * @date 2017-07-03 11:58:29
 */
public interface IAccountService {
	
	/**
	 * 通过登录信息查询用户信息
	 * @param userBo
	 * @return
	 */
	public User login(UserBo userBo);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public User register(User user);
	
	/**
	 * 通过注册手机号查询用户信息 
	 * @param mobile
	 * @return
	 */
	public User findByMobile(String mobile);
	
	/**
	 * 通过unionid查询用户信息
	 * @param unionid
	 * @return
	 */
	public User findByUnionid(String unionid);
	
	/**
	 * 绑定注册手机号
	 * @param mobile
	 * @return
	 */
	public User bindMobile(String mobile);
	
	/**
	 * 修改用户信息
	 * @param mobile
	 * @return
	 */
	public User updateInfo(User user);
	
	/**
	 * 查询用户所有账户交易记录
	 * @param userid
	 * @return
	 */
	public List<AccountTradeRecord> findAllTradeRecordByUserid(String userid);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param userid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<AccountTradeRecord> findTradeRecordByUserid(String userid,Date start,Date end);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	public AccountTradeRecord findTradeRecordByReferId(String referId,Integer tradeType);
	
}