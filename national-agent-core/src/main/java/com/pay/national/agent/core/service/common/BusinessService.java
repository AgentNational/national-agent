package com.pay.national.agent.core.service.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.BusinessOrder;

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
}
