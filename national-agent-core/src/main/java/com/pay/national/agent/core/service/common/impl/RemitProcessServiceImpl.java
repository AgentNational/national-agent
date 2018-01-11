package com.pay.national.agent.core.service.common.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.commons.utils.lang.AmountUtils;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.utils.AmountUtil;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.RedisUtils;
import com.pay.national.agent.core.service.common.AccountInterfaceService;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.RemitInterfaceService;
import com.pay.national.agent.core.service.common.RemitPaymentService;
import com.pay.national.agent.core.service.common.RemitProcessService;
import com.pay.national.agent.core.service.common.SettleInterfaceService;
import com.pay.national.agent.core.service.common.exception.RemitException;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.SettleParamBean;
import com.pay.national.agent.model.beans.results.UserAccountBean;
import com.pay.national.agent.model.beans.results.UserSettleCardBean;
import com.pay.national.agent.model.beans.results.UserSettleRuleBean;
import com.pay.national.agent.model.constants.AccountConstants;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.entity.RemitLog;
import com.pay.national.agent.model.entity.RemitPayment;
import com.pay.national.agent.model.enums.AccountHistoryStatus;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;
import com.pay.national.agent.model.enums.SettleStatus;
/**
 * 结算处理service
 * @author shuyan.qi
 * Date:2017年9月6日上午3:49:23
 */
@Service("remitProcessService")
public class RemitProcessServiceImpl implements RemitProcessService{
	//TODO
	private static final String REMIT_REQUEST_PREFIX = "NA_";
	
	@Resource
	private RemitPaymentService remitPaymentService;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private AccountInterfaceService accountInterfaceService;
	
	@Resource
	private SettleInterfaceService settleInterfaceService;
	
	@Resource
	private RemitInterfaceService remitInterfaceService;
	
	
	@Override
	public ReturnBean<String> settle(SettleParamBean param) {
		ReturnBean<String> returnBean = new ReturnBean<String>();
		
		RemitLog remitLog = createRemitLog(param);
		String userNo = param.getUserNo();
		RemitPayment remitPayment = null;
		AccountHistory accountHistory = null;

		String errorCode = "";
		String errorMsg = "";
		String executeResult = "SUCCESS";
		
		try {/*
			//账户
			UserAccountBean account = accountInterfaceService.findAccount(userNo);
			//结算规则
			UserSettleRuleBean settleRule =  settleInterfaceService.findSettleRule(userNo,account.getAccountNo());
			//结算卡
			List<UserSettleCardBean> settleCards = settleInterfaceService.findSettleCards(userNo,account.getAccountNo());
			
			validateSettleStatus(account,settleRule,settleCards);
			
			//可用余额（总金额-冻结金额-在途金额，四舍五入后保留两位小数）
			double ableBalance = AmountUtil.round(AmountUtil.sub(AmountUtil.sub(account.getBalance(), account.getFreezeBalance()),account.getTransitBalance()), 2);
			boolean flag = AmountUtils.geq(ableBalance, param.getSettleAmount());
			if(!flag){
				String s = "settle amount bigger the able balance .userNo:"+userNo+",accountNo:"+account.getAccountNo()
							+",settleAmount:"+param.getSettleAmount()+",ableBalance:"+ableBalance+",balance:"+account.getBalance();
				LogUtil.error(s);
				throw new RemitException(RetCodeConstants.FAIL, "余额不足");
			}else{
				UserSettleCardBean settleCard = settleCards.get(0);
				//生成付款单
				remitPayment = createRemitPayment(param,account,settleRule,settleCard);
				
				//生成账户操作记录
				accountHistory = createAccountHistory(param,remitPayment,ableBalance);
				
				//计算费用(目前没有费用)
				calculateFee(remitPayment);
			
				//付款
				remitInterfaceService.remit(remitPayment);
				
				//更新付款单的付款状态
				remitPayment.setRemitStatus(SettleStatus.REMIT_REQ_SEND.name());
				remitPaymentService.updateRemitPayment(remitPayment);
				
				//更新账户操作记录
				accountHistory.setStatus(AccountHistoryStatus.SUCCESS);
				accountService.updateAccountHistory(accountHistory);
				
				returnBean.setCode(RetCodeConstants.SUCCESS);
				returnBean.setMsg("提现申请已受理，请耐心等待");
			}
		*/} catch (Exception e) {
			LogUtil.error(" 付款流程异常:", e);
			executeResult = "ERROR";
			if(e instanceof RemitException){
				RemitException se = (RemitException)e;
				errorCode = se.getCode();
				errorMsg = se.getMessage();
			}else{
				errorCode = RetCodeConstants.ERROR;
				errorMsg = RetCodeConstants.ERROR_DESC;
			}
			
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode(errorCode);
			returnBean.setMsg("提现异常");
			
			if(null != remitPayment){
				remitPayment.setStatus(SettleStatus.FAILED.name());
				remitPaymentService.updateRemitPayment(remitPayment);
			}
			
			if(null != accountHistory){
				accountHistory.setStatus(AccountHistoryStatus.FAIL);
				accountService.updateAccountHistory(accountHistory);
			}
		}
		
		if(null != remitPayment){
			remitLog.setRemitRequestId(remitPayment.getRemitRequestId());
		}
		remitLog.setExecuteResult(executeResult);
		remitLog.setErrorCode(errorCode);
		remitLog.setErrorMsg(errorMsg);
		remitLog.setEndExecuteTime(new Date());
		remitPaymentService.updateRemitLog(remitLog);
		
		return returnBean;
	}
	
