package com.pay.national.agent.core.test.service;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void  main (String [] args){
        ReturnBean<Map<String,String>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        Map<String,String> result = new HashMap<>();
        result.put("timeStamp", "dsafdasfds");
        result.put("nonceStr", "fdsagdsagf");
        result.put("packageValue", "fdsagdfsag");
        result.put("sign", "gfdsagfsag");
        result.put("outerTradeNo", "fsagdadgasd");
        returnBean.setData(result);
        System.out.println(JSON.toJSONString(returnBean));
    }
}
