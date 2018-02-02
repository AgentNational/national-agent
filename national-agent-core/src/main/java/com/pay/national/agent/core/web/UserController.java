package com.pay.national.agent.core.web;

import com.pay.commons.utils.lang.AmountUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
			WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(openId);
			Account account = accountService.findByUser(wxUserInfo.getUserNo());
			Map<String,Object> map = new HashMap<String,Object>(3);
			map.put("touxiang",wxUserInfo.getHeadimgurl());
			map.put("niName",wxUserInfo.getNickname());
			//余额-在途金额-冻结金额
			double subtract = AmountUtils.subtract(account.getBalance(), account.getTransAmount());
			double balance = AmountUtils.subtract(subtract, account.getFrozenAmount());
			map.put("balance",balance);
			returnBean.setData(map);
		} catch (Exception e) {
			LogUtil.error("Com 个人中心 error openId={}",openId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
		}
		String result = JSONUtils.alibabaJsonString(returnBean);
		LogUtil.info("Con 个人中心 return openId={},result={}",openId,result);
		return result;
	}
}
