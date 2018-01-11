/**
 *
 */
package com.pay.national.agent.core.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.dao.common.AppFeedbackMapper;
import com.pay.national.agent.core.service.common.AppFeedbackService;
import com.pay.national.agent.model.entity.AppFeedback;

/**
 * @ClassName:  AppFeedbackServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午2:01:54
 *
 */
@Service("appFeedbackService")
public class AppFeedbackServiceImpl implements AppFeedbackService {

	@Resource
	private AppFeedbackMapper appFeedbackMapper;

	@Override
	public void insert(AppFeedback feedback) {
		appFeedbackMapper.insert(feedback);
	}

	@Override
	public void modify(AppFeedback feedback) {

	}

	@Override
	public AppFeedback findAppFeedbackById(String id) {
		return null;
	}

	@Override
	public List<AppFeedback> findAppFeedbackByPhone(String phone) {
		return appFeedbackMapper.findAppFeedbackByPhone(phone);
	}


	@Override
	public List<AppFeedback> queryByParams(Map<String, String> params) {
		return null;
	}


}
