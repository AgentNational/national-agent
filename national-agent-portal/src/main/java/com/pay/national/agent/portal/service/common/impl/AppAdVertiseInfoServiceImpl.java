package com.pay.national.agent.portal.service.common.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.entity.AppAdVertiseInfo;
import com.pay.national.agent.portal.dao.common.AppAdVertiseInfoMapper;
import com.pay.national.agent.portal.service.common.AppAdVertiseInfoService;
@Service("appAdVertiseInfoService")
public class AppAdVertiseInfoServiceImpl implements AppAdVertiseInfoService {

	@Resource
	private AppAdVertiseInfoMapper appAdVertiseInfoMapper;

	//根据ID查询
	@Override
	public AppAdVertiseInfo findById(String id) {

		return appAdVertiseInfoMapper.findById(id);
	}
	//分页查询
	@Override
	public List<AppAdVertiseInfo> findAllAdvertise(
			Page<List<AppAdVertiseInfo>> page, Map<String, Object> queryParams) {
		List<AppAdVertiseInfo> appAdVertiseInfos =
				appAdVertiseInfoMapper.findAllAdvertise(page, queryParams);
		return appAdVertiseInfos;
	}

	//编辑
	@Override
	public void insert(AppAdVertiseInfo appAdvertiseInfo) {
		appAdVertiseInfoMapper.insert(appAdvertiseInfo);
	}

	//修改
	@Override
	public void update(AppAdVertiseInfo appAdvertiseInfo) {
		appAdVertiseInfoMapper.update(appAdvertiseInfo);
	}

	//按照广告类型与序号查询
	@Override
	public AppAdVertiseInfo findByTypeAndIndexNoAndSta(String advertiseType,
			Integer indexNo, String appType, String status) {
		return appAdVertiseInfoMapper.findByTypeAndIndexNoAndSta(advertiseType, indexNo, appType, status);
	}

	//广告编辑
	@Override
	public AppAdVertiseInfo appAdvertiseEditor(AppAdVertiseInfo appAdvertiseInfo,MultipartFile file) {
		//判断广告排列序号是否存在，如果存在，则置为失效，生效本次修改广告
		LogUtil.info("method appAdvertiseEditor data:{}",JSONUtils.toJsonString(appAdvertiseInfo));
		if("ENABLE".equals(appAdvertiseInfo.getStatus())){
			AppAdVertiseInfo appAdVertiseInfo2 =findByTypeAndIndexNoAndSta(
							appAdvertiseInfo.getAdvertiseType(), appAdvertiseInfo.getIndexNo(),
							appAdvertiseInfo.getAppType(), "ENABLE");
			if(appAdVertiseInfo2 != null){
				appAdVertiseInfo2.setStatus("DISABLE");
				update(appAdVertiseInfo2);
			}
			AppAdVertiseInfo appAdVertiseInfo3 = findById(appAdvertiseInfo.getId());
			String url = imageUpload(file);
			LogUtil.info("图片地址 url:{}",url);
			if(appAdVertiseInfo3 != null){

				//修改
				appAdVertiseInfo3.setImagesUrl(url);//图片路径
				appAdVertiseInfo3.setDescription(appAdvertiseInfo.getDescription());	//描述
				appAdVertiseInfo3.setIndexNo(appAdvertiseInfo.getIndexNo());			//序号
				appAdVertiseInfo3.setAdvertiseType(appAdvertiseInfo.getAdvertiseType());//广告类型
				appAdVertiseInfo3.setStatus(appAdvertiseInfo.getStatus());			//状态
				appAdVertiseInfo3.setUseType(appAdvertiseInfo.getUseType()); 			//使用类型
				appAdVertiseInfo3.setAppType(appAdvertiseInfo.getAppType());//系统类型
				//使用类型
				if("H5".equals(appAdvertiseInfo.getUseType())){
					appAdVertiseInfo3.setLinkUrl(appAdvertiseInfo.getLinkUrl());			//链接地址
					appAdVertiseInfo3.setClassName(null);
					appAdVertiseInfo3.setProperties(null);
				}
				if("CLASS".equals(appAdvertiseInfo.getUseType())){
					appAdVertiseInfo3.setClassName(appAdvertiseInfo.getClassName());	//类名
					appAdVertiseInfo3.setProperties(appAdvertiseInfo.getProperties()); //属性名
					appAdVertiseInfo3.setLinkUrl(null);
				}
				LogUtil.info("method appAdvertiseEditor data:{}",JSONUtils.toJsonString(appAdVertiseInfo3));
				update(appAdVertiseInfo3);
			}else {
				//创建
				appAdvertiseInfo.setId(CommonCodeUtil.getPkId());
				appAdvertiseInfo.setCreateTime(new Date());//创建时间
				insert(appAdvertiseInfo);
			}
		}
		return null;
	}

	//校验序号位置
	@Override
	public String checkAdvertiseIndexNo(AppAdVertiseInfo appAdvertiseInfo,
			Map<String, Object> queryParams) {
		AppAdVertiseInfo appAdVertiseInfo2 = appAdVertiseInfoMapper.findByTypeAndIndexNoAndSta(
				appAdvertiseInfo.getAdvertiseType(), appAdvertiseInfo.getIndexNo(),
				appAdvertiseInfo.getAppType(), "ENABLE");
		if(!appAdVertiseInfo2.getId().equals(appAdvertiseInfo.getId())){
			return "exist";
		}
		return "";
	}

	//上传图片
	@Override
	public String imageUpload(MultipartFile file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String filePath = "/attachment/customer"+today+"/"+ System.currentTimeMillis() + ".jpg";
		LogUtil.info("图片路径:filePath:{}",filePath);
		try {
			boolean b = false;//LFSUtils.uploadFile(filePath,file.getInputStream());
			if(b == true){
				LogUtil.info("图片上传成功 filePath:{}",filePath);
				return filePath;
			}else{
				LogUtil.info("图片上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("图片上传失败",e);
		}
		return null;
	}


}
