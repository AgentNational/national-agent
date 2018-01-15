package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.bean.wx.TextMessage;
import com.pay.national.agent.common.constants.WxEventConstant;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.WxMessageUtil;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 处理微信事件
 * Created by shuyan.qi on 2018/1/15.
 */
@Controller
@RequestMapping("/weixinEvent")
public class WxEventController {
    //跟微信公众号配置的token一致
    private static final String TOKEN = "shuyan";
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private WxUserInfoService wxUserInfoService;

    /**
     * 处理微信的各种事件
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/event",method = RequestMethod.POST)
    public void test1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final Map<String, String> map = WxMessageUtil.parseXml(request);
        LogUtil.info("用户发送信息："+ JSON.toJSONString(map));
        PrintWriter writer = response.getWriter();
        StringBuffer returnStr = new StringBuffer();
        if (null != map){
            String msgType = map.get("MsgType");

            //事件
            if(WxMessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
                String event = map.get("Event");
                //关注事件
                if(WxEventConstant.EVENT_SUBSCRIBE.equals(event)){
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(map.get("FromUserName"));
                    textMessage.setCreateTime(System.currentTimeMillis());
                    textMessage.setFromUserName(map.get("ToUserName"));
                    textMessage.setMsgType(WxMessageUtil.RESP_MESSAGE_TYPE_TEXT);

                    //保存用户openId及订阅状态
                    WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(textMessage.getToUserName());
                    if(wxUserInfo == null){
                        wxUserInfo = new WxUserInfo();
                        wxUserInfo.setOpenid(textMessage.getToUserName());
                        wxUserInfo.setSubscribe("1");
                        wxUserInfo.setSubcribeTime(SDF.format(new Date()));
                        wxUserInfo.setCreatetime(new Date());
                        wxUserInfoService.insert(wxUserInfo);
                    }else{
                        wxUserInfo.setSubscribe("1");
                        wxUserInfo.setSubcribeTime(SDF.format(new Date()));
                        wxUserInfoService.update(wxUserInfo);
                    }

                    returnStr.append("   感谢关注shuyan.qi的微信测试公众号。\n")
                            .append("这是一个学习微信公众号开发的测试账号，回复\"1\",会将您的openId返回,回复\"2\",会将该公众号的openId返回。");
                    textMessage.setContent(returnStr.toString());
                    String s = WxMessageUtil.textMessageToXml(textMessage);
                    LogUtil.info("关注事件返回消息 return :{}",s);
                    writer.write(s);
                }

                //取消关注事件
                if(WxEventConstant.EVENT_UNSUBSCRIBE.equals(event)){
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(map.get("FromUserName"));
                    textMessage.setCreateTime(System.currentTimeMillis());
                    textMessage.setFromUserName(map.get("ToUserName"));
                    textMessage.setMsgType(WxMessageUtil.RESP_MESSAGE_TYPE_TEXT);

                    WxUserInfo wxUserInfo = wxUserInfoService.selectByOpenId(textMessage.getToUserName());
                    if(wxUserInfo != null){
                        wxUserInfo.setSubscribe("0");
                        wxUserInfoService.update(wxUserInfo);
                    }

                    returnStr.append("您已经取消关注本公众号");
                    textMessage.setContent(returnStr.toString());
                    String s = WxMessageUtil.textMessageToXml(textMessage);
                    LogUtil.info("取消关注事件返回消息 return :{}",s);
                    writer.write(s);
                }
            }

            //文本消息
            if (WxMessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(map.get("FromUserName"));
                textMessage.setCreateTime(System.currentTimeMillis());
                textMessage.setFromUserName(map.get("ToUserName"));
                textMessage.setMsgType(WxMessageUtil.RESP_MESSAGE_TYPE_TEXT);

                //用户发送的文本消息
                String customerContent = map.get("Content");

                if("1".equals(customerContent)) {
                    returnStr.append("用户OPEN_ID："+textMessage.getToUserName());
                }else if("2".equals(customerContent)){
                    returnStr.append("公众号OPEN_ID:"+textMessage.getFromUserName());
                }

                textMessage.setContent(returnStr.toString());
                String s = WxMessageUtil.textMessageToXml(textMessage);
                LogUtil.info("文本返回消息 return :{}",s);
                writer.write(s);
            }
        }
    }
}
