package com.pay.national.agent.model.constants;

/**
 * @Description: redis key 前缀
 * @see: 需要参考的类
 * @version 2017年9月6日 上午11:54:33
 * @author zhenhui.liu
 */
public class RedisKeys {
	/**
	 * 注册发送验证码redis前缀
	 */
	public static final String REGISTER_CHECK_CODE_PREFIX = "NATIONAL_REGISTER_CHECK_CODE_PREFIX";
	
	/**
	 * 领取pos验证码redis前缀
	 */
	public static final String KAYOU_POS_CHECK_CODE_PREFIX = "NATIONAL_KAYOU_POS_CHECK_CODE_PREFIX";
	
	/**
	 * 找回密码发送验证码redis前缀
	 */
	public static final String FIND_PASSWORD_CHECK_CODE_PREFIX = "NATIONAL_FIND_PASSWORD_CHECK_CODE_PREFIX";
	
	
	/**
	 * 注册防止重复提交redis前缀
	 */
	public static final String REGISTER_REPEAT_LOCK = "NATIONAL_REGISTER_REPEAT_LOCK";

	/**
	 * 领取pos防止重复提交redis前缀
	 */
	public static final String POS_REPEAT_LOCK = "NATIONAL_POS_REPEAT_LOCK";

	/**
	 * 登录用户信息存储redis前缀
	 */
	public static final String LOGIN_USER_INFO_PREFIX = "NATIONAL_LOGIN_USER_INFO_PREFIX";
}
