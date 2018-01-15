package com.pay.national.agent.common.bean.wx;

import com.pay.national.agent.common.utils.WxMessageUtil;

import java.util.List;

/**
 * Created by shuyan.qi on 2018/1/15.
 */
public class NewsMessage extends BaseMessage{

    /**图文消息个数，限制为10条以内
     *
     */
    private int ArticleCount;
    /**多条图文消息信息，默认第一个item为大图
     *
     */
    private List<Article> Articles;

    public NewsMessage() {

    }

    public NewsMessage(String fromUserName, String toUserName, int articleCount, List<Article> articleList) {
        this.setFromUserName(fromUserName);
        this.setToUserName(toUserName);
        this.setCreateTime(System.currentTimeMillis());
        this.setMsgType(WxMessageUtil.RESP_MESSAGE_TYPE_NEWS);
        this.ArticleCount = articleList.size();
        this.Articles = articleList;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }



}
