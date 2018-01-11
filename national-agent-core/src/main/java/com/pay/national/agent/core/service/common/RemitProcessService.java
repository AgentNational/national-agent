package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.SettleParamBean;

/**
 * 结算处理service
 * @author shuyan.qi
 * Date:2017年9月6日上午3:39:25
 */
public interface RemitProcessService {
	
	/**
	 * 结算
	 * @param settleParamBean
	 * @return
	 */
	ReturnBean<String> settle(SettleParamBean settleParamBean);
}
