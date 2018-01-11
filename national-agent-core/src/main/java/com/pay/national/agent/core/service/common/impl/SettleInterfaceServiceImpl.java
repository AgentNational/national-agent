package com.pay.national.agent.core.service.common.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.service.common.SettleInterfaceService;
import com.pay.national.agent.model.beans.results.UserSettleCardBean;
import com.pay.national.agent.model.beans.results.UserSettleRuleBean;

@Service("settleInterfaceService")
public class SettleInterfaceServiceImpl implements SettleInterfaceService{

	@Override
	public UserSettleRuleBean findSettleRule(String userNo, String accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserSettleCardBean> findSettleCards(String userNo, String accountNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Resource
	private SettleQueryInterface settleQueryInterface;

	@Override
	public UserSettleRuleBean findSettleRule(String userNo, String accountNo) {
		OwnerParam ownerParam = new OwnerParam(userNo,"RECOMMENDER");
		SettleOrgParam settleOrgParam = new SettleOrgParam("PAY",null);
		CustomerSettleRuleBean remoteSettleRule = null;
		try {
			remoteSettleRule = settleQueryInterface.queryCustomerSettleRule(ownerParam, settleOrgParam);
		} catch (Exception e) {
			String s = "调用 settleQueryInterface.queryCustomerSettleRule() error";
			LogUtil.error(s, e);
			throw new RemitException("0200106", s);
		}
		
		if(null != remoteSettleRule){
			UserSettleRuleBean settleRuleBean = new UserSettleRuleBean();
			settleRuleBean.setUserNo(userNo);
			settleRuleBean.setAccountNo(accountNo);
			settleRuleBean.setId(remoteSettleRule.getId());
			settleRuleBean.setStatus(remoteSettleRule.getStatus());
			settleRuleBean.setStartSettleAmount(remoteSettleRule.getStartSettleAmount());
			
			return settleRuleBean;
		}else{
			return null;
		}
	}

	@Override
	public List<UserSettleCardBean> findSettleCards(String userNo, String accountNo) {
		OwnerParam ownerParam = new OwnerParam(userNo,"RECOMMENDER");
		SettleOrgParam settleOrgParam = new SettleOrgParam("PAY",null);
		List<SettleCardBean> remoteSettleCards = null;
		try {
			remoteSettleCards = settleQueryInterface.querySettleCardByOwner(ownerParam, settleOrgParam);
		} catch (Exception e) {
			String s = "调用 settleQueryInterface.querySettleCardByOwner() error";
			LogUtil.error(s, e);
			throw new RemitException("0200107", s);
		}
		
		if(null != remoteSettleCards && !remoteSettleCards.isEmpty()){
			List<UserSettleCardBean> settleCards = new ArrayList<UserSettleCardBean>();
			for (SettleCardBean remoteSettleCard : remoteSettleCards) {
				UserSettleCardBean settleCard = new UserSettleCardBean();
				settleCard.setUserNo(userNo);
				settleCard.setAccountNo(accountNo);
				settleCard.setAlliedBankCode(remoteSettleCard.getAlliedBankCode());
				settleCard.setBankAccountName(remoteSettleCard.getBankAccountName());
				settleCard.setBankCode(remoteSettleCard.getBankCode());
				settleCard.setBankName(remoteSettleCard.getBankName());
				settleCard.setBankAccountNo(remoteSettleCard.getBankAccountNo());
				settleCard.setCardBin(remoteSettleCard.getCardBin());
				settleCard.setCardCategory(remoteSettleCard.getCardCategory());
				settleCard.setCardType(remoteSettleCard.getCardType());
				settleCard.setCity(remoteSettleCard.getCity());
				settleCard.setOrgId(remoteSettleCard.getOrgId());
				settleCard.setOwnBankCode(remoteSettleCard.getOwnBankCode());
				settleCard.setPriority(remoteSettleCard.getPriority());
				settleCard.setProvince(remoteSettleCard.getProvince());
				settleCard.setSabkCode(remoteSettleCard.getSabkCode());
				settleCard.setSettleCardId(remoteSettleCard.getId());
				settleCard.setStatus(remoteSettleCard.getStatus());
				
				settleCards.add(settleCard);
			}
			return settleCards;
		}else{
			return null;
		}
	}*/

}
