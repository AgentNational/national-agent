package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.entity.RemitLog;
import com.pay.national.agent.model.entity.RemitPayment;
import com.pay.national.agent.model.entity.RemitResult;

public interface RemitPaymentService {

	/**
	 * 创建付款单
	 * @param remitPayment
	 * @return
	 */
	RemitPayment createRemitPayment(RemitPayment remitPayment);

	/**
	 * 更新付款单
	 * @param remitPayment
	 */
	void updateRemitPayment(RemitPayment remitPayment);

	/**
	 * 创建付款结果
	 * @param result
	 */
	RemitResult createRecommenderRemitResult(RemitResult result);

	/**
	 * 查询结算单
	 * @param requestId
	 * @return
	 */
	RemitPayment findRemitPaymentByRequestId(String requestId);

	/**
	 * 创建付款日志
	 * @param remitLog
	 * @return
	 */
	RemitLog createRemitLog(RemitLog remitLog);

	/**
	 * 更新付款日志
	 * @param remitLog
	 */
	void updateRemitLog(RemitLog remitLog);

}
