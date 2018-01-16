package com.pay.national.agent.core.facade.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.RedisUtils;
import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.core.service.common.SmsService;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.beans.results.FindInformationResultBean;
import com.pay.national.agent.model.beans.results.LoginResultBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.User;

@Service("userFacade")
public class UserFacadeImpl implements UserFacade{

	/**
	 * 用户基础service
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 短信
	 */
	@Autowired
	private SmsService smsService;
	
	/**
	 * 注册发送验证码
	 */
	@Override
	public String sendRegisterCheckCode(Map<String, String> params) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,"验证码发送成功！");
		LogUtil.info("method sendRegisterCheckCode start params:{}",params);
		//获取手机号
		String phoneNo = params.get("phoneNo");
		//判断当前信息是否已经注册
		User user = userService.findUserByUserName(phoneNo);
		if(user != null){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("该手机号已经存在！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
		//若未注册发送注册验证码。
		try {
			boolean result = smsService.sendCheckCodeSms(phoneNo);
			if(result){//短信发送成功
				return JSONUtils.alibabaJsonString(returnBean);
			}else{//短信发送失败
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("短信发送失败!");
				return JSONUtils.alibabaJsonString(returnBean);
			}
		} catch (Exception e) {
			LogUtil.error("method sendRegisterCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("短信发送失败!");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

	/**
	 * 用户注册
	 */
	/*@Override
	public String register(RegisterParamBean registerParamBean) {
		LogUtil.info("registerParamBean:{}",registerParamBean.toString());
		ReturnBean<String> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,"注册成功");
		//添加防止重复提交锁
		Long lock = RedisUtils.setnx(RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo(), "addRegisterLock");//锁的key
		if(lock != 0l){//重复提交检验通过
			RedisUtils.expire(RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo(), 120);//设置锁存在时间
			//校验注册信息
			try {
				returnBean = userService.checkRegistInfo(registerParamBean);
			} catch (Exception e) {
				returnBean.setCode(RetCodeConstants.ERROR);
				returnBean.setErrorCode(RetCodeConstants.REGISTER_CHECK_ERROR);
				returnBean.setMsg(RetCodeConstants.REGISTER_CHECK_ERROR_DESC);
				LogUtil.error("校验error registerParamBean:{}",registerParamBean.toString(),e);
			}
			//提交注册信息
			if(returnBean.getCode().equals(RetCodeConstants.SUCCESS)){//校验通过
				try {
					returnBean = userService.register(registerParamBean);
				} catch (Exception e) {
					returnBean.setCode(RetCodeConstants.ERROR);
					returnBean.setErrorCode(RetCodeConstants.REGISTER_ERROR);
					returnBean.setMsg(RetCodeConstants.REGISTER_ERROR_DESC);
					LogUtil.error("注册error registerParamBean:{}",registerParamBean.toString(),e);
				}finally {
					Long result = RedisUtils.del(RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo());
					LogUtil.info("注册完成释放锁 key:{},result:{}",RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo(),result);
				}
			}else{
				LogUtil.error("注册校验数据合法性fail registerParamBean：{}",registerParamBean);
				//释放锁
				Long result = RedisUtils.del(RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo());
				LogUtil.info("注册校验数据合法性fail释放锁 key:{},result:{}",RedisKeys.REGISTER_REPEAT_LOCK+registerParamBean.getPhoneNo(),result);
			}
		}else{
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("请勿重复提交！");
		}

		return JSONUtils.alibabaJsonString(returnBean);
	}*/

	/**
	 * 用户登录
	 */
	@Override
	public String login(LoginParamBean loginParamBean) {
		LogUtil.info("用户登录 loginParamBean：{}",loginParamBean.toString());
		ReturnBean<LoginResultBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		//用户登录
		try {
			returnBean = userService.login(loginParamBean);
		} catch (Exception e) {
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode(RetCodeConstants.LOGIN_ERROR);
			returnBean.setMsg(RetCodeConstants.LOGIN_ERROR_DESC);
			LogUtil.error("用户登录异常 loginParamBean:{}",loginParamBean,e);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}

	/**
	 * @Description 用户退出登录
	 * @param loginKey
	 * @return
	 * @see 需要参考的类或方法
	 */
	@Override
	public String logout(String loginKey) {
		LogUtil.info("退出登录 start loginKey:{}",loginKey);
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		if(!userService.logout(loginKey)){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("退出登录失败");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}

	/**
	 * 用户修改密码
	 */
	/*@Override
	public String updatePassword(UpdatePasswordParamBean updatePasswordParamBean) {
		LogUtil.info("用户修改密码 updatePasswordParamBean：{}",updatePasswordParamBean.toString());
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			returnBean = userService.updatePassword(updatePasswordParamBean);
		} catch (Exception e) {
			LogUtil.error("用户修改密码异常 updatePasswordParamBean：{}",updatePasswordParamBean,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode(RetCodeConstants.UPDATE_PASSWORD_ERROR);
			returnBean.setMsg(RetCodeConstants.UPDATE_PASSWORD_ERROR_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}*/

	/**
	 * 发送找回密码验证
	 */
	@Override
	public String sendFindPasswordCheckCode(Map<String, String> params) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,"验证码发送成功！");
		LogUtil.info("method sendFindPasswordCheckCode start params:{}",params);
		//获取手机号
		String phoneNo = params.get("phoneNo");
		//判断当前信息是否已经注册
		User user = userService.findUserByUserName(phoneNo);
		if(user == null){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("该手机号未注册！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
		//若未注册发送注册验证码。
		try {
			boolean result = smsService.sendFindPasswordCheckCode(phoneNo);
			if(result){//短信发送成功
				return JSONUtils.alibabaJsonString(returnBean);
			}else{//短信发送失败
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("短信发送失败!");
				return JSONUtils.alibabaJsonString(returnBean);
			}
		} catch (Exception e) {
			LogUtil.error("method sendRegisterCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("短信发送失败!");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

	/**
	 * 找回密码
	 */
	@Override
	public String findPassword(Map<String, String> params) {
		LogUtil.info("method findPassword start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			returnBean = userService.findPassword(params);
		} catch (Exception e) {
			LogUtil.error("method findPassword error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("操作失败!");
			return JSONUtils.alibabaJsonString(returnBean);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 找回密码
	 */
	@Override
	public String resetPassword(Map<String, String> params) {
		LogUtil.info("method resetPassword start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			returnBean = userService.resetPassword(params);
		} catch (Exception e) {
			LogUtil.error("method resetPassword error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("操作失败!");
			return JSONUtils.alibabaJsonString(returnBean);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}

	/**
	 * @Description 实名认证
	 * @param realNameParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	/*@Override
	public String realName(RealNameParamBean realNameParamBean) {
		LogUtil.info("实名认证 start realNameParamBean：{}",realNameParamBean);
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			returnBean = userService.realName(realNameParamBean);
		}catch (NationalAgentException e) {
			LogUtil.info("实名认证 error realNameParamBean：{}",realNameParamBean);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg("实名认证失败!");
			returnBean.setErrorCode(e.getCode());
		}catch (Exception e) {
			LogUtil.info("实名认证 error realNameParamBean：{}",realNameParamBean);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("实名认证失败");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}*/

	/**
	 * 查找个人资料
	 * @param loginKey 登录标示
	 */
	@Override
	public String findInformation(Map<String, String> params) {
		LogUtil.info("查找个人资料 start params：{}",params);
		ReturnBean<FindInformationResultBean> returnBean = new ReturnBean<>();
		try {
			returnBean = userService.findInformation(params);
		} catch (Exception e) {
			LogUtil.error("查找个人资料 error params：{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("查找资料失败！");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}

	/**
	 * 编辑个人资料
	 */
	/*@Override
	public String editInformation(EditInformationParamBean editInformationParamBean) {
		LogUtil.info("查找个人资料 start editInformationParamBean：{}",editInformationParamBean);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			returnBean = userService.editInformation(editInformationParamBean);
		} catch (Exception e) {
			LogUtil.error("查找个人资料 error editInformationParamBean：{}",editInformationParamBean,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("编辑资料失败！");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
*/
}
