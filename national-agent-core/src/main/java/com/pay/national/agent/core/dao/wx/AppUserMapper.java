package com.pay.national.agent.core.dao.wx;

import com.pay.national.agent.model.entity.AppUser;

public interface AppUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    int insert(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    int insertSelective(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    AppUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(AppUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_user
     *
     * @mbggenerated Mon Jan 29 17:12:37 GMT+08:00 2018
     */
    int updateByPrimaryKey(AppUser record);
}