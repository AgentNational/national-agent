package com.pay.national.agent.core.web;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.model.entity.BusinessOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 代理业务
 *
 * @author shuyan.qi
 * @date 2018/1/23
 */
@Controller
@RequestMapping("/buss")
@CrossOrigin
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    /**
     * 创建订单
     * @param order
     * @return
     */
    public String createOrder(BusinessOrder order){
        LogUtil.info("Con 创建信用卡业务订单 order={}",order);
        String result = businessService.createOrder(order);
        LogUtil.info("Con 创建信用卡业务订单 return order={},result={}",order,result);
        return result;
    }

    /**
     * 查询订单
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @return
     */
    public String orders(String userNo,String parentBusinessCode,Integer pageIndex){
        LogUtil.info("Con 查询订单 userNo={},parentBusinessCode={},pageIndex={}",userNo,parentBusinessCode,pageIndex);
        Page<BusinessOrder> page = new Page<>();
        page.setCurrentPage(pageIndex == null?1:pageIndex);
        String result = businessService.orders(userNo,parentBusinessCode,page);
        LogUtil.info("Con 查询订单 return userNo={},parentBusinessCode={},pageIndex={},result={}",userNo,parentBusinessCode,pageIndex,result);
        return result;
    }
}
