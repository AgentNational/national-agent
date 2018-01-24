package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.common.persistence.Page;
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
}