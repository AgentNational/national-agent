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
import com.pay.national.agent.model.entity.AppMenu;
import com.pay.national.agent.portal.dao.common.AppMenuMapper;
import com.pay.national.agent.portal.service.common.AppMenuService;

/**
 * @Description: 一句话描述类的用法
 * @see: 需要参考的类
 * @version 2016年10月9日 下午3:14:16
 * @author zhenhui.liu
 */
@Service("appMenuService")
public class AppMenuServiceImpl implements AppMenuService {
	
	@Resource
	private AppMenuMapper appMenuMapper;
	
	@Override
	public List<AppMenu> findAllAppMenu(Page<AppMenu> page, Map<String, Object> queryParams) {
		return appMenuMapper.findAllAppMenu(page ,queryParams);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addAppMenu(AppMenu appMenu) {
		appMenuMapper.insert(appMenu);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modifyAppMenu(AppMenu appMenu) {
		appMenuMapper.updateByPrimaryKey(appMenu);
	}


	@Override
	public List<Map<String, Object>> findRole() {
		return appMenuMapper.findRole();
	}

	@Override
	public String[] findAuthorizeRole(String menuId) {
		return appMenuMapper.findAuthorizeRole(menuId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveMenuAuthorize(String roleId, String menuId) {
		//判断表中是否已经存在记录,不存在执行插入操作。
		if(appMenuMapper.checkAuthorize(roleId, menuId) == null || appMenuMapper.checkAuthorize(roleId, menuId).equals("") ){
			appMenuMapper.saveMenuAuthorize(roleId, menuId);
		}else{//已经存在，将状态有DISABLE改为ENABLE
			appMenuMapper.updateAuthorizeStatus(roleId, menuId, "ENABLE");
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteMenuAuthorize(String roleId, String menuId) {
		//判断表中是否已经存在记录,不存不执行。存在将状态改为DISABLE
		if(appMenuMapper.checkAuthorize(roleId, menuId) == null || appMenuMapper.checkAuthorize(roleId, menuId).equals("") ){
		}else{//已经存在，将状态有ENABLE改为DISABLE
			appMenuMapper.updateAuthorizeStatus(roleId, menuId, "DISABLE");
		}
		
	}

	@Override
	public AppMenu findAppMenuByMenuId(Long menuId) {
		return appMenuMapper.selectByPrimaryKey(menuId);
	}

}
