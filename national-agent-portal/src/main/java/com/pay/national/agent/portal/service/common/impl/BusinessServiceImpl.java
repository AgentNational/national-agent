package com.pay.national.agent.portal.service.common.impl;

import com.pay.national.agent.common.bean.CreditCardUserModel;
import com.pay.national.agent.model.entity.CreditCardUser;
import com.pay.national.agent.portal.dao.common.CreditCardUserMapper;
import com.pay.national.agent.portal.service.common.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shuyan.qi on 2018/3/17.
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private CreditCardUserMapper creditCardUserMapper;

    /**
     * 导入用户数据
     * @param list
     */
    @Override
    public void importUser(List<CreditCardUserModel> list,String cardType) {
        if(list != null && list.size() >0){
            for (CreditCardUserModel model: list) {
                CreditCardUser creditCardUser = new CreditCardUser();
                creditCardUser.setBusinessCode(cardType);
                creditCardUser.setUserName(model.getUserName());
                creditCardUser.setPhoneNo(model.getPhoneNo());
                creditCardUser.setStatus("INIT");
                creditCardUser.setCreateTime(new Date());

                creditCardUserMapper.insert(creditCardUser);
            }
        }
    }
}
