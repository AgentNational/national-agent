package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.*;
import com.pay.national.agent.core.bean.result.DayRewardGatherBean;
import com.pay.national.agent.core.bean.result.MonthRewardGatherBean;
import com.pay.national.agent.core.bean.result.RewardGatherBean;
import com.pay.national.agent.core.dao.common.*;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.*;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/25
 */
@Service
public class RewardServiceImpl implements RewardService {
    private static final String REWARD_FAIL_01 = "订单不存在或该订单不可奖励";
    private static final String REWARD_FAIL_02 = "奖励规则不存在或规则已失效";
    private static final String REWARD_FAIL_03 = "奖励金额必须大于0";
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private BusinessOrderMapper businessOrderMapper;
    @Autowired
    private BusinessRewardRuleMapper businessRewardRuleMapper;
    @Autowired
    private RewardRecordMapper rewardRecordMapper;
    @Autowired
    private RewardGatherDayMapper rewardGatherDayMapper;
    @Autowired
    private RewardGatherMonthMapper rewardGatherMonthMapper;
    @Autowired
    private RewardGatherMapper rewardGatherMapper;

    /**
     * 奖励总汇总信息
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @return
     */
    @Override
    public String rewardGather(String userNo,ParentBusinessCode parentBusinessCode) {
        ReturnBean<RewardGatherBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            List<RewardGather> list  = rewardGatherMapper.selectByUser(userNo);
            if(list != null && list.size() >0){
                RewardGatherBean gatherBean = new RewardGatherBean();
                List<RewardGatherBean.RewardGatherDetailBean> details = new ArrayList<>();
                gatherBean.setUserNo(userNo);
                Double totalAmount = 0.00;
                for (RewardGather gather : list) {
                    if(parentBusinessCode == null){
                        RewardGatherBean.RewardGatherDetailBean gatherDetailBean =
                                gatherBean.new RewardGatherDetailBean();
                        gatherDetailBean.setAmount(gather.getTotalAmount());
                        gatherDetailBean.setParentBusinessCode(gather.getParentBusinessCode());
                        details.add(gatherDetailBean);
                    }else{
                        if (parentBusinessCode.name().equals(gather.getParentBusinessCode())){
                            RewardGatherBean.RewardGatherDetailBean gatherDetailBean =
                                    gatherBean.new RewardGatherDetailBean();
                            gatherDetailBean.setAmount(gather.getTotalAmount());
                            gatherDetailBean.setParentBusinessCode(gather.getParentBusinessCode());
                            details.add(gatherDetailBean);
                        }
                    }
                    totalAmount += gather.getTotalAmount();
                }
                gatherBean.setTotalAmount(totalAmount);
                gatherBean.setDetails(details);
                returnBean.setData(gatherBean);
            }
        } catch (Exception e) {
            LogUtil.error("查询奖励汇总信息异常 userNo={},parentBusinessCode={}",userNo,parentBusinessCode,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 奖励月汇总信息
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param startDate 开始日期（选择某月1号)
     * @param endDate 结束日期 （选择某月1号）
     * @return
     */
    @Override
    public String gatherOfMonth(String userNo, ParentBusinessCode parentBusinessCode,Date startDate,Date endDate) {
        ReturnBean<List<MonthRewardGatherBean>> returnBean  = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        List<MonthRewardGatherBean> list = null;
        try {
            if(parentBusinessCode == null){
                 list = rewardGatherMonthMapper.group1(userNo,startDate,endDate);
            }else{
                 list = rewardGatherMonthMapper.group2(userNo,parentBusinessCode.name(),startDate,endDate);
            }
            returnBean.setData(list);
        } catch (Exception e) {
            LogUtil.error("奖励月汇总信息 异常 userNo={},parentBusinessCode={},startDate={},endDate={}",userNo,parentBusinessCode,startDate,endDate,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 奖励日汇总信息
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    @Override
    public String gatherOfDay(String userNo, ParentBusinessCode parentBusinessCode, Date startDate, Date endDate) {
        ReturnBean<List<DayRewardGatherBean>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        List<DayRewardGatherBean> list = null;
        try {
            if (parentBusinessCode == null){
               list = rewardGatherDayMapper.group1(userNo,startDate,endDate);
            }else {
                list = rewardGatherDayMapper.group2(userNo,parentBusinessCode.name(),startDate,endDate);
            }
            returnBean.setData(list);
        } catch (Exception e) {
            LogUtil.error("奖励日汇总信息 异常 userNo={},parentBusinessCode={},startDate={},endDate={}",userNo,parentBusinessCode,startDate,endDate,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 执行奖励汇总
     * @param day 汇总日期
     * @param userNo 汇总用户; 传null或空,汇总所有用户的奖励
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void executeSummary(Date day, String userNo) {
        try {
            executeSummaryOfDay(day,userNo);
            executeSummaryOfMonth(day,userNo);
            executeSummaryOfAll(day,userNo);
        } catch (Exception e) {
            LogUtil.error("奖励汇总异常 day={},userNo={}",day,userNo,e);
            throw e;
        }
    }

    /**
     * 执行奖励的日汇总
     * @param day 日期
     * @param userNo 用户编号
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void executeSummaryOfDay(Date day,String userNo){
        try {
            String dayStr = DateUtil.formatDate(day, "yyyy-MM-dd");
            String startDate = dayStr +" 00:00:00";
            String endDate = dayStr + " 23:59:59";
            rewardGatherDayMapper.summary(userNo,startDate,endDate);
        } catch (Exception e) {
            LogUtil.error("执行奖励的日汇总异常 day={},userNo={}",day,userNo,e);
            throw e;
        }
    }

    /**
     * 执行奖励的月汇总
     * @param day 日期
     * @param userNo 用户编号
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void executeSummaryOfMonth(Date day,String userNo){
        try {
            List<RewardGatherDay> gatherDays = rewardGatherDayMapper.selectByRewardDay(day,userNo);
            if(gatherDays != null && gatherDays.size() > 0){
                for (RewardGatherDay gatherDay:gatherDays) {
                    Date firstDay = DateUtil.getFirstDay(gatherDay.getDay(), 0);
                    RewardGatherMonth gatherMonth = rewardGatherMonthMapper.selectByMonth(firstDay,gatherDay.getBusinessCode(),gatherDay.getUserNo());
                    if(gatherMonth == null){
                        gatherMonth =  new RewardGatherMonth();
                        gatherMonth.setCreateTime(new Date());
                        gatherMonth.setBusinessCode(gatherDay.getBusinessCode());
                        gatherMonth.setParentBusinessCode(gatherDay.getParentBusinessCode());
                        gatherMonth.setMonth(firstDay);
                        gatherMonth.setTotalAmount(gatherDay.getTotalAmount());
                        gatherMonth.setUserNo(gatherDay.getUserNo());
                        rewardGatherMonthMapper.insert(gatherMonth);
                    }else{
                        gatherMonth.setTotalAmount(gatherMonth.getTotalAmount()+gatherDay.getTotalAmount());
                        gatherMonth.setLastUpdateTime(new Date());
                        rewardGatherMonthMapper.updateByPrimaryKey(gatherMonth);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.error("执行奖励的月汇总异常 day={},userNo={}",day,userNo,e);
            throw e;
        }
    }

    /**
     * 执行奖励的总汇总
     * @param day 日期
     * @param userNo 用户编号
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void executeSummaryOfAll(Date day,String userNo){
        try {
            List<RewardGatherDay> gatherDays = rewardGatherDayMapper.selectByRewardDay(day,userNo);
            if(gatherDays != null && gatherDays.size() > 0) {
                for (RewardGatherDay gatherDay : gatherDays) {
                    RewardGather gather  = rewardGatherMapper.selectByBusiness(gatherDay.getUserNo(),gatherDay.getBusinessCode());
                    if(gather == null){
                        gather = new RewardGather();
                        gather.setUserNo(gatherDay.getUserNo());
                        gather.setTotalAmount(gatherDay.getTotalAmount());
                        gather.setBusinessCode(gatherDay.getBusinessCode());
                        gather.setParentBusinessCode(gatherDay.getParentBusinessCode());
                        gather.setCreateTime(new Date());
                        rewardGatherMapper.insert(gather);
                    }else{
                        gather.setTotalAmount(gather.getTotalAmount()+gatherDay.getTotalAmount());
                        gather.setLastUpdateTime(new Date());
                        rewardGatherMapper.updateByPrimaryKey(gather);
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.error("执行奖励的总汇总异常 day={},userNo={}",day,userNo,e);
            throw e;
        }
    }



    /**
     * 奖励
     * @param orderId 订单Id
     * @param rewardRuleId 奖励规则Id
     * @param rewardAmount 奖励金额
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
     public RewardRecord reward(Long orderId,Long rewardRuleId,double rewardAmount){
        BusinessOrder order = businessOrderMapper.selectByPrimaryKey(orderId);
        if(order == null || !"INIT".equals(order.getStatus())){
           throw new NationalAgentException(RetCodeConstants.FAIL,REWARD_FAIL_01);
        }
        BusinessRewardRule rule = businessRewardRuleMapper.selectByPrimaryKey(rewardRuleId);
        if(rule == null || StatusConstants.ENABLE.equals(rule.getStatus())){
            throw new NationalAgentException(RetCodeConstants.FAIL,REWARD_FAIL_02);
        }

        if(rewardAmount <= 0.0){
            throw new NationalAgentException(RetCodeConstants.FAIL,REWARD_FAIL_03);
        }

        //TODO 奖励操作

        RewardRecord rewardRecord = new RewardRecord();
        rewardRecord.setStatus(StatusConstants.SUCCESS);
        rewardRecord.setUserNo(order.getUserNo());
        rewardRecord.setAmount(rewardAmount);
        rewardRecord.setBusinessCode(order.getBusinessCode());
        rewardRecord.setParentBusinessCode(order.getParentBusinessCode());
        rewardRecord.setOrderId(order.getId());
        rewardRecord.setRuleId(rule.getId());
        rewardRecord.setCreateTime(new Date());
        rewardRecord.setRewardTime(new Date());

        rewardRecordMapper.insert(rewardRecord);
        return rewardRecord;
    }


}
