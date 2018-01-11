package com.pay.national.agent.core.facade;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.entity.CreditCardOrder;

public interface CreditCardFacade {

	/**
	 * 信用卡业务列表
	 * @return
	 */
	ReturnBean<CreditCardBusinessListBean> businessList(String userNo);

	/**
	 * 办理信用卡业务
	 * @param userNo
	 * @param businessCode
	 * @param customerName
	 * @param customerPhone
	 * @return
	 */
	ReturnBean<String> transactBusiness(String userNo, String businessCode, String customerName, String customerPhone);

	/**
	 * 订单查询
	 * @param query
	 * @param page
	 * @return
	 */
	ReturnBean<Page<List<CreditCardOrder>>> queryOrders(CreditOrderQueryBean query, Page<List<CreditCardOrder>> page);

}