	/**
	 * 计算费用
	 * @param remitPayment
	 */
	private void calculateFee(RemitPayment remitPayment) {/*
		Double fee = 0.00D;
		remitPayment.setRemitFee(fee);
		remitPayment.setRemitAmount(AmountUtil.round(AmountUtil.sub(remitPayment.getSettleAmount(), fee), 2));
		remitPayment.setFeeBusCode("");
		remitPayment.setRemitBusCode("");
	*/}

	/**
	 * 创建账户操作记录
	 * @param param
	 * @param settlePayment
	 * @param ableBalance
	 * @return
	 */
	private AccountHistory createAccountHistory(SettleParamBean param, RemitPayment remitPayment,
			double ableBalance) {
		AccountHistory accountHistory = new AccountHistory();
		accountHistory.setUserNo(param.getUserNo());
		accountHistory.setBalance(ableBalance);
		accountHistory.setAmount(param.getSettleAmount());
		accountHistory.setBusinessCode(BusinessCode.WITHDRAW);
		accountHistory.setChildBusinessCode(ChildBusinessCode.WITHDRAW);
		accountHistory.setQueryId(remitPayment.getRemitRequestId());
		accountHistory.setStatus(AccountHistoryStatus.INIT);
		accountHistory.setSymbol(AccountConstants.SYMBOL_SUBTRACT);
		accountHistory.setCreateTime(new Date());
		accountHistory.setOperateTime(new Date());
		accountHistory.setAccountNo(remitPayment.getSettleAccount());
		accountHistory.setBalance(AmountUtils.subtract(ableBalance, param.getSettleAmount()));
		return accountService.createAccountHistory(accountHistory);
	}

