package com.pay.national.agent.core.facade;

import java.util.Map;

import com.pay.national.agent.model.beans.query.EditInformationParamBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.beans.query.RealNameParamBean;
import com.pay.national.agent.model.beans.query.RegisterParamBean;
import com.pay.national.agent.model.beans.query.UpdatePasswordParamBean;

/**
 * @Description: 用户相关dubbo接口
 * @see: 需要参考的类
 * @version 2017年9月6日 下午1:52:46
 * @author zhenhui.liu
 */
public interface UserFacade {

	/**
	 * @Description 注册发送验证码
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String sendRegisterCheckCode(Map<String, String> params);

	/**
	 * @Description 用户注册
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String register(RegisterParamBean registerParamBean);

	/**
	 * @Description 用户登录
	 * @param loginParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String login(LoginParamBean loginParamBean);

	/**
	 * @Description 退出登录
	 * @param loginKey
	 * @return
	 * @see 需要参考的类或方法
	 */
	String logout(String loginKey);

	/**
	 * @Description 修改密码
	 * @param updatePasswordParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String updatePassword(UpdatePasswordParamBean updatePasswordParamBean);

	/**
	 * @Description 发送找回密码验证码
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String sendFindPasswordCheckCode(Map<String, String> params);

	/**
	 * @Description 找回密码第一步
	 * @param phoneNo 手机号
	 * @param code 验证码
	 * @return
	 * @see 需要参考的类或方法
	 */
	String findPassword(Map<String, String> params);

	/**
	 * @Description 找回密码第二步
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String resetPassword(Map<String, String> params);
	
	/**
	 * @Description 实名认证
	 * @param realNameParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String realName(RealNameParamBean realNameParamBean);

	/**
	 * @Description 查找个人资料
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String findInformation(Map<String, String> params);

	/**
	 * @Description 编辑个人资料
	 * @param editInformationParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String editInformation(EditInformationParamBean editInformationParamBean);

	

}
