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

import com.pay.national.agent.common.bean.Authorization;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.AppMessageInfo;
import com.pay.national.agent.portal.service.common.AppMessageInfoService;

@Controller
@RequestMapping("/message")
public class AppMessageInfoController {
	@Resource
	private AppMessageInfoService appMessageInfoService;
	
	/**
	 * 添加消息——跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddMessage.action")
	public ModelAndView toAddMessage(HttpServletRequest request,ModelAndView model){
		//测试时需要注掉，否则报nullpointexception 因为操作员需要集成boss之后才能获取
		Authorization auth = (Authorization) request.getSession().getAttribute("plouto.auth");
		String operator = auth.getUserName();
		model.addObject("operator", operator);
		model.setViewName("appMessage/messageAdd");
		return model;
	}
	
	/**
	 * 添加消息
	 * @param appMessageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/add.action")
	public ModelAndView saveMessage(AppMessageInfo appMessageInfo,ModelAndView model){
		try {
			appMessageInfoService.saveMessage(appMessageInfo);
		} catch (Exception e){
			LogUtil.error("Method saveMessage error ",e);
		}
		model.setViewName("appMessage/messageQuery");
		return model;
	}
	
	/**
	 * 推送消息
	 * @param appMessageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendMessage.action")
	public @ResponseBody String sendMessage(@RequestParam("messageId")String messageId){
		boolean boo = appMessageInfoService.sendMessage(messageId);
		return boo+"";
	}
	
	/**
	 * 修改消息-跳转
	 * @param appMessageId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toUpdateMessage.action")
	public ModelAndView toUpdateMessage(@RequestParam String messageId,HttpServletRequest request,ModelAndView model){
		//测试时需要注掉，否则报nullpointexception 因为操作员需要集成boss之后才能获取
		Authorization auth = (Authorization) request.getSession().getAttribute("plouto.auth");
		String operator = auth.getUserName();
		LogUtil.info("method toUpdateMessage messageId:{},operator:{}",messageId,operator);
		AppMessageInfo appMessageInfo =  appMessageInfoService.getMessage(messageId);
		appMessageInfo.setOperator(operator);
		model.addObject("appMessageInfo", appMessageInfo);
		model.setViewName("appMessage/messageEdit");
		return model;
	}
	
	/**
	 * 修改消息
	 * @param appMessageInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/update.action")
	public @ResponseBody String updateMessage(AppMessageInfo appMessageInfo){
		try {
			AppMessageInfo messageDb = appMessageInfoService.getMessage(appMessageInfo.getId());
			messageDb.setMsgType(appMessageInfo.getMsgType());
			messageDb.setAbleStatus(appMessageInfo.getAbleStatus());
			messageDb.setTitle(appMessageInfo.getTitle());
			messageDb.setUserName(appMessageInfo.getUserName());
			messageDb.setBriefContent(appMessageInfo.getBriefContent());
			messageDb.setIsPop(appMessageInfo.getIsPop());
			messageDb.setContent(appMessageInfo.getContent());
			messageDb.setLastUpdateTime(new Date());
			appMessageInfoService.updateMessage(messageDb);
		} catch (Exception e) {
			LogUtil.error("Method updateMessage error ",e);
			return "FAIL";
		}
		return "SUCCESS";
	}
	
	/**
	 * 查询消息-跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toQuery.action")
	public ModelAndView toQuery(HttpServletRequest request,ModelAndView model){
		model.setViewName("appMessage/messageQuery");
		return model;
	}
	
	/**
	 * 查询消息列表
	 * @param appMessageInfo
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryMessageList.action")
	public ModelAndView queryMessageList(@RequestParam Map<String, Object> queryParams,HttpServletRequest request,ModelAndView model){
		String pageNoStr = request.getParameter("currentPage");
		Integer pageNo = (null == pageNoStr)?1:Integer.valueOf(pageNoStr);
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		page.setCurrentPage(pageNo);
		//查询
		List<Map<String,Object>> appMessageInfoList =  appMessageInfoService.findAllMessage(page,queryParams);
		model.addObject("appMessageInfoList", appMessageInfoList);
		model.addObject("page", page);
		model.setViewName("appMessage/messageQueryResult");
		return model;
	}
	
	/**
	 * 展示消息详情
	 * @param appMessageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMessageDetail.action")
	public ModelAndView getMessageDetail(@RequestParam String messageId,ModelAndView model){
		LogUtil.info("getMessageDetail start messageId:{}",messageId);
		AppMessageInfo appMessageInfo = appMessageInfoService.getMessage(messageId);
		model.addObject("appMessageInfo",appMessageInfo);
		model.setViewName("appMessage/messageDetail");
		return model;
	}
	
	/**
	 * 删除消息
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value="/deleteMessage.action")
	public @ResponseBody String deleteMessage(@RequestParam String messageId){
		try {
			appMessageInfoService.deleteMessage(messageId);
		} catch (Exception e) {
			LogUtil.error("Method deleteMessage error:",e);
			return "FAIL";
		}
		return "SUCCESS";
	}
}
