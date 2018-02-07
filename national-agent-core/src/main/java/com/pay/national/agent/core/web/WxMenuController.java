package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.annotation.NeedOpenId;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.wx.MenuInfoService;
import com.pay.national.agent.model.entity.MenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wxMenu")
public class WxMenuController {

    @Autowired
    private MenuInfoService menuInfoService;

    @NeedOpenId(needOpenId = true,isLoginFlag = false)
    @RequestMapping(value = "menu/{menuNo}")
    public void menuRedirect(HttpServletRequest request, HttpServletResponse response, @PathVariable("menuNo")String menuNo) throws IOException {
        LogUtil.info("微信菜单统一重定向开始 menuNo:{}",menuNo);
        //获取openId
        String openId = (String) request.getSession().getAttribute(WeiXinConstant.sessinoOpenIdKey);
        System.out.println(openId);
        StringBuffer sb = new StringBuffer();
        try {
            MenuInfo menuInfo = menuInfoService.findMenuInfoByMenuNo(menuNo);
            LogUtil.info("通过menuNo查询菜单信息结果menuInfo:{}", JSON.toJSONString(menuInfo));
            if(menuInfo == null){
                //通过menuNo未查询到menu信息重定向到统一处理页面

            }else{
                String redirectUrl = menuInfo.getRedirectMenuUrl();//重定向地址
                redirectUrl = redirectUrl.replace("openIdZhanWeiFu",openId);
                sb.append(redirectUrl);
            }
        }catch (Exception e){

        }
        LogUtil.info("查询微信菜单重定向地址结束 menuNo:{},url:{}",menuNo,sb.toString());
        response.sendRedirect(sb.toString());
    }
}
