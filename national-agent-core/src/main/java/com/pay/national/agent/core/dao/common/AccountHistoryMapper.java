package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AccountHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<AccountHistory> findAllHistory(@Param("userNo") String userNo,@Param("parentBusinessCode") String parentBusinessCode, @Param("page") Page<AccountHistory> page);

    List<AccountHistory> selectByUser(@Param("userNo") String userNo, @Param("businessCode") String businessCode);
}