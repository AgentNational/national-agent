package com.pay.national.agent.core.service.common;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
 */
public interface CommonService {

    /**
     * 查询地区
     * @param parentOrganizationCode 父地区编码
     * @return
     */
    String organizations(String parentOrganizationCode);
}
