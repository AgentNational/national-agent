package com.pay.national.agent.core.facade;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.beans.results.PosBusinessListBean;
import com.pay.national.agent.model.entity.PosOrder;

/**
 * @Description: pos相关dubbo接口
 * @see: 需要参考的类
 * @version 2017年10月10日 下午3:43:58
 * @author zhenhui.liu
 */
public interface PosFacade {

	/**
	 * @Description pos业务列表查询
	 * @param userNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	ReturnBean<PosBusinessListBean> businessList(String userNo);

	/**
	 * @Description 卡友手刷办理
	 * @param posBusinessParamBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String businessProcessing(PosBusinessParamBean posBusinessParamBean);

	/**
	 * @Description 分页查询所有订单
	 * @param page
	 * @param posOrderQueryBean
	 * @return
	 * @see 需要参考的类或方法
	 */
	String queryOrders(Page<List<PosOrder>> page, PosOrderQueryBean posOrderQueryBean);

	/**
	 * @Description 发送领取pos验证码
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	String sendCheckCode(Map<String, String> params);

}
