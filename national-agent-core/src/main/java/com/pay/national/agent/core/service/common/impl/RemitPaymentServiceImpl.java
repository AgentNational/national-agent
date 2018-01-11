package com.pay.national.agent.core.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pay.national.agent.core.dao.common.RemitLogMapper;
import com.pay.national.agent.core.dao.common.RemitPaymentMapper;
import com.pay.national.agent.core.dao.common.RemitResultMapper;
import com.pay.national.agent.core.service.common.RemitPaymentService;
import com.pay.national.agent.model.entity.RemitLog;
import com.pay.national.agent.model.entity.RemitPayment;
import com.pay.national.agent.model.entity.RemitResult;

@Service("remitPaymentService")
public class RemitPaymentServiceImpl implements RemitPaymentService{
	
	@Resource
	private RemitPaymentMapper remitPaymentMapper;
	
	@Resource
	private RemitResultMapper remitResultMapper;
	
	@Resource
	private RemitLogMapper remitLogMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public RemitPayment createRemitPayment(RemitPayment remitPayment) {
		remitPaymentMapper.insert(remitPayment);
		return remitPayment;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateRemitPayment(RemitPayment remitPayment) {
		remitPaymentMapper.updateByPrimaryKey(remitPayment);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public RemitResult createRecommenderRemitResult(RemitResult result) {
		remitResultMapper.insert(result);
		return result;
	}

	@Override
	public RemitPayment findRemitPaymentByRequestId(String requestId) {
		return remitPaymentMapper.findByRequestId(requestId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public RemitLog createRemitLog(RemitLog remitLog) {
		remitLogMapper.insert(remitLog);
		return remitLog;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateRemitLog(RemitLog remitLog) {
		remitLogMapper.updateByPrimaryKey(remitLog);
	}

}
