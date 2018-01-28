package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.AccountHistory;

/**
 * @author shuyan.qi
 */
public interface AccountHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountHistory record);

    int insertSelective(AccountHistory record);

    AccountHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountHistory record);

    int updateByPrimaryKey(AccountHistory record);
}