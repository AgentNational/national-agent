package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.Account;

/**
 * @author shuyan.qi
 */
public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}