package com.pay.national.agent.portal.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.AppRole;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.portal.service.common.AppRoleService;

/**
 * @Description: app角色controller
 * @see: 需要参考的类
 * @version 2017年9月27日 上午9:27:19
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/appRole")
public class AppRoleController {

	@Resource
	private AppRoleService appRoleService;
	
	@RequestMapping("/toAppRoleQuery.action")
	public ModelAndView toAppRoleQuery(){
		ModelAndView model = new ModelAndView();
		model.setViewName("appRole/roleQuery");
		return model;
	}
	
	@RequestMapping("/appRoleQuery.action")
	public ModelAndView appRoleQuery(HttpServletRequest request,@RequestParam Map<String, String> queryParams){
		
		ModelAndView model = new ModelAndView();
		int currentPage = queryParams.get("currentPage") == null ? 1 : Integer.parseInt(queryParams.get("currentPage").toString());
		Page<AppRole> page = new Page<AppRole>();
		page.setCurrentPage(currentPage);
		// 分页查询
		List<AppRole> appRoleList = appRoleService.findAllAppRole(page, queryParams);
		model.addObject("appRoleList", appRoleList);
		model.addObject("page", page);
		model.setViewName("appRole/roleQueryResult");
		return model;
	}
	
	/**
	 * @Description app角色编辑跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppRoleEdit.action")
	public ModelAndView toAppRoleEdit(@RequestParam("roleId") String roleId){
		ModelAndView model = new ModelAndView();
		AppRole appRole = appRoleService.findAppRoleByRoleId(roleId);
		model.addObject("appRole",appRole);
		model.setViewName("/appRole/roleEdit");
		return model;
	}
	
	/**
	 * @Description app角色编辑
	 * @param appRole
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appRoleEdit.action")
	public ModelAndView appRoleEdit(AppRole appRole , @RequestParam Map<String , Object> queryParams){
		LogUtil.info("appRoleEdit params : {}", queryParams);
		ModelAndView model = new ModelAndView();
		//修改appmenu
		appRoleService.modifyAppRole(appRole);
		//重定向到查询页面
		model.setViewName("redirect:/appRole/toAppRoleQuery.action");
		return model;
	}
	
	/**
	 * @Description app角色编辑跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppRoleAdd.action")
	public ModelAndView toAppRoleAdd(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/appRole/roleAdd");
		return model;
	}
	
	/**
	 * @Description app角色编辑
	 * @param appRole
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appRoleAdd.action")
	public ModelAndView appRoleAdd(AppRole appRole , @RequestParam Map<String , Object> queryParams){
		LogUtil.info("appRoleAdd params : {}", queryParams);
		ModelAndView model = new ModelAndView();
		try {
			appRoleService.addAppRole(appRole);
		} catch (Exception e) {
			LogUtil.error("error ",e);
		}
		//重定向到查询页面
		model.setViewName("redirect:/appRole/toAppRoleQuery.action");
		return model;
	}
	
	
}
