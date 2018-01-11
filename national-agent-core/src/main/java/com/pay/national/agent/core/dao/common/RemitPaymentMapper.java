package com.pay.national.agent.core.dao.common;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.RemitPayment;

public interface RemitPaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RemitPayment record);

    RemitPayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RemitPayment record);

	RemitPayment findByRequestId(@Param("requestId")String requestId);
}