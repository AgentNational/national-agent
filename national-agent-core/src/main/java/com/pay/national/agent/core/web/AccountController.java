package com.pay.national.agent.core.web;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账户
 * @author shuyan.qi
 * @date 2018/1/28
 */
@RequestMapping("/acc")
@Controller
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private WxUserInfoService wxUserInfoService;

    @RequestMapping("/histories")
    @ResponseBody
    public String accHistories(@RequestParam("openId")String openId,Integer pageIndex){
        LogUtil.info("Con 账户历史记录 openId={},pageIndex={}",openId,pageIndex);
        String result = null;
        try {
            Page<AccountHistory> page = new Page<AccountHistory>();
            page.setCurrentPage(pageIndex == null?1:pageIndex);
            WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(openId);
            result = accountService.accHistories(wxUserInfo.getUserNo(),page);
        } catch (Exception e) {
            LogUtil.error("Con 账户历史记录 error openId={},pageIndex={}",openId,pageIndex,e);
        }
        LogUtil.info("Con 账户历史记录 openId={},pageIndex={},result={}",openId,pageIndex,result);
        return result;
    }
}
