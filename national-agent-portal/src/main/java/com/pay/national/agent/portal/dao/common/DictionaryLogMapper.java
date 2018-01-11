
package com.pay.national.agent.portal.dao.common;

import com.pay.national.agent.model.entity.DictionaryLog;


/**
 * @ClassName:  DictionaryLogMapper
 * @Description:字典日志
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午10:17:31
 *
 */
public interface DictionaryLogMapper {
	void insert(DictionaryLog dictionaryLog);

	void update(DictionaryLog dictionaryLog);
}
