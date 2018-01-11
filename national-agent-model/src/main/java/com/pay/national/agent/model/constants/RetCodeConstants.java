package com.pay.national.agent.model.constants;

/**
 * @Description: 返回状态码
 * @see: RetCodeConstants 此处填写需要参考的类
 * @version 2016年8月29日 上午10:51:05
 * @author qinji.xu
 */
public class RetCodeConstants {

	private RetCodeConstants(){}

	/**成功*/
	public static final String SUCCESS = "0000";
	public static final String SUCCESS_DESC = "操作成功";

	/**异常*/
	public static final String ERROR = "0001";
	public static final String ERROR_DESC = "服务器维护中，请稍后重试";

	/**失败*/
	public static final String FAIL = "0002";
	public static final String FAIL_DESC = "操作失败";

	public static final String VERIFY_CODE_1_DESC = "验证码超时,请重新发送";

	public static final String VERIFY_CODE_2_DESC = "验证码不匹配";

	public static final String VERIFY_CODE_3_DESC = "用户名或密码错误";

	public static final String LOGIN_FAIL = "1002";

	public static final String LOGIN_FAIL_DESC = "未登录或登录已超时，请重新登陆";
	
	/**
	 * 注册相关异常信息返回码
	 */
	public static final String REGISTER_CHECK_ERROR = "065200";
	
	public static final String REGISTER_CHECK_ERROR_DESC = "注册校验异常！";
	
	public static final String REGISTER_ERROR = "065201";
	
	public static final String REGISTER_ERROR_DESC = "注册异常！";
	
	/**
	 * 登录相关信息
	 */
	public static final String LOGIN_CHECK_ERROR = "065300";
	
	public static final String LOGIN_CHECK_ERROR_DESC = "登录校验异常！";
	
	public static final String LOGIN_ERROR = "065301";
	
	public static final String LOGIN_ERROR_DESC = "登录异常！";
	
	/**
	 * 修改密码
	 */
	public static final String UPDATE_PASSWORD_ERROR = "065400";
	
	public static final String UPDATE_PASSWORD_ERROR_DESC = "修改密码异常！";
	
	/**
	 * 前置调core项目dubbo服务异常
	 */
	public static final String DUBOO_ERROR_CODE = "0000001";
}
