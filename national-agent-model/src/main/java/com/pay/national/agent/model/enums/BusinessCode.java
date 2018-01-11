package com.pay.national.agent.model.enums;
/**
 * 业务编码
 * @author shuyan.qi
 * Date:2017年9月11日上午6:29:58
 */
public enum BusinessCode {
	CREDIT_CARD("RET_HD_CREDIT_CARD"),//信用卡
	LOAN("RET_HD_LOAN"),//贷款
	POS("RET_HD_BANKCARD"),//pos支付
	EXTENT("RET_HD_EXTEND"),//推广注册
	WITHDRAW("");//提现
	
	private String accountBusinessCode;//账户系统定义的业务编码
	
	private BusinessCode(String accountBusinessCode){
		this.accountBusinessCode = accountBusinessCode;
	}

	public String getAccountBusinessCode() {
		return accountBusinessCode;
	}
	
}
