package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.wx.MenuInfoService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.beans.wx.FatherButton;
import com.pay.national.agent.model.beans.wx.Menu;
import com.pay.national.agent.model.beans.wx.SonButton;
import com.pay.national.agent.model.entity.MenuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private MenuInfoService menuInfoService;

    @Resource
    private WxService wxService;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public @ResponseBody  String hello(){
       return "hello";
    }

    @RequestMapping(value = "/testMenu",method = RequestMethod.GET)
    public @ResponseBody String testMenu(){
        String ACCESS_TOKEN = wxService.getEffectAccessToken(WeiXinConstant.APP_ID, WeiXinConstant.APP_SECRET);// 获取AccessToken，AccessTokenUtils是封装好的类
        // 拼接api要求的httpsurl链接
        String urlString = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                + ACCESS_TOKEN;
        try {
            String result = HttpClientUtil.sendPost(urlString,getDbMenuJson());
            LogUtil.info(result);
            JSONObject parseObject = JSON.parseObject(result);
            if (parseObject.containsKey("errmsg")) {
                String errmsg = (String) parseObject.get("errmsg");
                if (errmsg.equals("ok")) {
                    return "SUCCESS";
                }else{
                    return "FAIL";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROE";
    }

    public String getDbMenuJson(){
        //查找所有的父菜单
        Menu menu = new Menu();// 菜单类
        List<FatherButton> fatherButtons = new ArrayList<>();
        List<MenuInfo> fatherMenus = menuInfoService.findAllFatherMenu();
        //遍历父菜单
        for(MenuInfo menuInfo : fatherMenus){
            List<MenuInfo> childMenuInfos = menuInfoService.findChildMenuInfo(menuInfo.getId());
            List<SonButton> sonButtons = new ArrayList<>();
            for(MenuInfo childMenuInfo : childMenuInfos){
                SonButton sonButton = new SonButton();
                sonButton.setName(childMenuInfo.getMenuName());
                sonButton.setType(childMenuInfo.getMenuType());
                sonButton.setUrl(childMenuInfo.getWxMenuUrl());
            }
            FatherButton fatherButton = new FatherButton();
            fatherButton.setName(menuInfo.getMenuName());
            fatherButton.setType(menuInfo.getMenuType());
            fatherButton.setUrl(menuInfo.getWxMenuUrl());
            fatherButton.setSonButtons(sonButtons);
            fatherButtons.add(fatherButton);
        }
        menu.setFatherButtons(fatherButtons);
        System.out.println(JSON.toJSONString(fatherButtons));
        return JSON.toJSONString(menu);
    }
}
