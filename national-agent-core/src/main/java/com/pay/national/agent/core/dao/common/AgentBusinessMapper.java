package com.pay.national.agent.core.dao.common;

import com.pay.national.agent.model.entity.AgentBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentBusiness record);

    int insertSelective(AgentBusiness record);

    AgentBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentBusiness record);

    int updateByPrimaryKey(AgentBusiness record);

    /**
     * 只会返回状态为'ENABLE'的代理业务
     * @param businessCode
     * @return
     */
    List<AgentBusiness> selectByBusiness(@Param("businessCode") String businessCode);

    AgentBusiness selectByChildBusiness(String childBusinessCode);
}