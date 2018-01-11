package com.pay.national.agent.common.exception;

public class NationalAgentException extends RuntimeException{
	private static final long serialVersionUID = 4832302352342242280L;

	private String code;		//错误码
	private String message;		//错误描述

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NationalAgentException() {
		super();
	}

	public NationalAgentException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NationalAgentException(String code, String message) {
		this.message = message;
		this.code = code;
	}

	public NationalAgentException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

}
