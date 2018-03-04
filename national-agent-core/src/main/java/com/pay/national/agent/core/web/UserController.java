package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.commons.utils.lang.AmountUtils;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
	private AccountService accountService;
	@Autowired
	private WxUserInfoService wxUserInfoService;

	@Autowired
	private UserService userService;


	/**
	 * 个人中心
	 * @param openId
	 * @return
	 */
	@RequestMapping("/myInfo")
	@ResponseBody
	public String myInfo(@RequestParam("openId")String openId){
		LogUtil.info("Con 个人中心 openId={}",openId);
		ReturnBean<Map<String,Object>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);

		try {
			WxUserInfo wxUserInfo = wxUserInfoService.find4Login(openId);
			Account account = accountService.findByUser(wxUserInfo.getUserNo());
			Map<String,Object> map = new HashMap<String,Object>(3);
			map.put("touxiang",wxUserInfo.getHeadimgurl());
			map.put("niName",wxUserInfo.getNickname());
			//余额-在途金额-冻结金额
			double subtract = AmountUtils.subtract(account.getBalance(), account.getTransAmount());
			double balance = AmountUtils.subtract(subtract, account.getFrozenAmount());
			map.put("balance",balance);
			returnBean.setData(map);
		} catch(NationalAgentException e1){
			returnBean.setCode(e1.getCode());
			returnBean.setMsg(e1.getMessage());
		} catch (Exception e) {
			LogUtil.error("Con 个人中心 error openId={}",openId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
		}
		String result = JSONUtils.alibabaJsonString(returnBean);
		LogUtil.info("Con 个人中心 return openId={},result={}",openId,result);
		return result;
	}

	/**
	 * 校验是否弹屏
	 * @return
	 */
	@RequestMapping(value = "/checkPhone",method = RequestMethod.POST)
	public @ResponseBody String checkPhone(@RequestParam("openId")String openId){
		LogUtil.info("校验用户是否需要弹屏openId：{}",openId);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userService.checkPhone(openId);
		}catch (Exception e){
			LogUtil.info("校验用户是否需要弹屏 ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSON.toJSONString(returnBean);


	}

	/**
	 * 校验是否弹屏
	 * @return
	 */
	@RequestMapping("/sendCheckCode")
	public @ResponseBody String sendCheckCode(@RequestParam("openId")String openId,@RequestParam("phoneNo")String phoneNo){
		LogUtil.info("发送手机验证码openId：{}，",openId);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userService.sendCheckCode(openId,phoneNo);
		}catch (Exception e){
			LogUtil.info("校验用户是否需要弹屏 ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSON.toJSONString(returnBean);


	}

	/**
	 * 完善手机号
	 * @return
	 */
	@RequestMapping("/completelPhone")
	public @ResponseBody String completelPhone(@RequestParam("openId")String openId,@RequestParam("checkCode")String checkCode
	,@RequestParam("phoneNo")String phoneNo){
		LogUtil.info("完善手机号openId：{}，checkCode:{},phoneNo:{}",openId,checkCode,phoneNo);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			return userService.completelPhone(openId,checkCode,phoneNo);
		}catch (Exception e){
			LogUtil.info("完善手机号异常 ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSON.toJSONString(returnBean);


	}


	/**
	 * 校验是否有代理权限
	 * @return
	 */
	@RequestMapping("/checkAgentRight")
	public @ResponseBody String checkAgentRight(@RequestParam("openId")String openId){
		LogUtil.info("校验是否有代理权限openId：{}",openId);
		ReturnBean<Map<String,String>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		Map<String,String> map = new HashMap<>();
		map.put("STATUS","TRUE");
		returnBean.setData(map);
		/*try {
			WxUserInfo wxUserInfo = wxUserInfoService.find4Login(openId);
			if(wxUserInfo != null){
				return userService.checkAgentRight(wxUserInfo.getUserNo());
			}else{
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("用户信息不存在！");
			}
		}catch (Exception e){
			LogUtil.info("校验是否有代理权限异常 ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}*/
		return JSON.toJSONString(returnBean);


	}


}
