package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppNoticeInfo;

public interface AppNoticeInfoMapper {
	/**
	 * 新建公告
	 * @param record
	 * @return
	 */
    int insert(AppNoticeInfo record);
    
    /**
     * 查找公告
     * @param id
     * @return
     */
    AppNoticeInfo selectByPrimaryKey(String id);

    /**
     * 更新公告
     * @param record
     * @return
     */
    int updateByPrimaryKey(AppNoticeInfo record);
    
    /**
	 * @Description 分页查找所以的app公告
	 * @param page
	 * @param queryParams
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, Object>> findAllAppNotice(@Param("page")Page<Map<String, Object>> page, @Param("queryParams")Map<String, Object> queryParams);

	/**
	 * 删除用户读取记录
	 * @param noticeId
	 */
	void deleteReadRecord(@Param("noticeId")String noticeId);
	/**
	 * 删除公告
	 * @param noticeId
	 */
	void deleteNotice(@Param("noticeId")String noticeId);
}