package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.results.OrderMatchBean;
import com.pay.national.agent.model.entity.BusinessOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessOrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BusinessOrder record);

    int insertSelective(BusinessOrder record);

    BusinessOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessOrder record);

    int updateByPrimaryKey(BusinessOrder record);

    BusinessOrder selectByUser(@Param("businessCode") String businessCode, @Param("customerPhone") String customerPhone);

    List<BusinessOrder> findAllOrders(@Param("userNo") String userNo, @Param("parentBusinessCode") String parentBusinessCode,
                                      @Param("page") Page<BusinessOrder> page);

    BusinessOrder selectByUserNoAndBusinessCode(@Param("userNo") String userNo, @Param("businessCode") String businessCode);

    /**
     * 查询是否存在支付成功的订单
     * @param userNo
     * @return
     */
    BusinessOrder selectUserAgentRight(String userNo);

    List<OrderMatchBean> matchPUFA();

    List<OrderMatchBean> matchJIAOTONG();

    List<OrderMatchBean> matchPINGAN();

    List<OrderMatchBean> matchXINGYE();
}