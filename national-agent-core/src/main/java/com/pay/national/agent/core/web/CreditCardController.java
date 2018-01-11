package com.pay.national.agent.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.commons.cache.util.CacheUtils;
import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.core.facade.CreditCardFacade;
import com.pay.national.agent.model.annotation.ParamValidate;
import com.pay.national.agent.model.annotation.ParamsValidate;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.entity.User;

/**
 * 信用卡业务controller
 * @author shuyan.qi
 * Date:2017年9月11日上午6:17:04
 */
@Controller
@CrossOrigin
@RequestMapping("/creditCard")
public class CreditCardController {
	
	@Resource
	private CreditCardFacade creditCardFacade;
	
	/**
	 * 信用卡业务列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/businessList",method=RequestMethod.POST)
	@ResponseBody
	public String businessList(@RequestParam(value="loginKey",required=false)String loginKey){
		LogUtil.info("businessList start parsms loginKey:{}",loginKey);
		ReturnBean<CreditCardBusinessListBean> returnBean = null;
		try {
			String userNo = null;
			if(com.pay.national.agent.common.utils.StringUtils.isNotBlank(loginKey)){
				User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
				userNo = user.getUserNo();
			}
			
			returnBean = creditCardFacade.businessList(userNo);
		} catch (Exception e) {
			LogUtil.error("businessList dubbo loginKey:{} error :{}",loginKey,e);
			returnBean = new ReturnBean<>(RetCodeConstants.ERROR, "列表加载异常");
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
		}
		LogUtil.info("businessList return loginKey:{},result:{}",returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 办理业务
	 * @param userNo
	 * @param businessCode
	 * @param customerName
	 * @param phone
	 * @return
	 */
	@ParamsValidate(value = {@ParamValidate(name = "userNo", required = true),@ParamValidate(name = "businessCode", required = true),
			@ParamValidate(name = "customerName", required = true),@ParamValidate(name = "customerPhone", required = true)})
	@RequestMapping(value="/transact",method=RequestMethod.POST)
	@ResponseBody
	public String transactBusiness(@RequestParam("userNo") String userNo,@RequestParam("businessCode") String businessCode,
			@RequestParam("customerName") String customerName,@RequestParam("customerPhone")String customerPhone){
		LogUtil.info("transactBusiness start param: userNo:{},businessCode:{},customerName:{},customerPhone:{}",userNo,businessCode,customerName,customerPhone);
		ReturnBean<String> returnBean = null;
		if(StringUtils.isBlank(customerName) || StringUtils.isBlank(customerPhone)){
			returnBean = new ReturnBean<>(RetCodeConstants.FAIL,"姓名和手机号为必填项");
		}else{
			try {
				Long lock = CacheUtils.setnx(businessCode+userNo+customerPhone, "transactBusinessLock");
				if(lock != 0l){
					CacheUtils.expire(businessCode+userNo+customerPhone, 10);
					returnBean = creditCardFacade.transactBusiness(userNo,businessCode,customerName,customerPhone);
				}else{
					returnBean = new ReturnBean<>(RetCodeConstants.FAIL,"请求正在处理，请勿重复提交");
				}
			} catch (Exception e) {
				LogUtil.error("transactBusiness dubbo userNo:{} customerPhone:{},error:{}",userNo,customerPhone,e);
				returnBean = new ReturnBean<>(RetCodeConstants.ERROR,RetCodeConstants.ERROR_DESC);
				returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
			}finally{
				CacheUtils.del(businessCode+userNo+customerPhone);
			}
		}
		LogUtil.info("transactBusiness return userNo:{},customerPhone:{},result:{}",userNo,customerPhone,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	/**
	 * 订单查询
	 * @param loginKey
	 * @return
	 */
	@RequestMapping(value="/orders",method=RequestMethod.POST)
	@ResponseBody
	public String orders(@RequestParam("loginKey") String loginKey,@RequestParam(value="pageNo",required=false) Integer pageNo){
		LogUtil.info("orders start param: loginKey:{},pageNo:{}",loginKey,pageNo);
		ReturnBean<Page<List<CreditCardOrder>>> returnBean = null;
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			CreditOrderQueryBean query = new CreditOrderQueryBean();
			query.setUserNo(user.getUserNo());
			Page<List<CreditCardOrder>> page = new Page<List<CreditCardOrder>>();
			page.setCurrentPage(pageNo == null?0:pageNo);
			page.setShowCount(10);
			returnBean = creditCardFacade.queryOrders(query,page);
		} catch (Exception e) {
			LogUtil.error("orders dubbo loginKey:{},error:{}",loginKey,e);
			returnBean = new ReturnBean<Page<List<CreditCardOrder>>>();
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode(RetCodeConstants.DUBOO_ERROR_CODE);
			returnBean.setMsg("查询异常");
		}
		
		LogUtil.info("orders return  loginKey:{},result:{}",loginKey,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
}
