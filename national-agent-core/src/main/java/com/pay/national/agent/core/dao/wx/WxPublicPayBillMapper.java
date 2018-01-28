package com.pay.national.agent.core.dao.wx;

import com.pay.national.agent.model.entity.WxPublicPayBill;
import org.apache.ibatis.annotations.Param;

public interface WxPublicPayBillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    int insert(WxPublicPayBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    int insertSelective(WxPublicPayBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    WxPublicPayBill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    int updateByPrimaryKeySelective(WxPublicPayBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_public_pay_bill
     *
     * @mbggenerated Sun Jan 28 11:25:01 GMT+08:00 2018
     */
    int updateByPrimaryKey(WxPublicPayBill record);

    /**
     * 通过商户订单号查询微信支付订单信息
     * @param outerTradeNo
     * @return
     */
    WxPublicPayBill findWxPublicPayByTradeno(@Param("outerTradeNo") String outerTradeNo);
}