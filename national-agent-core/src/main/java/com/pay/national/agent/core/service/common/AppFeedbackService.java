
package com.pay.national.agent.core.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.model.entity.AppFeedback;

/**
 * @ClassName:  AppFeedbackService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午2:00:41
 *
 */
public interface AppFeedbackService {
	/**
	 * 插入数据
	 * @param feedback
	 */
	void insert(AppFeedback feedback);

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
	 *
	 */
	List<AppFeedback> findAppFeedbackByPhone(String phone);
	/**
	 * 根据条件，查询反馈意见列表
	 * @param params
	 * @return
	 */
	List<AppFeedback> queryByParams(Map<String, String> params);

}
