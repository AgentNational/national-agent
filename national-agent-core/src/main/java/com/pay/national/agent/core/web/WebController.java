/**
 * 
 */
package com.pay.national.agent.core.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.core.facade.AccountFacade;
import com.pay.national.agent.core.facade.RewardFacade;
import com.pay.national.agent.core.facade.TestFacade;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2017年9月5日 上午9:03:26
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/web")
public class WebController {
	
	@Autowired
	private TestFacade testFacade;
	
	@Autowired
	private AccountFacade accountFacade;
	
	@Autowired
	private RewardFacade rewardFacade;

	@RequestMapping(value="/test01",method=RequestMethod.GET)
	public @ResponseBody String test(HttpServletRequest request){
		LogUtil.info("访问成功");
		return "SUCCESS";
	}

	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public @ResponseBody String test(HttpServletRequest request,
			@RequestParam("userName")String userName,@RequestParam("pwd")String pwd){
		String result = "";
		try {
			result = testFacade.helloWorld(userName, pwd);
		} catch (Exception e) {
		}
		return result;
	}
	
	/*@RequestMapping(value="/creditTest",method=RequestMethod.GET)
	@ResponseBody
	public String creditTest(){
		String userNo = "8519103854754";
		ReturnBean<String> returnBean = null;
		try {
			returnBean = accountFacade.credit(userNo,150.00);
		} catch (Exception e) {
			LogUtil.error("withdrawDetail dubbo userNo:{}, error:{}",userNo,e);
			returnBean = new ReturnBean<String>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("creditTest return userNo:{},result:{}",userNo,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	*//**
	 * 查询账户信息
	 * @param loginKey
	 * @return
	 *//*
	@RequestMapping(value="/accountBalance",method=RequestMethod.GET)
	@ResponseBody
	public String accountBalance(){
		ReturnBean<AccountBalanceBean> returnBean = null;
		String userNo = "8519103854754";
		try {
			returnBean = accountFacade.accountBalance(userNo);
		} catch (Exception e) {
			LogUtil.error("accountBalance dubbo userNo:{}, error:{}",userNo,e);
			 returnBean = new ReturnBean<AccountBalanceBean>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("accountBalance return userNo:{}, result:{}",userNo,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	*//**
	 * 提现
	 * @param
	 * @return
	 */
	@RequestMapping(value="/withdrawTest",method=RequestMethod.GET)
	@ResponseBody
	public String withdrawTest(Double amount){
		LogUtil.info("测试提现：amount:{}",amount);
		String userNo = "8519103854754";
		ReturnBean<String> returnBean = null;
		try {
			returnBean = accountFacade.withdrawDeposit(userNo, amount);
		} catch (Exception e) {
			LogUtil.error("withdrawTest dubbo userNo:{},amount:{}, error:{}",userNo,amount,e);
			 returnBean = new ReturnBean<String>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("withdrawTest return userNo:{}, result:{}",userNo,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 提现详情
	 * @param
	 * @return
	 */
	@RequestMapping(value="/wdTest",method=RequestMethod.GET)
	@ResponseBody
	public String withdrawDetailTest(String  requestId){
		LogUtil.info("测试提现详情：requestId:{}",requestId);
		String userNo = "8519103854754";
		ReturnBean<WithdrawDetailBean> returnBean = null;
		try {
			returnBean = accountFacade.findWithdrawDetail(userNo, requestId);
		} catch (Exception e) {
			LogUtil.error("withdrawDetailTest dubbo userNo:{},requestId:{}, error:{}",userNo,requestId,e);
			 returnBean = new ReturnBean<WithdrawDetailBean>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("withdrawDetailTest return userNo:{}, result:{}",userNo,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 测试奖励
	 * @return
	 */
	@RequestMapping(value="/rwTest",method=RequestMethod.GET)
	@ResponseBody
	public String rewardTest(){
		LogUtil.info("测试奖励开始。。。");
		String userNo = "8519103854754";
		RewardRecord rewardRecord = new RewardRecord();
		rewardRecord.setBusinessCode(BusinessCode.CREDIT_CARD);
		rewardRecord.setChildBusinessCode(ChildBusinessCode.PUFA);
		rewardRecord.setCreateTime(new Date());
		rewardRecord.setOrderNo("CC_1505894923227689278");
		rewardRecord.setRewardAmount(40.0);
		rewardRecord.setStatus("INIT");
		rewardRecord.setUserNo(userNo);
		ReturnBean<String> returnBean = rewardFacade.reward(rewardRecord);
		LogUtil.info("rewardTest return userNo:{}, result:{}",userNo,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	

}
