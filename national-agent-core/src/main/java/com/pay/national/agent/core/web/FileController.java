package com.pay.national.agent.core.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: 文件操作相关controller
 * @see: 需要参考的类
 * @version 2017年9月20日 上午9:47:56
 * @author zhenhui.liu
 */
@Controller
@RequestMapping("/file")
public class FileController {

	/**
	 * @Description 图片下载
	 * @param filePath
	 * @param request
	 * @return
	 * @see 需要参考的类或方法
	 */
	@RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
	public @ResponseBody String downloadFile(@RequestParam("filePath")String filePath,HttpServletResponse response){
		LogUtil.info("图片下载start filePath:{}",filePath);
		try {
			byte[] bytes = null;//LFSUtils.downloadFile(filePath);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
			out.close();
			LogUtil.info("图片下载 success... filePath:{}",filePath);
			return JSONUtils.alibabaJsonString(new ReturnBean<String>(RetCodeConstants.SUCCESS, "成功获取图片"));
		} catch (Exception e) {
			LogUtil.error("图片下载 error... filePath:{}",filePath,e);
			return JSONUtils.alibabaJsonString(new ReturnBean<String>(RetCodeConstants.FAIL, "获取图片失败"));
		}
	}
	
}
