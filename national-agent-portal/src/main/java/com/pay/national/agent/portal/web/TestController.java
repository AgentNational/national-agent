package com.pay.national.agent.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/toTest.action")
	public String toTest(){
		return "test/test";
	}
}
