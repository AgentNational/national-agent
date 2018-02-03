package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.SequenceUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.common.UserMapper;
import com.pay.national.agent.core.dao.wx.WxUserInfoMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.beans.results.FindInformationResultBean;
import com.pay.national.agent.model.beans.results.LoginResultBean;
import com.pay.national.agent.model.constants.IncrementerConstant;
import com.pay.national.agent.model.entity.User;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 用户service
 * @see: 需要参考的类
 * @version 2017年9月6日 下午2:26:44
 * @author zhenhui.liu
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public User findUserByUserName(String phoneNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUserNo(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBean<LoginResultBean> checkLoginInfo(LoginParamBean loginParamBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnBean<LoginResultBean> login(LoginParamBean loginParamBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logout(String loginKey) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ReturnBean<Object> findPassword(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ReturnBean<FindInformationResultBean> findInformation(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ReturnBean<Object> resetPassword(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Resource
	private IncrementerService incrementerService;

	@Resource
	private UserMapper userMapper;

	@Resource
	private WxUserInfoMapper wxUserInfoMapper;

	@Resource
	private AccountService accountService;
	/**
	 * 公众号注册
	 * @param fromUserName
	 * @param eventKey
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void register(String fromUserName, String eventKey) {
		//创建用户编号
		long nextLongValue = incrementerService.nextLongValue(IncrementerConstant.SEQ_USER_NO);
		String userNo = SequenceUtils.createSequence(nextLongValue, new int[] { 8, 6, 1, 9, 3, 8, 0, 5, 2, 1,5,2},
				new int[] { 4, 5 }, new int[] { 6, 8 }, new int[] { 7, 8 });
		WxUserInfo wxUserInfo = new WxUserInfo();
		//wxUserInfo.setUserId();
		wxUserInfo.setCreatetime(new Date());
		wxUserInfo.setOpenid(fromUserName);
		User appUser = new User();
		//获取用户父编号
		String parentUserNo = null;
		if(StringUtils.isNotBlank(eventKey)){
			//微信推送的eventKey格式为qrscene_861235988，所以需要进行截取
			String [] eventKeys = eventKey.split("_");
			parentUserNo = eventKeys[1];
		}
		appUser.setAbleStatus("ENABLE");
		appUser.setCreateTime(new Date());
		appUser.setParentUserNo(parentUserNo);
		appUser.setUserNo(userNo);
		userMapper.insert(appUser);
		wxUserInfoMapper.insert(wxUserInfo);

		try {
			accountService.openAccount(userNo);
		} catch (Exception e) {
			LogUtil.error("用户注册 开户失败 userNo={}",userNo,e);
		}
	}
}
