package com.pay.national.agent.core.listener;

import org.springframework.stereotype.Component;
/**
 * 消费mqdemo
 * @author shuyan.qi
 * Date:2017年9月7日上午12:05:49
 */
@Component("demoMessageListener")
public class DemoMessageListener{ //implements MessageListener{

	/*@Override
	public void onMessage(ATMessage message) {
		LogUtil.info("recieve mq message : {}",message);
		if(message instanceof StringMessage)
		{
			StringMessage msg = (StringMessage) message;
		}
		else if(message instanceof ObjectMessage)
		{
			
		}
	}*/
}
