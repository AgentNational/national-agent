package com.pay.national.agent.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jump")
public class JumpJsp {

    @RequestMapping("/jsp")
    public String jumpJsp(@RequestParam("url")String url){
        return url;
    }
}
