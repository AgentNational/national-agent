package com.pay.national.agent.core.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.BeanUtils;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.PosFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.beans.results.PosBusinessListBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.PosOrder;
import com.pay.national.agent.model.entity.User;

/**
 * @Description: pos业务相关controller
 * @see: 需要参考的类
 * @version 2017年10月10日 下午2:45:36
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/pos")
@CrossOrigin
public class PosController {

	@Resource
	private PosFacade posFacade;
	/**
	 * @Description pos业务列表信息
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/businessList",method=RequestMethod.POST)
	public @ResponseBody String businessList(HttpServletRequest request,@RequestParam("loginKey") String loginKey){
		LogUtil.info(" pos业务列表查询开始 loginKey:{}",loginKey);
		ReturnBean<PosBusinessListBean> returnBean = new ReturnBean<>();
		try {
			String userNo = null;
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			if(user != null){
				userNo = user.getUserNo();
			}
			returnBean = posFacade.businessList(userNo);
		} catch (Exception e) {
			LogUtil.error(" pos业务列表查询异常",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * @Description 发送领取pos验证码。
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value="/sendCheckCode",method=RequestMethod.POST)
	@ParamsValidate(value = {@ParamValidate(name = "phoneNo", required = true),
			@ParamValidate(name="osType",required = true),
			@ParamValidate(name="version",required = true)})
	public @ResponseBody String sendCheckCode(HttpServletRequest request,
			@RequestParam Map<String, String> params){
		LogUtil.info("method sendCheckCode start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			//调用core dubbo接口发送验证码
			return posFacade.sendCheckCode(params);
		} catch (Exception e) {
			//调用dubbo服务异常
			LogUtil.error("method sendCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * pos业务办理
	 */
	@RequestMapping(value = "/businessProcessing",method=RequestMethod.POST)
	public @ResponseBody String businessProcessing(HttpServletRequest request,@RequestParam Map<String, Object> params){
		LogUtil.info("pos办理start params:{}",params);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		PosBusinessParamBean posBusinessParamBean = new PosBusinessParamBean();
		BeanUtils.transMap2Beannew(params, posBusinessParamBean);
		try {
			Long lock = CacheUtils.setnx(RedisKeys.POS_REPEAT_LOCK+posBusinessParamBean.getBusinessCode()+posBusinessParamBean.getPhoneNo(), "transactBusinessLock");
			if(lock != 0l){
				CacheUtils.expire(RedisKeys.POS_REPEAT_LOCK+posBusinessParamBean.getBusinessCode()+posBusinessParamBean.getPhoneNo(), 60);
				return posFacade.businessProcessing(posBusinessParamBean);
			}else{
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("请求正在处理，请勿重复提交！");
			}
		} catch (Exception e) {
			LogUtil.error("卡友手刷办理error",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}finally {
			CacheUtils.del(RedisKeys.POS_REPEAT_LOCK+posBusinessParamBean.getBusinessCode()+posBusinessParamBean.getPhoneNo());
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 订单查询
	 */
	@RequestMapping(value = "/queryOrders",method=RequestMethod.POST)
	public @ResponseBody String queryOrders(HttpServletRequest request,@RequestParam("loginKey")String loginKey,@RequestParam(value="pageNo",required=false)Integer pageNo){
		LogUtil.info("pos订单查询 start loginKey:{},pageNo:{}",loginKey,pageNo);
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			PosOrderQueryBean posOrderQueryBean = new PosOrderQueryBean();
			if(user != null){
				posOrderQueryBean.setUserNo(user.getUserNo());
			}
			Page<List<PosOrder>> page = new Page<List<PosOrder>>();
			page.setCurrentPage(pageNo == null?0:pageNo);
			page.setShowCount(10);
			return posFacade.queryOrders(page,posOrderQueryBean);
		} catch (Exception e) {
			LogUtil.error("pos订单查询 error",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
			return JSONUtils.alibabaJsonString(returnBean);
		}
	}
}

