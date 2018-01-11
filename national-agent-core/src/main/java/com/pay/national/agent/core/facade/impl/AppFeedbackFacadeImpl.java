package com.pay.national.agent.core.facade.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppFeedbackFacade;
import com.pay.national.agent.core.service.common.AppFeedbackService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppFeedback;

/**
 * @ClassName:  AppFeedbackFacadeImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午2:12:02
 *
 */
@Component("appFeedbackFacade")
public class AppFeedbackFacadeImpl implements AppFeedbackFacade{

	@Resource
	private AppFeedbackService appFeedbackService;

	/*@Resource
	private OperatorService operatorService;*/

	@Override
	public String saveFeedback(Map<String, String> params) {

		String userName = params.get("userName");
		try {
			ReturnBean<Map<String, Object>> returnBean = new ReturnBean<Map<String, Object>>();
			appFeedbackService.insert(convertAppFeedback(params));
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
			LogUtil.error("method saveFeedback data={}", JSONUtils.toJsonString(returnBean));
			return JSONUtils.alibabaJsonString(returnBean);
		} catch(Exception e) {
			LogUtil.error("保存意见反馈异常, userName = {}", userName);
			ReturnBean<Map<String, Object>> returnBean = new ReturnBean<Map<String, Object>>();
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setMsg(RetCodeConstants.ERROR_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}

	/**
	 * 将Map转换为AppFeedback对象
	 * @Description 一句话描述方法的用法
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	private AppFeedback convertAppFeedback(Map<String, String> params) {
		LogUtil.info("意见反馈 params={}",params);
		String userName = params.get("userName");
		AppFeedback feedback = new AppFeedback();
		feedback.setId(CommonCodeUtil.getPkId());
		feedback.setPhone(userName);
		// TODO operatorService 暂无
		/*通过operatorService接口查找用户ID*/
		/*Operator operator = operatorService.findOperatorByName(userName);
		if(null != operator)
		{
			feedback.setUserId(operator.getId());
		}*/
		feedback.setUserId("1");//测试数据
		feedback.setClientType(params.get("clientType"));
		feedback.setStatus("INIT");
		feedback.setType("");
		feedback.setContent(params.get("content"));
		feedback.setIsRead("N");
		feedback.setOperator("");
		feedback.setCreateTime(new Date());
		LogUtil.info("意见反馈 feedback={}",feedback.toString());
		return feedback;
	}


}
