package com.pay.national.agent.core.web;

import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.SimpleDateUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.service.common.RewardService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.entity.WxUserInfo;
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
    @Autowired
    private WxUserInfoService wxUserInfoService;

    /**
     * 奖励汇总信息
     * @param openId 微信用户openId
     * @param  parentBusinessCode 父业务编码
     * @return
     */
    @RequestMapping("/rewardGather")
    @ResponseBody
    public String rewardGather(@RequestParam("openId")String openId,String parentBusinessCode){
        LogUtil.info("Con 奖励汇总信息 openId={},parentBusinessCode={}",openId,parentBusinessCode);
        WxUserInfo wxUserInfo = null;
        String result = null;
        try {
            wxUserInfo = wxUserInfoService.selectByOpenId(openId);
            result = rewardService.rewardGather(wxUserInfo.getUserNo(), StringUtils.isBlank(parentBusinessCode)?null:ParentBusinessCode.valueOf(parentBusinessCode));
        } catch (Exception e) {
            LogUtil.error("Con 奖励汇总信息 error",e);
        }
        LogUtil.info("Con 奖励汇总信息 return userNo={},result={}",wxUserInfo.getUserNo(),result);
        return result;
    }

    /**
     * 近三个月的奖励月汇总
     * @param openId 微信用户openId
     * @param parentBusinessCode 父业务编码
     * @return
     */
    @RequestMapping("/gatherOfThreeMonth")
    @ResponseBody
    public String gatherOfThreeMonth(@RequestParam("openId")String openId,String parentBusinessCode){
        LogUtil.info("Con 近三个月的奖励月汇总 openId={},parentBusinessCode={}",openId,parentBusinessCode);
        Date startDate = DateUtil.getFirstDay(new Date(), 0);
        Date endDate = DateUtil.getFirstDay(new Date(), 2);
        WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(openId);
        String result =  rewardService.gatherOfMonth(wxUserInfo.getUserNo(),StringUtils.isBlank(parentBusinessCode)?null:ParentBusinessCode.valueOf(parentBusinessCode),startDate,endDate);
        LogUtil.info("Con 近三个月的奖励月汇总 return userNo={},parentBusinessCode={},result={}",wxUserInfo.getUserNo(),parentBusinessCode,result);
        return result;
    }

    /**
     * 奖励日汇总信息
     * @param month 月份 格式 yyyy-MM
     * @param openId 微信用户openId
     * @return
     */
    @RequestMapping("/gatherOfDay")
    @ResponseBody
    public String gatherOfDay(@RequestParam("month")String month,@RequestParam("openId")String openId,String parentBusinessCode){
        LogUtil.info("Con 奖励日汇总信息 openId={},month={},parentBusinessCode={}",openId,month,parentBusinessCode);
        Date startDate = SimpleDateUtils.parseDate(month+"-01");
        Date endDate = DateUtil.getLastDay(startDate,0);
        WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(openId);
        String result = rewardService.gatherOfDay(wxUserInfo.getUserNo(),StringUtils.isBlank(parentBusinessCode)?null:ParentBusinessCode.valueOf(parentBusinessCode),startDate,endDate);
        LogUtil.info("Con 奖励日汇总信息 return userNo={},month={},parentBusinessCode={},result={}",wxUserInfo.getUserNo(),month,parentBusinessCode,result);
        return result;
    }
}
