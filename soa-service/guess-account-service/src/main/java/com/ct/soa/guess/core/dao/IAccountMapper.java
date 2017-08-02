package com.ct.soa.guess.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ct.soa.guess.core.bo.UserBo;
import com.ct.soa.guess.core.entity.AccountTradeRecord;
import com.ct.soa.guess.core.entity.User;

/**************************
 * account
 * 账户操作mapper
 * @author caiqianyi
 * @date 2017-06-22 16:22:29
 * 
 **************************/
@Mapper
public interface IAccountMapper {
	
	/**
	 * 通过注册账号查询用户信息 
	 * @param account
	 * @return
	 */
	public User findByAccount(@Param("account")String account);
	
	/**
	 * 通过注册手机号查询用户信息 
	 * @param mobile
	 * @return
	 */
	public User findByMobile(@Param("mobile")String mobile);
	
	/**
	 * 通过unionid查询用户信息
	 * @param unionid
	 * @return
	 */
	public User findByUnionid(@Param("unionid")String unionid);
	
	/**
	 * 通过微信公众号openid和appid 查询绑定用户
	 * @param openid
	 * @return
	 */
	public User findByOpenid(@Param("openid")String openid,@Param("wxAppid")String wxAppid);
	
	/**
	 * 通过用户ID查询用户信息
	 * @param id
	 * @return
	 */
	public User findById(@Param("id")String id);
	
	/**
	 * 通过登录信息查询用户信息
	 * @param userBo
	 * @return
	 */
	public User login(UserBo userBo);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	
	/**
	 * 新增用户信息
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
	
	/**
	 * 查询用户所有账户交易记录
	 * @param userid
	 * @return
	 */
	public List<AccountTradeRecord> findAllTradeRecordByUserid(@Param("userid")String userid);
	
	/**
	 * 查询用户时间内账户交易记录
	 * @param userid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<AccountTradeRecord> findTradeRecordByUserid(@Param("userid")String userid,@Param("start")Date start,@Param("end")Date end);
	
	/**
	 * 通关用户关联记录 查询对应交易记录
	 * @param referId
	 * @param tradeType
	 * @return
	 */
	public AccountTradeRecord findTradeRecordByReferId(@Param("referId")String referId,@Param("tradeType")Integer tradeType);
	
	/**
	 * 新增交易记录
	 * @param tradeRecord
	 * @return
	 */
	public int saveTradeRecord(AccountTradeRecord tradeRecord);
}
