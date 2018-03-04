package com.pay.national.agent.core.job;

import com.pay.commons.utils.lang.DateUtils;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.model.beans.results.OrderMatchBean;
import com.pay.national.agent.model.entity.BusinessOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/26
 */
@Component
public class MyJob {
   /*
    //测试定时
    @Scheduled(cron = "0/5 * * * * ?")
    */
    public void test01(){
        LogUtil.info("睡眠线程开始.....");
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtil.info("睡眠线程结束.....");
    }

    @Autowired
    private RewardService rewardService;
    @Autowired
    private BusinessService businessService;

    /**
     * 业务奖励发放定时
     */
    public void orderReward(){
        LogUtil.info("信用卡奖励开始发放..."+DateUtil.formatDate(new Date(),"yyyy-MM-dd"));
        //TODO 匹配成功的业务订单数据
        List<OrderMatchBean> orders = businessService.matchRewardOrder();
        if(orders != null && orders.size() >0){
            for (OrderMatchBean order:orders) {
                rewardService.reward(order.getOrderId(),null);
            }
        }

    }

    /**
     * 奖励汇总定时
     */
    public void rewardSummany(){
        LogUtil.info("奖励汇总定时开始.....");
        Date yesterday = DateUtils.addDays(new Date(),-1);
        try {
            rewardService.executeSummary(yesterday,null);
        } catch (Exception e) {
            LogUtil.error("奖励汇总定时异常 date={}",yesterday,e);
        }
    }
}
