package com.pay.national.agent.common.constants;

/**
 * @Description: redis相关常量
 * @see: 需要参考的类
 * @version 2017年9月4日 下午5:27:19
 * @author zhenhui.liu
 */
public class RedisConstants {

	private RedisConstants() {
	}

	public static final String WAIT_PROFIT_QUEUE_CODE = "waitProfitQueueCode";// 待计算借款收益队列

	public static final String PROFIT_RULE_ONE_LEVEL = "1";// 等级1

	public static final String PROFIT_RULE_TWO_LEVEL = "2";// 等级2

	public static final String PROFIT_RULE_LEVEL_CODE = "levelCode";// 等级缓存code
	
	public static final int EXPIRED_TIME_24HOUR = 60 * 60 * 24;//过期时间

	/** 流水号生成相关配置*/
	
	/**流水号类型：入账*/
	public static final String TRANSIN = "001"; // 入账
	public static final String TRANSOUT = "002"; // 提现

	public static final String ONLINEDPAY = "601"; // 代付商户订单号
	public static final String ONLINECARDAUTH = "602"; // 绑卡商户订单号

	public static final String ACCOUNTMODIFY = "101"; // 账户变动流水号
	
	public static final String LFPH_TRANS_IN = "lfphTransIn"; // 入账交易流水
	public static final String LFPH_TRANS_OUT = "lfphTransOut"; // 提现交易流水

	public static final String LFPH_ONLINE_DPAY = "lfphOnlineDpay"; // 代付商户订单号
	public static final String LFPH_ONLINE_CARD_AUTH = "lfphOnlineCardAuth"; // 绑卡认证订单号

	public static final String LFPH_ACCOUNT_MODIFY = "lfphAccountModify"; // 账户流水号
	
	public static final String SMS_CODE = "smsCode"; // 短信编码
	
	public static final String PROFIT_ACCOUNT_PHONE_ARRAY = "profitAccountPhoneArray"; //返佣入账手机数组
	
	public static final String CREDIT_CHANNEL_OPEN_URL = "creditChannelOpenUrl"; //进入信用卡频道URL
	
	public static final String CREDIT_PROGRESS_CLICK_COUNT = "creditProgressClickCount"; //办卡进度点击次数
	
	public static final String CREDIT_GUIDE_CLICK_COUNT = "creditGuideClickCount"; //办卡指南点击次数
}
