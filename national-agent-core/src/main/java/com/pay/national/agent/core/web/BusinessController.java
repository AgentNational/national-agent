package com.pay.national.agent.core.web;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.BusinessOrder;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代理业务
 *
 * @author shuyan.qi
 * @date 2018/1/23
 */
@Controller
@RequestMapping("/buss")
@CrossOrigin
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private WxUserInfoService wxUserInfoService;


    /**
     * 代理业务列表
     * @param parentCode 父业务编码
     * @return
     */
   /* @RequestMapping("/bussList")
    public String businessList(String parentCode){
        LogUtil.info("获取代理业务列表 parentCode={}",parentCode);
        return businessService.businessList(parentCode);
    }*/

    /**
     * 创建订单
     * @param order
     * @return
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(BusinessOrder order){
        LogUtil.info("Con 创建信用卡业务订单 order={}",order);
        String result = businessService.createOrder(order);
        LogUtil.info("Con 创建信用卡业务订单 return order={},result={}",order,result);
        return result;
    }

    /**
     * 查询订单
     * @param openId 微信用户openId
     * @param parentBusinessCode 父业务编码
     * @return
     */
    @RequestMapping(value = "/orders")
    @ResponseBody
    public String orders(@RequestParam("openId")String openId , @RequestParam("parentBusinessCode") String parentBusinessCode,Integer pageIndex){
        LogUtil.info("Con 查询订单 openId={},parentBusinessCode={},pageIndex={}",openId,parentBusinessCode,pageIndex);
        String result = null;
        WxUserInfo wxUserInfo = null;
        try {
            wxUserInfo = wxUserInfoService.find4Login(openId);
            Page<BusinessOrder> page = new Page<>();
            page.setCurrentPage(pageIndex == null?1:pageIndex);
            result = businessService.orders(wxUserInfo.getUserNo(),parentBusinessCode,page);
        } catch (NationalAgentException e1) {
            result = JSONUtils.alibabaJsonString(new ReturnBean<Object>(e1.getCode(),e1.getMessage()));
        }
        LogUtil.info("Con 查询订单 return wxUserInfo={},parentBusinessCode={},pageIndex={},result={}",wxUserInfo,parentBusinessCode,pageIndex,result);
        return result;
    }
}
