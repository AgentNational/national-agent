package com.pay.national.agent.core.dao.common;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.model.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * @Description 通过userName查询user信息
     * @param phoneNo
     * @return
     * @see 需要参考的类或方法
     */
	User findUserByUserName(@Param("userName")String userName);

	/**
	 * @Description 通过userNo查询user信息
	 * @param userNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	User findUserByUserNo(@Param("userNo")String userNo);

	/**
	 * @Description 插入关系
	 * @param id
	 * @param i
	 * @see 需要参考的类或方法
	 */
	void insertUserRoleLink(@Param("userId")String userId, @Param("roleId")String roleId);
}