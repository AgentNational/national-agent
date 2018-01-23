package com.pay.national.agent.core.web;

import com.pay.national.agent.common.annotation.NeedOpenId;
import com.pay.national.agent.model.constants.WeixinConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wxMenu")
public class WxMenuCtroller {

    @NeedOpenId(needOpenId = true,isLoginFlag = false)
    @RequestMapping(value = "menu/{menuNo}")
    public String menuRedirect(HttpServletRequest request, @PathVariable("menuNo")String menuNo){
       System.out.println(menuNo);
        //获取openId
        String openId = (String) request.getSession().getAttribute(WeixinConstants.sessinoOpenIdKey);
        System.out.println(openId);
        //TODO 通过menuNo查询menu信息
        String uri = "http://www.baidu.com";
        //根据查询到的url信息进行重定向
        String url = uri +"openId="+openId;
        return "redirect:"+url;
    }
}
