package com.pay.national.agent.core.service.common;

import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.model.entity.RemitPayment;

public interface RemitInterfaceService {

	/**
	 * 付款
	 * @param remitPayment
	 */
	void remit(RemitPayment remitPayment);
	
	/**
	 * 查询提现进度
	 * @param requestId
	 * @return
	 */
	WithdrawDetailBean findWithdrawDetail(String requestId);

}
