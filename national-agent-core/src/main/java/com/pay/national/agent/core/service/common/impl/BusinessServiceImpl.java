package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.BeanUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.BusinessOrderMapper;
import com.pay.national.agent.core.dao.common.BusinessRewardRuleMapper;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.AgentBusiness;
import com.pay.national.agent.model.entity.BusinessExpantion;
import com.pay.national.agent.model.entity.BusinessOrder;
import com.pay.national.agent.model.entity.BusinessRewardRule;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.RewardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.*;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/23
 */
@Service
public class BusinessServiceImpl implements BusinessService{
    private static final String BUSINESS_LIST_ERR = "查询代理业务异常";
    private static final String ORDER_NULL_MSG = "不允许提交空订单";
    private static final String ORDER_INCOMPLETE_MSG = "订单缺失必要参数";
    private static final String CUSTOMER_IS_REPEAT = "您已推荐过该用户";
    private static final String BUSINESS_DISABLE_MSG = "该业务不存在或已下架";
    @Autowired
    private BusinessOrderMapper businessOrderMapper;
    @Autowired
    private BusinessRewardRuleMapper businessRewardRuleMapper;

    /**
     * 代理业务列表
     * @param parentCode 父业务编码
     * @return
     */
    /*@Override
    public String businessList(String parentCode){
        ReturnBean<List<Map<String,Object>>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            List<AgentBusiness> businesses = agentBusinessMapper.selectByBusiness(parentCode);
            if(businesses != null && businesses.size() > 0){
                List<Map<String,Object>> list = new ArrayList<>();
                for (AgentBusiness business:businesses) {
                    Map<String,Object> businessMap = new HashMap<>();
                    BeanUtils.applyIf(businessMap,business);
                    List<BusinessExpantion> expantions = businessExpantionMapper.selectByBusiness(business.getChildBusinessCode());
                    if(expantions != null && expantions.size() > 0){
                        for (BusinessExpantion expantion:expantions) {
                            businessMap.put(expantion.getColumnCode(),expantion.getColumnValue());
                        }
                    }
                    list.add(businessMap);
                }
                returnBean.setData(list);
            }
        } catch (Exception e) {
            LogUtil.error("获取代理业务列表异常 parentCode={},e={}",parentCode,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(BUSINESS_LIST_ERR);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }*/

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    public String createOrder(BusinessOrder order) {
        ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        checkOrder(order);
       BusinessOrder dbOrder =  businessOrderMapper.selectByUser(order.getBusinessCode(),order.getCustomerPhone());
       if(dbOrder == null || StatusConstants.DELETE.equals(dbOrder.getStatus())){
          initOrder(order);
       }else{
           returnBean.setCode(RetCodeConstants.FAIL);
           returnBean.setMsg(CUSTOMER_IS_REPEAT);
       }
        return JSONUtils.alibabaJsonString(returnBean);
    }

    /**
     * 创建订单
     * @param order
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void initOrder(BusinessOrder order) {
        BusinessCode businessCode = BusinessCode.valueOf(order.getBusinessCode());
        switch (businessCode){
            case XINGYE:
            case PUFA:
            case PINGAN:
                Double rewardAmount = calculateRewardAmount(order);
                order.setRewardAmount(rewardAmount);
                order.setStatus("INIT");
                order.setCreateTime(new Date());
                break;
            default:
                break;
        }
        businessOrderMapper.insert(order);
    }


    /**
     * 计算奖励金额
     * @param order 订单
     * @return
     */
    private Double calculateRewardAmount(BusinessOrder order) {
        Double rewardAmount = null;
        BusinessRewardRule rule = businessRewardRuleMapper.selectByBusiness(order.getBusinessCode());
        if(rule == null || !StatusConstants.ENABLE.equals(rule.getStatus())){
            throw new NationalAgentException(RetCodeConstants.FAIL,BUSINESS_DISABLE_MSG);
        }
        RewardType rewardType = RewardType.valueOf(rule.getRewardType());
        switch (rewardType){
            case AMOUNT:
                rewardAmount = rule.getRewardAmount();
                break;
            case PROPORTION:
                break;
            default:
                break;
        }
        return rewardAmount;
    }

    /**
     * 检查订单的正确性
     * @param order 订单
     */
    private void checkOrder(BusinessOrder order) {
        if(order == null){
            throw new NationalAgentException(RetCodeConstants.FAIL,ORDER_NULL_MSG);
        }

        if(StringUtils.isBlank(order.getBusinessCode()) || StringUtils.isBlank(order.getParentBusinessCode()) ||
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
}
