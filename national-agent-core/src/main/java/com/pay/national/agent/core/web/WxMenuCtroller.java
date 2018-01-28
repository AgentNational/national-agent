package com.pay.national.agent.core.web;

import com.pay.national.agent.common.annotation.NeedOpenId;
import com.pay.national.agent.common.constants.WeiXinConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wxMenu")
public class WxMenuCtroller {

    @NeedOpenId(needOpenId = true,isLoginFlag = false)
    @RequestMapping(value = "menu/{menuNo}")
    public void menuRedirect(HttpServletRequest request, HttpServletResponse response, @PathVariable("menuNo")String menuNo) throws IOException {
       System.out.println(menuNo);
        //获取openId
        String openId = (String) request.getSession().getAttribute(WeiXinConstant.sessinoOpenIdKey);
        System.out.println(openId);
        //TODO 通过menuNo查询menu信息
        String uri = "http://www.baidu.com?";
        //根据查询到的url信息进行重定向
        String url = uri +"openId="+openId;
        response.sendRedirect(url);
    }
}
