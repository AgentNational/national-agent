package com.pay.national.agent.core.service.common.impl;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.bean.result.WithdrawDetailBean;
import com.pay.national.agent.core.service.common.RemitInterfaceService;
import com.pay.national.agent.model.entity.RemitPayment;

@Service("remitInterfaceService")
public class RemitInterfaceServiceImpl implements RemitInterfaceService{

	@Override
	public void remit(RemitPayment remitPayment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WithdrawDetailBean findWithdrawDetail(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	@Resource
	private SimpleRemitFrontInterface simpleRemitFrontInterface;
	
	@Resource
	private RemitQueryInterface remitQueryInterface;
	
	@Resource
	private RemitPaymentService remitPaymentService;

	@Override
	public void remit(RemitPayment remitPayment) {
		String userNo = remitPayment.getUserNo();
		OperParam operParam = new OperParam(Constants.SYSTEM_CODE, Constants.SYSTEM_CODE);
		OwnerParam ownerParam = new OwnerParam(userNo, UserRole.RECOMMENDER.name());
		SimpleRequestParam simpleRequestParam = getSimpleRequestParam(remitPayment);
		RemitFrontOrgParam frontOrgParam=new RemitFrontOrgParam("PAY", null);
		RemitResponse remitResponse = null;
		try{
			remitResponse = simpleRemitFrontInterface.remit(operParam, ownerParam, simpleRequestParam, frontOrgParam);
		} catch(Exception e) {
			String s = "call simpleRemitFrontInterface.remit error, userNo:" + userNo + ",remitRequestId:" + remitPayment.getRemitRequestId();
			LogUtil.error(s, e);
			throw new RemitException("0200108", s);
		}
		if (remitResponse == null || StringUtils.isBlank(remitResponse.getCode())) {
			String s = "call simpleRemitFrontInterface.remit not success, userNo:" + userNo + ",remitRequestId:" + remitPayment.getRemitRequestId();
			LogUtil.error(s);
			throw new RemitException("0200109", s);
		}

		if (!RemitFrontContent.SUCCESS.equals(remitResponse.getCode())) {
			String s = "call simpleRemitFrontInterface.remit not success, userNo:" + userNo + ",remitRequestId:" + remitPayment.getRemitRequestId() + ",code:" + remitResponse.getCode() + ",description:" + remitResponse.getDescription() + ",remitBillId:" + remitResponse.getRemitBillId();
			LogUtil.error(s);
			throw new RemitException("0200110", s);
		}

		// 更新付款单ID
		remitPayment.setRemitPaymentId(remitResponse.getRemitBillId());
	}

	*//**
	 * 组装请求参数
	 * @param remitPayment
	 * @return
	 *//*
	private SimpleRequestParam getSimpleRequestParam(RemitPayment remitPayment) {
		// 基本信息
		SimpleRequestParam simpleRequestParam = new SimpleRequestParam();
		simpleRequestParam.setInterfaceFlowId(remitPayment.getRemitRequestId());
		simpleRequestParam.setRequestId(remitPayment.getRemitRequestId());
		//TODO
		simpleRequestParam.setDescription("合伙人分润");
		simpleRequestParam.setUrgent(false);
		simpleRequestParam.setBusinessDate(new Date());
		//TODO
		simpleRequestParam.setRemitProduct("付款产品");

		// 结算卡
		simpleRequestParam.setCardId(remitPayment.getSettleCardId());

		// 付款金额
		AccountOptParam amountOptParam = new AccountOptParam(remitPayment.getRemitBusCode(), remitPayment.getRemitAmount());
		simpleRequestParam.setAmountOptParam(amountOptParam);

		// 付款费用
		Double fee = remitPayment.getRemitFee();
		if(null != remitPayment.getRemitFee() && fee.compareTo(0.0) == 1)
		{
			LogUtil.info("remitPayment.getRemitFee  > 0 ");
			AccountOptParam feeParam = new AccountOptParam(remitPayment.getFeeBusCode(), remitPayment.getRemitFee());
			simpleRequestParam.setFeeParam(feeParam);
		}
		LogUtil.info("remit request param simpleRequestParam:{}",simpleRequestParam);
		return simpleRequestParam;
	}

	@Override
	public WithdrawDetailBean findWithdrawDetail(String requestId) {
		WithdrawDetailBean detailBean = null;
		//TODO
		RemitBillBean remitBillBean = null;
		try {
			remitBillBean = remitQueryInterface.queryBy(requestId, "付款产品", new RemitOrgParam("PAY",null));
		} catch (Exception e) {
			LogUtil.error("call remitQueryInterface.queryBy() error:{}",e);
		}
		if(null != remitBillBean){
			detailBean = new WithdrawDetailBean();
			detailBean.setBankAccountNo(remitBillBean.getBankAccountNo());
			detailBean.setBankCode(remitBillBean.getBankCode());
			detailBean.setBankName(remitBillBean.getBankName());
			detailBean.setCreateDate(remitBillBean.getCreateDate());
			detailBean.setFailReason(remitBillBean.getFailReason());
			detailBean.setLastUpdateDate(remitBillBean.getLastUpdateDate());
			detailBean.setRemitAmount(remitBillBean.getRemitAmount());
			detailBean.setRemitBillStatus(remitBillBean.getRemitBillStatus());
		}
		return detailBean;
	}
*/
}
