package com.pay.national.agent.portal.service.credit;

import java.util.List;

import com.pay.national.agent.common.bean.CreditCardUserModel;

public interface CreditCardUserService {

	/**
	 * 导入信用卡用户信息
	 * @param list
	 * @param creditCardType
	 * @return
	 */
	void importUser(List<CreditCardUserModel> list, String creditCardType);

}
