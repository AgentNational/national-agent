package com.pay.national.agent.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.BeanUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.UserFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: 用户管理相关controller
 * @see: 需要参考的类
 * @version 2017年9月5日 下午6:23:02
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserFacade userFacade;
	
	/**
	 * @Description 登录
	 * @param userName 用户名
	 * @param password 密码
	 * @param coordinate 经纬度
	 * @param deviceNo 设备号
	 * @return
	 * @see 需要参考的类或方法
	 */
	@ParamsValidate(value = {@ParamValidate(name = "userName", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="password",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="version",required = true)})
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request,@RequestParam Map<String,Object>params){
		//登录
		LogUtil.info("用户登录 参数列表 params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		//参数转换为bean
		LoginParamBean loginParamBean = new LoginParamBean();
		BeanUtils.transMap2Beannew(params, loginParamBean);
		try {
			//调用服务进行登录
			return userFacade.login(loginParamBean);
		} catch (Exception e) {
			LogUtil.error("用户登录调用dubbo异常 params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("登录失败！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}
	
	/**
	 * @Description 退出登录
	 * @param loginKey 登录唯一标示
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "loginKey", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="version",required = true),
			@ParamValidate(name="deviceType",required = true)})
	public @ResponseBody String logout(HttpServletRequest request,@RequestParam Map<String, String> params){
		LogUtil.info("用户退出登录start params:{}",JSONUtils.alibabaJsonString(params));
		ReturnBean<Object> returnBean = new ReturnBean<>();
		String loginKey = params.get("loginKey"); 
		try {
			return userFacade.logout(loginKey);
		} catch (Exception e) {
			LogUtil.error("用户退出登录调用dubbo异常 params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("退出登录失败！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

	/**
	 * @Description 用户注册
	 * @param phoneNo 注册手机号
	 * @param password 密码
	 * @param repeatPassword 重复密码
	 * @param parentUserNo 推荐人编码
	 * @param checkCode 验证码
	 * @return
	 * @see 需要参考的类或方法
	 */
	/*@RequestMapping(value = "/register", method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="password",required = true),
			@ParamValidate(name="checkCode",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String register(HttpServletRequest request,@RequestParam Map<String, Object> params){
		LogUtil.info("method register start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		//将参数转换bean
		RegisterParamBean registerParamBean = new RegisterParamBean();
		BeanUtils.transMap2Beannew(params, registerParamBean);
		try {
			
			//调用core dubbo接口发送验证码
			return userFacade.register(registerParamBean);
		} catch (Exception e) {
			//调用dubbo服务异常
			LogUtil.error("method register error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}*/
	
	/**
	 * @Description 发送注册验证码。
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/sendCheckCode",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="businessType",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String sendCheckCode(HttpServletRequest request,
			@RequestParam Map<String, String> params){
		LogUtil.info("method sendRegisterCheckCode start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		String businessType = params.get("businessType");
		try {
			//调用core dubbo接口发送验证码
			if("REGISTER".equals(businessType)){
				return userFacade.sendRegisterCheckCode(params);
			}else if("FINDPASSWORD".equals(businessType)){
				return userFacade.sendFindPasswordCheckCode(params);
			}
		} catch (Exception e) {
			//调用dubbo服务异常
			LogUtil.error("method sendRegisterCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	/**
	 * @Description 修改密码
	 * @param password 原密码
	 * @param newPassword 新密码
	 * @param loginKey 登录唯一标示
	 * @return
	 * @see 需要参考的类或方法
	 */
	/*@RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="loginKey",required = true),
			@ParamValidate(name="password",required = true),
			@ParamValidate(name="newPassword",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String updatePassword(HttpServletRequest request,@RequestParam Map<String, Object> params){
		LogUtil.info("用户修改密码 start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		UpdatePasswordParamBean updatePasswordParamBean = new UpdatePasswordParamBean();
		BeanUtils.transMap2Beannew(params, updatePasswordParamBean);
		try {
			return userFacade.updatePassword(updatePasswordParamBean);
		} catch (Exception e) {
			LogUtil.info(" 用户修改密码调用dubbo服务异常 params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("修改密码失败！");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}*/
	
	/**
	 * @Description 发送找回密码验证码。
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/sendFindPasswordCheckCode",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String sendFindPasswordCheckCode(HttpServletRequest request,
			@RequestParam Map<String, String> params){
		LogUtil.info("method sendRegisterCheckCode start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			//调用core dubbo接口发送验证码
			return userFacade.sendFindPasswordCheckCode(params);
		} catch (Exception e) {
			//调用dubbo服务异常
			LogUtil.error("method sendFindPasswordCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}
	
	/**
	 * @Description 重置密码（找回密码第一步）
	 * @param phoneNo 手机号
	 * @param code 验证码
	 * @param password 新密码
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/findPassword",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="code",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String findPassword(HttpServletRequest request,@RequestParam Map<String, String> params){
		LogUtil.info("用户找回密码第一步 start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userFacade.findPassword(params);
		} catch (Exception e) {
			LogUtil.error("用户找回密码dubbo异常 params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("找回密码异常！");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * @Description 重置密码（找回密码第二步）
	 * @param phoneNo 手机号
	 * @param code 验证码
	 * @param password 新密码
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="password",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String resetPassword(HttpServletRequest request,@RequestParam Map<String, String> params){
		LogUtil.info("用户找回密码第二步 修改密码 start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userFacade.resetPassword(params);
		} catch (Exception e) {
			LogUtil.error("用户找回密码dubbo异常 params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("找回密码异常！");
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * @Description 实名认证（完善信息）
	 * @param loginKey 登录标示
	 * @param bankAccountName 用户真实姓名
	 * @param identityNo 身份证号
	 * @param bankAccountNo 开户账号
	 * @param bankName 开户行名称
	 * @param bankCode 开户行总行编号
	 * @param alliedBankCode 联行号
	 * @param sabkCode 清分行号
	 * @param province 开户行省份
	 * @param city 开户城市
	 * @param areaCode 地区
	 * @param coordinate 经纬度
	 * @return
	 * @see 需要参考的类或方法
	 */
	/*@ParamsValidate(value = {@ParamValidate(name = "loginKey", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="identityCardId",required = true),
			@ParamValidate(name="bankAccountId",required = true),
			@ParamValidate(name="version",required = true)})
	@RequestMapping(value="/realName",method=RequestMethod.POST)
	public @ResponseBody String realName(HttpServletRequest request ,@RequestParam Map<String,Object> params){
		LogUtil.info("实名认证 start 参数 params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		RealNameParamBean realNameParamBean = new RealNameParamBean();
		//将参数转为bean
		BeanUtils.transMap2Beannew(params, realNameParamBean);
		//调用dubbo进行实名认证
		try {
			return userFacade.realName(realNameParamBean);
		} catch (Exception e) {
			LogUtil.error(" 实名认证error params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("实名认证失败！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}*/
	
	/**
	 * @Description 查找个人资料
	 * @param loginKey 登录标示
	 * @return
	 * @see 需要参考的类或方法
	 */
	@ParamsValidate(value = {@ParamValidate(name = "loginKey", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="deviceType",required = true),
			@ParamValidate(name="version",required = true)})
	@RequestMapping(value = "/findInformation",method = RequestMethod.POST)
	public @ResponseBody String findInformation(HttpServletRequest request,@RequestParam Map<String,String> params){
		LogUtil.info("查找个人资料 start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userFacade.findInformation(params);
		} catch (Exception e) {
			LogUtil.error("查找个人资料 error params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("查找资料异常");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}
	
	/**
	 * @Description 编辑个人资料
	 * @return
	 * @see 需要参考的类或方法
	 */
	/*@RequestMapping(value = "/editInformation",method = RequestMethod.POST)
	public String editInformation(HttpServletRequest request,@RequestParam Map<String, Object> params){
		ReturnBean<Object> returnBean = new ReturnBean<>();
		LogUtil.info("编辑个人资料 start params:{}",params);
		EditInformationParamBean editInformationParamBean = new EditInformationParamBean();
		BeanUtils.transMap2Beannew(params, editInformationParamBean);
		try {
			return userFacade.editInformation(editInformationParamBean);
		} catch (Exception e) {
			LogUtil.info("编辑个人资料 error params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("编辑个人资料失败！");
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}*/
}
