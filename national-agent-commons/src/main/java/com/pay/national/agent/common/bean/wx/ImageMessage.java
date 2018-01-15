package com.pay.national.agent.common.bean.wx;

/**
 * 回复的图文消息
 * Created by shuyan.qi on 2018/1/15.
 */
public class ImageMessage extends BaseMessage {
    /**图文消息名称
     *
     */
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }
}
