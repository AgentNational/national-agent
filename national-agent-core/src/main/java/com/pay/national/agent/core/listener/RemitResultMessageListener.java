package com.pay.national.agent.core.listener;

import org.springframework.stereotype.Component;

/**
 * 付款结果
 * @author shuyan.qi
 * Date:2017年9月7日上午12:07:46
 */
@Component("remitResultMessageListener")
public class RemitResultMessageListener{//implements MessageListener{

	
	/*@Resource
	private RemitPaymentService remitPaymentService;

	@Override
	public void onMessage(ATMessage message) {
		if(null != message){
			StringMessage strMessage = (StringMessage)message;
			LogUtil.info("付款结果通知消息内容:{}",strMessage);
			Map<String,Object> content = JSONUtils.toObject(strMessage.getMsg(), Map.class);

			String requestId = (String) content.get("requestId");
			String remitBillId = (String) content.get("remitBillId");
			String description = (String) content.get("description");
			String status = (String) content.get("status");
			Date lastUpdateDate = new Date((Long) content.get("lastUpdateDate"));

			RemitResult result = new RemitResult();
			result.setChangeTime(lastUpdateDate);
			result.setRemark(description);
			result.setRemitStatus(status);
			result.setRemitRequestId(requestId);
			result.setRemitPaymentId(remitBillId);
			remitPaymentService.createRecommenderRemitResult(result);
		}
	}*/

}
