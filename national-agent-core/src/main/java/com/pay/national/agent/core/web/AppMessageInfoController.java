package com.pay.national.agent.core.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.AppMessageInfoFacade;
import com.pay.national.agent.core.facade.PushMsgServiceFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PushMessageBean;
import com.pay.national.agent.model.beans.results.AppMessageDetailBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.beans.results.AppPopListBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.User;

@Controller
@RequestMapping("/appMessage")
public class AppMessageInfoController {

	@Resource
	private AppMessageInfoFacade appMessageInfoFacade;
	
	@Resource
	private PushMsgServiceFacade pushMsgServiceFacade;
	
	/**
	 * 注册消息平台账号
	 * @param loginKey
	 * @param pushId
	 * @param osType
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	@ParamsValidate(value = {@ParamValidate(name = "pushId", required = true),@ParamValidate(name = "osType", required = true)})
	public String register(@RequestParam("loginKey")String loginKey,@RequestParam("pushId")String pushId,@RequestParam("osType")String osType,
			@RequestParam("deviceToken")String deviceToken){
		LogUtil.info("register start params loginKey:{},pushId:{},opType:{},deviceToken:{}",loginKey,pushId,osType,deviceToken);
		ReturnBean<String> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = pushMsgServiceFacade.register(user.getUserNo(),pushId,osType,deviceToken);
		} catch (Exception e) {
			LogUtil.error("register dubbo loginKey:{},error:{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("register return loginKey:{},result:{}", loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 解绑消息平台账号
	 * @param loginKey
	 * @return
	 */
	@RequestMapping(value="/unbind",method=RequestMethod.POST)
	@ResponseBody
	public String unbind(@RequestParam("loginKey")String loginKey){
		LogUtil.info("unbind start params loginKey:{}",loginKey);
		ReturnBean<String> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = pushMsgServiceFacade.unbind(user.getUserNo());
		} catch (Exception e) {
			LogUtil.error("unbind dubbo loginKey:{},error:{}",loginKey, e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("unbind return loginKey:{},result:{}",loginKey, returnBean);
		return JSONUtils.alibabaJsonString(returnBean); 
	}
	
	/**
	 * 修改消息状态为已读
	 * @param request
	 * @param params
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/updateIsRead",method=RequestMethod.POST)
	@ResponseBody
	@ParamsValidate(value = {@ParamValidate(name = "ids", required = true)})
	public String updateIsRead(@RequestParam("loginKey")String loginKey,@RequestParam("ids")String ids){
		LogUtil.info("updateIsRead start params loginKey:{},ids:{}",loginKey,ids);
		ReturnBean<Object> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			String[] idArray = JSONUtils.toObject(ids, String[].class);
		    returnBean = appMessageInfoFacade.updateIsRead(user.getUserNo(),idArray);
		} catch (Exception e) {
			LogUtil.error("updateIsRead dubbo loginKey:{}, error",loginKey,e);
			returnBean = new ReturnBean<Object>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("updateIsRead return loginKey:{}, result:{}", loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 查询消息列表
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/findAllAppMessageInfo",method=RequestMethod.POST)
	@ResponseBody
	public String findAllAppMessageInfo(@RequestParam("loginKey")String loginKey,@RequestParam(value="currentPage",required=false)Integer currentPage){
		LogUtil.info("findAllAppMessageInfo start params loginKey:{},currentPage:{}",loginKey,currentPage);
		ReturnBean<Page<List<AppMessageListBean>>> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			currentPage = null == currentPage?1:currentPage;
			returnBean = appMessageInfoFacade.findAllAppMessageInfo(user.getUserNo(),currentPage);
		} catch (Exception e) {
			LogUtil.error("findAllAppMessageInfo dubbo loginKey:{}, error",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("findAllAppMessageInfo return  loginKey:{},result:{}",loginKey, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 消息详情
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/findAppMessageInfoDetail" ,method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "messageId", required = true)})
	@ResponseBody
	public String findAppMessageInfoDetail(@RequestParam("messageId")String messageId){
		LogUtil.info("findAppMessageInfoDetail start params messageId:{}",messageId);
		ReturnBean<AppMessageDetailBean> returnBean = null;
		try {
			returnBean = appMessageInfoFacade.findAppMessageInfoDetail(messageId);
		} catch (Exception e) {
			LogUtil.error("findAppMessageInfoDetail dubbo messageId:{}, error",messageId,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("findAppMessageInfoDetail return messageId:{}, result:{}",messageId, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 查询弹窗消息和弹窗公告列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/findPopList",method=RequestMethod.POST)
	@ResponseBody
	public String findPopList(@RequestParam("loginKey")String loginKey){
		LogUtil.info("findPopList start params loginKey:{}",loginKey);
		ReturnBean<List<AppPopListBean>> returnBean  = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = appMessageInfoFacade.findPopList(user.getUserNo(),user.getAppRoles().get(0).getRoleType());
		} catch (Exception e) {
			LogUtil.error("findPopList dubbo loginKey:{},error",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("findPopList return  loginKey:{},result:{}",loginKey, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 查询用户未读消息数和未读公告数
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/findUnReadCount",method=RequestMethod.POST)
	@ResponseBody
	public String findUnReadCount(@RequestParam("loginKey")String loginKey){
		LogUtil.info("findUnReadCount start params loginKey:{}",loginKey);
		ReturnBean<Map<String, Integer>> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			returnBean = appMessageInfoFacade.findUnReadCount(user.getUserNo(),user.getAppRoles().get(0).getRoleType());
		} catch (Exception e) {
			LogUtil.error("findUnReadCount dubbo loginKey:{}, error",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("findUnReadCount return loginKey:{},result:{}",loginKey, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
		
	}
	
	/**
	 * 删除个人消息
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/deleteMessage",method=RequestMethod.POST)
	@ResponseBody
	@ParamsValidate(value = {@ParamValidate(name = "messageId", required = true)})
	public String deleteMessage(@RequestParam("messageId")String messageId){
		LogUtil.info("deleteMessage start params messageId:{}",messageId);
		ReturnBean<Object> returnBean = null;
		try {
			returnBean = appMessageInfoFacade.deleteMessage(messageId);
		} catch (Exception e) {
			LogUtil.error("deleteMessage dubbo messageId:{},error",messageId,e);
			returnBean  = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("deleteMessage return messageId:{},result:{}",messageId, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	/**
	 * 测试推送
	 * @param loginKey
	 * @return
	 */
	@RequestMapping(value="/pushT",method=RequestMethod.POST)
	@ResponseBody
	public String pushT(@RequestParam("loginKey")String loginKey,@RequestParam("type")String type,@RequestParam(value="redirect",required=false)String redirect,
			@RequestParam(value = "keys",required=false)String keys,@RequestParam(value="values",required=false)String values){
		LogUtil.info("pushT start params loginKey:{},type:{},redirect:{},keys:{},values:{}",loginKey,type,redirect,keys,values);
		ReturnBean<String> returnBean = null;
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
		try {
			PushMessageBean bean  = new PushMessageBean();
			bean.setUser_no(user.getUserNo());
			bean.setPush_bar_txt("欢迎使用全名代理！");
			Map<String, String> redirect_params = new HashMap<String, String>();
			redirect_params.put("type", type);
			if(StringUtils.isNotBlank(keys)){
				redirect_params.put("keys", keys);
			}
			if(StringUtils.isNotBlank(redirect)){
				redirect_params.put("redirect", redirect);
			}
			if(StringUtils.isNotBlank(values)){
				Map<String,String> map1 = JSONUtils.toObject(values,Map.class);
				redirect_params.putAll(map1);
			}
			String alibabaJsonString = JSONUtils.alibabaJsonString(redirect_params);
			LogUtil.info("pushT redirect_params:{}",alibabaJsonString);
			bean.setRedirect_params(alibabaJsonString);
			returnBean = pushMsgServiceFacade.push(bean);
		} catch (Exception e) {
			LogUtil.error("pushT dubbo loginKey:{},error",loginKey,e);
			returnBean  = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("pushT return loginKey:{},result:{}",loginKey, returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
}
