package com.pay.national.agent.core.facade.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.facade.PosFacade;
import com.pay.national.agent.core.service.common.SmsService;
import com.pay.national.agent.core.service.pos.PosService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.ReturnListBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.beans.results.PosBusinessListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.PosBusiness;
import com.pay.national.agent.model.entity.PosOrder;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.PosOrderStatus;
import com.pay.national.agent.model.enums.PosStatus;

@Service("posFacade")
public class PosFacadeImpl implements PosFacade{

	private static final String POS_ORDER_PREFIX = "PP_";
	@Resource
	private PosService posService;
	@Override
	public ReturnBean<PosBusinessListBean> businessList(String userNo) {
		LogUtil.info("查找所有的pos业务start userNo:{}",userNo);
		ReturnBean<PosBusinessListBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		PosBusinessListBean posBusinessListBean = new PosBusinessListBean(); 
		List<PosBusiness> list = new ArrayList<>();
		try {
			list = posService.businessList(userNo);
			posBusinessListBean.setPosBusinessList(list);
			posBusinessListBean.setUserNo(userNo);
			returnBean.setData(posBusinessListBean);
		} catch (Exception e) {
			LogUtil.error("查找所有的pos业务error",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		return returnBean;
	}
	/**
	 * 办理卡友手刷
	 */
	@Override
	public String businessProcessing(PosBusinessParamBean posBusinessParamBean) {
		LogUtil.info("办理卡友pos start posBusinessListBean：{}",posBusinessParamBean);
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		
		//验证短信验证码是否正确，订单是否已经存在（业务编码手机号校验唯一性）
		try {
			returnBean = posService.checkBusinessProcessing(posBusinessParamBean);
		} catch (Exception e) {
			LogUtil.error("办理卡友pos 校验error posBusinessListBean：{}",posBusinessParamBean,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("061105");
			returnBean.setMsg("校验信息异常！");
		}
		//若校验通过，切为卡友手刷业务，调用pospro生成采购单、订单。
		if(returnBean.getCode().equals(RetCodeConstants.SUCCESS)){
			
			//根据生成采购单数据生成订单
			PosOrder posOrder = new PosOrder();
			posOrder.setBusinessCode(ChildBusinessCode.valueOf(posBusinessParamBean.getBusinessCode()));
			posOrder.setRewardAmountTotal(120d);//
			posOrder.setCreateTime(new Date());
			Calendar calendar = Calendar.getInstance();
			Date date = new Date();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, 5);
			date = calendar.getTime();
			posOrder.setInvalidTime(date);
			posOrder.setCustomerPhone(posBusinessParamBean.getPhoneNo());
			posOrder.setLinkman(posBusinessParamBean.getLinkman());
			posOrder.setPosStatus(PosStatus.UNFILLED);
			posOrder.setReceiveAddress(posBusinessParamBean.getReceiveAddress());
			posOrder.setUserNo(posBusinessParamBean.getUserNo());
			posOrder.setStatus(PosOrderStatus.FALSE);
			posOrder.setOrderNo(POS_ORDER_PREFIX+System.currentTimeMillis()+CommonCodeUtil.NextInt(100000, 999999));
			try {
				posService.createPosOrder(posOrder);
			} catch (Exception e) {
				LogUtil.error("办理卡友pos error posBusinessListBean：{}",posBusinessParamBean,e);
				returnBean.setCode(RetCodeConstants.ERROR);
				returnBean.setErrorCode("066100");
				returnBean.setMsg("保存订单失败！");
				return JSONUtils.alibabaJsonString(returnBean);
			}
		}
		return JSONUtils.alibabaJsonString(returnBean);
	}
	/**
	 *查询所有的pos订单 
	 */
	@Override
	public String queryOrders(Page<List<PosOrder>> page, PosOrderQueryBean posOrderQueryBean) {
		LogUtil.info("查询所有的pos业务订单 start page:{},posOrderQueryBean:{}",page,posOrderQueryBean);
		ReturnListBean<List<PosOrder>> returnBean = new ReturnListBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			List<PosOrder> posOrders = posService.queryOrders(page,posOrderQueryBean);
			returnBean.setData(posOrders);
			returnBean.setTotalPage(page.getTotalPage());
			returnBean.setTotalResult(page.getTotalResult());
		} catch (Exception e) {
			LogUtil.error("查询所有的pos业务订单 error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg(RetCodeConstants.FAIL_DESC);
		}
		LogUtil.info("查询所有的pos业务订单 end returnBean:{}",returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}
	
	@Resource
	private SmsService smsService;
	/**
	 * 发生验证码
	 */
	@Override
	public String sendCheckCode(Map<String, String> params) {
		LogUtil.info("发生领取pos验证码 start params:{}",params);
		String phoneNo = params.get("phoneNo");
		ReturnBean<Object> returnBean = new ReturnBean<>();
		try {
			boolean result = smsService.sendKaYouPosCheckCode(phoneNo);
			if(result){//短信发送成功
			}else{//短信发送失败
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("短信发送失败!");
			}
		} catch (Exception e) {
			LogUtil.error("method sendRegisterCheckCode error ",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("短信发送失败!");
		}
		LogUtil.info("发生领取pos验证码end params:{},returnBean:{}",params,returnBean);
		return JSONUtils.alibabaJsonString(returnBean);
	}

}
