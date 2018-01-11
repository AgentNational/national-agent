
package com.pay.national.agent.core.dao.common;

import java.util.List;

import com.pay.national.agent.model.entity.AppFeedback;


/**
 * @ClassName:  AppFeedbackMapper
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午1:57:17
 *
 */
public interface AppFeedbackMapper {
	/**
	 * @Description  保存反馈消息
	 * @param appUserAsset
	 * @see 需要参考的类或方法
	 */
	public void insert(AppFeedback feedback);

	/**
	 * @Description 一句话描述方法的用法
	 * @param phone
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<AppFeedback> findAppFeedbackByPhone(String phone);

}
