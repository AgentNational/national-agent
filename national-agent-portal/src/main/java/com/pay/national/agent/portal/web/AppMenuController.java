/**
 * 
 */
package com.pay.national.agent.portal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.AppMenu;
import com.pay.national.agent.portal.service.common.AppMenuService;
/**
 * @Description: app菜单controller
 * @see: 需要参考的类
 * @version 2016年10月9日 上午11:26:37
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/appMenu")
public class AppMenuController {

	private Logger logger = LoggerFactory.getLogger(AppMenuController.class);
	
	
	@Resource 
	private AppMenuService appMenuService;
	/**
	 * @Description app菜单查询跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppMenuQuery.action")
	public ModelAndView toAppMenuQuery(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/appMenu/menuQuery");
		return model;
	}
	/**
	 * @Description app菜单查询（分页）
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appMenuQuery.action")
	public ModelAndView appMenuQuery(@RequestParam Map<String, Object> queryParams){
		logger.info("appMenuQuery params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		int currentPage = queryParams.get("currentPage") == null ? 1 : Integer.parseInt(queryParams.get("currentPage").toString());
		Page<AppMenu> page = new Page<AppMenu>();
		page.setCurrentPage(currentPage);
		// 分页查询
		List<AppMenu> appMenuList = new ArrayList<>();
		try {
			appMenuList = this.appMenuService.findAllAppMenu(page, queryParams);
		} catch (Exception e) {
			LogUtil.error(" 异常 error",e);
		}
		model.addObject("page", page);
		model.addObject("appMenuList",appMenuList);
		model.setViewName("/appMenu/menuQueryResult");
		return model;
	}
	
	
	/**
	 * @Description app菜单添加跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppMenuAdd.action")
	public ModelAndView toAppMenuAdd(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/appMenu/menuAdd");
		return model;
	}
	
	/**
	 * @Description app菜单添加
	 * @param appMenu
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appMenuAdd.action")
	public ModelAndView appMenuAdd(AppMenu appMenu ,@RequestParam Map<String, Object> queryParams){
		logger.info("appMenuAdd params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		//添加app菜单
		appMenuService.addAppMenu(appMenu);
		//重定向到查询页面
		model.setViewName("redirect:/appMenu/toAppMenuQuery.action");
		return model;
	}
	
	/**
	 * @Description app菜单编辑跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppMenuEdit.action")
	public ModelAndView toAppMenuEdit(@RequestParam("menuId") String menuId){
		ModelAndView model = new ModelAndView();
		AppMenu appMenu = null;
		try {
			appMenu = appMenuService.findAppMenuByMenuId(Long.parseLong(menuId));
		} catch (Exception e) {
			LogUtil.error("method error",e);
		}
		model.addObject("appMenu",appMenu);
		model.setViewName("/appMenu/menuEdit");
		return model;
	}
	
	/**
	 * @Description 编辑app菜单
	 * @param appMenu
	 * @param quertParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appMenuEdit.action")
	public ModelAndView appMenuEdit(AppMenu appMenu , @RequestParam Map<String , Object> quertParams){
		logger.info("appMenuEdit params : {}", quertParams);
		ModelAndView model = new ModelAndView();
		//修改appmenu
		appMenuService.modifyAppMenu(appMenu);
		//重定向到查询页面
		model.setViewName("redirect:/appMenu/toAppMenuQuery.action");
		return model;
		
	}
	
	/**
	 * @Description 删除AppMenu
	 * @param menuId
	 * @return
	 * @see 需要参考的类或方法
	 *//*
	@RequestMapping("/deleteAppMenu.action")
	public ModelAndView deleteAppMenu(@RequestParam("menuId") String menuId ){
		ModelAndView model = new ModelAndView();
		appMenuService.deleteAppMenu(menuId);
		model.setViewName("redirect:/appMenu/toAppMenuQuery.action");
		return model;
	}*/
	
	/**
	 * @Description app菜单授权跳转
	 * @param menuId
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppMenuAuthorize.action")
	public ModelAndView toAppMenuAuthorize(@RequestParam("menuId") String menuId){
		ModelAndView model = new ModelAndView();
		//查找所有的角色信息
		List<Map<String, Object>> list = appMenuService.findRole();
		//查询当前菜单已赋权的角色
		String [] roleIdes = appMenuService.findAuthorizeRole(menuId);
		String roleIds = null ;
		if(roleIdes != null && roleIdes.length > 0){
			for(String str: roleIdes){
				if(null != roleIds){
					roleIds = roleIds +","+ str;//sms,message
				}else{
					roleIds = str;
				}
			}
		}
		//将所有角色的ID封装成1,2,3字符串中中
		String  appRoleIds = null ;
		for(Map<String, Object> appRole : list){
			if(null != appRoleIds){
				appRoleIds = appRoleIds +","+ appRole.get("id");//sms,message
			}else{
				appRoleIds = appRole.get("id").toString();
			}
		}
		model.addObject("appRoles" , list);
		model.addObject("roleIds" , roleIds);
		model.addObject("appRoleIds" , appRoleIds);
		model.addObject("menuId" , menuId);
		model.setViewName("/appMenu/menuAuthorize");
		return model;
	}
	
	 
	/**
	 * @Description app菜单授权
	 * @param queryParams
	 * @param meubId
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appMenuAuthorize")
	public ModelAndView appMenuAuthorize(@RequestParam Map<String, Object> queryParams, @RequestParam("menuId")String menuId ,@RequestParam("roleIds") String[] roleIds,@RequestParam("appRoleIds")String appRoleIds){
		ModelAndView model = new ModelAndView();
		String []roleIdArray = appRoleIds.split(",");
		//遍历所以角色将选中的做插入或修改状态为ENABLE处理，将未选中的做DISABLE
		for(String appRole :roleIdArray){
			boolean flag = true;
			for(String roleId :roleIds){//判断当前id是否被选中
				if((appRole).equals(roleId)){
					appMenuService.saveMenuAuthorize(roleId , menuId);
					flag = false;
				}
			}
			if(flag){
				appMenuService.deleteMenuAuthorize(appRole, menuId);
			}
		}
		model.setViewName("redirect:/appMenu/toAppMenuQuery.action");
		return model;
	}
	
	
}
