package com.pay.national.agent.core.service.pos;

import java.util.List;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.entity.PosBusiness;
import com.pay.national.agent.model.entity.PosOrder;

public interface PosService {

	/**
	 * @Description 查询所有的pos相关业务
	 * @param userNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<PosBusiness> businessList(String userNo);

	/**
	 * @Description 创建pos订单
	 * @param posOrder
	 * @see 需要参考的类或方法
	 */
	void createPosOrder(PosOrder posOrder);

	/**
	 * @Description 分页查询所有的订单
	 * @param page
	 * @param posOrderQueryBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<PosOrder> queryOrders(Page<List<PosOrder>> page, PosOrderQueryBean posOrderQueryBean);
	
	List<PosOrder> findTrueOrders();

	/**
	 * 更新pos业务订单
	 * @param posOrder
	 */
	void updatePosOrder(PosOrder posOrder);

	/**
	 * @Description 办理卡友pos校验
	 * @param posBusinessParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	ReturnBean<Object> checkBusinessProcessing(PosBusinessParamBean posBusinessParamBean);

	/**
	 * @Description 通过手机号和业务编码获取订单信息
	 * @param businessCode
	 * @param phoneNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<PosOrder> findOrderByPhoneNoAndBusinessCode(String businessCode, String phoneNo) ;


	/**
	 * @Description 更新订单状态
	 * @param posOrder
	 * @see 需要参考的类或方法
	 */
	void updatePosOrderBySalesBillNo(PosOrder posOrder);
}
