package com.pay.national.agent.core.web;

import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

/**
 *
 * 公共
 * @author shuyan.qi
 * @date 2018/1/28
 */
@RequestMapping("/comm")
@Controller
@CrossOrigin
public class CommonController {
    @Autowired
    private CommonService commonService;

    /**
     * 查询地区
     * @param pOrganCd 父地区编码
     * @return
     */
    @RequestMapping("/organs")
    @ResponseBody
    public String organizations(String pOrganCd){
        LogUtil.info("Con 查询地区 pOrganCd={}",pOrganCd);
        String result = commonService.organizations(pOrganCd);
        LogUtil.info("Con 查询地区 return pOrganCd={},result={}",pOrganCd,result);
        return result;
    }
}
