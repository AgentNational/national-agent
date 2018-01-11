package com.pay.national.agent.core.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.AppMenuResultBean;

public interface AppMenuService {

	/**
	 * @Description 查找所有菜单
	 * @param params
	 * @return
	 * @see 需要参考的类或方法
	 */
	ReturnBean<List<AppMenuResultBean>> findAllMenu(Map<String, String> params);

}
