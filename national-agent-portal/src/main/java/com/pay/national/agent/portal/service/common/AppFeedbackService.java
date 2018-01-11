/**
 *
 */
package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppFeedback;

/**
 * @ClassName:  AppFeedbackService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午4:33:10
 *
 */
public interface AppFeedbackService {
	/**
	 * 编辑反馈意见
	 * @param feedback
	 */
	void modify(AppFeedback feedback);

	/**
	 * 获取反馈意见
	 * @param id
	 * @return
	 */
	AppFeedback findAppFeedbackById(String id);

	/**
	 * 根据条件，查询反馈意见列表
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String,Object>> findAllFeedback(Page<Map<String,Object>> page,Map<String, Object> params);
}
