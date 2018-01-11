package com.pay.national.agent.core.facade.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppMenuFacade;
import com.pay.national.agent.core.service.common.AppMenuService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.AppMenuResultBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: app菜单相关
 * @see: 需要参考的类
 * @version 2017年9月13日 上午11:06:00
 * @author zhenhui.liu
 */
@Service("appMenuFacade")
public class AppMenuFacadeImpl implements AppMenuFacade{

	@Resource
	private AppMenuService appMenuService;
	/**
	 * 查找所有菜单
	 */
	@Override
	public String findAllMenu(Map<String, String> params) {
		LogUtil.info("查找所有菜单 start params:{}",params);
		ReturnBean<List<AppMenuResultBean>> returnBean = new ReturnBean<>();
		try {
			returnBean = appMenuService.findAllMenu(params);
		} catch (Exception e) {
			LogUtil.info("查找所有菜单 error params:{}",params,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("查找菜单失败！");
		}
		LogUtil.info("查找所有菜单 end params:{},resutl:{}",params,JSONUtils.alibabaJsonString(returnBean));
		return JSONUtils.alibabaJsonString(returnBean);
	}

	
}
