package com.pay.national.agent.portal.service.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;

public interface AppAdVertiseInfoService {
	public AppAdVertiseInfo findById(String id);

	public List<AppAdVertiseInfo> findAllAdvertise(@Param("page") Page<List<AppAdVertiseInfo>> page, @Param("queryParams")Map<String, Object> queryParams);

	public void insert(AppAdVertiseInfo appAdvertiseInfo);

	public void update(AppAdVertiseInfo appAdvertiseInfo);

	public AppAdVertiseInfo findByTypeAndIndexNoAndSta(@Param("advertiseType") String advertiseType, @Param("indexNo") Integer indexNo,
					@Param("appType")String appType,@Param("status") String status);

	public AppAdVertiseInfo appAdvertiseEditor(AppAdVertiseInfo appAdvertiseInfo,@RequestParam(value = "file",required=false) MultipartFile file);

	public String checkAdvertiseIndexNo(AppAdVertiseInfo appAdvertiseInfo,Map<String, Object> queryParams);

	public String imageUpload(@RequestParam(value = "file",required=false) MultipartFile file);
}
