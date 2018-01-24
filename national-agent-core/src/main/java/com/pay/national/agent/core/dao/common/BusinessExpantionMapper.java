package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.BusinessExpantion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessExpantionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessExpantion record);

    int insertSelective(BusinessExpantion record);

    BusinessExpantion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessExpantion record);

    int updateByPrimaryKey(BusinessExpantion record);

    List<BusinessExpantion> selectByBusiness(@Param("businessCode") String businessCode);
}