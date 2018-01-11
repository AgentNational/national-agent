package com.pay.national.agent.model.beans;

import java.io.Serializable;

import com.pay.commons.utils.lang.StringUtils;
import com.pay.national.agent.model.constants.RetCodeConstants;

/**
 * @Description: 集合实体返回
 * @see: ReturnListBean 此处填写需要参考的类
 * @version 2016年8月29日 上午10:54:16
 * @author qinji.xu
 */
public class ReturnListBean<T> implements Serializable {
	private static final long serialVersionUID = 229530487060354004L;

	private String isAllowed = "0";

	/**
	 * 返回码
	 */
	private String code;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 操作对象
	 */
	private T data;

	/**
	 * 总页数（分页）
	 */
	private int totalPage;

	/**
	 * 记录总条数（分页）
	 */
	private int totalResult;


	/** 无参构造方法 */
	public ReturnListBean() {
		super();
	}

	/**
	 * 构造方法
	 * @param returnCode 返回码
	 * @param errorMsg 返回消息
	 */
	public ReturnListBean(String code, String msg) {
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
	public ReturnListBean(String code, String msg,T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 构造方法
	 * @param content 操作对象
	 */
	public ReturnListBean(T data) {
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
		return StringUtils.isBlank(msg) ? "" : StringUtils.getLimitLengthString(msg, 512);
	}

	public void setMsg(String msg) {
		this.msg = msg;
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


	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	@Override
	public String toString() {
		return "ReturnListBean [isAllowed=" + isAllowed + ", code=" + code
				+ ", msg=" + msg + ", data=" + data + ", totalPage="
				+ totalPage + ", totalResult=" + totalResult + "]";
	}



}
