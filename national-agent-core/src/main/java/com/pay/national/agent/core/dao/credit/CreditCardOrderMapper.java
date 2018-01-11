package com.pay.national.agent.core.dao.credit;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.core.bean.request.CreditOrderQueryBean;
import com.pay.national.agent.model.beans.CreditCardOrderMatchBean;
import com.pay.national.agent.model.entity.CreditCardOrder;

public interface CreditCardOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditCardOrder record);

    int insertSelective(CreditCardOrder record);

    CreditCardOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditCardOrder record);

    int updateByPrimaryKey(CreditCardOrder record);

	List<CreditCardOrder> findAllOrders(@Param("query")CreditOrderQueryBean query, @Param("page")Page<List<CreditCardOrder>> page);

	CreditCardOrder findApplyOrder(@Param("businessCode")String businessCode, @Param("customerPhone")String customerPhone);

	/**
	 * 合作方回导的“脱敏”数据与信用卡订单匹对
	 * @param businessCode 业务编码
	 * @param customerPhoneNo 用户手机号
	 * @param customerName 用户姓名
	 * @param status 订单状态
	 * @return
	 */
	List<CreditCardOrderMatchBean> match();
}