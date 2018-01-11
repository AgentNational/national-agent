package com.pay.national.agent.core.service.pos.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.RedisUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.pos.PosBusinessMapper;
import com.pay.national.agent.core.dao.pos.PosOrderMapper;
import com.pay.national.agent.core.service.pos.PosService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.PosBusinessParamBean;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.constants.RedisKeys;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.PosBusiness;
import com.pay.national.agent.model.entity.PosOrder;
import com.pay.national.agent.model.enums.PosOrderStatus;

@Service("posService")
public class PosServiceImpl implements PosService{

	@Resource
	private PosBusinessMapper posBusinessMapper;

	@Resource
	private PosOrderMapper posOrderMapper;
	@Override
	public List<PosBusiness> businessList(String userNo) {
		return posBusinessMapper.findPosBusiness();
	}

	@Override
	public void createPosOrder(PosOrder posOrder) {
		posOrderMapper.insert(posOrder);
	}

	@Override
	public List<PosOrder> queryOrders(Page<List<PosOrder>> page, PosOrderQueryBean posOrderQueryBean) {
		return posOrderMapper.findAllPosOrder(page,posOrderQueryBean);
	}

	/**
	 *领取pos校验
	 */
	@Override
	public ReturnBean<Object> checkBusinessProcessing(PosBusinessParamBean posBusinessParamBean) {
		ReturnBean<Object> returnBean = new ReturnBean<Object>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		String code = RedisUtils.get(RedisKeys.KAYOU_POS_CHECK_CODE_PREFIX+posBusinessParamBean.getPhoneNo());
		String businessCode = posBusinessParamBean.getBusinessCode();
		String phoneNo = posBusinessParamBean.getPhoneNo();
		if(StringUtils.isBlank(code)){//验证码为空
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("请先获取验证码！");
			return returnBean;
		}
		if(StringUtils.isBlank(businessCode)){//业务编码为空
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("业务编码为空");
			return returnBean;
		}
		if(StringUtils.isBlank(phoneNo)){//手机号为空
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("手机号为空！");
			return returnBean;
		}
		if(!code.equals(posBusinessParamBean.getCheckCode())){//验证码不正确
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("验证码不正确！");
			return returnBean;
		}
		List<PosOrder> posOrders = findOrderByPhoneNoAndBusinessCode(businessCode,phoneNo);
		//校验是否已经领取过pos
		if(posOrders != null && posOrders.size()>0){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("您已领取过pos，请勿重复领取！");
			return returnBean;
		}
		return returnBean;
	}

	public List<PosOrder> findOrderByPhoneNoAndBusinessCode(String businessCode, String phoneNo) {
		return posOrderMapper.findOrderByPhoneNoAndBusinessCode(businessCode,phoneNo);
	}

	@Override
	public List<PosOrder> findTrueOrders() {
		return posOrderMapper.findTrueOrders();
	}

	@Override
	public void updatePosOrder(PosOrder posOrder) {
		posOrderMapper.updateByPrimaryKey(posOrder);
	}


	@Override
	public void updatePosOrderBySalesBillNo(PosOrder posOrder) {
		posOrderMapper.updateBySalesBillNoSelective(posOrder);
	}

}
