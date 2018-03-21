package com.pay.national.agent.core.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.PropertiesLoader;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.BusinessOrderMapper;
import com.pay.national.agent.core.dao.common.BusinessRewardRuleMapper;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.OrderMatchBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.BusinessOrder;
import com.pay.national.agent.model.entity.BusinessRewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
            BusinessOrder dbOrder;
            if(order.getBusinessCode().equals(BusinessCode.AGENT_FEE.name())){
                //如果是代理费相关的业务通过用户编号和业务编码查询
                dbOrder = businessOrderMapper.selectByUserNoAndBusinessCode(order.getUserNo(),order.getBusinessCode());
            }else{
                //
                dbOrder = businessOrderMapper.selectByUser(order.getBusinessCode(),order.getCustomerPhone());
            }

            if(dbOrder == null || StatusConstants.DELETE.equals(dbOrder.getStatus())){
               initOrder(order);
               Map<String,Object> returnMap = new HashMap<String,Object>(2);
               returnMap.put("orderId",order.getId());
               returnMap.put("jumpUrl",selectBusJumpUrl(order));
               returnBean.setData(returnMap);
            }else{
                if(order.getBusinessCode().equals(BusinessCode.AGENT_FEE.name())){
                    Map<String,Object> returnMap = new HashMap<String,Object>(2);
                    returnMap.put("orderId",dbOrder.getId());
                    returnBean.setData(returnMap);
                }else{
                    returnBean.setCode(RetCodeConstants.FAIL);
                    returnBean.setMsg(CUSTOMER_IS_REPEAT);
                }
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
    public void initOrder(BusinessOrder order) {
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
            case AGENT_FEE:
                order.setParentBusinessCode(ParentBusinessCode.NATIONAL_AGENT.name());
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

        if(order.getBusinessCode().equals(BusinessCode.AGENT_FEE.name())){
            if(StringUtils.isBlank(order.getBusinessCode()) || StringUtils.isBlank(order.getUserNo())){
                throw new NationalAgentException(RetCodeConstants.FAIL,ORDER_INCOMPLETE_MSG);
            }
        }else{
            if(StringUtils.isBlank(order.getBusinessCode()) || StringUtils.isBlank(order.getCustomerName()) ||
                    StringUtils.isBlank(order.getCustomerPhone()) || StringUtils.isBlank(order.getUserNo())){
                throw new NationalAgentException(RetCodeConstants.FAIL,ORDER_INCOMPLETE_MSG);
            }
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

    @Override
    public String checkAgentRight(String userNo) {
        ReturnBean<Map<String,String>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        Map<String,String> map = new HashMap<>();
        BusinessOrder businessOrder = businessOrderMapper.selectUserAgentRight(userNo);
        if(businessOrder != null){//支付成功
            map.put("STATUS","TRUE");
            returnBean.setData(map);
        }else{
            map.put("STATUS","FALSE");
            returnBean.setData(map);
        }
        LogUtil.info("校验是否有代理权限 userNo:{},returnBean:{}",userNo,JSON.toJSONString(returnBean));
        return JSON.toJSONString(returnBean);
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
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public BusinessOrder findOrder(Long orderId) {
        return businessOrderMapper.selectByPrimaryKey(orderId);
    }

    /**
     * 更新订单
     * @param order
     */
    @Override
    public void updateOrder(BusinessOrder order) {
        businessOrderMapper.updateByPrimaryKey(order);
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
        List<OrderMatchBean> xingye = businessOrderMapper.matchXINGYE();
        all.addAll(xingye);
        return all;
    }


}
