package com.pay.national.agent.core.service.common.impl;

import com.pay.commons.utils.lang.AmountUtils;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.*;
import com.pay.national.agent.core.dao.common.BusinessOrderMapper;
import com.pay.national.agent.core.dao.common.BusinessRewardRuleMapper;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.OrderMatchBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.AgentBusiness;
import com.pay.national.agent.model.entity.BusinessExpantion;
import com.pay.national.agent.model.entity.BusinessOrder;
import com.pay.national.agent.model.entity.BusinessRewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import com.pay.national.agent.model.enums.RewardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/23
 */
@Service
public class BusinessServiceImpl implements BusinessService{
    private static final String ORDER_NULL_MSG = "不允许提交空订单";
    private static final String ORDER_INCOMPLETE_MSG = "订单缺失必要参数";
    private static final String CUSTOMER_IS_REPEAT = "该用户已被推荐过";
    private static final String BUSINESS_DISABLE_MSG = "该业务不存在或已下架";

    private static String jiaotongUrl;
    private static String pufaUrl;
    private static String pinganUrl;
    private static String xzfUrl;
    private static String yipiaoUrl;

    private static PropertiesLoader system = new PropertiesLoader("system.properties");
    @Autowired
    private BusinessOrderMapper businessOrderMapper;
    @Autowired
    private BusinessRewardRuleMapper businessRewardRuleMapper;

    static {
        jiaotongUrl = system.getProperty("business.jiaotong.url").trim();
        pufaUrl = system.getProperty("business.pufa.url").trim();
        pinganUrl = system.getProperty("business.pingan.url").trim();
        xzfUrl = system.getProperty("business.xzf.url").trim();
        yipiaoUrl = system.getProperty("business.yipiao.url").trim();
    }
    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public String createOrder(BusinessOrder order) {
       ReturnBean<Map<String,Object>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            checkOrder(order);
            BusinessOrder dbOrder =  businessOrderMapper.selectByUser(order.getBusinessCode(),order.getCustomerPhone());
            if(dbOrder == null || StatusConstants.DELETE.equals(dbOrder.getStatus())){
               initOrder(order);
               Map<String,Object> returnMap = new HashMap<String,Object>(2);
               returnMap.put("orderId",order.getId());
               returnMap.put("jumpUrl",selectBusJumpUrl(order));
               returnBean.setData(returnMap);
            }
        } catch (Exception e) {
            LogUtil.error("创建订单异常 order={}",order,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_DESC_01);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 获取业务跳转链接
     * @param order
     * @return
     */
    private String selectBusJumpUrl(BusinessOrder order) {
        BusinessCode businessCode = BusinessCode.valueOf(order.getBusinessCode());
        String jumpUrl = "";
        switch (businessCode){
            case JIAOTONG:
                jumpUrl =  jiaotongUrl;
            break;
            case PINGAN:
                jumpUrl =  pinganUrl;
            break;
            case PUFA:
                jumpUrl =  pufaUrl;
            break;
            case YIPIAO:
                jumpUrl =  yipiaoUrl;
            break;
            case XZF:
                jumpUrl = xzfUrl;
            break;
            default:
            break;
        }
        return jumpUrl;
    }

    /**
     * 创建订单
     * @param order
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void initOrder(BusinessOrder order) {
        BusinessCode businessCode = BusinessCode.valueOf(order.getBusinessCode());
        BusinessRewardRule rule = businessRewardRuleMapper.selectByBusiness(order.getBusinessCode());
        if(rule == null || !StatusConstants.ENABLE.equals(rule.getStatus())){
            throw new NationalAgentException(RetCodeConstants.FAIL,BUSINESS_DISABLE_MSG);
        }
        switch (businessCode){
            case JIAOTONG:
            case PUFA:
            case PINGAN:
                order.setParentBusinessCode(ParentBusinessCode.CREDIT_CARD.name());
                order.setRewardAmount(rule.getRewardAmount());
                break;
            case XZF:
                order.setParentBusinessCode(ParentBusinessCode.POS.name());
                break;
            case YIPIAO:
                order.setParentBusinessCode(ParentBusinessCode.TICKET.name());
                order.setRewardAmount(rule.getRewardAmount());
                break;
            default:
                break;
        }
        order.setBusinessName(businessCode.getBusienssName());
        order.setStatus("INIT");
        order.setCreateTime(new Date());
        businessOrderMapper.insert(order);
    }

    /**
     * 检查订单的正确性
     * @param order 订单
     */
    private void checkOrder(BusinessOrder order) {
        if(order == null){
            throw new NationalAgentException(RetCodeConstants.FAIL,ORDER_NULL_MSG);
        }

        try {
            BusinessCode businessCode = BusinessCode.valueOf(order.getBusinessCode());
        } catch (IllegalArgumentException e) {
            LogUtil.error("创建订单 业务编码不正确 businessCode={}",order.getBusinessCode());
            throw new NationalAgentException(RetCodeConstants.FAIL,BUSINESS_DISABLE_MSG);
        }

        if(StringUtils.isBlank(order.getBusinessCode()) || StringUtils.isBlank(order.getCustomerName()) ||
                StringUtils.isBlank(order.getCustomerPhone()) || StringUtils.isBlank(order.getUserNo())){
            throw new NationalAgentException(RetCodeConstants.FAIL,ORDER_INCOMPLETE_MSG);
        }
    }

    /**
     *  查询订单
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param page 分页
     *
     * @return
     */
    @Override
    public String orders(String userNo, String parentBusinessCode, Page<BusinessOrder> page) {
        ReturnBean<List<BusinessOrder>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            List<BusinessOrder> orders =  businessOrderMapper.findAllOrders(userNo,parentBusinessCode,page);
            returnBean.setData(orders);
        } catch (Exception e) {
            LogUtil.error("查询订单异常 userNo={},parentBusinessCode={},page={},",userNo,parentBusinessCode,page,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 匹配需要奖励的订单
     * @return
     */
    @Override
    public List<OrderMatchBean> matchRewardOrder() {
        List<OrderMatchBean> allOrders = new ArrayList<>();
        //信用卡订单匹配
        List<OrderMatchBean> creditCardOrders  = matchCreditCardOrder();
        allOrders.addAll(creditCardOrders);
        return allOrders;
    }

    /**
     * 匹配需要奖励的信用卡订单
     * @return
     */
    private List<OrderMatchBean> matchCreditCardOrder() {
        List<OrderMatchBean> all = new ArrayList<>();
        List<OrderMatchBean> pufa = businessOrderMapper.matchPUFA();
        all.addAll(pufa);
        List<OrderMatchBean> jiaotong = businessOrderMapper.matchJIAOTONG();
        all.addAll(jiaotong);
        List<OrderMatchBean> pingan = businessOrderMapper.matchPINGAN();
        all.addAll(pingan);
        return all;
    }
}
