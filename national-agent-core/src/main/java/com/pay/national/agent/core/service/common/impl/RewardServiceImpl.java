package com.pay.national.agent.core.service.common.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.RewardGatherDayMapper;
import com.pay.national.agent.core.dao.common.RewardGatherMapper;
import com.pay.national.agent.core.dao.common.RewardRecordMapper;
import com.pay.national.agent.core.dao.common.RewardRuleMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.RewardDayResultBean;
import com.pay.national.agent.model.beans.results.RewardMonthResultBean;
import com.pay.national.agent.model.beans.results.RewardSummaryResultBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.RewardGather;
import com.pay.national.agent.model.entity.RewardGatherDay;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.entity.RewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.RewardStatus;

@Service("rewardService")
public class RewardServiceImpl implements RewardService{
	@Resource
	private RewardRecordMapper rewardRecordMapper;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private RewardGatherDayMapper rewardGatherDayMapper;
	
	@Resource
	private RewardGatherMapper rewardGatherMapper;
	
	@Resource
	private RewardRuleMapper rewardRuleMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public RewardRecord createRewardRecord(RewardRecord rewardRecord) {
		ChildBusinessCode childBusinesssCode = rewardRecord.getChildBusinessCode();
		String systemFlowId = childBusinesssCode.name()+System.currentTimeMillis()+CommonCodeUtil.NextInt(10000, 99999);
		rewardRecord.setSystemFlowId(systemFlowId);
		rewardRecordMapper.insert(rewardRecord);
		return rewardRecord;
	}
	
