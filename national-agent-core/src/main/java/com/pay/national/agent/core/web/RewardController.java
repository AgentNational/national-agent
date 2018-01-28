package com.pay.national.agent.core.web;

import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.SimpleDateUtils;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 *奖励
 * @author shuyan.qi
 * @date 2018/1/25
 */
@Controller
@RequestMapping("/reward")
@CrossOrigin
public class RewardController {
    @Autowired
    private RewardService rewardService;

    /**
     * 奖励汇总信息
     * @param userNo 用户编号
     * @param  parentBusinessCode 父业务编码
     * @return
     */
    @RequestMapping("/rewardGather")
    @ResponseBody
    public String rewardGather(@RequestParam("userNo")String userNo,@RequestParam("parentBusinessCode")String parentBusinessCode){
        LogUtil.info("Con 奖励汇总信息 userNo={},parentBusinessCode={}",userNo,parentBusinessCode);
        String result = rewardService.rewardGather(userNo, ParentBusinessCode.valueOf(parentBusinessCode));
        LogUtil.info("Con 奖励汇总信息 return userNo={},result={}",userNo,result);
        return result;
    }

    /**
     * 近三个月的奖励月汇总
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @return
     */
    @RequestMapping("/gatherOfThreeMonth")
    @ResponseBody
    public String gatherOfThreeMonth(@RequestParam("userNo")String userNo,@RequestParam("parentBusinessCode")String parentBusinessCode){
        LogUtil.info("Con 近三个月的奖励月汇总 userNo={},parentBusinessCode={}",userNo,parentBusinessCode);
        Date startDate = DateUtil.getFirstDay(new Date(), -2);
        Date endDate = DateUtil.getFirstDay(new Date(), 0);
        String result =  rewardService.gatherOfMonth(userNo,ParentBusinessCode.valueOf(parentBusinessCode),startDate,endDate);
        LogUtil.info("Con 近三个月的奖励月汇总 return userNo={},parentBusinessCode={},result={}",userNo,parentBusinessCode,result);
        return result;
    }

    /**
     * 奖励日汇总信息
     * @param month
     * @param userNo
     * @return
     */
    @RequestMapping("/gatherOfDay")
    @ResponseBody
    public String gatherOfDay(@RequestParam("month")String month,@RequestParam("userNo")String userNo,@RequestParam("parentBusinessCode")String parentBusinessCode){
        LogUtil.info("Con 奖励日汇总信息 userNo={},month={},parentBusinessCode={}",userNo,month,parentBusinessCode);
        Date startDate = SimpleDateUtils.parseDate(month+"-01");
        Date endDate = DateUtil.getLastDay(startDate,0);
        String result = rewardService.gatherOfDay(userNo,ParentBusinessCode.valueOf(parentBusinessCode),startDate,endDate);
        LogUtil.info("Con 奖励日汇总信息 return userNo={},month={},parentBusinessCode={},result={}",userNo,month,parentBusinessCode,result);
        return result;
    }
}
