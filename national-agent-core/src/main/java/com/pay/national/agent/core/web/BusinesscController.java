package com.pay.national.agent.core.web;

import com.pay.national.agent.common.persistence.Page;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.BusinessService;
import com.pay.national.agent.model.entity.BusinessOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 代理业务
 * Created by shuyan.qi on 2018/1/23.
 */
@Controller
@RequestMapping("/buss")
public class BusinesscController {
    @Autowired
    private BusinessService businessService;
    private Page<BusinessOrder> page = new Page<>();

    /**
     * 代理业务列表
     * @param parentCode 父业务编码
     * @return
     */
   /* @RequestMapping("/bussList")
    public String businessList(String parentCode){
        LogUtil.info("获取代理业务列表 parentCode={}",parentCode);
        return businessService.businessList(parentCode);
    }*/

    /**
     * 创建订单
     * @param order
     * @return
     */
    public String createOrder(BusinessOrder order){
        LogUtil.info("创建信用卡业务订单 order={}",order);
        String result = businessService.createOrder(order);
        LogUtil.info("创建信用卡业务订单 return order={},result={}",order,result);
        return result;
    }

    /**
     * 查询订单
     * @param userNo 用户编号
     * @param parentBusinessCode 父业务编码
     * @return
     */
    public String orders(String userNo,String parentBusinessCode,Integer page){
        LogUtil.info("查询订单 userNo={},parentBusinessCode={},page={}",userNo,parentBusinessCode,page);
        this.page.setCurrentPage(page == null?1:page);
        String result = businessService.orders(userNo,parentBusinessCode,this.page);
        LogUtil.info("查询订单 return userNo={},parentBusinessCode={},page={},result={}",userNo,parentBusinessCode,page,result);
        return result;
    }
}
