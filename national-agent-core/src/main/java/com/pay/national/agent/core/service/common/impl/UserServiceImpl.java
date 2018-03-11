package com.pay.national.agent.core.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.SequenceUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.wx.AppUserMapper;
import com.pay.national.agent.core.service.common.*;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.core.service.wx.gate.WxService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.constants.IncrementerConstant;
import com.pay.national.agent.model.constants.RetCodeConstants;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.model.entity.CheckCodeInfo;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户service
 * @see: 需要参考的类
 * @version 2017年9月6日 下午2:26:44
 * @author zhenhui.liu
 */
@Service("userService")
public class UserServiceImpl implements UserService{



	@Resource
	private IncrementerService incrementerService;

	@Resource
	private AppUserMapper appUserMapper;

	@Resource
	private WxUserInfoService wxUserInfoService;
	@Resource
	private AccountService accountService;

	@Resource
	private WxService wxService;
	/**
	 * 公众号注册
	 * @param fromUserName
	 * @param eventKey
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void register(String fromUserName, String eventKey) {
		WxUserInfo wxUserInfoDb = wxUserInfoService.selectUserInfoByOpenId(fromUserName);
		//openId已经注册
		if(wxUserInfoDb == null) {
			//创建用户编号
			long nextLongValue = incrementerService.nextLongValue(IncrementerConstant.SEQ_USER_NO);
			String userNo = SequenceUtils.createSequence(nextLongValue, new int[]{8, 6, 1, 9, 3, 8, 0, 5, 2, 1, 5, 2},
					new int[]{4, 5}, new int[]{6, 8}, new int[]{7, 8});
			WxUserInfo wxUserInfo = new WxUserInfo();
			wxUserInfo.setUserNo(userNo);
			wxUserInfo.setCreatetime(new Date());
			wxUserInfo.setSubscribe("1");
			wxUserInfo.setOpenid(fromUserName);
			AppUser appUser = new AppUser();
			//获取用户父编号
			String parentUserNo = null;
			if (StringUtils.isNotBlank(eventKey)) {
				//微信推送的eventKey格式为qrscene_861235988，所以需要进行截取
				String[] eventKeys = eventKey.split("_");
				parentUserNo = eventKeys[1];
			}
			appUser.setAbleStatus("ENABLE");
			appUser.setCreateTime(new Date());
			appUser.setParentUserNo(parentUserNo);
			appUser.setUserNo(userNo);
			appUserMapper.insert(appUser);
			wxUserInfoService.insert(wxUserInfo);

			try {
				accountService.openAccount(userNo);
			} catch (Exception e) {
				LogUtil.error("用户注册 开户失败 userNo={}",userNo,e);
			}
		}else{
			wxUserInfoDb.setSubscribe("1");
			wxUserInfoService.update(wxUserInfoDb);
		}

		getUserInfo(fromUserName);

	}

	private void getUserInfo(String openId){
		//拉取用户信息
		String result = wxService.getUserInfo(openId);
		Map<String, Object> userMap = JSONObject.parseObject(result, Map.class);
		try {
			if (userMap != null && null == userMap.get("errcode")) {
				WxUserInfo wxUserInfo = wxUserInfoService.selectUserInfoByOpenId(openId);
				if (wxUserInfo != null) {
					wxUserInfo.setOptimistic(wxUserInfo.getOptimistic()+1);
					wxUserInfo.setCountry(userMap.get("country").toString());
					wxUserInfo.setProvince(userMap.get("province").toString());
					wxUserInfo.setCity(userMap.get("city").toString());
					wxUserInfo.setLanguage(userMap.get("language").toString());
					wxUserInfo.setNickname(userMap.get("nickname").toString());
					wxUserInfo.setSex(userMap.get("sex").toString());
					wxUserInfo.setHeadimgurl(userMap.get("headimgurl").toString());
					wxUserInfoService.update(wxUserInfo);
				}else{
					wxUserInfo = new WxUserInfo();
					wxUserInfo.setCountry(userMap.get("country").toString());
					wxUserInfo.setProvince(userMap.get("province").toString());
					wxUserInfo.setCity(userMap.get("city").toString());
					wxUserInfo.setLanguage(userMap.get("language").toString());
					wxUserInfo.setNickname(userMap.get("nickname").toString());
					wxUserInfo.setSex(userMap.get("sex").toString());
					wxUserInfo.setCreatetime(new Date());
					wxUserInfo.setHeadimgurl(userMap.get("headimgurl").toString());
					wxUserInfo.setOpenid(openId);
					wxUserInfo.setCreatetime(new Date());
					wxUserInfo.setOptimistic(0);
					wxUserInfoService.insert(wxUserInfo);
				}
			}
		} catch (Exception e) {
			LogUtil.error("保存微信用户信息到数据库异常  e:{}",e);
		}
	}

	/**
	 * 校验是否需要填写手机号
	 * @param openId
	 * @return
	 */
	@Override
	public String checkPhone(String openId) {
		ReturnBean<Map<String,String>> returnBean = new ReturnBean(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		Map<String,String> map = new HashMap<>();
		//通过openId查询用户信息
		AppUser appUser = appUserMapper.findUserByOpenId(openId);
		if(appUser != null){//若查询到用户信息
			//判断是否已经存在手机号
			if(StringUtils.isNotBlank(appUser.getUserName())){
				map.put("STATUS","FALSE");
				returnBean.setData(map);
			}else {
				map.put("STATUS","TRUE");
				returnBean.setData(map);
			}
		}else{
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("用户信息不存在！");
		}
		return JSON.toJSONString(returnBean);
	}

	@Resource
	private CheckCodeInfoService checkCodeInfoService;

	/**
	 * 完善手机号
	 * @param openId
	 * @param checkCode
	 * @param phoneNo
	 * @return
	 */
	@Override
	public String completelPhone(String openId, String checkCode, String phoneNo) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		//校验手机号是否已经存在
		AppUser appUser = appUserMapper.findUserByUserName(phoneNo);
		if(appUser != null){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("手机号已经存在!");
			return  JSON.toJSONString(returnBean);
		}
		//校验验证码的正确性 通过数据表来实现
		CheckCodeInfo checkCodeInfo = checkCodeInfoService.getEffectCheckCode(openId,phoneNo,new Date());
		if(checkCodeInfo == null){
			//未获取验证码或验证码已经过期
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("未获取验证码或验证码已经过期!");
			return JSON.toJSONString(returnBean);
		}else{//查询到了验证码
			if(!checkCodeInfo.getCode().equals(checkCode)){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("验证码不正确!");
				return JSON.toJSONString(returnBean);
			}
		}
		//更新appuser表
		//查询用户编号
		AppUser appUser1 = appUserMapper.findUserByOpenId(openId);
		if(appUser1 == null){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("用户信息不存在!");
			return JSON.toJSONString(returnBean);
		}else{
			//修改用户手机号
			appUserMapper.updateUserNameByUserNo(appUser1.getUserNo(),phoneNo);
		}
		return JSON.toJSONString(returnBean);
	}

	@Resource
	private SmsService smsService;


	@Override
	public String sendCheckCode(String openId, String phoneNo) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);

		try {
			CheckCodeInfo checkCodeInfo = checkCodeInfoService.getEffectCheckCode(openId, phoneNo, new Date());
			if(checkCodeInfo != null){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("已经获取过验证码！");
				return  JSON.toJSONString(returnBean);
			}
			Boolean result =  smsService.sendSms(openId,phoneNo);
			if(result){
				return JSON.toJSONString(returnBean);
			}else{
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("短信验证码获取失败！");
				return  JSON.toJSONString(returnBean);
			}
		} catch (ClientException e) {
			LogUtil.error("获取短信验证码异常",e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("短信验证码获取失败！");
			return  JSON.toJSONString(returnBean);
		}
	}

	/**
	 * 业务service
	 */
	@Resource
	private BusinessService businessService;
	/**
	 * 校验是否有发展全民代理业务权限
	 * @param userNo
	 * @return
	 */
	@Override
	public String checkAgentRight(String userNo) {
		return businessService.checkAgentRight(userNo);
	}

	@Override
	public AppUser findUserInfo(String userNo) {
		return appUserMapper.findUserInfo(userNo);
	}

}
