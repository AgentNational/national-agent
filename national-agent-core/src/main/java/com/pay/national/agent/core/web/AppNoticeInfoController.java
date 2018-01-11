package com.pay.national.agent.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppNoticeInfoFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.NoticeListQueryBean;
import com.pay.national.agent.model.beans.results.AppNoticeDetailBean;
import com.pay.national.agent.model.beans.results.AppNoticeListBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.User;

@Controller
@RequestMapping("/appNotice")
public class AppNoticeInfoController {

	@Resource
	private AppNoticeInfoFacade appNoticeInfoFacade;
	
	/**
	 * 公告列表
	 * @param userName
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/queryNoticeList",method=RequestMethod.POST)
	public @ResponseBody String noticeList(@RequestParam("loginKey")String loginKey,@RequestParam(value="currentPage",required=false)Integer currentPage){
		LogUtil.info("noticeList start params loginKey:{},currentPage:{}",loginKey,currentPage);
		ReturnBean<Page<List<AppNoticeListBean>>> returnBean = null;
		try {
			
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			NoticeListQueryBean queryBean  = new NoticeListQueryBean();
			queryBean.setUserName(user.getUserNo());
			queryBean.setUserGroup(user.getAppRoles().get(0).getRoleType());
			
			Page<List<AppNoticeListBean>> page = new Page<List<AppNoticeListBean>>();
			page.setCurrentPage(currentPage == null?1:currentPage);
			
		    returnBean = appNoticeInfoFacade.findAllNotice(queryBean,page); 
		} catch (Exception e) {
			LogUtil.error("noticeList dubbo loginKey:{},error",loginKey,e);
			returnBean =  new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("noticeList return loginKey:{},result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 记录用户已读取公告
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/readNotice",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "ids", required = true)})
	public @ResponseBody String readNotice(@RequestParam("loginKey")String loginKey,@RequestParam("ids")String ids){
		LogUtil.info("readNotice start params  loginKey:{},ids:{}",loginKey,ids);
		ReturnBean<Object> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			String[] idArray = JSONUtils.toObject(ids, String[].class);
			returnBean = appNoticeInfoFacade.readNotice(user.getUserNo(),idArray);
		} catch (Exception e) {
			LogUtil.error("readNotice dubbo loginKey:{}, error",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("readNotice return  loginKey:{},result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 公告详情
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/noticeDetail")
	@ParamsValidate(value = {@ParamValidate(name = "noticeId", required = true)})
	public @ResponseBody String noticeDetail(@RequestParam("noticeId")String noticeId){
		LogUtil.info("noticeDetail start params noticeId:{}",noticeId);
		 ReturnBean<AppNoticeDetailBean> returnBean = null;
		try {
			returnBean = appNoticeInfoFacade.noticeDetail(noticeId);
		} catch (Exception e) {
			LogUtil.error("noticeDetail dubbo noticeId:{}, error",noticeId,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("noticeDetail return noticeId:{},result:{}",noticeId,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 删除公告
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("deleteNotice")
	@ResponseBody
	@ParamsValidate(value = {@ParamValidate(name = "noticeId", required = true)})
	public String deleteNotice(@RequestParam("loginKey")String loginKey,@RequestParam("noticeId")String noticeId){
		LogUtil.info("deleteNotice start params loginKey:{},noticeId:{}",loginKey,noticeId);
		ReturnBean<Object> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = appNoticeInfoFacade.deleteNotice(user.getUserNo(),noticeId);
		} catch (Exception e) {
			LogUtil.error("deleteNotice dubbo loginKey:{}, error:{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("deleteNotice return loginKey:{},result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
}
