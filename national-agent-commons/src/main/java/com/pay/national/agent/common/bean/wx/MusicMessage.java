package com.pay.national.agent.common.bean.wx;

/**
 * 回复的音乐消息
 * Created by shuyan.qi on 2018/1/15.
 */
public class MusicMessage extends BaseMessage{
    /**
     *
     */
    private String Title;
    /**音乐描述
     *
     */
    private String Description;
    /**音乐链接
     *
     */
    private String MusicUrl;
    /**高质量音乐链接，WIFI环境优先使用该链接播放音乐
     *
     */
    private String HQMusicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String musicUrl) {
        HQMusicUrl = musicUrl;
    }
}
