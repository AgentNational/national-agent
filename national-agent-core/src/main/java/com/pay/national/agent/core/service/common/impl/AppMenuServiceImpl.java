package com.pay.national.agent.core.service.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.AppMenuMapper;
import com.pay.national.agent.core.service.common.AppMenuService;
import com.pay.national.agent.core.service.common.AppRoleService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.AppMenuResultBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppMenu;
import com.pay.national.agent.model.entity.AppRole;
import com.pay.national.agent.model.entity.User;

/**
 * @Description: app菜单service
 * @see: 需要参考的类
 * @version 2017年9月13日 上午11:18:36
 * @author zhenhui.liu
 */
@Service("appMenuService")
public class AppMenuServiceImpl implements AppMenuService{

	/**
	 * 
	 */
	@Resource
	private AppMenuMapper appMenuMapper;
	
	/**
	 * 
	 */
	@Resource
	private AppRoleService appRoleService;
	
	@Override
	public ReturnBean<List<AppMenuResultBean>> findAllMenu(Map<String, String> params) {
		ReturnBean<List<AppMenuResultBean>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		List<AppMenuResultBean> lists = new ArrayList<>();
		List<AppMenu> appMenus = new ArrayList<>();
		String loginKey = params.get("loginKey");
		User user = null ;
		//若loginKey不为空
		if(StringUtils.isNotBlank(loginKey)){
			user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
		}
		List<AppRole> list = new ArrayList<>();
		if(user == null){
			//用户未登录 游客角色
			list = appRoleService.findRolesByRoleType(com.pay.national.agent.model.enums.AppRole.TOURISTS);
		}else{
			list = user.getAppRoles();
		}
		if(list == null || list.size()==0){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("未找到用户角色信息！");
			return returnBean;
		}else{
			//查询角色所包含的所有菜单
			appMenus = appMenuMapper.findMenuByRole(list);
			for (AppMenu appMenu : appMenus) {//梳理菜单的父子节点关系
				if(appMenu.getMenuLevel().equals("1")){//一级菜单
					AppMenuResultBean appMenuResultBean = new AppMenuResultBean();
					BeanUtils.copyProperties(appMenu, appMenuResultBean);
					List<AppMenu> menuResults = new ArrayList<>();
					for (AppMenu appMenu2 : appMenus) {//遍历一级菜单下所有的二级菜单
						if(appMenu2.getParentMenuId() != null&& appMenu2.getParentMenuId().equals(appMenu.getId())){
							menuResults.add(appMenu2);
						}
					}
					//将所有二级菜单封装进结果bean
					appMenuResultBean.setAppMenus(menuResults);
					lists.add(appMenuResultBean);
				}
				
			}
			returnBean.setData(lists);
			
		}
		return returnBean;
	}

}
