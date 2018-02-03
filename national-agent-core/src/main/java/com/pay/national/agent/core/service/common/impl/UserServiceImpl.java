package com.pay.national.agent.core.service.common.impl;

import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.common.utils.SequenceUtils;
import com.pay.national.agent.common.utils.StringUtils;
import com.pay.national.agent.core.dao.wx.AppUserMapper;
import com.pay.national.agent.core.service.common.AccountService;
import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.core.service.wx.WxUserInfoService;
import com.pay.national.agent.model.constants.IncrementerConstant;
import com.pay.national.agent.model.entity.AppUser;
import com.pay.national.agent.model.entity.WxUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
		}

	}
}
