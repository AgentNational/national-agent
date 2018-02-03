package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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

    Account findByuser(@RequestParam("userNo") String userNo);

    Account find(@Param("accountNo") String accountNo);

    /**
     * 操作账户金额的方法，谨慎使用
     * @param account
     * @return
     */
    int amountOnLock(@Param("account") Account account);
}