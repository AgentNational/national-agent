package com.pay.national.agent.model.beans;

import java.io.Serializable;

import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: 单独实体返回
 * @see: ReturnBean 此处填写需要参考的类
 * @version 2016年8月29日 上午10:53:32
 * @author qinji.xu
 */
public class ReturnBean<T> implements Serializable {
	private static final long serialVersionUID = 2367368611687911954L;

	private String isAllowed = "0";

	/**
	 * 返回码
	 */
	private String code;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 操作对象
	 */
	private T data;

	/** 无参构造方法 */
	public ReturnBean() {
		super();
	}

	/**
	 * 构造方法
	 * @param returnCode 返回码
	 * @param errorMsg 返回消息
	 */
	public ReturnBean(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 构造方法
	 * @param returnCode 返回码
	 * @param errorMsg 返回消息
	 * @param content 操作对象
	 */
	public ReturnBean(String code, String msg,T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 构造方法
	 * @param content 操作对象
	 */
	public ReturnBean(T data) {
		super();
		this.code = RetCodeConstants.SUCCESS;
		this.msg = "";
		this.data = data;
	}

	public String getCode() {
		return StringUtils.isBlank(code) ? "" : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		if(StringUtils.notBlank(errorCode)){
			msg = errorCode+":"+msg;
		}
		return StringUtils.isBlank(msg) ? "" : StringUtils.getLimitLengthString(msg, 512);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReturnBean [code=");
		builder.append(code);
		builder.append(", isAllowed=");
		builder.append(isAllowed);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}



}
