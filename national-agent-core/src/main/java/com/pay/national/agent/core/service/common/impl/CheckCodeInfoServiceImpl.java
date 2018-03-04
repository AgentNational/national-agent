package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.core.dao.common.CheckCodeInfoMapper;
import com.pay.national.agent.core.service.common.CheckCodeInfoService;
import com.pay.national.agent.model.entity.CheckCodeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("checkCodeInfoService")
public class CheckCodeInfoServiceImpl implements CheckCodeInfoService {


    @Resource
    private CheckCodeInfoMapper checkCodeInfoMapper;

    @Override
    public CheckCodeInfo getEffectCheckCode(String openId, String phoneNo, Date date) {
        return checkCodeInfoMapper.getEffectCheckCode(openId,phoneNo,date);
        //return null;
    }

    @Override
    public void insert(CheckCodeInfo checkCodeInfo) {
        checkCodeInfoMapper.insert(checkCodeInfo);
    }
}
