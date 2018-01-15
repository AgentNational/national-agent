package com.pay.national.agent.common.constants;

/**
 * 微信事件
 * Created by shuyan.qi on 2018/1/15.
 */
public class WxEventConstant {
    public static final String EVENT_SUBSCRIBE = "subscribe";//订阅事件
    public static final String EVENT_UNSUBSCRIBE = "unsubscribe";//取消订阅事件
    public static final String EVENT_SCAN = "SCAN";//扫描二维码事件

    /**
     * 上报地理位置事件
     */
    public static final String EVENT_LOCATION = "LOCATION";//上报地理位置事件

    /**
     * 自定义菜单事件
     */
    public static final String EVENT_CLICK = "CLICK";//点击菜单拉取消息时的事件推送
    public static final String EVENT_VIEW = "VIEW";//点击菜单跳转链接时的事件推送
}
