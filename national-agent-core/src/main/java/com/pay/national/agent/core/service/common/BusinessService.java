package com.pay.national.agent.core.service.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.results.OrderMatchBean;
import com.pay.national.agent.model.entity.BusinessOrder;

import java.util.List;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/23
 */
public interface BusinessService {
    /**
     * 创建订单
     * @param order
     * @return
     */
    String createOrder(BusinessOrder order);

    /**
     * 查询订单
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @param page 分页
     *
     * @return
     */
    String orders(String userNo, String parentBusinessCode, Page<BusinessOrder> page);

    /**
     *检验是否有代理权限
     * @param userNo
     * @return
     */
    String checkAgentRight(String userNo);

    /**
     * 匹配需要奖励的订单
     * @return
     */
    List<OrderMatchBean> matchRewardOrder();

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    BusinessOrder findOrder(Long orderId);

    /**
     * 更新订单
     * @param order
     */
    void updateOrder(BusinessOrder order);
}
