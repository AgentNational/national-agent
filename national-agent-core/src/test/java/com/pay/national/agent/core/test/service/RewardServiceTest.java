package com.pay.national.agent.core.test.service;

import com.pay.commons.utils.lang.DateUtils;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by shuyan.qi on 2018/1/28.
 */
public class RewardServiceTest extends BaseTest {
    @Autowired
    private RewardService rewardService;

    /**
     * 奖励汇总-测试
     */
    @Test
    public void executeSummaryTest(){
        Date day = DateUtils.parseDate("2018-01-27","yyyy-MM-dd");
        String userNo = null;//"123456";
        rewardService.executeSummary(day,userNo);
    }

    /**
     * 查询奖励总汇总-测试
     */
    @Test
    public void rewardGatherTest(){
        String userNo = "123456";
        ParentBusinessCode parentBusinessCode = null;//ParentBusinessCode.CREDIT_CARD;
        String result = rewardService.rewardGather("123456", parentBusinessCode);
    }

    /**
     * 查询奖励月汇总-测试
     */
    @Test
    public void gatherOfMonthTest(){
        String userNo = "123456";
        ParentBusinessCode parentBusinessCode = null;//ParentBusinessCode.CREDIT_CARD;
        Date startDate = DateUtils.parseDate("2018-01-01", "yyyy-MM-dd");
        Date endDate =  DateUtils.parseDate("2018-01-31", "yyyy-MM-dd");
        String result = rewardService.gatherOfMonth(userNo, parentBusinessCode,startDate,endDate);
    }

    /**
     * 查询奖励日汇总-测试
     */
    @Test
    public void gatherOfDayTest(){
        String userNo = "123456";
        ParentBusinessCode parentBusinessCode = null;//ParentBusinessCode.CREDIT_CARD;
        Date startDate = DateUtils.parseDate("2018-01-26", "yyyy-MM-dd");
        Date endDate = DateUtils.parseDate("2018-01-26", "yyyy-MM-dd");
        rewardService.gatherOfDay(userNo,parentBusinessCode,startDate,endDate);
    }

}
