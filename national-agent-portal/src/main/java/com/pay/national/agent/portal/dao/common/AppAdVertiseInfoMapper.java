package com.pay.national.agent.portal.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;

public interface AppAdVertiseInfoMapper {
	AppAdVertiseInfo findById(String id);

	List<AppAdVertiseInfo> findAllAdvertise(@Param("page") Page<List<AppAdVertiseInfo>> page, @Param("queryParams")Map<String, Object> queryParams);

	void insert(AppAdVertiseInfo appAdvertiseInfo);

	void update(AppAdVertiseInfo appAdvertiseInfo);

	AppAdVertiseInfo findByTypeAndIndexNoAndSta(@Param("advertiseType") String advertiseType, @Param("indexNo") Integer indexNo,
					@Param("appType")String appType,@Param("status") String status);


}
