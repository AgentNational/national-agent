package com.pay.national.agent.common.bean.wx;

/**
 * Created by shuyan.qi on 2018/1/15.
 */
public class TextMessage extends BaseMessage{
        /**回复的消息内容
         *
         */
        private String Content;

    public TextMessage() {

    }

    public TextMessage(String fromUserName, String toUserName, String responseText, String messageType) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(System.currentTimeMillis());
        this.setMsgType(messageType);
        this.Content = responseText;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }



}
