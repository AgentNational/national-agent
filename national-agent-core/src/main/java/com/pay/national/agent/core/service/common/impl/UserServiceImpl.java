package com.pay.national.agent.core.service.common.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.service.common.UserService;
import com.pay.national.agent.model.beans.ReturnBean;
import com.pay.national.agent.model.beans.query.LoginParamBean;
import com.pay.national.agent.model.beans.results.FindInformationResultBean;
import com.pay.national.agent.model.beans.results.LoginResultBean;
import com.pay.national.agent.model.entity.User;

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
	}/*

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserLoginLogMapper userLoginLogMapper;
	
	@Autowired
	private IncrementerService incrementerService;
	
	@Autowired
	private SettleCardService settleCardService;
	
	@Autowired
	private PushMsgService pushMsgService;
	
	private static PropertiesLoader property = new PropertiesLoader("system.properties");
	*//**
	 * 结算信息查询接口
	 *//*
	@Autowired
	private SettleQueryInterface settleQueryInterface;
	
	*//**
	 * 结算信息修改接口
	 *//*
	@Autowired
	private SettleManageInterface settleManageInterface;
	
	@Override
	public User findUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}
	
	@Override
	public User findUserByUserNo(String userNo) {
		return userMapper.findUserByUserNo(userNo);
	}

	*//**
	 * 校验注册信息合法性
	 * 1、校验验证码是否正确
	 * 2、校验手机号是否已经存在
	 * 3、校验上级用户是否存在，不存在默认挂至统一的用户
	 *//*
	@Override
	public ReturnBean<String> checkRegistInfo(RegisterParamBean registerParamBean) {
		ReturnBean<String> returnBean = new ReturnBean<String>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		String code = RedisUtils.get(RedisKeys.REGISTER_CHECK_CODE_PREFIX+registerParamBean.getPhoneNo());
		if(StringUtils.isBlank(code)){//验证码为空
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("请先获取验证码！");
			return returnBean;
		}
		if(!code.equals(registerParamBean.getCheckCode())){//验证码不正确
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("验证码不正确！");
			return returnBean;
		}
		User user = findUserByUserName(registerParamBean.getPhoneNo());
		if(null != user){//手机号已经注册
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("该手机号已经注册");
			return returnBean;
		}
		return returnBean;
	}

	*//**
	 * 用户注册
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<String> register(RegisterParamBean registerParamBean) {
		ReturnBean<String> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		User user = new User();
		user.setOptimistic(0);//版本号
		user.setCreateTime(new Date());//创建时间
		user.setUserName(registerParamBean.getPhoneNo());//
		user.setParentUserNo(registerParamBean.getParentUserNo());
		user.setPassword(DigestUtil.md5(registerParamBean.getPassword()));
		user.setPasswdEffectTime(new Date());
		user.setPasswdModifyTime(new Date());
		user.setAllowBeginTime("00:00:00");
		user.setAllowEndTime("23:59:59");
		user.setMaxErrorTimes(0);
		user.setSource(registerParamBean.getSource());
		//生成用户编号
		long nextLongValue = incrementerService.nextLongValue(IncrementerConstant.SEQ_USER_NO);
	    String userNo = SequenceUtils.createSequence(nextLongValue, new int[] { 8, 5, 1, 1, 9, 3, 8, 0, 5, 2, 1,5,2},
	        new int[] { 4, 5 }, new int[] { 6, 8 }, new int[] { 7, 8 });
	    user.setUserNo(userNo);
	    user.setStatus(UserStatusConstants.INIT);
	    user.setAbleStatus(UserStatusConstants.TRUE);
		userMapper.insert(user);
		//角色信息默认初始化角色为1
		User userDb = userMapper.findUserByUserNo(userNo);
		userMapper.insertUserRoleLink(userDb.getId().toString(),"1");
		LoginParamBean loginParamBean = new LoginParamBean();
		loginParamBean.setDeviceType(registerParamBean.getDeviceType());
		loginParamBean.setOsType(registerParamBean.getOsType());
		loginParamBean.setVersion(registerParamBean.getVersion());
		loginParamBean.setUserName(registerParamBean.getPhoneNo());
		loginParamBean.setPassword(registerParamBean.getPassword());
		ReturnBean<LoginResultBean> returnBean1 = login(loginParamBean);
		if(returnBean1.getCode().equals(RetCodeConstants.SUCCESS)){
			returnBean.setData(returnBean1.getData().getLoginKey());
		}
		//TODO 用户开户
		OpenV open = new OpenV();
        open.setUserNo(userNo);
        open.setUserRole(com.pay.account.api.enums.UserRole.RECOMMENDER);
        open.setSystemCode("NATIONAL_AGENT");
        open.setSystemFlowId("N"+userNo);
        open.setBussinessCode("OPEN_ACCOUNT");
        open.setRequestTime(new Date());
        open.setOperator(userNo);
        open.setRemark("开户");
		settleCardService.openAccount(open);
		
		return returnBean;
		
	}

	*//**
	 * 登录校验
	 *//*
	@Override
	public ReturnBean<LoginResultBean> checkLoginInfo(LoginParamBean loginParamBean) {
		ReturnBean<LoginResultBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		//校验参数是否正确
		if(null == loginParamBean){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("参数不正确！");
			return returnBean;
		}
		if(loginParamBean.getUserName() == null ){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("用户名不能为空！");
			return returnBean;
		}
		if(loginParamBean.getPassword() == null ){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("密码不能为空！");
			return returnBean;
		}
		//查询用户信息
		User user = findUserByUserName(loginParamBean.getUserName());
		if(null == user){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("用户不存在！");
			return returnBean;
		}else{
			//校验用户状态是否正确
			if(!UserStatusConstants.TRUE.equals(user.getAbleStatus())){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("用户状态不正确！");
				return returnBean;
			}
			//校验密码是否正确
			if(!user.getPassword().equalsIgnoreCase(DigestUtil.md5(loginParamBean.getPassword()))){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("密码不正确！");
				return returnBean;
			}
			
		}
		return returnBean;
	}

	*//**
	 * 用户登录
	 * 登录，记录登录日志
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<LoginResultBean> login(LoginParamBean loginParamBean) {
		ReturnBean<LoginResultBean> returnBean= new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		LoginResultBean loginResultBean = new LoginResultBean();
		returnBean = checkLoginInfo(loginParamBean);
		if(returnBean.getCode().equals(RetCodeConstants.SUCCESS)){
			//用户登录
			User user = findUserByUserName(loginParamBean.getUserName());
			
			//判断用户状态是否可用
			if(!UserStatusConstants.TRUE.equals(user.getAbleStatus())){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("用户状态不正确！");
				return returnBean;
			};
			
			String createTime = String.valueOf(System.currentTimeMillis());
			//生成loginKey
			String loginKey = DigestUtil.md5(createTime+user.getUserNo());
			LogUtil.info("用户登录唯一标示:loginKey:{}",loginKey);
			//默认设置过期时间为一周
			Integer expirationTime = property.getInteger("login.info.expiration.time");
			//将用户信息放入缓存
			CacheUtils.setEx(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, user,expirationTime);
			//创建用户登录日志
			UserLoginLog userLoginLog = new UserLoginLog();
			userLoginLog.setCoordinate(loginParamBean.getCoordinate());//经纬度
			userLoginLog.setCreateTime(new Date());//创建时间
			userLoginLog.setDeviceNo(loginParamBean.getDeviceNo());//设备号
			userLoginLog.setDeviceType(loginParamBean.getDeviceType());//设备类型
			userLoginLog.setLoginKey(loginKey);//登录标示
			userLoginLog.setOptimistic(0);//版本号
			userLoginLog.setOsType(loginParamBean.getOsType());//系统类型
			userLoginLog.setUserName(loginParamBean.getUserName());//用户名
			userLoginLog.setUserNo(user.getUserNo());//用户编号
			userLoginLog.setVersion(loginParamBean.getVersion());//版本号
			userLoginLogMapper.insert(userLoginLog);
			loginResultBean.setLoginKey(loginKey);
			returnBean.setData(loginResultBean);
		}else{
			LogUtil.error("用户登录校验异常 loginParamBean：{} result:{}",loginParamBean,returnBean);
		}
		return returnBean;
	}

	*//**
	 * 退出登录方法
	 *//*
	@Override
	public boolean logout(String loginKey) {
		try {
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
			pushMsgService.unbunding(user.getUserNo());
		} catch (Exception e) {
			LogUtil.error("logout 解绑消息推送平台loginKey:{}, error:{}",loginKey,e);
		}
		Long del = RedisUtils.del(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey);
		
		LogUtil.info("退出登录 删除记录数 count:{}",del);
		if(null != del && 0l != del ){
			return true;
		}
		return false;
	}

	*//**
	 * 用户修改密码
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> updatePassword(UpdatePasswordParamBean updatePasswordParamBean) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		//校验参数合法性
		if(updatePasswordParamBean == null || StringUtils.isBlank(updatePasswordParamBean.getLoginKey())
				||StringUtils.isBlank(updatePasswordParamBean.getPassword())
				||StringUtils.isBlank(updatePasswordParamBean.getNewPassword())){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("参数不符合规范！");
			return returnBean;
		}
		//缓存中获取用户信息
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+updatePasswordParamBean.getLoginKey(), User.class);
		if(null == user){//若缓存中没有用户信息，说明登录失效
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("未登录或登录已超时！");
			return returnBean;
		}else{
			User userDb = findUserByUserNo(user.getUserNo()); 
			//校验原密码是否输入正确
			if(!userDb.getPassword().equalsIgnoreCase(DigestUtil.md5(updatePasswordParamBean.getPassword()))){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("原密码输入不正确！");
				return returnBean;
			}else{//校验通过,修改密码
				userDb.setPassword(DigestUtil.md5(updatePasswordParamBean.getNewPassword()));
				userDb.setLastUpdateTime(new Date());
				userMapper.updateByPrimaryKey(userDb);
			}
		}
		return returnBean;
	}

	*//**
	 * 找回密码
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> findPassword(Map<String, String> params) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		String phoneNo = params.get("phoneNo");
		String code = params.get("code");
		//参数校验
		if(params == null || StringUtils.isBlank(phoneNo)||
				StringUtils.isBlank(code)){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("参数不正确！");
			return returnBean;
		}
		//校验验证码是否正确
		String checkCode = RedisUtils.get(RedisKeys.FIND_PASSWORD_CHECK_CODE_PREFIX+phoneNo);
		if(StringUtils.isBlank(checkCode)){//验证码为空
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("请先获取验证码！");
			return returnBean;
		}
		if(!code.equals(checkCode)){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("验证码不正确！");
			return returnBean;
		}
		//获取用户信息
		User user  = findUserByUserName(phoneNo);
		if(user == null ){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("该手机号未注册！");
			return returnBean;
		}
		return returnBean;
	}

	@Override
	public ReturnBean<Object> resetPassword(Map<String, String> params) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		String phoneNo = params.get("phoneNo");
		String password = params.get("password");
		//参数校验
		if(params == null || StringUtils.isBlank(phoneNo)||
				StringUtils.isBlank(password)){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("参数不正确！");
			return returnBean;
		}
		//获取用户信息
		User user  = findUserByUserName(phoneNo);
		if(user == null ){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("该手机号未注册！");
			return returnBean;
		}else{
			user.setPassword(DigestUtil.md5(password));
			user.setLastUpdateTime(new Date());
			userMapper.updateByPrimaryKey(user);
		}
		return returnBean;
	}
	*//**
	 * 实名认证
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> realName(RealNameParamBean realNameParamBean) {
		ReturnBean<Object> returnBean = new ReturnBean<Object>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+realNameParamBean.getLoginKey(), User.class);
		if(null != user){//若loginKey有效
			//三要素校验
			String result = settleCardService.realNameAuth(user.getUserNo(), realNameParamBean.getBankAccountNo(), realNameParamBean.getIdentityNo()
					, realNameParamBean.getBankAccountName(), null);
			if(!result.equals(Constants.SUCCESS)){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg(result);
				return returnBean;
			}
			//保存结算信息
			SettleCard settleCard = new SettleCard();
			settleCard.setAlliedBankCode(realNameParamBean.getAlliedBankCode());
			settleCard.setBankAccountName(realNameParamBean.getBankAccountName());
			settleCard.setBankAccountNo(realNameParamBean.getBankAccountNo());
			settleCard.setBankCode(realNameParamBean.getBankCode());
			settleCard.setBankName(realNameParamBean.getBankName());
			settleCard.setCity(realNameParamBean.getCity());
			settleCard.setCreateTime(new Date());
			settleCard.setLastUpdateTime(new Date());
			settleCard.setOptimistic(0);
			settleCard.setSettleAccountType("INDIVIDUAL");
			settleCard.setOwnerId(user.getUserNo());
			settleCard.setProvince(realNameParamBean.getProvince());
			settleCard.setSabkCode(realNameParamBean.getSabkCode());
			settleCard.setStatus("ENABLE");
			settleCardService.insert(settleCard);
			//创建结算规则及结算卡
			SettleAccountInfoBean settleAccountInfoBean = new SettleAccountInfoBean();
			BeanUtils.copyProperties(settleCard, settleAccountInfoBean);
			settleAccountInfoBean.setOpenBankName(settleCard.getBankName());
			settleAccountInfoBean.setOwnerRole(Constants.NATIONALAGENT);
			settleCardService.createSettleAndRule(settleAccountInfoBean);
			//修改用户信息
			User userDb = findUserByUserNo(user.getUserNo());
			userDb.setRealname(realNameParamBean.getBankAccountName());
			userDb.setIdentityNo(realNameParamBean.getIdentityNo());
			userMapper.updateByPrimaryKey(userDb);
		}else{//loginKey失效
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("实名认证失败");
		}
		return returnBean;
	}

	*//**
	 * 查找个人资料
	 *//*
	@Override
	public ReturnBean<FindInformationResultBean> findInformation(Map<String, String> params) {
		ReturnBean<FindInformationResultBean> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		FindInformationResultBean findInformationResultBean = new FindInformationResultBean();
		SettleCardBean scb = new SettleCardBean();
		String loginKey = params.get("loginKey");
		if(StringUtils.isBlank(loginKey)){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("参数不正确！");
			return returnBean;
		}
		User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+loginKey, User.class);
		if(user == null){
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("登录已失效！");
			return returnBean;
		}else{
			//调用结算查询用户结算卡信息
			List<SettleCardBean> list = settleQueryInterface.querySettleCardByOwner(new OwnerParam(user.getUserNo(),Constants.NATIONALAGENT)
					,new SettleOrgParam("PAY",null));
			//判断用户是否有可用的结算卡
			if(list != null && list.size()>0){//存在多张直接取第一张
				scb = list.get(0);
			}
			findInformationResultBean.setIdentityNo(user.getIdentityNo());
			findInformationResultBean.setName(user.getRealname());
			findInformationResultBean.setSettleCardBean(scb);
			returnBean.setData(findInformationResultBean);
			
		}
		return returnBean;
	}

	*//**
	 * 编辑个人资料
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ReturnBean<Object> editInformation(EditInformationParamBean editInformationParamBean) {
		ReturnBean<Object> returnBean = new ReturnBean<>(RetCodeConstants.SUCCESS,RetCodeConstants.SUCCESS_DESC);
		List<SettleCardBean> list = null;
		SettleCardBean settleCardBean = new SettleCardBean();
		SettleCardParam settleCardParam = new SettleCardParam();
		try {
			//查询是否有结算卡
			User user = CacheUtils.get(RedisKeys.LOGIN_USER_INFO_PREFIX+editInformationParamBean.getLoginKey(),User.class);
			list = settleQueryInterface.querySettleCardByOwner(new OwnerParam(user.getUserNo(), Constants.NATIONALAGENT),
					new SettleOrgParam("PAY", null));
			if(list==null || list.size()==0){
				returnBean.setCode(RetCodeConstants.FAIL);
				returnBean.setMsg("未查询到结算卡信息");
				return returnBean;
			}
			settleCardBean = list.get(0);
			//修改本地结算信息
			SettleCard settleCard = settleCardService.findByownerId(user.getUserNo());
			settleCard.setBankAccountNo(editInformationParamBean.getBankAccountNo());
			settleCard.setBankName(editInformationParamBean.getBankName());
			settleCard.setProvince(editInformationParamBean.getProvince());
			settleCard.setCity(editInformationParamBean.getCity());
			settleCard.setBankCode(editInformationParamBean.getBankCode());
			settleCard.setSabkCode(editInformationParamBean.getSabkCode());
			settleCard.setAlliedBankCode(editInformationParamBean.getAlliedBankCode());
			settleCardService.update(settleCard);
			//修改接口结算信息
			BeanUtils.copyProperties(settleCardBean, settleCardParam);
			settleCardParam.setBankAccountNo(editInformationParamBean.getBankAccountNo());
			settleCardParam.setBankName(editInformationParamBean.getBankName());
			settleCardParam.setProvince(editInformationParamBean.getProvince());
			settleCardParam.setCity(editInformationParamBean.getCity());
			settleCardParam.setBankCode(editInformationParamBean.getBankCode());
			settleCardParam.setSabkCode(editInformationParamBean.getSabkCode());
			settleCardParam.setAlliedBankCode(editInformationParamBean.getAlliedBankCode());
			OperParam operParam = new OperParam(user.getUserName());
			OwnerParam ownerParam = new OwnerParam(user.getUserNo(), Constants.NATIONALAGENT);
			settleManageInterface.updateSettleCard(operParam, ownerParam, settleCardParam);
		}catch(Exception e){
			LogUtil.error("编辑个人资料error settleCardBean ",settleCardBean,e);
			returnBean.setCode(RetCodeConstants.FAIL);
			returnBean.setMsg("编辑资料异常");
		}
		
		return returnBean;
	
	}

	

	
*/}
