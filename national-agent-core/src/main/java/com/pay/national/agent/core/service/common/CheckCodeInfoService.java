package com.pay.national.agent.core.service.common;

import com.pay.national.agent.model.entity.CheckCodeInfo;

import java.util.Date;

public interface CheckCodeInfoService {

    /**
     * 查询生效的
     * @param openId
     * @param phoneNo
     * @return
     */
    CheckCodeInfo getEffectCheckCode(String openId, String phoneNo, Date date);

    /**
     * 插入
     * @param checkCodeInfo
     */
    void insert(CheckCodeInfo checkCodeInfo);
}
