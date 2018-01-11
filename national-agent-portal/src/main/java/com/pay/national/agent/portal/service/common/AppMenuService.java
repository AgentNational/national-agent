/**
 * 
 */
package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppMenu;

/**
 * @Description: app菜单service接口
 * @see: 需要参考的类
 * @version 2016年10月9日 下午2:51:53
 * @author zhenhui.liu
 */
public interface AppMenuService {
	/**
	 * @Description 查询所以的菜单信息（分页）
	 * @param page
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<AppMenu> findAllAppMenu(Page<AppMenu> page, Map<String , Object> queryParams);
	
	/**
	 * @Description 添加AppMenu
	 * @param appMenu
	 * @see 需要参考的类或方法
	 */
	public void addAppMenu(AppMenu appMenu);
	
	/**
	 * @Description 修改AppMenu
	 * @param appMenu
	 * @see 需要参考的类或方法
	 */
	public void modifyAppMenu(AppMenu appMenu);


	/**
	 * @Description 查询所有角色信息
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<Map<String, Object>> findRole();

	/**
	 * @Description 查询当前菜单已经赋予的角色Id
	 * @param menuId
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String[] findAuthorizeRole(String menuId);

	/**
	 * @Description 保存菜单授权
	 * @param roleId
	 * @param menuId
	 * @see 需要参考的类或方法
	 */
	public void saveMenuAuthorize(String roleId, String menuId);

	/**
	 * @Description 通过Id查找app菜单
	 * @param menuId
	 * @return
	 * @see 需要参考的类或方法
	 */
	public AppMenu findAppMenuByMenuId(Long menuId);

	/**
	 * @Description 删除角色授权
	 * @param roleId
	 * @param menuId
	 * @see 需要参考的类或方法
	 */
	void deleteMenuAuthorize(String roleId, String menuId);
	
}
