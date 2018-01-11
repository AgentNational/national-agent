package com.pay.national.agent.core.service.common.impl;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.service.common.AccountInterfaceService;
import com.pay.national.agent.model.beans.query.CreditParamBean;
import com.pay.national.agent.model.beans.results.UserAccountBean;

@Service("accountInterfaceService")
public class AccountInterfaceServiceImpl implements AccountInterfaceService{

	@Override
	public UserAccountBean findAccount(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void credit(CreditParamBean creditParamBean) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 账户查询处理接口
	 *//*
	@Resource
	private AccountQueryInterface accountQueryInterface;
	
	@Resource
	private AccountInterfaceVI accountInterfaceVI;

	@Override
	public UserAccountBean findAccount(String userNo) {
		AccountQuery query  = new AccountQuery();
		query.setInstitNo("PAY");//机构编码
		query.setUserNo(userNo);
		//TODO 待定
		query.setUserRole(UserRole.RECOMMENDER);
		AccountQueryResponse accountQueryResponse = null;
		try {
			accountQueryResponse = accountQueryInterface.findAccountBy(query);
		} catch (Exception e) {
			String s = "调用accountQueryInterface.findAccountBy() error";
			LogUtil.error("调用accountQueryInterface.findAccountBy() error", e);
			throw new RemitException("0200101", s,e);
		}
		
		if (accountQueryResponse == null) {
			String s = "accountQueryInterface.findAccountBy not success, accountQueryResponse is null, userNo:" + userNo;
			LogUtil.error(s);
			throw new RemitException("0200102", s);
		}

		if(!HandlerResult.SUCCESS.equals(accountQueryResponse.getResult())
				&& !HandlerResult.SUCCESS_01.equals(accountQueryResponse.getResult())) {
			String s = "accountQueryInterface.findAccountBy not success, userNo:" + userNo + ",status:" + accountQueryResponse.getResult() + ",msg:" + accountQueryResponse.getResultMsg();
			LogUtil.error(s);
			throw new RemitException("0200103", s);
		}

		List<AccountBean> accountBeanList = accountQueryResponse.getAccountBeans();
		if(accountBeanList == null || accountBeanList.isEmpty()) {
			String s = "accountQueryInterface.findAccountBy not success, accountBeanList is empty, userNo:" + userNo;
			LogUtil.error(s);
			throw new RemitException("0200104", s);
		}
		if(accountBeanList.size() > 1) {
			String s = "accountQueryInterface.findAccountBy not success, too many account found, userNo:" + userNo + ",account count " + accountBeanList.size();
			LogUtil.error(s);
			throw new RemitException("0200105", s);
		}
		AccountBean accountBean = accountBeanList.get(0);
		UserAccountBean myAccountBean = new UserAccountBean();
		myAccountBean.setUserNo(userNo);
		myAccountBean.setAccountNo(accountBean.getCode());
		myAccountBean.setBalance(accountBean.getBalance());
		myAccountBean.setFreezeBalance(accountBean.getFreezeBalance());
		myAccountBean.setTransitBalance(accountBean.getTransitBalance());
		myAccountBean.setStatus(accountBean.getStatus() == null ? null : accountBean.getStatus().name());
		myAccountBean.setOpenDate(accountBean.getOpenDate());
		myAccountBean.setRemark(accountBean.getRemark());
		return myAccountBean;
	}

	@Override
	public void credit(CreditParamBean creditParamBean) {
		//成本
		Cost cost = new Cost();
		cost.setClearingDate(new Date());
		cost.setFundChannelCode(creditParamBean.getFundChannelCode());
		
		Pay pay = new Pay();
		pay.setCost(cost);
		pay.setAmount(creditParamBean.getAmount());
		pay.setUserNo(creditParamBean.getUserNo());
		pay.setUserRole(UserRole.RECOMMENDER);
		pay.setBussinessCode(creditParamBean.getBussinessCode());
		pay.setTransDate(creditParamBean.getTransDate());
		pay.setTransOrder(creditParamBean.getSystemFlowId());
		pay.setAmount(creditParamBean.getAmount());
		pay.setProductOrg("OFFLINE");//线下，先写死
		pay.setWaitAccountDate(creditParamBean.getTransDate());	
		
		
		AccountRequestV requestV = new AccountRequestV();
		requestV.setTradeVoucher(pay);
		requestV.setSystemCode(Constants.SYSTEM_CODE);
		requestV.setSystemFlowId(creditParamBean.getSystemFlowId());
		requestV.setRequestTime(new Date());
		requestV.setOperator(creditParamBean.getOperator());
		requestV.setRemark(creditParamBean.getRemark());
		//成本
		Cost cost = new Cost();
		cost.setClearingDate(new Date());
		cost.setFundChannelCode(creditParamBean.getFundChannelCode());
		
		//入账主体
		List<Pay> pays = new ArrayList<Pay>();
		Pay pay = new Pay();
		pay.setCost(cost);
		pay.setUserNo(creditParamBean.getUserNo());
		pay.setUserRole(UserRole.RECOMMENDER);
		pay.setBussinessCode(creditParamBean.getBussinessCode());
		pay.setTransDate(creditParamBean.getTransDate());
		pay.setTransOrder(creditParamBean.getSystemFlowId());
		pay.setAmount(creditParamBean.getAmount());
		pay.setProductOrg("OFFLINE");
		pay.setWaitAccountDate(creditParamBean.getTransDate());
		
		pays.add(pay);
		
		Profit profit = new Profit();
		profit.setPays(pays);
		
		AccountRequestV requestV = new AccountRequestV();
		requestV.setSystemCode(Constants.SYSTEM_CODE);
		requestV.setSystemFlowId(creditParamBean.getSystemFlowId());
		requestV.setRequestTime(new Date());
		requestV.setOperator(creditParamBean.getOperator());
		requestV.setRemark(creditParamBean.getRemark());

		requestV.setTradeVoucher(profit);
		
		AccountProfitResponse response = null;
		try {
			response = accountInterfaceVI.profit(requestV);
			LogUtil.info("入账结果 :{}",response);
			if((!HandlerResult.SUCCESS.equals(response.getResult()) 
					|| !Status.SUCCESS.equals(response.getStatus()))
					&& !HandlerResult.SUCCESS_01.equals(response.getResult())) {
				String s = "accountInterface.profit not success, " + creditParamBean + ",result:" + response.getResult() + ",status:" + response.getStatus() + ",msg:" + response.getResultMsg();
				LogUtil.error(s);
				throw new NationalAgentException(RetCodeConstants.ERROR, s);
			}
		} catch (Exception e) {
			LogUtil.error("入账异常:", e);
			throw new NationalAgentException(RetCodeConstants.ERROR, "入账异常");
		}
	}*/

}
