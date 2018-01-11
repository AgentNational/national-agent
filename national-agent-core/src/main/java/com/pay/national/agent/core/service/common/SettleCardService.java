package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.beans.query.SettleAccountInfoBean;
import com.pay.national.agent.model.entity.SettleCard;

public interface SettleCardService {

	/**
	 * @Description 三要素校验
	 * @param recommenderNo
	 * @param bankAccountNo
	 * @param certNo
	 * @param name
	 * @param mobileNum
	 * @return
	 * @see 需要参考的类或方法
	 */
	String realNameAuth(String recommenderNo, String bankAccountNo, String certNo, String name, String mobileNum);

	/**
	 * @Description 用户创建结算卡和结算规则
	 * @param settleAccountInfoParam
	 * @see 需要参考的类或方法
	 */
	void createSettleAndRule(SettleAccountInfoBean settleAccountInfoParam);

	/**
	 * @Description 创建结算卡信息
	 * @param settleCard
	 * @see 需要参考的类或方法
	 */
	void insert(SettleCard settleCard);

	/**
	 * @Description 通过所属人查找结算卡
	 * @param userNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	SettleCard findByownerId(String userNo);

	/**
	 * @Description 修改结算信息
	 * @param settleCard
	 * @see 需要参考的类或方法
	 */
	void update(SettleCard settleCard);

	/**
	 * @Description 开户
	 * @param open
	 * @see 需要参考的类或方法
	 */
	void openAccount(Object open);
}