	/**
	 * 创建付款单
	 * @param param
	 * @param account
	 * @param settleRule
	 * @param settleCard
	 * @return
	 */
	private RemitPayment createRemitPayment(SettleParamBean param, UserAccountBean account,
			UserSettleRuleBean settleRule, UserSettleCardBean settleCard) {
		RemitPayment remitPayment = new RemitPayment();
		Integer number = getNumber();
		remitPayment.setRemitRequestId(REMIT_REQUEST_PREFIX+DateUtil.formatDate(new Date(),"yyyyMMdd")+number);
		
		//用户
		remitPayment.setUserNo(param.getUserNo());
		//账户
		remitPayment.setSettleAccount(account.getAccountNo());
		//结算规则
		remitPayment.setSettleRuleId(settleRule.getId());
		
		//结算卡
		remitPayment.setSettleCardId(settleCard.getSettleCardId());
		remitPayment.setProvince(settleCard.getProvince());
		remitPayment.setCity(settleCard.getCity());
		remitPayment.setBankCode(settleCard.getBankCode());
		remitPayment.setAlliedBankCode(settleCard.getAlliedBankCode());
		remitPayment.setSabkCode(settleCard.getSabkCode());
		remitPayment.setOpenBankName(settleCard.getBankName());
		remitPayment.setOwnBankCode(settleCard.getOwnBankCode());
		remitPayment.setBankAccountType(settleCard.getCardType());
		remitPayment.setBankAccountName(settleCard.getBankAccountName());
		remitPayment.setBankCardNo(settleCard.getBankAccountNo());
		
		remitPayment.setSettleAmount(param.getSettleAmount());
		//remitPayment.setIsUrgent("");
		remitPayment.setCreateTime(new Date());
		return remitPaymentService.createRemitPayment(remitPayment);
	}
	
	/**
	 * 生成订单号
	 */
	private synchronized  Integer getNumber(){/*
		String number = RedisUtils.get("remitQueryId");
		if(null != number){
			RedisUtils.incr("remitQueryId");
			return Integer.valueOf(RedisUtils.get("remitQueryId"));
		}else{
			Date now = new Date();
			int seconds = 0;
			try {
				seconds = DateUtil.getSecondsBetweenDate(now,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtil.formatDate(now, "yyyy-MM-dd")+" 23:59:59"));
			} catch (ParseException e) {
				LogUtil.error("计算订单号在缓存中的过期时间异常:{}",e);
			}
			RedisUtils.setEx("remitQueryId", "1", seconds);
			return Integer.valueOf(RedisUtils.get("remitQueryId"));
		}
		
	*/
		return null;
	}
	
	/**
	 * 验证结算状态
	 * @param account
	 * @param settleRule
	 * @param settleCards
	 */
	private void validateSettleStatus(UserAccountBean account, UserSettleRuleBean settleRule,
			List<UserSettleCardBean> settleCards) {
		if(account.getStatus() == null || "EN_OUT".equals(account.getStatus()) || "FREEZE".equals(account.getStatus())) {
			String msg = "can not settle by account status. userNo:" + account.getUserNo() + ",accountNo:" + account.getAccountNo() + ",status:" + account.getStatus();
			LogUtil.error(msg);
			throw new NationalAgentException(RetCodeConstants.FAIL, "账户禁止出账");
		}
		
		if(null == settleRule){
			String s = "settleQueryInterface.queryCustomerSettleRule not success, not found customerSettleRuleBean, userNo:" + account.getUserNo() + ",accountNo:" + account.getAccountNo();
			LogUtil.error(s);
			throw new NationalAgentException(RetCodeConstants.FAIL, "未找到结算规则");
		}
		
		if(null == settleCards || settleCards.isEmpty()){
			String msg = "settleQueryInterface.querySettleCardByOwner not success, can't find settle card, userNo:" + account.getUserNo() + ",accountNo:" + account.getAccountNo();
			LogUtil.error(msg);
			throw new NationalAgentException(RetCodeConstants.FAIL, "未找到结算卡");
		}
	}

	/**
	 * 创建付款日志
	 * @param param
	 * @return
	 */
	private RemitLog createRemitLog(SettleParamBean param) {
		RemitLog remitLog = new RemitLog();
		remitLog.setUserNo(param.getUserNo());
		remitLog.setAmount(param.getSettleAmount());
		remitLog.setBeginExecuteTime(new Date());
		remitLog.setCreateTime(new Date());
		return remitPaymentService.createRemitLog(remitLog);
	}
}
