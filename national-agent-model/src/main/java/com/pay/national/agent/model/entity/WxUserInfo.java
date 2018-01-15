package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuyan.qi
 */
public class WxUserInfo implements Serializable{
    private static final long serialVersionUID = 5915463841245114769L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer optimistic;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private String subscribe;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;

    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户头像，
     * 最后一个数值代表正方形头像大小 有0、46、64、96、132数值可选，0代表640*640正方形头像
     * 用户没有头像时该项为空
     */
    private String headimgurl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private String subcribeTime;

    /**
     * 关联系统用户ID
     */
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setSubcribeTime(String subcribeTime) {
        this.subcribeTime = subcribeTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getSubcribeTime() {
        return subcribeTime;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "WxUserInfo{" +
                "id=" + id +
                ", optimistic=" + optimistic +
                ", subscribe='" + subscribe + '\'' +
                ", createtime=" + createtime +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", subcribeTime='" + subcribeTime + '\'' +
                ", userId=" + userId +
                '}';
    }
}