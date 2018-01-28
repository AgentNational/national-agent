package com.pay.national.agent.core.test.service;

import com.pay.national.agent.core.service.common.CommonService;
import com.pay.national.agent.core.test.context.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shuyan.qi on 2018/1/28.
 */
public class CommonServiceTest extends BaseTest{
    @Autowired
    private CommonService commonService;

    /**
     * 查询地区-测试
     */
    @Test
    public void organizationsTest(){
        String result = commonService.organizations("34");
    }
}
