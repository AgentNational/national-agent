package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.entity.BusinessOrder;
import com.pay.national.agent.model.entity.RewardRecord;
import com.pay.national.agent.model.enums.ParentBusinessCode;

import java.util.Date;

/**
 * @author shuyan.qi
 * @date 2018/1/25
 */
public interface RewardService {
    /**
     * 奖励汇总
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码

     * @return
     */
    String rewardGather(String userNo, ParentBusinessCode parentBusinessCode);

    /**
     * 奖励月汇总
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param startDate 开始日期（选择某月1号)
     * @param endDate 结束日期 （选择某月1号）
     * @return
     */
    String gatherOfMonth(String userNo, ParentBusinessCode parentBusinessCode, Date startDate,Date endDate);

    /**
     * 奖励日汇总
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    String gatherOfDay(String userNo, ParentBusinessCode parentBusinessCode, Date startDate, Date endDate);

    /**
     * 奖励汇总
     * @param day 汇总日期
     * @param userNo 汇总用户; 传null或空,汇总所有用户的奖励
     */
    void executeSummary(Date day, String userNo);

    /**
     * 奖励
     * @param orderId 订单Id
     * @param rewardRuleId 奖励规则Id
     * @param rewardAmount 奖励金额
     * @return
     */
    RewardRecord reward(Long orderId,Long rewardRuleId,double rewardAmount);
}
