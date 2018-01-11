package com.pay.national.agent.model.constants;

/**
 * @Description: 常量类
 * @see: Constants 此处填写需要参考的类
 * @version 2016年9月6日 上午11:04:56
 * @author qinji.xu
 */
public class Constants {

	private Constants() {
	}
	public static final String NATIONALAGENT = "NATIONALAGENT";
	
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String FAILURE = "FAILURE";
	
	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";

	/**
	 * TODO 短信平台对应系统编码
	 */
	public static final String SMS_APP_CODE = "";
	/**
	 * TODO 短信平台对应系统认证token
	 */
	public static final String SMS_APP_TOKEN = "";

	/**
	 * 注册重复提交锁前缀
	 */
	public static final String REGIST_REPEAT_LOCK = "ALLIANCE_REGIST_LOCK";

	/**
	 * 重复提交过期时间 5秒
	 */
	public static int REGIST_REPEAT_LOCKEXPIRE = 5;

	/**
	 * 注册 验证码缓存的前缀
	 */
	public static final String REGIST_CHECKCODE_PREFIX = "ALLIANCE_REGIST_CHECKCODE";

	/**
	 * 公共验证码过期时间 60 秒
	 */
	public static final int COMMON_CHECKCODE_EXPIRE = 60;

	/**
	 * 公共重复提交过期时间5秒
	 */
	public static final int COMMON_REPEAT_EXPIRE = 5;

	/**
	 * 实名认证重复提交前缀
	 */
	public static final String AUTH_REALNAME_REPEAT_LOCK = "ALLIANCE_AUTH_REALNAME_LOCK";

	/**
	 * 风险处理商户自查审核重复提交前缀
	 */
	public static final String RISK_CUSTOMER_SELFCHECK_REPEAT_LOCK = "ALLIANCE_RISK_CUSTOMER_SELFCHECK_REPEAT_LOCK";

	/**
	 * 风险处理我的调查重复提交前缀
	 */
	public static final String RISK_MY_INVESTIGATE_REPEAT_LOCK = "ALLIANCE_RISK_MY_INVESTIGATE_REPEAT_LOCK";

	/**
	 * 登录有效时间30天
	 */
	public static final Integer LOGIN_KEY_EXPIRE = 30*24*60*60;

	/**
	 * 登录时间戳
	 */
	public static final String RECOM_LOGIN_TIME = "LOGIN_TIME";

	/**
	 * 用户名
	 */
	public static final String USER = "userName";

	/**
	 * 登录标识
	 */
	public static final String TOKEN = "loginKey";
	public final static String AGENT_PREFIX = "AGENT_";
	public final static String AGENT_NO = "NO";

	public static final String PRO_DEF_KEY_H001 = "H001";  //流程定义主键C001

	public static final String TASK_DEF_KEY_H00101="H001_01";

	public static final String TASK_DEF_KEY_H00102="H001_02";

	public static final String ALLIANCE = "Alliance";
	public static final String ALLIANCE_CORE = "alliance-core";
	
	/**
	 * 标示待绑定pos的商户集合
	 */
	public static final String BIND_POS_KEY = "alliance-bindposkey";
	
	/**
	 * 报单防止重复提交锁标示
	 */
	public static final String ADD_CUSTOMER_LOCK = "ADD_CUSTOMER_LOCK";
	
	/**微信关注引导标示*/
	public static final String WECHAT_GUIDE_FLAG_A = "WECHAT_GUIDE_FLAG_A";
	public static final String WECHAT_GUIDE_FLAG_B = "WECHAT_GUIDE_FLAG_B";
	
	/**
	 * 商户全称或企业名称中包含“公司”字样
	 */
	public static final String CUSTOMERNAME_CHECK_COMPANY = "公司";
	
	/**
	 * ALLIANCE系统审批人
	 */
	public static final String ALLIANCE_SYSTEM_AUDIT_USER = "alliance_system";
	
	/**
	 * Pay宝非活动商户标示
	 */
	public static final String LFB_GENERAL_CUSTOMER = "LFB_GENERAL_CUSTOMER";
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 系统编码
	 */
	public static final String SYSTEM_CODE = "NATIONAL_AGENT";
	
	
	
}
