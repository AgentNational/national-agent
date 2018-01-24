package com.pay.national.agent.core.test.service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.entity.BusinessOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shuyan.qi on 2018/1/25.
 */
public class BusinessServiceTest extends BaseTest {
    @Autowired
    private BusinessService businessService;

    @Test
    public void createOrderTest(){
        BusinessOrder businessOrder = new BusinessOrder();
        businessOrder.setUserNo("123455");
        businessOrder.setParentBusinessCode("CREDIT_CARD");
        businessOrder.setBusinessCode("PINGAN");
        businessOrder.setBusinessName("平安银行信用卡");
        businessOrder.setCustomerName("吴玉龙");
        businessOrder.setCustomerPhone("18260497637");
        String result = businessService.createOrder(businessOrder);
        LogUtil.info("method createOrderTest result={}",result);
    }

    @Test
    public void ordersTest(){
        Page<BusinessOrder> page = new Page<>();
        page.setCurrentPage(2);
        page.setShowCount(5);
        String result = businessService.orders("123455", "CREDIT_CARD", page);
        LogUtil.info("method ordersTest result={}",result);
    }
}
