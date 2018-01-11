package com.pay.national.agent.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 文件上传下载utils，基于公司的LFS
 * @see: 需要参考的类
 * @version 2017年9月20日 上午8:55:38
 * @author zhenhui.liu
 */
public class LFSUtils {
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("system.properties");
	
	/*// TODO token需要申请
	private static LFSClient client;

	static {
		String ip = propertiesLoader.getProperty("lfs.ip");
		Integer port = propertiesLoader.getInteger("lfs.port");
		LFSClientBuilder builder = new LFSClientBuilder().token("3KEQx6MpMUqSGnQvAjL+Iw==").readHost(ip).readPort(new Integer(port))
				.writeHost(ip).writePort(new Integer(port)).operationTimeout(10000);
		client = LFSClientFactory.getLFSClient(builder);
		
	}
	*//**
	 * @Description LFS文件下载
	 * @param filePath
	 * @return
	 * @see 需要参考的类或方法
	 *//*
	public static byte[] downloadFile(String filePath){
		byte[] byteArray = null ;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			LFSResponse lfsresponse = client.download(filePath, baos);
			if (LFSResponseCode.SUCCESS.equals(lfsresponse.getCode())) {
				LogUtil.info("method downloadFile 下载文件成功 filePath:{}",filePath);
			}
			byteArray = baos.toByteArray();
			LogUtil.info("下载的字节流的大小为:"+byteArray.length);
			baos.flush();
			baos.close();
		} catch (IOException e) {
			LogUtil.error("method downloadFile 下载文件失败 filePath:{}",filePath,e);
		}
		return byteArray;
	}
	
	*//**
	 * @Description LFS文上传字节数组
	 * @param filePath
	 * @return
	 * @see 需要参考的类或方法
	 *//*
	public static boolean uploadFile(String filePath,byte [] fileBytes){
		boolean result = false;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);
			LFSResponse lfsresponse = client.upload(filePath, bais);
			if (LFSResponseCode.SUCCESS.equals(lfsresponse.getCode())) {
				result = true;
				LogUtil.info("method downloadFile 上传文件成功 filePath:{}",filePath);
			}
			bais.close();
		} catch (IOException e) {
			LogUtil.error("method downloadFile 上传文件失败 filePath:{}",filePath,e);
		}
		return result;
	}
	
	*//**
	 * @Description LFS文上传 输入流
	 * @param filePath
	 * @return
	 * @see 需要参考的类或方法
	 *//*
	public static boolean uploadFile(String filePath,InputStream inputStream){
		boolean result = false;
		try {
			LFSResponse lfsresponse = client.upload(filePath, inputStream);
			if (LFSResponseCode.SUCCESS.equals(lfsresponse.getCode())) {
				result = true;
				LogUtil.info("method downloadFile 上传文件成功 filePath:{}",filePath);
			}
			inputStream.close();
		} catch (IOException e) {
			LogUtil.error("method downloadFile 上传文件失败 filePath:{}",filePath,e);
		}
		return result;
	}*/


}
