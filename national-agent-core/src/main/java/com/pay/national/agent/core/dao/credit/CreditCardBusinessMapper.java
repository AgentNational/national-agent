package com.pay.national.agent.core.dao.credit;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.CreditCardBusiness;

public interface CreditCardBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditCardBusiness record);

    int insertSelective(CreditCardBusiness record);

    CreditCardBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditCardBusiness record);

    int updateByPrimaryKey(CreditCardBusiness record);

	List<CreditCardBusiness> businessList();

	CreditCardBusiness findByCode(@Param("businessCode")String businessCode);
}