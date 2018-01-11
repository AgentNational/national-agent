package com.pay.national.agent.portal.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pay.commons.utils.lang.DateUtils;
import com.pay.national.agent.common.bean.Authorization;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.model.entity.AppNoticeInfo;
import com.pay.national.agent.portal.service.common.AppNoticeInfoService;
/**
 * 
 * @author shuyan.qi
 * Date:2017年9月18日下午11:29:40
 */
@Controller
@RequestMapping("/appNotice")
public class AppNoticeInfoController {
	
	@Resource
	private AppNoticeInfoService appNoticeInfoService;
	/**
	 * @Description app公告查询跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppNoticeQuery.action")
	public ModelAndView toAppNoticeQuery(){
		ModelAndView model = new ModelAndView();
		model.setViewName("appNotice/noticeQuery");
		return model;
	} 
	
	/**
	 * @Description app公告查询
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appNoticeQuery.action")
	public ModelAndView appNoticeQuery(@RequestParam Map<String, Object> queryParams,ModelAndView model){
		LogUtil.info("appNoticeQuery params : {}",queryParams);
		//获取分页查询当前需要查询的页码
		int currentPage = queryParams.get("currentPage") == null ? 1 : Integer.parseInt(queryParams.get("currentPage").toString());
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		page.setCurrentPage(currentPage);
		// 分页查询
		List<Map<String,Object>> appNoticeList = appNoticeInfoService.findAllAppNotice(page, queryParams);
		model.addObject("appNoticeList", appNoticeList);
		model.addObject("page", page);
		model.setViewName("appNotice/noticeQueryResult");
		LogUtil.info("appNoticeQuery return...");
		return model;
	}
	
	/**
	 * @Description 跳转添加公告
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppNoticeAdd.action")
	public ModelAndView toAppNoticeAdd(HttpServletRequest request,ModelAndView model){
		//测试时需要注掉，否则报nullpointexception 因为操作员需要集成boss之后才能获取
		Authorization auth = (Authorization) request.getSession().getAttribute("plouto.auth");
		String operator = auth.getUserName();
		model.addObject("operator", operator);
		model.setViewName("appNotice/noticeAdd");
		return model;
	}
	
	/**
	 * @Description app公告添加
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appNoticeAdd.action")
	public ModelAndView appNoticeAdd(AppNoticeInfo appNoticeInfo,@RequestParam Map<String, Object> queryParams){
		LogUtil.info("appNoticeAdd params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		appNoticeInfo.setId(CommonCodeUtil.getPkId());
		appNoticeInfo.setOptimistic(0);
		appNoticeInfo.setCreateTime(new Date());
		appNoticeInfoService.addAppNotice(appNoticeInfo);
		model.setViewName("appNotice/noticeQuery");
		return model;
	}
	
	/**
	 * @Description app公告编辑跳转
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toAppNoticeEdit.action")
	public ModelAndView toAppNoticeEdit(@RequestParam("noticeId") String noticeId){
		ModelAndView model = new ModelAndView();
		AppNoticeInfo appNotice = appNoticeInfoService.find(noticeId);
		model.addObject("appNotice",appNotice);
		model.setViewName("appNotice/noticeEdit");
		return model;
	}
	
	/**
	 * @Description 编辑app公告
	 * @param appMenu
	 * @param quertParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appNoticeEdit.action")
	public @ResponseBody String appNoticeEdit(@RequestParam Map<String , Object> quertParams){
		LogUtil.info("appNoticeEdit params : {}", quertParams);
		String noticeId = (String)quertParams.get("id");
		AppNoticeInfo appNoticeInfo = appNoticeInfoService.find(noticeId);
		if(null != appNoticeInfo){
			try {
				String beginTime = (String)quertParams.get("beginTime");
				if(StringUtils.isNotBlank(beginTime)){
					appNoticeInfo.setBeginTime(DateUtils.parseDate(beginTime, "yyyy-MM-dd HH:mm:ss"));
				}
				String endTime = (String)quertParams.get("endTime");
				if(StringUtils.isNotBlank(endTime)){
					appNoticeInfo.setEndTime(DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"));
				}
				String isPop = (String)quertParams.get("isPop");
				if(StringUtils.isNotBlank(isPop) && "true".equals(isPop)){
					appNoticeInfo.setIsPop(true);
				}else{
					appNoticeInfo.setIsPop(false);
				}
				appNoticeInfo.setTitle((String)quertParams.get("title"));
				appNoticeInfo.setNoticeType((String)quertParams.get("noticeType"));
				appNoticeInfo.setUsableStatus((String)quertParams.get("usableStatus"));
				appNoticeInfo.setUserGroup((String)quertParams.get("userGroup"));
				appNoticeInfo.setBriefContent((String)quertParams.get("briefContent"));
				appNoticeInfo.setContent((String)quertParams.get("content"));
				appNoticeInfo.setLastUpdateTime(new Date());
				appNoticeInfoService.update(appNoticeInfo);
			} catch (Exception e) {
				LogUtil.error("appNoticeEdit   error noticeId:{},exception:{}",noticeId,e);
				return "FAIL";
			}
		}else{
			LogUtil.error("appNoticeInfo is null noticeId:{}",noticeId);
			return "FAIL";
		}
		return "SUCCESS";
	}
	
	/**
	 * @Description app公告详情
	 * @param noticeId
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/appNoticeDetail.action")
	public ModelAndView appNoticeDetail(@RequestParam("noticeId") String noticeId){
		ModelAndView model = new ModelAndView();
		AppNoticeInfo appNotice = appNoticeInfoService.find(noticeId);
		model.addObject("appNotice",appNotice);
		model.setViewName("appNotice/noticeDetail");
		return model;
	}

	@RequestMapping("/deleteNotice.action")
	public @ResponseBody String deleteNotice(@RequestParam String noticeId){
		try {
			appNoticeInfoService.deleteNotice(noticeId);
		} catch (Exception e) {
			LogUtil.error("Method deleteNotice error:",e);
			return "FAIL";
		}
		return "SUCCESS";
	}
}
