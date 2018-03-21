package com.pay.national.agent.portal.service.common;

import com.pay.national.agent.common.bean.CreditCardUserModel;

import java.util.List;

/**
 * Created by shuyan.qi on 2018/3/17.
 */
public interface BusinessService {
    /**
     * 导入用户数据
     * @param list
     */
    void importUser(List<CreditCardUserModel> list,String cardType);
}
