package com.pay.national.agent.core.web;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.wx.WxPayBillService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.RemitParam;
import com.pay.national.agent.model.beans.results.RemitBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.entity.WxPayBill;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private WxPayBillService wxPayBillService;

    /**
     * 账户历史记录
     * @param openId 微信用户openId
     * @param pageIndex 页码
     * @return
     */
    @RequestMapping("/histories")
    @ResponseBody
    public String accHistories(@RequestParam("openId")String openId,@RequestParam("businessCode")String businessCode, String month,Integer pageIndex) {
        LogUtil.info("Con 账户历史记录 openId={},businessCode={},pageIndex={}", openId, businessCode, pageIndex);
        String result = null;
        try {
            Page<AccountHistory> page = new Page<AccountHistory>();
            page.setCurrentPage(pageIndex == null ? 1 : pageIndex);
            WxUserInfo wxUserInfo = wxUserInfoService.find4Login(openId);
            result = accountService.accHistories(wxUserInfo.getUserNo(), businessCode,month, page);
        } catch(NationalAgentException e1){
            result = JSONUtils.alibabaJsonString(new ReturnBean<Object>(e1.getCode(),e1.getMessage()));
        } catch (Exception e) {
            LogUtil.error("Con 账户历史记录 error openId={},pageIndex={}",openId,pageIndex,e);
            result = JSONUtils.alibabaJsonString(new ReturnBean<Object>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC_01));
        }
        LogUtil.info("Con 账户历史记录 return openId={},pageIndex={},result={}",openId,pageIndex,result);
        return result;
    }

    /**
     * 提现
     * @param openId 微信用户openId
     * @param amount 提现金额
     * @return
     */
    @RequestMapping("/WD")
    @ResponseBody
    public String withdraw(@RequestParam("openId")String openId, @RequestParam("amount") Double amount, HttpServletRequest request){
        LogUtil.info("Con 提现 openId={},amount={}",openId,amount);
        ReturnBean<RemitBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            WxUserInfo wxUserInfo = wxUserInfoService.find4Login(openId);
            RemitParam remitParam = new RemitParam();
            remitParam.setUserNo(wxUserInfo.getUserNo());
            remitParam.setOpenId(wxUserInfo.getOpenid());
            remitParam.setUserIp(request.getRemoteAddr());
            remitParam.setAmount(amount == null?0.00:amount);
            RemitBean remitBean = accountService.remit(remitParam);
            returnBean.setData(remitBean);
        } catch (NationalAgentException e1) {
            returnBean.setCode(e1.getCode());
            returnBean.setMsg(e1.getMessage());
        }catch (Exception e) {
            LogUtil.error("Con 提现 error openId={},amount={}",openId,amount,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_DESC_01);
        }
        String result = JSONUtils.alibabaJsonString(returnBean);
        LogUtil.info("Con 提现 return openId={},amount={},result={}",openId,amount,result);
        return result;
    }

    /**
     * 提现进度
     * @param billNo 付款单号
     * @return
     */
    @RequestMapping("/wdDetail")
    @ResponseBody
    public String withdrawDetail(@RequestParam("billNo") String billNo){
        LogUtil.info("Con 提现进度 billNo={}",billNo);
        ReturnBean<WxPayBill> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            WxPayBill wxPayBill = wxPayBillService.findPayBillByPartnerTradeNo(billNo);
            returnBean.setData(wxPayBill);
        } catch (NationalAgentException e1) {
            returnBean.setCode(e1.getCode());
            returnBean.setMsg(e1.getMessage());
        }catch (Exception e) {
            LogUtil.error("Con 提现进度 error billNo={}",billNo,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        String result = JSONUtils.alibabaJsonString(returnBean);
        LogUtil.info("Con 提现进度 return billNo={},result={}",billNo,result);
        return result;
    }
}
