package com.pay.national.agent.core.dao.credit;

import com.pay.national.agent.model.entity.CreditCardUser;

public interface CreditCardUserMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(CreditCardUser record);

    CreditCardUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(CreditCardUser record);
}
