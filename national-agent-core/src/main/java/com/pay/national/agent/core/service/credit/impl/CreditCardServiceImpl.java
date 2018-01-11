package com.pay.national.agent.core.service.credit.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.core.dao.credit.CreditCardBusinessMapper;
import com.pay.national.agent.core.dao.credit.CreditCardOrderMapper;
import com.pay.national.agent.core.dao.credit.CreditCardUserMapper;
import com.pay.national.agent.core.service.credit.CreditCardService;
import com.pay.national.agent.model.beans.CreditCardOrderMatchBean;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.results.CreditCardBusinessListBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.CreditCardBusiness;
import com.pay.national.agent.model.entity.CreditCardOrder;
import com.pay.national.agent.model.entity.CreditCardUser;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.CreditCardOrderStatus;

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService{
	private static final String ORDER_PREFIX = "CC_";
	
	@Resource
	private CreditCardBusinessMapper creditCardBusinessMapper;
	
	@Resource
	private CreditCardOrderMapper creditCardOrderMapper;
	
	@Resource
	private CreditCardUserMapper creditCardUserMapper;

	@Override
	public ReturnBean<CreditCardBusinessListBean> businessList(String userNo) {
		ReturnBean<CreditCardBusinessListBean> returnBean = new ReturnBean<CreditCardBusinessListBean>();
		CreditCardBusinessListBean ccbusBean = null;
		List<CreditCardBusiness> list = null;
		
		try {
			list = creditCardBusinessMapper.businessList();
			if(null != list){
				ccbusBean = new CreditCardBusinessListBean();
				ccbusBean.setUserNo(userNo);
				ccbusBean.setBusList(list);
			}
		} catch (Exception e) {
			LogUtil.error("businessList userNo:{} error:{}",userNo,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0500101");
			returnBean.setMsg("加载信用卡业务列表异常");
			return returnBean;
		}
		returnBean.setData(ccbusBean);
		returnBean.setCode(RetCodeConstants.SUCCESS);
		returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		return returnBean;
	}

	@Override
	public ReturnBean<String> createOrder(String userNo, ChildBusinessCode businessCode, String customerName,
			String customerPhone) {
		ReturnBean<String> returnBean = new ReturnBean<String>();
		try {
			CreditCardBusiness business = creditCardBusinessMapper.findByCode(businessCode.name());
			if(null == business){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("对不起,你所办理的业务不存在或已下架");
				return returnBean;
			}
			
			CreditCardOrder order = creditCardOrderMapper.findApplyOrder(businessCode.name(),customerPhone);
			if(null == order){
				order = new CreditCardOrder();
				order.setUserNo(userNo);
				order.setBusinessCode(businessCode);
				order.setCustomerName(customerName);
				order.setCustomerPhone(customerPhone);
				order.setCradType(business.getBusinessName());
				order.setOrderNo(ORDER_PREFIX+System.currentTimeMillis()+CommonCodeUtil.NextInt(100000, 999999));
				order.setStatus(CreditCardOrderStatus.APPLY);
				order.setRewardAmount(business.getRewardAmount());
				order.setCreateTime(new Date());
				createOrder(order);
			}
			returnBean.setData(business.getLink());
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("createOrder 创建信用卡订单异常 userNo:{},customerPhone:{} ,error:{}",userNo,customerPhone,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0500201");
			returnBean.setMsg("订单创建异常");
		}
		return returnBean;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	private CreditCardOrder createOrder(CreditCardOrder order){
		creditCardOrderMapper.insert(order);
		return order;
	}
	
	@Override
	public ReturnBean<Page<List<CreditCardOrder>>> queryOrders(CreditOrderQueryBean query,
			Page<List<CreditCardOrder>> page) {
		ReturnBean<Page<List<CreditCardOrder>>> returnBean = new ReturnBean<Page<List<CreditCardOrder>>>();
		try {
			List<CreditCardOrder> list = creditCardOrderMapper.findAllOrders(query,page);
			page.setObject(list);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setData(page);
		} catch (Exception e) {
			LogUtil.error("queryOrders 查询信用卡订单列表异常CreditOrderQueryBean:{}  error:{}",query,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0500301");
			returnBean.setMsg("查询异常");
		}
		return returnBean;
	}
	
	@Override
	public List<CreditCardOrderMatchBean> matchOrder() {
		return creditCardOrderMapper.match();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateOrder(CreditCardOrder order) {
		creditCardOrderMapper.updateByPrimaryKey(order);
	}
	
	@Override
	public CreditCardUser findCreditCardUser(Integer creditCardUserId) {
		return creditCardUserMapper.selectByPrimaryKey(creditCardUserId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateCreditCardUser(CreditCardUser creditCardUser) {
		creditCardUserMapper.updateByPrimaryKey(creditCardUser);
	}
}