	@Override
	public ReturnBean<String> reward(RewardRecord rewardRecord) {
		ReturnBean<String> returnBean = null;
		RewardRecord rewardRecordDb = null;
		
		//校验奖励记录
		if(null == rewardRecord)return new ReturnBean<String>(RetCodeConstants.ERROR, "奖励记录不能为null") ;
		if(null != rewardRecord.getId()){
			rewardRecordDb = rewardRecordMapper.selectByPrimaryKey(rewardRecord.getId());
			if(null == rewardRecordDb)return new ReturnBean<String>(RetCodeConstants.ERROR, "奖励记录不存在") ;
		}else{
			rewardRecordDb = createRewardRecord(rewardRecord);
		}
		
		//入账
		returnBean = accountService.credit(rewardRecord.getUserNo(), rewardRecord.getRewardAmount(), rewardRecord.getSystemFlowId(),
				rewardRecord.getBusinessCode(),rewardRecord.getChildBusinessCode());
		
		if(RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
			rewardRecordDb.setStatus("SUCCESS");
			rewardRecordDb.setRewardStatus(RewardStatus.SUCCESS);
			rewardRecordDb.setRewardTime(new Date());
		}else{
			rewardRecordDb.setStatus("FAIL");
			rewardRecordDb.setRewardStatus(RewardStatus.FAIL);
			rewardRecordDb.setErrorCode(returnBean.getErrorCode());
			rewardRecordDb.setErrorMsg(returnBean.getMsg());
		}
		//更新奖励记录
		rewardRecordMapper.updateByPrimaryKey(rewardRecordDb);
		return returnBean;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void gatherForDay(Date date) {
		String day = DateUtil.formatDate(date, "yyyy-MM-dd");
		try {
			rewardGatherDayMapper.gather(day);
		} catch (Exception e) {
			LogUtil.error("gatherForDay day:{},error:{}",day,e);
			throw e;
		}
	}
	
	@Override
	public void gatherForAll(Date date) {
		String day = DateUtil.formatDate(date, "yyyy-MM-dd");
		try {
			List<RewardGatherDay> lists = rewardGatherDayMapper.selectByDate(day);
			if(null != lists && lists.size() >0){
				LogUtil.info("gatherForAll day:{} 奖励日汇总列表size:{}",day,lists.size());
				for (RewardGatherDay rewardGatherDay : lists) {
					processSummary(rewardGatherDay);
				}
			}
		} catch (Exception e) {
			LogUtil.error("gatherForAll 查询奖励日汇总列表异常 day:{}:error:{}",day,e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	private void processSummary(RewardGatherDay rewardGatherDay){
		try {
			List<RewardGather> list = rewardGatherMapper.selectByBusiness(rewardGatherDay.getUserNo(),rewardGatherDay.getBusinessCode().name(),rewardGatherDay.getChildBusinessCode().name());
			
			if(null != list && list.size() > 0){
				RewardGather gather = list.get(0);
				if(null != gather){
					gather.setRewardAmountTotal(gather.getRewardAmountTotal() + rewardGatherDay.getRewardAmountTotal());
					rewardGatherMapper.updateByPrimaryKey(gather);
				}else{
					gather = new RewardGather();
					gather.setUserNo(rewardGatherDay.getUserNo());
					gather.setBusinessCode(rewardGatherDay.getBusinessCode());
					gather.setChildBusinessCode(rewardGatherDay.getChildBusinessCode());
					gather.setRewardAmountTotal(rewardGatherDay.getRewardAmountTotal());
					gather.setCreateTime(new Date());
					rewardGatherMapper.insert(gather);
				}
			}
		} catch (Exception e) {
			LogUtil.error("processSummary 处理奖励汇总异常 rewardGatherDay:{}:error:{}",rewardGatherDay,e);
			throw e;
		}
	}

	@Override
	public List<RewardRule> getRewardRules(ChildBusinessCode childBusinessCode) {
		return rewardRuleMapper.getRewardRules(childBusinessCode.name());
	}

	@Override
	public RewardRecord getRewardRecord(String userNo, String orderNo ,Integer ruleId) {
		return rewardRecordMapper.getRewardRecord(userNo,orderNo,ruleId);
	}
	
	/**
	 * 业务奖励月汇总列表
	 * @param userNo 用户编号
	 * @param businessCode 业务编码
	 * @return
	 */
	@Override
	public ReturnBean<RewardSummaryResultBean> rewardSummaryMonth(String userNo, BusinessCode businessCode) {
		ReturnBean<RewardSummaryResultBean> returnBean = new ReturnBean<>();
		RewardSummaryResultBean rewardSummary = new RewardSummaryResultBean();
		String busCode = null;
		try {
			if(null != businessCode){
				busCode = businessCode.name();
			}
			List<RewardGather> rewardGatherList = rewardGatherMapper.selectByBusiness(userNo,busCode, null);
			//奖励总金额
			double amountOfAll =  0.00D;
			if(null != rewardGatherList && rewardGatherList.size() > 0){
				for (RewardGather rewardGather : rewardGatherList) {
					amountOfAll += rewardGather.getRewardAmountTotal();
				}
			}
			rewardSummary.setAmountOfAll(amountOfAll);
			
			//月奖励汇总数据
			List<RewardMonthResultBean> monthLists = null;
			if(null == businessCode){
				//不分业务
				monthLists = rewardGatherDayMapper.rewardSummaryMonth2(userNo);
			}else{
				//分业务
				monthLists = rewardGatherDayMapper.rewardSummaryMonth(userNo,busCode);
			}
			rewardSummary.setMonthLists(monthLists);
			returnBean.setData(rewardSummary);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("rewardSummaryMonth error userNo:{},businessCode:{},exception:{}",userNo,businessCode,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0400101");
			returnBean.setMsg("查询奖励月汇总异常");
		}
		return returnBean;
	}
	
	/**
	 * 业务奖励日汇总列表
	 * @param userNo
	 * @param startDate 格式（yyyy-MM-dd）
	 * @param endDate 格式（yyyy-MM-dd）
	 * @param businessCode
	 * @return
	 */
	@Override
	public ReturnBean<List<RewardDayResultBean>> rewardSummaryDay(String userNo, String startDate, String endDate,
			BusinessCode businessCode) {
		ReturnBean<List<RewardDayResultBean>> returnBean  = new ReturnBean<List<RewardDayResultBean>>();
		try {
			List<RewardDayResultBean> list = null;
			if(null == businessCode){
				//不分业务
				list = rewardGatherDayMapper.rewardSummaryDay2(userNo,startDate,endDate);
			}else{
				//分业务
				list = rewardGatherDayMapper.rewardSummaryDay(userNo,startDate,endDate,businessCode.name());
			}
			returnBean.setData(list);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("rewardSummary error userNo:{},businessCode:{},exception:{}",userNo,businessCode,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0400102");
			returnBean.setMsg("查询奖励日汇总异常");
		}
		return returnBean;
	}
	
}
