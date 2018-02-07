package com.pay.national.agent.core.test.service;

public class Test {

    public static void  main (String [] args){
        String redirectUrl = "http://git.javams.com/na-source/index.html?openId=openIdZhanWeiFu#/home";//重定向地址
        redirectUrl = redirectUrl.replace("openIdZhanWeiFu","12346465464");
        System.out.println(redirectUrl);
    }
}
