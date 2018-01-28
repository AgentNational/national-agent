package com.pay.national.agent.core.service.common;

import java.util.Map;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.beans.results.FindInformationResultBean;
import com.pay.national.agent.model.beans.results.LoginResultBean;
import com.pay.national.agent.model.entity.User;

public interface UserService {

	/**
	 * @Description 通过用户名查询用户是否存在
	 * @param phoneNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	User findUserByUserName(String phoneNo);
	
	/**
	 * @Description 通过用户编号查询用户是否存在
	 * @param
	 * @return
	 * @see
	 */
	User findUserByUserNo(String userNo);

	/**
	 * @Description 校验注册信息
	 * @param registerParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	//ReturnBean<String> checkRegistInfo(RegisterParamBean registerParamBean);

	/**
	 * @Description 注册用户
	 * @param registerParamBean
	 * @return 
	 * @see 需要参考的类或方法
	 */
	//ReturnBean<String> register(RegisterParamBean registerParamBean);

	/**
	 * @Description 校验登录信息
	 * @param loginParamBean
	 * @return
	 * @see
	 */
	ReturnBean<LoginResultBean> checkLoginInfo(LoginParamBean loginParamBean);

	/**
	 * @Description 登录
	 * @param loginParamBean
	 * @return
	 * @see
	 */
	ReturnBean<LoginResultBean> login(LoginParamBean loginParamBean);

	/**
	 * @Description 退出登录
	 * @param loginKey
	 * @return
	 * @see
	 */
	boolean logout(String loginKey);

	/**
	 * @Description 用户修改密码
	 * @param updatePasswordParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	//ReturnBean<Object> updatePassword(UpdatePasswordParamBean updatePasswordParamBean);

	/**
	 * @Description 找回密码
	 * @param phoneNo 手机号
	 * @param code 验证码
	 * @param password 新密码
	 * @return 
	 * @see 需要参考的类或方法
	 */
	ReturnBean<Object> findPassword(Map<String, String> params);

	/**
	 * @Description 实名认证
	 * @param realNameParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	//ReturnBean<Object> realName(RealNameParamBean realNameParamBean);

	/**
	 * @Description 查找个人资料
	 * @param params
	 * @return 
	 * @see
	 */
	ReturnBean<FindInformationResultBean> findInformation(Map<String, String> params);

	/**
	 * @Description 编辑个人资料
	 * @param editInformationParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	//ReturnBean<Object> editInformation(EditInformationParamBean editInformationParamBean);

	/**
	 * @Description 找回密码第二步
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	ReturnBean<Object> resetPassword(Map<String, String> params);

	/**
	 * 公众号用户注册
	 * @param fromUserName
	 * @param eventKey
	 */
	void register(String fromUserName, String eventKey);
}
