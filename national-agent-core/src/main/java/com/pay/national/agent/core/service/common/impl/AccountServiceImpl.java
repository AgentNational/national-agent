package com.pay.national.agent.core.service.common.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.AmountUtil;
import com.pay.national.agent.common.utils.CommonCodeUtil;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.bean.result.AccountBalanceBean;
import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.core.dao.common.AccountHistoryMapper;
import com.pay.national.agent.core.service.common.AccountInterfaceService;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.RemitInterfaceService;
import com.pay.national.agent.core.service.common.RemitProcessService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.CreditParamBean;
import com.pay.national.agent.model.beans.query.SettleParamBean;
import com.pay.national.agent.model.beans.results.UserAccountBean;
import com.pay.national.agent.model.constants.AccountConstants;
import com.pay.national.agent.model.constants.Constants;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.enums.AccountHistoryStatus;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ChildBusinessCode;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Resource
	private AccountHistoryMapper accountHistoryMapper;
	
	@Resource
	private AccountInterfaceService accountInterfaceService;
	
	@Resource
	private RemitProcessService remitProcessService;
	
	@Resource
	private RemitInterfaceService remitInterfaceService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public AccountHistory createAccountHistory(AccountHistory accountHistory) {
		accountHistoryMapper.insert(accountHistory);
		return accountHistory;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateAccountHistory(AccountHistory accountHistory) {
		accountHistoryMapper.updateByPrimaryKey(accountHistory);
	}

	@Override
	public ReturnBean<String> withdrawDeposit(String userNo, Double withdrawAmount) {
		ReturnBean<String> returnBean = new ReturnBean<String>();
		
		 try {
			SettleParamBean settleParamBean = new SettleParamBean();
			 settleParamBean.setSystemCode(Constants.SYSTEM_CODE);
			 settleParamBean.setUserNo(userNo);
			 settleParamBean.setSettleAmount(withdrawAmount);
			 settleParamBean.setSettleTime(new Date());
			 returnBean =  remitProcessService.settle(settleParamBean);
		} catch (Exception e) {
			LogUtil.error("withdrawDeposit error userNo:{},withdrawAmount:{},exception:{}",userNo,withdrawAmount,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0200111");
			returnBean.setMsg("提现异常");
		}
		return returnBean;
	}

	@Override
	public ReturnBean<AccountBalanceBean> accountBalance(String userNo) {
		ReturnBean<AccountBalanceBean> returnBean = new ReturnBean<AccountBalanceBean>();
		AccountBalanceBean accountBalanceBean = new AccountBalanceBean();
		UserAccountBean account;
		try {
			account = accountInterfaceService.findAccount(userNo);
			accountBalanceBean.setUserNo(userNo);
			accountBalanceBean.setAccountNo(account.getAccountNo());
			accountBalanceBean.setBalance(processBalance(account));
			accountBalanceBean.setStatus(account.getStatus());
			
			returnBean.setData(accountBalanceBean);
			returnBean.setCode(RetCodeConstants.SUCCESS);
			returnBean.setMsg(RetCodeConstants.SUCCESS_DESC);
		} catch (Exception e) {
			LogUtil.error("accountBalance error userNo:{},exception:{}", userNo,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0200201");
			returnBean.setMsg("查询账户信息异常");
		}
		return returnBean;
	}

	/**
	 * 处理账户余额
	 * @param account
	 * @return
	 */
	private Double processBalance(UserAccountBean account) {
		/*//总余额-在途余额-冻结金额
		return AmountUtil.round(AmountUtil.sub(AmountUtil.sub(account.getBalance(), account.getFreezeBalance()),account.getTransitBalance()), 2);
		double amount = AmountUtils.subtract(
							AmountUtils.subtract(account.getBalance(), account.getFreezeBalance()),
							account.getTransitBalance());
		return Double.valueOf(df.format(amount));*/
		return null;
	}
	
	@Override
	public ReturnBean<Page<List<AccountHistory>>> accountHistoryList(String userNo, String businessCode, String startDate,Page<List<AccountHistory>> page) {
		ReturnBean<Page<List<AccountHistory>>> returnBean = new ReturnBean<Page<List<AccountHistory>>>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		try {
			List<AccountHistory> list = accountHistoryMapper.findAllHistory(userNo,businessCode,startDate,page);
			page.setObject(list);
			returnBean.setData(page);
		} catch (Exception e) {
			LogUtil.error("accountHistoryList error userNo:{},businessCode:{},startDate:{}",userNo,businessCode,startDate,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0200301");
			returnBean.setMsg("查询异常");
		}
		return returnBean;
	}
	
	@Override
	public ReturnBean<WithdrawDetailBean> findWithdrawDetail(String userNo, String requestId) {
		ReturnBean<WithdrawDetailBean> returnBean = new ReturnBean<WithdrawDetailBean>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		WithdrawDetailBean detail;
		try {
			detail = remitInterfaceService.findWithdrawDetail(requestId);
			returnBean.setData(detail);
		} catch (Exception e) {
			LogUtil.error("findWithdrawDetail error userNo:{},requestId:{},exception:{}",userNo,requestId,e);
			returnBean.setCode(RetCodeConstants.ERROR);
			returnBean.setErrorCode("0200401");
			returnBean.setMsg("查询异常");
		}
		return returnBean;
	}

	@Override
	public ReturnBean<String> credit(String userNo,Double amount,String systemFlowId,BusinessCode businessCode,ChildBusinessCode childBusinessCode ) {
		ReturnBean<String> returnBean = null;
		AccountHistory history = new AccountHistory();
		try {
			if(StringUtils.isBlank(userNo)){
				returnBean = new ReturnBean<String>(RetCodeConstants.FAIL, "用户编号不能为null或空");return returnBean;
			}
			if(null ==businessCode){
				returnBean = new ReturnBean<String>(RetCodeConstants.FAIL, "业务编码不能为null");return returnBean;
			}
			if(null == childBusinessCode){
				returnBean = new ReturnBean<String>(RetCodeConstants.FAIL, "子业务编码不能为null");return returnBean;
			}
			if(amount <0.00){
				returnBean = new ReturnBean<String>(RetCodeConstants.FAIL, "入账金额不得小于0.00元");return returnBean;
			}
			
			//获取账户
			UserAccountBean account = accountInterfaceService.findAccount(userNo);
			if(null != account){
				if(StringUtils.isBlank(systemFlowId)){
					systemFlowId = businessCode.name()+DateUtil.formatDate(new Date(), "yyyyMMddHHmmss")+CommonCodeUtil.NextInt(100000, 999999);
				}
				//新建账户操作历史
				history.setAccountNo(account.getAccountNo());
				history.setAmount(amount);
				history.setBalance(processBalance(account) + amount);
				history.setBusinessCode(businessCode);
				history.setChildBusinessCode(childBusinessCode);
				history.setCreateTime(new Date());
				history.setStatus(AccountHistoryStatus.INIT);
				history.setSymbol(AccountConstants.SYMBOL_PLUS);
				history.setUserNo(userNo);
				history.setQueryId(systemFlowId);
				createAccountHistory(history);
				
				CreditParamBean creditParamBean = new CreditParamBean();
				creditParamBean.setBussinessCode(businessCode.getAccountBusinessCode());
				creditParamBean.setUserNo(userNo);
				creditParamBean.setAccountNo(account.getAccountNo());
				creditParamBean.setAmount(amount);
				creditParamBean.setSystemFlowId(systemFlowId);
				creditParamBean.setOperator(Constants.SYSTEM_CODE);
				creditParamBean.setFundChannelCode("V_PAY_100001");	
				creditParamBean.setTransDate(history.getCreateTime());
				creditParamBean.setRemark(systemFlowId+"/"+userNo+"/"+account.getAccountNo());
				//入账
				accountInterfaceService.credit(creditParamBean);
				
				//更新账户操作历史
				history.setStatus(AccountHistoryStatus.SUCCESS);
				history.setOperateTime(new Date());
				updateAccountHistory(history);
				
				returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS, RetCodeConstants.SUCCESS_DESC);
			}else{
				LogUtil.error("credit fail , the account is null userNo:{}",userNo );
				returnBean = new ReturnBean<String>(RetCodeConstants.ERROR, "入账失败,账户不存在");
			}
		} catch (Throwable e) {
			LogUtil.error("credit error:{}", e);
			history.setStatus(AccountHistoryStatus.FAIL);
			updateAccountHistory(history);
			returnBean = new ReturnBean<String>(RetCodeConstants.ERROR, "入账失败");
		}
		return returnBean;
	}

}
