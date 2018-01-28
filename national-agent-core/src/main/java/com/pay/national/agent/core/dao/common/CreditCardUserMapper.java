package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.CreditCardUser;

public interface CreditCardUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreditCardUser record);

    int insertSelective(CreditCardUser record);

    CreditCardUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditCardUser record);

    int updateByPrimaryKey(CreditCardUser record);
}