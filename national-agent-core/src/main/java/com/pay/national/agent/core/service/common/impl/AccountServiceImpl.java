package com.pay.national.agent.core.service.common.impl;

import com.pay.commons.utils.lang.AmountUtils;
import com.pay.national.agent.common.exception.NationalAgentException;
import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.*;
import com.pay.national.agent.core.dao.common.AccountHistoryMapper;
import com.pay.national.agent.core.dao.common.AccountMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.wx.EnterPrisePaymentService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.DepositParam;
import com.pay.national.agent.model.beans.query.RemitParam;
import com.pay.national.agent.model.beans.results.AccHistoryResultBean;
import com.pay.national.agent.model.beans.results.DepositBean;
import com.pay.national.agent.model.beans.results.RemitBean;
import com.pay.national.agent.model.constants.AccountConstants;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.constants.StatusConstants;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.AccountHistory;
import com.pay.national.agent.model.enums.AccountStatus;
import com.pay.national.agent.model.enums.BusinessCode;
import com.pay.national.agent.model.enums.ParentBusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final String ILLEGAL_ACCOUNT_NO = "非法编号,开户失败";
    private static final String REMIT_ACC_EXCEPTION = "账户异常,禁止出款";
    private static final String REMIT_BALANCE_SHORTAGE = "余额不足";
    private static final String REMIT_AMOUNT_LIMIT = "提现金额必须不低于40元";
    private static final String REMIT_ERROR="提现异常";


    /**
     * 用户提现最低金额限制
     */
    public static double USER_WITHDRAW_LIMIT = 0.00;
    /**
     * 押金
     */
    public static double REMIT_YAJIN = 0.00;

    private static PropertiesLoader system = new PropertiesLoader("system.properties");
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountHistoryMapper accountHistoryMapper;
    @Autowired
    private EnterPrisePaymentService enterPrisePaymentService;

    static {
        USER_WITHDRAW_LIMIT = system.getDouble("user.withdraw.limit");
        REMIT_YAJIN = system.getDouble("user.withdraw.yajin");
    }
    /**
     * 用户信息
     * @param userNo 用户编号
     * @return
     */
    @Override
    public Account findByUser(String userNo) {
        return accountMapper.findByuser(userNo);
    }

    /**
     * 账户历史记录
     * @param userNo 用户编号
     * @param parentBusinessCode 业务编码
     * @param month 月份 格式 yyyy-MM-dd
     * @param page 分页
     * @return
     */
    @Override
    public String accHistories(String userNo,String parentBusinessCode,String month, Page<AccountHistory> page) {
        ReturnBean<List<AccHistoryResultBean>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            List<AccountHistory> list = accountHistoryMapper.findAllHistory(userNo,parentBusinessCode,month,page);
            if(list != null && list.size() >0){
                List<AccHistoryResultBean> resultList = new ArrayList<AccHistoryResultBean>(10);
                for (AccountHistory history:list) {
                    AccHistoryResultBean accHistoryResultBean = new AccHistoryResultBean();
                    BeanUtils.applyIf(accHistoryResultBean,history);
                    accHistoryResultBean.setBusinessName(BusinessCode.valueOf(accHistoryResultBean.getBusinessCode()).getBusienssName());
                    resultList.add(accHistoryResultBean);
                }
                returnBean.setData(resultList);
            }
        } catch (Exception e) {
            LogUtil.error("账户历史记录 error userNo={},parentBusinessCode={},page={}",userNo,parentBusinessCode,page,e);
            returnBean.setCode(RetCodeConstants.ERROR);
            returnBean.setMsg(RetCodeConstants.ERROR_QUERY_DESC);
        }
        return JSONUtils.alibabaJsonString(returnBean);
    }


    /**
     * 入账
     * @param param
     * @return
     */
    @Override
    public DepositBean deposit(DepositParam param) {
        return null;
    }

    /**
     * 出款
     * @param param
     * @return
     */
    @Override
    public RemitBean remit(RemitParam param) {
        RemitBean remitBean = null;
        //真正付款的金额
        double remitAmount = 0.00D;
        try {
            checkRemit(param);
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setAccountNo(param.getAccountNo());
            accountHistory.setUserNo(param.getUserNo());
            accountHistory.setParentBusinessCode(ParentBusinessCode.ACCOUNT_REMIT.name());
            accountHistory.setBusinessCode(BusinessCode.REMIT_USER.name());
            accountHistory.setSymbol(AccountConstants.SYMBOL_SUBTRACT);
            accountHistory.setAmount(param.getAmount());
            accountHistory.setCreateTime(new Date());
            List<AccountHistory> yanjin = accountHistoryMapper.selectByUser(param.getUserNo(),BusinessCode.REMIT_YAJIN.name());
            if(yanjin == null || yanjin.size() == 0){
                remitAmount = AmountUtils.subtract(param.getAmount(), REMIT_YAJIN);
                accountHistory.setBusinessCode(BusinessCode.REMIT_YAJIN.name());
            }else{
                remitAmount = param.getAmount();
            }

            Map<String,String> payParam =  new HashMap<String,String>(4);
            payParam.put("amount",String.valueOf(remitAmount));
            payParam.put("desc","全民代理用户提现");
            payParam.put("openId",param.getOpenId());
            payParam.put("ip",param.getUserIp());


            try {
                //生成付款单
                ReturnBean<Object> returnBean = enterPrisePaymentService.createPayBill(payParam);

                if(returnBean != null && RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
                    accountHistory.setStatus(StatusConstants.SUCCESS);
                    accountHistory.setWxBillNo(returnBean.getData().toString());
                }else{
                    accountHistory.setStatus(StatusConstants.FAIL);
                    if(returnBean != null){
                        accountHistory.setErrorMsg("创建付款单失败");
                    }else{
                        accountHistory.setErrorMsg("创建付款单异常,returnBean is null");
                    }
                }
            } catch (Exception e) {
                LogUtil.error("出款 创建付款单异常 payParam={}",payParam,e);
                accountHistory.setStatus(StatusConstants.ERROR);
                accountHistory.setErrorMsg("创建付款单异常;"+e.getMessage());
            }

            //调用微信企业付款进行出款操作
           if(StatusConstants.SUCCESS.equals(accountHistory.getStatus())){
                try {
                    String payResult = enterPrisePaymentService.payment(accountHistory.getWxBillNo());
                    if(StringUtils.isBlank(payResult)){
                        accountHistory.setStatus(StatusConstants.ERROR);
                        accountHistory.setErrorMsg("微信企业付款异常,result is null");
                    }

                    ReturnBean<Object> returnBean = JSONUtils.toObject(payResult, ReturnBean.class);
                    if(returnBean != null && RetCodeConstants.SUCCESS.equals(returnBean.getCode())){
                        accountHistory.setStatus(StatusConstants.SUCCESS);
                    }else{
                        accountHistory.setStatus(StatusConstants.FAIL);
                        if(returnBean != null){
                            accountHistory.setErrorMsg("微信企业付款失败");
                        }else{
                            accountHistory.setErrorMsg("微信企业付款异常 returnBean is null");
                        }
                    }
                } catch (Exception e) {
                    LogUtil.error("出款 微信父企业付款异常 accountHistory={}",accountHistory,e);
                    accountHistory.setStatus(StatusConstants.ERROR);
                    accountHistory.setErrorMsg("微信父企业付款异常;"+e.getMessage());
                }
            }

            //减去账户金额
            if(StatusConstants.SUCCESS.equals(accountHistory.getStatus())){
                try {
                    subAmount(accountHistory.getAccountNo(),accountHistory.getAmount());
                } catch (Exception e) {
                    LogUtil.error("出款 减去账户的金额异常 accountHistory={}",accountHistory,e);
                    accountHistory.setStatus(StatusConstants.ERROR);
                    accountHistory.setErrorMsg("减去账户金额异常;"+e.getMessage());
                }
            }

            accountHistoryMapper.insert(accountHistory);

            remitBean = new RemitBean();
            remitBean.setAmount(param.getAmount());
            remitBean.setRemitAmount(accountHistory.getAmount());
            remitBean.setPayBillNo(accountHistory.getWxBillNo());
            remitBean.setPayStatus(accountHistory.getStatus());
            remitBean.setFee(0.00);
            if(BusinessCode.REMIT_YAJIN.name().equals(accountHistory.getBusinessCode())){
                remitBean.setYajin(REMIT_YAJIN);
            }
            remitBean.setTransTime(accountHistory.getCreateTime());
        } catch (Exception e) {
            LogUtil.error("出款异常 param={}",param,e);
            throw new NationalAgentException(RetCodeConstants.ERROR,REMIT_ERROR);
        }
        return remitBean;
    }

    /**
     * 出款校验
     * @param param
     */
    private void checkRemit(RemitParam param) {
        if(param.getAmount() < USER_WITHDRAW_LIMIT){
            throw new NationalAgentException(RetCodeConstants.FAIL,REMIT_AMOUNT_LIMIT);
        }
        Account account = accountMapper.findByuser(param.getUserNo());
        if(account == null || AccountStatus.ENABLE != account.getStatus()){
            throw new NationalAgentException(RetCodeConstants.FAIL,REMIT_ACC_EXCEPTION);
        }
        param.setAccountNo(account.getAccountNo());
        double subtract = AmountUtils.subtract(account.getBalance(), param.getAmount());
        if(subtract <= 0.00){
            throw new NationalAgentException(RetCodeConstants.FAIL,REMIT_BALANCE_SHORTAGE);
        }


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void addAmount(String accountNo, double amount) {
       Account account  = accountMapper.find(accountNo);
       if(account != null){
           BigDecimal decimal = new BigDecimal(amount);
           BigDecimal amount1 = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
           double add = AmountUtils.add(account.getBalance(), amount1.doubleValue());
           account.setLastUpdateTime(new Date());
           account.setBalance(add);
           accountMapper.amountOnLock(account);
       }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Throwable.class)
    public void subAmount(String accountNo, double amount) {
        Account account = accountMapper.find(accountNo);
        if(account != null){
            BigDecimal decimal = new BigDecimal(amount);
            BigDecimal amount1 = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            double subtract = AmountUtils.subtract(account.getBalance(), amount1.doubleValue());
            account.setLastUpdateTime(new Date());
            account.setBalance(subtract);
            int low = accountMapper.amountOnLock(account);
        }
    }

    /**
     * 开户
     * @param userNo 用户编号
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void openAccount(String userNo) {
        try {
            if(StringUtils.isBlank(userNo)){
                throw new NationalAgentException(RetCodeConstants.ERROR,ILLEGAL_ACCOUNT_NO);
            }
            Account account = accountMapper.findByuser(userNo);
            if(account != null){
                LogUtil.info("该账户存在 userNo={}",userNo);
                return;
            }
            account = new Account();
            account.setUserNo(userNo);
            account.setAccountNo(userNo);
            account.setBalance(0.00);
            account.setFrozenAmount(0.00);
            account.setTransAmount(0.00);
            account.setCreateTime(new Date());
            account.setStatus(AccountStatus.ENABLE);
            accountMapper.insert(account);
        } catch (Exception e) {
            LogUtil.error("开户异常 userNo={}",userNo,e);
            throw e;
        }
    }

    @Override
    public AccountHistory findRemitHistory(String billNo) {
        return accountHistoryMapper.findRemitHistory(billNo);
    }
}
