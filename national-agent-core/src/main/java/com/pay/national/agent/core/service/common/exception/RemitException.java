package com.pay.national.agent.core.service.common.exception;

public class RemitException extends RuntimeException{
	private static final long serialVersionUID = 5333159832182014291L;
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

	public RemitException() {
		super();
	}

	public RemitException(String message, Throwable cause){
		super(message, cause);
	}
	
	public RemitException(String code, String message) {
		this.message = message;
		this.code = code;
	}

	public RemitException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	@Override
	public String toString() {
		return "RemitException [code=" + code + ", message=" + message + "]";
	}

	
}
