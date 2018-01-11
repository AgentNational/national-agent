package com.pay.national.agent.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.facade.RewardFacade;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardSummaryResultBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.User;
import com.pay.national.agent.model.enums.BusinessCode;
/**
 * 奖励
 * @author shuyan.qi
 * Date:2017年9月13日上午3:05:09
 */
@Controller
@CrossOrigin
@RequestMapping("/reward")
public class RewardController {

	@Resource
	private RewardFacade rewardFacade;
	
	/**
	 * 业务奖励月汇总列表
	 * @param loginKey
	 * @param months
	 * @param businessCode
	 * @return
	 */
	@RequestMapping(value="/rewardSummaryMonth",method=RequestMethod.POST)
	public @ResponseBody String rewardSummaryMonth(@RequestParam("loginKey")String loginKey,@RequestParam(value="businessCode",required=false)String businessCode){
		LogUtil.info("rewardSummaryMonth start params loginKey:{},businessCode:{}",loginKey,businessCode);
		ReturnBean<RewardSummaryResultBean> returnBean;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			BusinessCode busCode = null;
			if(StringUtils.isNotBlank(businessCode)){
				busCode = BusinessCode.valueOf(businessCode);
			}
			returnBean = rewardFacade.rewardSummaryMonth(user.getUserNo(),busCode);
		} catch (Exception e) {
			LogUtil.error("rewardSummaryMonth dubbo loginKey:{} error:{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
		}
		LogUtil.info("rewardSummaryMonth return loginKey:{} result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
		
	}
	
	/**
	 * 业务奖励日汇总列表
	 * @param loginKey
	 * @param month
	 * @param businessCode
	 * @return
	 */
	@RequestMapping(value="/rewardSummaryDay",method=RequestMethod.POST)
	public @ResponseBody String rewardSummaryDay(@RequestParam("loginKey")String loginKey,@RequestParam("month")String month,
			@RequestParam(value="businessCode",required=false)String businessCode){
		LogUtil.info("rewardSummaryDay start params loginKey:{},month:{},businessCode:{}",loginKey,month,businessCode);
		ReturnBean<List<RewardDayResultBean>> returnBean;
		try {
			String monthFirstDay = month+"-01";
			String monthLastDay = DateUtil.getMonthLastDay(monthFirstDay, 0);
			
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			BusinessCode busCode = null;
			if(StringUtils.isNotBlank(businessCode)){
				busCode = BusinessCode.valueOf(businessCode);
			}
			returnBean = rewardFacade.rewardSummaryDay(user.getUserNo(),monthFirstDay,monthLastDay,busCode);
		} catch (Exception e) {
			LogUtil.error("rewardSummaryDay dubbo loginKey:{} error:{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
		}
		LogUtil.info("rewardSummaryDay return loginKey:{} result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
		
	}
	
}
