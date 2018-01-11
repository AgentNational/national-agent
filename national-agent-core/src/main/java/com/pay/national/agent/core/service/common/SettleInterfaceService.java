package com.pay.national.agent.core.service.common;

import java.util.List;

import com.pay.national.agent.model.beans.results.UserSettleCardBean;
import com.pay.national.agent.model.beans.results.UserSettleRuleBean;

public interface SettleInterfaceService {

	/**
	 * 查询结算规则
	 * @param userNo 用户编号
	 * @param accountNo 账户编号
	 * @return
	 */
	UserSettleRuleBean findSettleRule(String userNo, String accountNo);

	/**
	 * 查询结算卡列表
	 * @param userNo 
	 * @param accountNo
	 * @return
	 */
	List<UserSettleCardBean> findSettleCards(String userNo, String accountNo);

}
