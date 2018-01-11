/**
 *
 */
package com.pay.national.agent.core.facade;

import java.util.Map;


/**
 * @ClassName:  AppFeedbackFacade
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午2:10:40
 *
 */
public interface AppFeedbackFacade {
	/**
	 * 保存反馈消息
	 * @Description 一句话描述方法的用法
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String saveFeedback(Map<String , String> params);

}
