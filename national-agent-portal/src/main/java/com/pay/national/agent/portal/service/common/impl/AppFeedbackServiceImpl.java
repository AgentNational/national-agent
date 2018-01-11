/**
 *
 */
package com.pay.national.agent.portal.service.common.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppFeedback;
import com.pay.national.agent.portal.dao.common.AppFeedbackMapper;
import com.pay.national.agent.portal.service.common.AppFeedbackService;

/**
 * @ClassName:  AppFeedbackServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午4:33:59
 *
 */
@Service("appFeedbackService")
public class AppFeedbackServiceImpl implements AppFeedbackService {

	@Resource
	private AppFeedbackMapper appFeedbackMapper;


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modify(AppFeedback feedback) {
		appFeedbackMapper.modify(feedback);//保存更新状态

	}

	/* (non-Javadoc)
	 * @see com.pay.pluton.portal.service.AppFeedbackService#findAppFeedbackById(java.lang.String)
	 */
	@Override
	public AppFeedback findAppFeedbackById(String id) {
		return appFeedbackMapper.findAppFeedbackById(id);
	}

	/* (non-Javadoc)
	 * @see com.pay.pluton.portal.service.AppFeedbackService#findAllFeedback(com.pay.commons.utils.Page, java.util.Map)
	 */
	@Override
	public List<Map<String,Object>> findAllFeedback(Page<Map<String,Object>> page, Map<String, Object> params) {
		return appFeedbackMapper.findAllFeedback(page, params);
	}

}
