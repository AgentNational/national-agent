package com.pay.national.agent.core.dao.pos;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.model.beans.query.PosOrderQueryBean;
import com.pay.national.agent.model.entity.PosOrder;

public interface PosOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PosOrder record);

    int insertSelective(PosOrder record);

    PosOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PosOrder record);

    int updateByPrimaryKey(PosOrder record);

	List<PosOrder> findAllPosOrder(@Param("page")Page<List<PosOrder>> page, @Param("posOrderQueryBean")PosOrderQueryBean posOrderQueryBean);

	/**
	 * @Description 通过手机号和业务编码查询订单信息
	 * @param businessCode
	 * @param phoneNo
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<PosOrder> findOrderByPhoneNoAndBusinessCode(@Param("businessCode")String businessCode, @Param("phoneNo")String phoneNo);

	List<PosOrder> findTrueOrders();

	/**
	 * @Description 通过采购单号更新订单
	 * @param posOrder
	 * @see 需要参考的类或方法
	 */
	void updateBySalesBillNoSelective(PosOrder posOrder);
}