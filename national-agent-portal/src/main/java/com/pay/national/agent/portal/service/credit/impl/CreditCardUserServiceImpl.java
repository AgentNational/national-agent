package com.pay.national.agent.portal.service.credit.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.common.bean.CreditCardUserModel;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.CreditCardUser;
import com.pay.national.agent.model.enums.CreditCardUserStatus;
import com.pay.national.agent.portal.dao.credit.CreditCardUserMapper;
import com.pay.national.agent.portal.service.credit.CreditCardUserService;

@Service("creditCardUserService")
public class CreditCardUserServiceImpl implements CreditCardUserService{
	@Resource
	private CreditCardUserMapper creditCardUserMapper;

	@Override
	public void importUser(List<CreditCardUserModel> list, String creditCardType) {
		if(null != list && !list.isEmpty()){
			for (CreditCardUserModel model : list) {
				imports(model,creditCardType);
			}
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private  void imports(CreditCardUserModel model,String creditCardType){
		try {
			CreditCardUser creditCardUser = new CreditCardUser();
			creditCardUser.setPhoneNo(model.getPhoneNo());
			creditCardUser.setUserName(model.getUserName());
			creditCardUser.setStatus(CreditCardUserStatus.IMPORT_INIT);
			creditCardUser.setCardType(creditCardType);
			creditCardUser.setCreateTime(new Date());
			creditCardUserMapper.insert(creditCardUser);
		} catch (Exception e) {
			LogUtil.error("import creditCard user info error:{}",e);
			throw e;
		}
	}

}
