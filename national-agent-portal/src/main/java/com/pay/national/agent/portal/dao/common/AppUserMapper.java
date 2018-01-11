package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppUser;

public interface AppUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    AppUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);

    /**
     * @Description 查找所有的用户信息（分页）
     * @param page
     * @param queryParams
     * @return
     * @see 需要参考的类或方法
     */
	List<AppUser> findAllAppUser(@Param("page")Page<AppUser> page, @Param("queryParams")Map<String, Object> queryParams);

	/**一句话描述方法的用法 修改用户状态
	 * @param userId
	 * @param status
	 * @see 需要参考的类或方法
	 */
	void updateUserStatus(@Param("userId")Long userId, @Param("status")String status);

	/**
	 * @Description 查询所有角色
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, Object>> findRole();

	/**
	 * @Description 查询用户已拥有的角色
	 * @param userId
	 * @return
	 * @see 需要参考的类或方法
	 */
	String[] findAuthorizeUser(String userId);

	/**
	 * @Description 保存用户角色
	 * @param roleId
	 * @param userId
	 * @see 需要参考的类或方法
	 */
	void saveUserAuthorize(@Param("roleId")String roleId, @Param("userId")String userId);

	/**
	 * @Description 删除用户角色
	 * @param appRole
	 * @param userId
	 * @param status 
	 * @see 需要参考的类或方法
	 */
	void updateAuthorizeStatus(@Param("roleId")String roleId, @Param("userId")String userId, @Param("status")String status);
}