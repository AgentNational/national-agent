package com.pay.national.agent.core.service.common;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.query.DepositParam;
import com.pay.national.agent.model.beans.query.RemitParam;
import com.pay.national.agent.model.beans.results.DepositBean;
import com.pay.national.agent.model.beans.results.RemitBean;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.AccountHistory;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
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
     * @param parentBusinessCode 业务编码
     * @param month 月份 格式 yyyy-MM-dd
     * @param page 分页
     * @return
     */
    String accHistories(String userNo,String parentBusinessCode,String month, Page<AccountHistory> page);

    /**
     * 入账
     * @param param
     * @return
     */
    DepositBean deposit(DepositParam param);

    /**
     * 出款
     * @param param
     * @return
     */
    RemitBean remit(RemitParam param);

    /**
     * 账户余额的加操作
     * @param accountNo
     * @param amount
     */
    void addAmount(String accountNo,double amount);

    /**
     * 账户余额的减操作
     * @param accountNo
     * @param amount
     */
    void subAmount(String accountNo,double amount);

    /**
     * 开户
     * @param userNo 用户编号
     */
    void openAccount(String userNo);

    /**
     * 查询提现记录
     * @param billNo
     * @return
     */
    AccountHistory findRemitHistory(String billNo);
}
