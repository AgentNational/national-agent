package com.pay.national.agent.core.test.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.service.common.AppMessageInfoService;
import com.pay.national.agent.core.test.context.BaseTest;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.MessageListQueryBean;
import com.pay.national.agent.model.beans.results.AppMessageListBean;
import com.pay.national.agent.model.entity.AppMessageInfo;

public class AppMessageInfoServiceTest extends BaseTest{
	@Resource
	private AppMessageInfoService appMessageInfoService;
	
	@org.junit.Test
	public void deleteTest(){
		ReturnBean<Object> returnBean = appMessageInfoService.deleteMessage("0a8e9f38b674433d862212a1de7f23ee");
		System.out.println(returnBean);
	}
	
	@org.junit.Test
	public void readTest(){
		ReturnBean<Object> returnBean = appMessageInfoService.updateIsRead("18205601657",new String[]{"c2d1531ba6884f6da9f47641d956b6b9"});
		System.out.println(returnBean);
	}
	
	@org.junit.Test
	public void findAllTest(){
		MessageListQueryBean queryBean = new MessageListQueryBean();
		queryBean.setUserName("18205601657");
		Page<List<AppMessageListBean>> page = new Page<>();
		page.setCurrentPage(2);
		page.setShowCount(10);
		
		Page<List<AppMessageListBean>> page1 = appMessageInfoService.findAllAppMessageInfo(queryBean, page);
		System.out.println(page1);
		
	}
	
	@org.junit.Test
	public void insertTest(){
		AppMessageInfo appMessageInfo = new AppMessageInfo();
		appMessageInfo.setUserName("18205601657");
		appMessageInfo.setTitle("提现通知");
		appMessageInfo.setContent("尊敬的代理人,您于2017年12点32分45秒提取的240.00元已成功到达卡号为26621************34045的银行卡中,请您及时查看，如有问题请联系我们的微信公众号\"全民代理人\"");
		appMessageInfo.setBriefContent("您提现的240元已到款，请查收");
		appMessageInfo.setCreateTime(new Date());
		appMessageInfo.setIsPop(true);
		appMessageInfoService.createMessage(appMessageInfo);
	}
}
