package com.pay.national.agent.core.dao.wx;

import com.pay.national.agent.model.entity.WxUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author shuyan.qi
 */
public interface WxUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    WxUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);

    WxUserInfo selectByOpenId(@Param("openId") String openId);
}