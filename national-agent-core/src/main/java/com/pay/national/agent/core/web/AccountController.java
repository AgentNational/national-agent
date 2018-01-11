package com.pay.national.agent.core.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.DateUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.common.utils.SimpleDateUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.bean.result.AccountBalanceBean;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.core.facade.AccountFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.entity.User;

/**
 * 账户
 * @author shuyan.qi
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	private static final String WITHDRAW_DEPOSIT_LOCK = "wd_";
	private static PropertiesLoader property = new PropertiesLoader("system.properties");
	
	
	@Resource
	private AccountFacade accountFacade;
	
	/**
	 * 提现操作
	 * @param loginKey
	 * @param withdrawAmount
	 * @return
	 */
	@ParamsValidate(value = {@ParamValidate(name = "amount", required = true)})
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	@ResponseBody
	public String withdraw(@RequestParam("loginKey")String loginKey,@RequestParam("amount")Double amount){
		LogUtil.info("withdraw start loginKey:{},amount:{}",loginKey,amount);
		ReturnBean<String> returnBean = new ReturnBean<String>();
		String userNo = "";
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			userNo = user.getUserNo();
			if(StringUtils.isNotBlank(userNo)){
				String limitWithdrawAccountStr = property.getProperty("user.withdraw.limit");
				Double limitWithdrawAccount = Double.valueOf(limitWithdrawAccountStr);
				if(amount >= limitWithdrawAccount){
					//防重复提交
					Long lock = CacheUtils.setnx(WITHDRAW_DEPOSIT_LOCK+userNo, "withdrawDepositLock");
					if(lock != 0l){
						CacheUtils.expire(WITHDRAW_DEPOSIT_LOCK+userNo, 10);
						returnBean = accountFacade.withdrawDeposit(userNo,amount);
					}else{
						returnBean.setCode(RetCodeConstants.SUCCESS);
						returnBean.setMsg("请求正在处理,请耐心等待");
					}
				}else{
					returnBean.setCode(RetCodeConstants.FAIL);
					returnBean.setMsg("提现金额不得小于 "+limitWithdrawAccount+" 元");
				}
			}
		} catch (Exception e) {
			LogUtil.error("withdraw dubbo loginKey:{}, error:{}",loginKey,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
		}finally{
			CacheUtils.del(WITHDRAW_DEPOSIT_LOCK+userNo);
		}
		LogUtil.info(" withdraw return loginKey:{}, result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	
	/**
	 * 查询账户信息
	 * @param loginKey
	 * @return
	 */
	@RequestMapping(value="/accountBalance",method=RequestMethod.POST)
	@ResponseBody
	public String accountBalance(@RequestParam("loginKey")String loginKey){
		LogUtil.info("accountBalance start 参数:loginKey:{}",loginKey);
		ReturnBean<AccountBalanceBean> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = accountFacade.accountBalance(user.getUserNo());
		} catch (Exception e) {
			LogUtil.error("accountBalance dubbo loginKey:{}, error:{}",loginKey,e);
			 returnBean = new ReturnBean<AccountBalanceBean>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("accountBalance return loginKey:{}, result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 查询账户记录
	 * @param loginKey
	 * @param businessCode
	 * @param date
	 * @return
	 */
	@ParamsValidate(value = {@ParamValidate(name = "date", required = true)})
	@RequestMapping(value="/historys",method=RequestMethod.POST)
	@ResponseBody
	public String accountHistorys(@RequestParam("loginKey")String loginKey,@RequestParam(value="businessCode",required=false)String businessCode,
			@RequestParam("pageNo")Integer pageNo,@RequestParam("date")String date){
		LogUtil.info("accountHistorys start params loginKey:{},businessCode:{},pageNo:{},date:{}",loginKey,businessCode,pageNo,date);
		ReturnBean<Page<List<AccountHistory>>> returnBean = null;
		
		//默认查询30天内的数据
		if(StringUtils.isBlank(date)){
			date = SimpleDateUtils.formatDate(DateUtils.addDays(new Date(), -30));
		}
		
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			Page<List<AccountHistory>> page = new Page<>();
			page.setCurrentPage(pageNo == null?1:pageNo);
			page.setShowCount(30);
			returnBean = accountFacade.accountHistoryList(user.getUserNo(),businessCode,date,page);
		} catch (Exception e) {
			LogUtil.error("accountHistorys dubbo loginKey:{}, error:{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("accountHistorys return loginKey:{}, result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 查询提现进度
	 * @param loginKey
	 * @param requestId
	 * @return
	 */
	@ParamsValidate(value = {@ParamValidate(name = "requestId", required = true)})
	@RequestMapping(value="/withdrawDetail",method=RequestMethod.POST)
	@ResponseBody
	public String withdrawDetail(@RequestParam("loginKey")String loginKey,@RequestParam("requestId")String requestId){
		LogUtil.info("withdrawDetail start params loginKey:{},requestId:{}",loginKey,requestId);
		ReturnBean<WithdrawDetailBean> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = accountFacade.findWithdrawDetail(user.getUserNo(),requestId);
		} catch (Exception e) {
			LogUtil.error("withdrawDetail dubbo loginKey:{}, error:{}",loginKey,e);
			returnBean = new ReturnBean<WithdrawDetailBean>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("withdrawDetail return loginKey:{}, result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
}
