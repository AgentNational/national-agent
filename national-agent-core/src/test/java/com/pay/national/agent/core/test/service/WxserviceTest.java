package com.pay.national.agent.core.test.service;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.bean.wx.WxJssdkConfig;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.HttpClientUtil;
import com.pay.national.agent.core.service.wx.EnterPrisePaymentService;
import com.pay.national.agent.core.service.wx.MenuInfoService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.core.service.wx.impl.WxJssdkConfigMethod;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.wx.FatherButton;
import com.pay.national.agent.model.beans.wx.Menu;
import com.pay.national.agent.model.beans.wx.SonButton;
import com.pay.national.agent.model.entity.MenuInfo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxserviceTest extends BaseTest{

    @Resource
    private WxService wxService;

    @Resource
    WxJssdkConfigMethod wxJssdkConfigMethod;

    @Resource
    EnterPrisePaymentService enterPrisePaymentService;

    @Test
    public void testWxpayBill(){
        Map<String,String> map = new HashMap<>();
        map.put("openId","dsfeqwrtgdfs");
        map.put("amount","100");
        enterPrisePaymentService.createPayBill(map);
    }

    @Test
    public void testGetConfig(){
        WxJssdkConfig wxJssdkConfig = wxJssdkConfigMethod.getWxJssdkConfig("http://www.baidu.com");
        System.out.println(JSON.toJSONString(wxJssdkConfig));
    }

    @Test
    public void test(){
        String result = wxService.getEffectAccessToken(WeiXinConstant.APP_ID, WeiXinConstant.APP_SECRET);
		System.out.println(result);
//        String content = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"sfgerjgreu\"}}}";
//        System.out.println(content);
//        String ticket = wxService.createQRCode(result,content);
//        System.out.println(ticket);

    }

    @Test
    public void createCommMenu() {
        String ACCESS_TOKEN = wxService.getEffectAccessToken(WeiXinConstant.APP_ID, WeiXinConstant.APP_SECRET);// 获取AccessToken，AccessTokenUtils是封装好的类
        // 拼接api要求的httpsurl链接
        String urlString = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                + ACCESS_TOKEN;
        try {
            String result = HttpClientUtil.sendPost(urlString,getDbMenuJson());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    private MenuInfoService menuInfoService;

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

    public String getMenuJson() {

        Menu menu = new Menu();// 菜单类
        List<FatherButton> fatherButtons = new ArrayList<>();// 菜单中的父按钮集合
        // -----------
        // 父按钮1
        FatherButton fb1 = new FatherButton();
        fb1.setName("click");
        fb1.setType("click");
        fb1.setKey("10");
        // -------------
        // 父按钮2
        FatherButton fb2 = new FatherButton();
        fb2.setName("父按钮2");
        List<SonButton> sonButtons2 = new ArrayList<SonButton>();// 子按钮的集合

        // 子按钮2-1
        SonButton sb21 = new SonButton();
        sb21.setName("view");
        sb21.setUrl("http://adfd160c.ngrok.io/wxMenu/menu/1");
        sb21.setType("view");
        // 子按钮2-2
        SonButton sb22 = new SonButton();
        sb22.setName("scancode_push");
        sb22.setType("scancode_push");
        sb22.setKey("22");
        // 子按钮2-3
        SonButton sb23 = new SonButton();
        sb23.setName("scancode_waitmsg");
        sb23.setType("scancode_waitmsg");
        sb23.setKey("23");
        // 子按钮2-4
        SonButton sb24 = new SonButton();
        sb24.setName("pic_sysphoto");
        sb24.setType("pic_sysphoto");
        sb24.setKey("24");
        // 子按钮2-5
        SonButton sb25 = new SonButton();
        sb25.setName("pic_photo_or_album");
        sb25.setType("pic_photo_or_album");
        sb25.setKey("25");

        // 添加子按钮到子按钮集合
        sonButtons2.add(sb21);
        sonButtons2.add(sb22);
        sonButtons2.add(sb23);
        sonButtons2.add(sb24);
        sonButtons2.add(sb25);

        // 将子按钮放到2-0父按钮集合
        fb2.setSonButtons(sonButtons2);

        // ------------------
        // 父按钮3
        FatherButton fb3 = new FatherButton();
        fb3.setName("父按钮3");
        List<SonButton> sonButtons3 = new ArrayList<SonButton>();

        // 子按钮3-1
        SonButton sb31 = new SonButton();
        sb31.setName("pic_weixin");
        sb31.setType("pic_weixin");
        sb31.setKey("31");
        // 子按钮3-2
        SonButton sb32 = new SonButton();
        sb32.setName("locatselect");
        sb32.setType("location_select");
        sb32.setKey("32");
        // // 子按钮3-3-->测试不了，因为要media_id。这需要调用素材id.
        // SonButton sb33 = new SonButton();
        // sb33.setName("media_id");
        // sb33.setType("media_id");
        // sb33.setMedia_id("???");
        // // 子按钮3-4-->测试不了，因为要media_id。这需要调用素材id.
        // SonButton sb34 = new SonButton();
        // sb34.setName("view_limited");
        // sb34.setType("view_limited");
        // sb34.setMedia_id("???");

        // 添加子按钮到子按钮队列
        sonButtons3.add(sb31);
        sonButtons3.add(sb32);
        // sonButtons3.add(sb33);
        // sonButtons3.add(sb34);

        // 将子按钮放到3-0父按钮队列
        fb3.setSonButtons(sonButtons3);
        // ---------------------

        // 将父按钮加入到父按钮集合
        fatherButtons.add(fb1);
        fatherButtons.add(fb2);
        fatherButtons.add(fb3);

        // 将父按钮队列加入到菜单栏
        menu.setFatherButtons(fatherButtons);
        String json = JSON.toJSONString(menu);
        System.out.println(json);// 测试输出
        return json;

    }



}
