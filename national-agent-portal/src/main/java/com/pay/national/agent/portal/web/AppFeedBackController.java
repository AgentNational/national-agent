
package com.pay.national.agent.portal.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppFeedback;
import com.pay.national.agent.portal.service.common.AppFeedbackService;

/**
 * @ClassName:  AppFeedBackController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午4:29:18
 *
 */
@Controller
@RequestMapping("/appFeedBack")
public class AppFeedBackController {
private Logger logger = LoggerFactory.getLogger(AppFeedBackController.class);

	@Resource
	private AppFeedbackService appFeedbackService;

//	//TODO SmsService接口，实现发送处理意见至用户邮箱，现阶段未定义相关接口，需后续加上。
//	@Resource
//	private SmsService smsService;
//
//	//TODO MessagePushFacade接口，实现推送处理意见至app端，现阶段未定义相关推送接口，需后续加上。
//	@Resource
//	private MessagePushFacade messagePushFacade;
//
	/**
	 * init反馈信息查询页面
	 * @Description 一句话描述方法的用法
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toFeedbackQuery.action")
	public ModelAndView toFeedbackQuery()
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("/feedBack/feedbackQuery");
		return model;
	}

	/**
	 * 查询消息主体列表
	 * @Description  一句话描述方法用法
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/feedbackQuery.action")
	public ModelAndView feedbackQuery(@RequestParam Map<String, Object> queryParams)
	{
		logger.info("feedbackQuery params : {}",queryParams);
		ModelAndView model = new ModelAndView();
		int currentPage = queryParams.get("currentPage") == null ? 1 : Integer.parseInt(queryParams.get("currentPage").toString());
		String source = queryParams.get("appClientType") == null ?  null: queryParams.get("appClientType").toString().toUpperCase();
		queryParams.put("source", source);
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		page.setCurrentPage(currentPage);
		// 分页查询
		List<Map<String,Object>> feedbackList = this.appFeedbackService.findAllFeedback(page, queryParams);
		model.addObject("feedbackList", feedbackList);
		model.addObject("page", page);
		model.setViewName("/feedBack/feedbackQueryResult");
		return model;
	}

	/**
	 *
	 * @Description  查询反馈详情
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/feedbackDetail.action")
	public ModelAndView feedbackDetail(@RequestParam("id") String id)
	{
		ModelAndView model = new ModelAndView();
		AppFeedback appFeedback = this.appFeedbackService.findAppFeedbackById(id);
		model.addObject("feedback", appFeedback);
		model.setViewName("/feedBack/feedbackDetail");
		return model;
	}

	/**
	 *
	 * @Description  处理反馈跳转
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/toFeedbackModify.action")
	public ModelAndView toFeedbackModify(@RequestParam("id") String id)
	{
		ModelAndView model = new ModelAndView();
		AppFeedback appFeedback = appFeedbackService.findAppFeedbackById(id);
		model.addObject("feedback", appFeedback);
		model.setViewName("/feedBack/feedbackEdit");
		return model;
	}

	/**
	 *
	 * @Description  处理反馈
	 * @param id
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping("/feedbackModify.action")
	public ModelAndView feedbackModify(@RequestParam("feedId") String feedId,@RequestParam("remark") String remark,@RequestParam("type") String[] type,
			HttpServletRequest request)
	{

		ModelAndView model = new ModelAndView();
		/*测试时需要注掉，否则报nullpointexception 因为操作员需要集成boss之后才能获取，*/
//		Authorization auth = (Authorization) request.getSession().getAttribute(Constants.SESSION_AUTH);
//		String userName = auth.getUserName();
		AppFeedback appFeedback = appFeedbackService.findAppFeedbackById(feedId);
		/*处理checkbox选择的返回类型*/
		for(String s : type){
			dealReturnType(s ,appFeedback ,remark);
		}
		appFeedback.setReturnType(appFeedback.arrayToString(type));
		appFeedback.setRemark(remark);
		appFeedback.setOperatorTime(new Date());
		appFeedback.setStatus("SUCCESS");
//		appFeedback.setOperator(userName);//测试时需要注掉。
		appFeedbackService.modify(appFeedback);
		model.setViewName("/feedBack/feedbackQuery");
		return model;
	}

	/**
	 * 处理意见返回
	 * @Description 一句话描述方法的用法
	 * @see 需要参考的类或方法
	 */
	public void dealReturnType(String s,AppFeedback appFeedback ,String remark){
		//判断是否需要发送短信
		/*if(s.equals("sms")){
			boolean flag = smsService.sendSms(appFeedback.getPhone(), remark ,true);
			if(flag){
				appFeedback.setIsRead("Y");//修改发送信息状态
			}
		}else if(s.equals("message")){
			Map<String, String> extras = new HashMap<String, String>();
			extras.put("type", "MESSAGE");
			boolean flag = messagePushFacade.push(appFeedback.getPhone(), "N", "反馈处理", appFeedback.getRemark(), true,extras);
			if(!flag)
			{
				logger.error("messagePushFacade 推送反馈处理失败!");
			}
		}*/
	}
}
