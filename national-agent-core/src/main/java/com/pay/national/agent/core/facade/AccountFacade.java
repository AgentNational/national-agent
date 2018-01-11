package com.pay.national.agent.core.facade;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.result.AccountBalanceBean;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AccountHistory;

public interface AccountFacade {

	/**
	 * 提现操作
	 * @param userNo 用户编号
	 * @param withdrawAmount 提现金额
	 * @return
	 */
	ReturnBean<String> withdrawDeposit(String userNo, Double withdrawAmount);

	/**
	 * 查询账户信息
	 * @param userNo 用户编号
	 * @return
	 */
	ReturnBean<AccountBalanceBean> accountBalance(String userNo);

	/**
	 * 查询账户操作记录列表
	 * @param userNo 用户编号
	 * @param businessCode 业务编码
	 * @param startDate 查询开始日期 yyyy-MM-dd
	 * @return
	 */
	ReturnBean<Page<List<AccountHistory>>> accountHistoryList(String userNo, String businessCode, String startDate,Page<List<AccountHistory>> page);

	/**
	 * 查询提现详情
	 * @param userNo 用户编号
	 * @param requestId 付款请求单号
	 * @return
	 */
	ReturnBean<WithdrawDetailBean> findWithdrawDetail(String userNo, String requestId);

	/**
	 * 测试账户入账
	 * @param userNo 
	 * @param amount
	 * @return
	 */
	ReturnBean<String> credit(String userNo, double amount);

}
