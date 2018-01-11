package com.pay.national.agent.core.dao.test;

import com.pay.national.agent.model.entity.TestMy;

public interface TestMyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestMy record);

    int insertSelective(TestMy record);

    TestMy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestMy record);

    int updateByPrimaryKey(TestMy record);
}