package com.pay.national.agent.core.test.service;

import com.aliyuncs.exceptions.ClientException;
import com.pay.national.agent.core.service.common.SmsService;
import com.pay.national.agent.core.test.context.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class UserFacadeTest extends BaseTest{

    @Resource
    SmsService smsService;
    @Test
    public void test(){
        try {
            smsService.sendSms("rerewtewtersdg","17600695056");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
