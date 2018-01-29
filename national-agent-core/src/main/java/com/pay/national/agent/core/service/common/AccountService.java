package com.pay.national.agent.core.service.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.AccountHistory;

/**
 * Created by shuyan.qi on 2018/1/28.
 */
public interface AccountService {
    /**
     * 账户信息
     * @param userNo 用户编号
     * @return
     */
    Account findByUser(String userNo);

    /**
     * 账户历史记录
     * @param userNo 用户编号
     * @param page 分页
     * @return
     */
    String accHistories(String userNo, Page<AccountHistory> page);
}
