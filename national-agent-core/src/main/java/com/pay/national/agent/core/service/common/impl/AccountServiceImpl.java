package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.core.dao.common.AccountMapper;
import com.pay.national.agent.core.service.common.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuyan.qi
 * @date 2018/1/28
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
}
