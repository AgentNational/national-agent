package com.pay.national.agent.portal.service.common;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	//上传图片
	public String uploadImg(MultipartFile file,String filePath);
}
