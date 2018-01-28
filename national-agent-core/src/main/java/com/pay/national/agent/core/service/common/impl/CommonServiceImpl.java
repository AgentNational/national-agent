package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.OrganizationMapper;
import com.pay.national.agent.core.service.common.CommonService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 查询地区
     * @param parentOrganizationCode 父地区编码
     * @return
     */
    @Override
    public String organizations(String parentOrganizationCode) {
        ReturnBean<List<Organization>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        List<Organization> organizationList = null;
        try {
            if(StringUtils.isBlank(parentOrganizationCode)){
                organizationList = organizationMapper.allProvinces();
            }else{
                organizationList = organizationMapper.allChildOrganizations(parentOrganizationCode);
            }
            returnBean.setData(organizationList);
        } catch (Exception e) {
            LogUtil.error("查询地区异常 parentOrganizationCode={}",parentOrganizationCode,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }
}
