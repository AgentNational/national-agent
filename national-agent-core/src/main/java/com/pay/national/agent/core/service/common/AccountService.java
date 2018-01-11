package com.pay.national.agent.core.service.common;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.result.AccountBalanceBean;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public interface AccountService {
	/**
	 * 新建账户操作历史
	 * @param accountHistory
	 * @return
	 */
	AccountHistory createAccountHistory(AccountHistory accountHistory);
	
	/**
	 * 更新账户操作历史
	 * @param accountHistory
	 */
	void updateAccountHistory(AccountHistory accountHistory);

	/**
	 * 提现
	 * @param userNo
	 * @param withdrawAmount
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
	 * @param page 
	 * @return
	 */
	ReturnBean<Page<List<AccountHistory>>> accountHistoryList(String userNo, String businessCode, String startDate, Page<List<AccountHistory>> page);

	/**
	 * 查询提现详情
	 * @param userNo 用户编号
	 * @param requestId 付款请求单号
	 * @return
	 */
	ReturnBean<WithdrawDetailBean> findWithdrawDetail(String userNo, String requestId);
	
	/**
	 * 入账
	 * @param userNo 用户编号
	 * @param amount 入账金额
	 * @param systemFlowId 业务流水号
	 * @param businessCode 本项目业务编码
	 * @param childBusinessCode 本项目子业务编码
	 * @return
	 */
	ReturnBean<String> credit(String userNo,Double amount,String systemFlowId,BusinessCode businessCode,ChildBusinessCode childBusinessCode );
	
	
}
