package com.pay.national.agent.core.web;

import com.alibaba.fastjson.JSON;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.core.service.wx.QrCodeInfoService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信二维码相关controller
 */
@Controller
@RequestMapping("/qrCode")
@CrossOrigin//解决跨域
public class WxQrCodeController {

    @Autowired
    private QrCodeInfoService qrCodeInfoService;

    /**
     * 获取微信二维码图片地址
     * @return
     */
    @RequestMapping("/getQrCodeUrl")
    public @ResponseBody String getQrCodeUrl(@RequestParam("openId")String openId){
        LogUtil.info("获取微信二维码地址开始openId：{}",openId);
        ReturnBean<Object> returnBean = new ReturnBean<>();
        try {
            return qrCodeInfoService.getQrCodeUrl(openId);
        }catch (Exception e){
            LogUtil.info("获取微信二维码地址异常 ",e);
            returnBean.setCode(RetCodeConstants.FAIL);
            returnBean.setMsg(RetCodeConstants.FAIL_DESC);
        }
        return JSON.toJSONString(returnBean);


    }

}
