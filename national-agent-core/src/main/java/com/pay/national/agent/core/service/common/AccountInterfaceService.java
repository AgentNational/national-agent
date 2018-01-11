package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.beans.query.CreditParamBean;
import com.pay.national.agent.model.beans.results.UserAccountBean;

/**
 * 封装账户接口
 * @author shuyan.qi
 * Date:2017年9月6日上午4:16:47
 */
public interface AccountInterfaceService {

	/**
	 * 查询账户信息
	 * @param userNo 用户编号
	 */
	UserAccountBean findAccount(String userNo);

	/**
	 * 入账
	 * @param userNo
	 * @param amount
	 * @param businessCode
	 * @return
	 */
	void credit(CreditParamBean creditParamBean);

}
