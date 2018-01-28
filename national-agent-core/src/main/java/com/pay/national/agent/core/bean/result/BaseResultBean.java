package com.pay.national.agent.core.bean.result;

import java.io.Serializable;

/**
 * Created by shuyan.qi on 2018/1/25.
 */
public class BaseResultBean implements Serializable{
    private static final long serialVersionUID = -6424527198527846259L;
    /**
     * 用户编号
     */
    private String userNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "BaseResultBean{" +
                "userNo='" + userNo + '\'' +
                '}';
    }
}
