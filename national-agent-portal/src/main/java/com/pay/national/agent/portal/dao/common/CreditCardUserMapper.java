package com.pay.national.agent.portal.dao.common;

import com.pay.national.agent.model.entity.CreditCardUser;

/**
 * Created by shuyan.qi on 2018/3/17.
 */
public interface CreditCardUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreditCardUser record);

    int insertSelective(CreditCardUser record);

    CreditCardUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditCardUser record);

    int updateByPrimaryKey(CreditCardUser record);

}
