package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.JSONUtils;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.dao.common.AccountHistoryMapper;
import com.pay.national.agent.core.dao.common.AccountMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.wx.WxPublicPayService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.DepositParam;
import com.pay.national.agent.model.beans.query.RemitParam;
import com.pay.national.agent.model.beans.results.DepositBean;
import com.pay.national.agent.model.beans.results.RemitBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.Account;
import com.pay.national.agent.model.entity.AccountHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountHistoryMapper accountHistoryMapper;
    @Autowired
    private WxPublicPayService wxPublicPayService;
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
     * @param page 分页
     * @return
     */
    @Override
    public String accHistories(String userNo, Page<AccountHistory> page) {
        ReturnBean<List<AccountHistory>> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        try {
            List<AccountHistory> list = accountHistoryMapper.findAllHistory(userNo,page);
            returnBean.setData(list);
        } catch (Exception e) {
            LogUtil.error("账户历史记录 error userNo={},page={}",userNo,page,e);
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
        //wxPublicPayService.createPayBill();
        return null;
    }
}
