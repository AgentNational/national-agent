package com.pay.national.agent.core.service.credit;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.model.beans.CreditCardOrderMatchBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.entity.CreditCardUser;
import com.pay.national.agent.model.enums.ChildBusinessCode;

public interface CreditCardService {

	/**
	 * 信用卡业务列表
	 * @return
	 */
	ReturnBean<CreditCardBusinessListBean> businessList(String userNo);

	/**
	 * 创建信用卡订单
	 * @param userNo
	 * @param businessCode
	 * @param customerName
	 * @param customerPhone
	 * @return
	 */
	ReturnBean<String> createOrder(String userNo, ChildBusinessCode businessCode, String customerName, String customerPhone);
	
	/**
	 * 更新信用卡订单
	 * @param order
	 */
	void updateOrder(CreditCardOrder order);

	/**
	 * 信用卡订单查询
	 * @param query
	 * @param page
	 * @return
	 */
	ReturnBean<Page<List<CreditCardOrder>>> queryOrders(CreditOrderQueryBean query, Page<List<CreditCardOrder>> page);

	/**
	 * 合作方回导的“脱敏”信用卡用户与信用卡订单匹对
	 * @param cardType 
	 * @param customerPhoneNo
	 * @param customerName
	 * @param status
	 * @return
	 */
	List<CreditCardOrderMatchBean> matchOrder();

	/**
	 * 查询信用卡用户信息
	 * @param creditCardUserId
	 * @return
	 */
	CreditCardUser findCreditCardUser(Integer creditCardUserId);

	/**
	 * 更新信用卡用户信息
	 * @param creditCardUser
	 */
	void updateCreditCardUser(CreditCardUser creditCardUser);

	

}
