package com.pay.national.agent.core.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AccountHistory;

public interface AccountHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountHistory record);

    AccountHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AccountHistory record);
    
    List<AccountHistory> findAllHistory(@Param("userNo")String userNo,@Param("businessCode") String businessCode, 
			@Param("startDate") String startDate,@Param("page") Page<List<AccountHistory>> page);
}