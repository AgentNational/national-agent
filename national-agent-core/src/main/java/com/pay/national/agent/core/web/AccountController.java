package com.pay.national.agent.core.web;

import com.pay.national.agent.core.service.common.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账户
 * @author shuyan.qi
 * @date 2018/1/28
 */
@RequestMapping("/acc")
@Controller
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;
}
