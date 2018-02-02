package com.pay.national.agent.portal.web;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.portal.service.common.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户相关运营功能
 * @see: 需要参考的类
 * @version 2017年9月21日 下午5:15:43
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/appUser")
public class AppUserController {

	@Resource
	private AppUserService appUserService;
	
	/**
	 * @Description app用户查询跳转
	 * @return
	 * @see
	 */
	@RequestMapping("/toAppUserQuery.action")
	public ModelAndView toAppUserQuery(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/appUser/userQuery");
		return model;
	}
	
	/**
	 * @Description app菜单查询（分页）
	 * @param queryParams
	 * @return
	 * @see
	 */
	@RequestMapping("/appUserQuery.action")
	public ModelAndView appUserQuery(@RequestParam Map<String, Object> queryParams){
		LogUtil.info("appUserQuery params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		int currentPage = queryParams.get("currentPage") == null ? 1 : Integer.parseInt(queryParams.get("currentPage").toString());
		Page<AppUser> page = new Page<AppUser>();
		page.setCurrentPage(currentPage);
		// 分页查询
		List<AppUser> appUserList = this.appUserService.findAllAppUser(page, queryParams);
		model.addObject("appUserList", appUserList);
		model.addObject("page", page);
		model.setViewName("/appUser/userQueryResult");
		return model;
	}
	
	/**
	 * @Description app用户编辑跳转
	 * @return
	 * @see
	 */
	@RequestMapping("/toAppUserEdit.action")
	public ModelAndView toAppUserEdit(@RequestParam("userId") String userId){
		ModelAndView model = new ModelAndView();
		AppUser appUser = appUserService.findAppUserByUserId(userId);
		model.addObject("appUser",appUser);
		model.setViewName("/appUser/userEdit");
		return model;
	}
	
	/**
	 * @Description app用户编辑
	 * @param appMenu
	 * @param quertParams
	 * @return
	 * @see
	 */
	@RequestMapping("/appUserEdit.action")
	public ModelAndView appUserEdit(AppUser appUser , @RequestParam Map<String , Object> queryParams){
		LogUtil.info("appUserEdit params : {}", queryParams);
		ModelAndView model = new ModelAndView();
		//修改appmenu
		appUserService.modifyAppUser(appUser);
		//重定向到查询页面
		model.setViewName("redirect:/appUser/toAppUserQuery.action");
		return model;
	}
	
	/**
	 * @Description 修改用户状态
	 * @param userId
	 * @param status
	 * @return
	 * @see
	 */
	@RequestMapping("/updateUserStatus.action")
	public @ResponseBody String updateUserStatus(@RequestParam("userId")String userId,@RequestParam("status")String status){
		ReturnBean<Object> returnBean = new ReturnBean<>();
		appUserService.updateUserStatus(Long.parseLong(userId),status);
		returnBean.setCode(RetCodeConstants.SUCCESS);
		returnBean.setMsg("修改成功");
		return JSONUtils.alibabaJsonString(returnBean);
	}
	/**
	 * @Description app用户添加跳转
	 * @return
	 * @see
	 */
	@RequestMapping("/toAppUserAdd.action")
	public ModelAndView toAppMenuAdd(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/appUser/userAdd");
		return model;
	}
	
	/**
	 * @Description app用户添加
	 * @param appMenu
	 * @param queryParams
	 * @return
	 * @see
	 */
	@RequestMapping("/appUserAdd.action")
	public ModelAndView appUserAdd(AppUser appUser ,@RequestParam Map<String, Object> queryParams){
		LogUtil.info("appUserAdd params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		//添加app菜单
		appUserService.addAppMenu(appUser);
		//重定向到查询页面
		model.setViewName("redirect:/appUser/toAppUserQuery.action");
		return model;
	}
	
	
	/**
	 * @Description app用户授权跳转
	 * @param menuId
	 * @return
	 * @see
	 */
	@RequestMapping("/toAppUserAuthorize.action")
	public ModelAndView toAppUserAuthorize(@RequestParam("userId") String userId){
		ModelAndView model = new ModelAndView();
		
		try {
			//查找所有的角色信息
			List<Map<String, Object>> list = appUserService.findRole();
			//查询当前用户已赋权的角色
			String [] roleIdes = appUserService.findAuthorizeUser(userId);
			String roleIds = null ;
			if(roleIdes != null && roleIdes.length > 0){
				for(String str: roleIdes){
					if(null != roleIds){
						roleIds = roleIds +","+ str;
					}else{
						roleIds = str;
					}
				}
			}
			//将所有角色的ID封装成1,2,3字符串中中
			String  appRoleIds = null ;
			for(Map<String, Object> appRole : list){
				if(null != appRoleIds){
					appRoleIds = appRoleIds +","+ appRole.get("id");
				}else{
					appRoleIds = appRole.get("id").toString();
				}
			}
			model.addObject("appRoles" , list);
			model.addObject("roleIds" , roleIds);
			model.addObject("appRoleIds" , appRoleIds);
			model.addObject("userId" , userId);
			model.setViewName("/appUser/userAuthorize");
		} catch (Exception e) {
			LogUtil.error("sdfdsf",e);
		}
		return model;
	}
	
	 
	/**
	 * @Description app用户授权
	 * @param queryParams
	 * @param meubId
	 * @return
	 * @see
	 */
	@RequestMapping("/appUserAuthorize")
	public ModelAndView appUserAuthorize(@RequestParam Map<String, Object> queryParams, @RequestParam("userId")String userId ,@RequestParam(value = "roleIds",required=false) String[] roleIds,@RequestParam("appRoleIds")String appRoleIds){
		ModelAndView model = new ModelAndView();
		String []roleIdArray = appRoleIds.split(",");
		//遍历所以角色将选中的做插入或修改状态为ENABLE处理，将未选中的做DISABLE
		try {
			for(String appRole :roleIdArray){
				boolean flag = true;
				if(roleIds != null && roleIds.length>0){
					for(String roleId :roleIds){//判断当前id是否被选中
						if((appRole).equals(roleId)){
							appUserService.saveUserAuthorize(roleId , userId);
							flag = false;
						}
					}
				}
				if(flag){
					appUserService.deleteUserAuthorize(appRole, userId);
				}
			}
		} catch (Exception e) {
			LogUtil.error("error:",e);
		}
		model.setViewName("redirect:/appUser/toAppUserQuery.action");
		return model;
	}
}
