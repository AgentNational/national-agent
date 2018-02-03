package com.pay.national.agent.core.service.wx.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.national.agent.common.constants.WeiXinConstant;
import com.pay.national.agent.common.utils.DateUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.wx.QrCodeInfoMapper;
import com.pay.national.agent.core.service.wx.QrCodeInfoService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.QrCodeInfo;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Service("qrCodeInfoService")
public class QrCodeInfoServiceImpl implements QrCodeInfoService {

    @Autowired
    private QrCodeInfoMapper qrCodeInfoMapper;

    @Autowired
    private WxService wxService;

    @Autowired
    private WxUserInfoService wxUserInfoService;

    @Override
    @Transactional
    public String getQrCodeUrl(String openId) throws UnsupportedEncodingException {
        ReturnBean<String> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
        //通过openId查询当前是否有生效的ticket
        QrCodeInfo qrCodeInfo = qrCodeInfoMapper.findTicketByOpenIdAndDate(openId,new Date());
        String qrCodeUrl = WeiXinConstant.ticketUrl;
        if(qrCodeInfo != null ){//如果存在当前生效的tickent
            //拼接链接
            String ticket = URLEncoder.encode(qrCodeInfo.getTicket(),"utf-8");
            returnBean.setData(qrCodeUrl+ticket);
        }else{//如果不存在当前生效的ticket
            //通过openId查询userNo
            WxUserInfo wxUserInfo = wxUserInfoService.selectUserInfoByOpenId(openId);
            //调用微信接口获取ticket
            String accessToken = wxService.getEffectAccessToken(WeiXinConstant.APP_ID,WeiXinConstant.APP_SECRET);
            //调用微信接口获取ticket
            String content = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" +wxUserInfo.getUserNo()+ "\"}}}";
            String result = wxService.createQRCode(accessToken, content);
            JSONObject obj = JSON.parseObject(result);
            String ticket = (String) obj.get("ticket");
            returnBean.setData(qrCodeUrl+URLEncoder.encode(ticket,"utf-8"));
            String expireSeconds = (String) obj.get("expire_seconds");
            //保存获取结果到表中
            qrCodeInfo = new QrCodeInfo();
            qrCodeInfo.setAbleStatus("ENABLE");
            qrCodeInfo.setCreateTime(new Date());
            qrCodeInfo.setEffectTime(new Date());
            if(StringUtils.isBlank(expireSeconds)){
                qrCodeInfo.setExpireTime(DateUtil.addYears(100));
            }else{
                qrCodeInfo.setExpireTime(DateUtil.addSeconds(new Date(),Integer.parseInt(expireSeconds)));
            }

            qrCodeInfo.setOpenId(openId);
            qrCodeInfo.setUserNo(wxUserInfo.getUserNo());
            qrCodeInfo.setTicket(ticket);
            insert(qrCodeInfo);
        }
        LogUtil.info("获取二维码地址结束 openId:{} returnBean:{}",openId,JSON.toJSONString(returnBean));
        return JSON.toJSONString(returnBean);
    }

    private void insert(QrCodeInfo qrCodeInfo) {
        qrCodeInfoMapper.insert(qrCodeInfo);
    }
}
