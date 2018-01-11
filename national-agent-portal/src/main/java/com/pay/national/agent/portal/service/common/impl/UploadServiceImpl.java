package com.pay.national.agent.portal.service.common.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.portal.service.common.UploadService;

@Component("uploadService")
public class UploadServiceImpl implements UploadService {


	@Override
	public String uploadImg(MultipartFile file,String filePath) {
		if (file == null || file.getSize() == 0) {
			return "";
		}
		try {
			//调用api上传图片
			boolean response = false;//LFSUtils.uploadFile(filePath,file.getInputStream());
			if (response) {
				LogUtil.info(" 上传文件成功 filePath:{}",filePath);
			}
		} catch (Exception e) {
			LogUtil.error("method getFile 保存图片异常 ERROR:"+e.getMessage());
		}
		return "";
	}

}
