package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    /**
     * 查询所有省级地区
     * @return
     */
    List<Organization> allProvinces();

    /**
     * 查询所有下级地区
     * @param parentCode 父地区编码
     * @return
     */
    List<Organization> allChildOrganizations(@Param("parentCode") String parentCode);
}