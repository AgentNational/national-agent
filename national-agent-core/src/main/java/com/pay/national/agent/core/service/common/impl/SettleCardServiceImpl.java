package com.pay.national.agent.core.service.common.impl;

import org.springframework.stereotype.Service;

import com.pay.national.agent.core.service.common.SettleCardService;
import com.pay.national.agent.model.beans.query.SettleAccountInfoBean;
import com.pay.national.agent.model.entity.SettleCard;

@Service("settleCardService")
public class SettleCardServiceImpl implements SettleCardService{

	@Override
	public String realNameAuth(String recommenderNo, String bankAccountNo, String certNo, String name,
			String mobileNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSettleAndRule(SettleAccountInfoBean settleAccountInfoParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(SettleCard settleCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SettleCard findByownerId(String userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SettleCard settleCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openAccount(Object open) {
		// TODO Auto-generated method stub
		
	}
	
/*
	private static final Logger logger = LoggerFactory.getLogger(SettleCardServiceImpl.class);
	@Resource
	private SettleCardInterface settleCardInterface;
	
	*//**结算周期dubbo服务*//*
	@Resource
	private CycleInterface cycleInterface;
	
	*//**结算规则查询dubbo接口*//*
	@Resource
	private SettleQueryInterface settleQueryInterface;
	
	@Resource
	private SettleCardMapper settleCardMapper;
	
	*//**结算规则更新dubbo接口*//*
	@Resource
	private SettleManageInterface settleManageInterface;
	
	@Resource
	private AccountServiceInterfaceV accountServiceInterfaceV;
	
	@Resource
	private OpenAccountFailMapper openAccountFailMapper;
	*//**
	 * 合伙人实名认证
	 * @param recommenderNo 合伙人编号
	 * @param bankAccountNo 银行卡号
	 * @param certNo 身份证号
	 * @param name  法人姓名
	 * @param mobileNum  手机号
	 *//*
	@Override
	public String realNameAuth(String recommenderNo, String bankAccountNo, String certNo, String name,
			String mobileNum) {
		logger.info("method realNameAuth校验结算卡三要素入参recommenderNo："+recommenderNo+"bankAccountNo:"+bankAccountNo+"certNo:"+certNo+"name:"+name);
		if(StringUtils.isBlank(recommenderNo)){
			logger.info("method realNameAuth校验结算卡三要素入参token为空："+recommenderNo+"bankAccountNo:"+bankAccountNo+"certNo:"+certNo+"name:"+name);
			return "参数不正确";
		}
		String infoJson = settleCardInterface.realNameAuth(recommenderNo, bankAccountNo, certNo, name, null);
		String result = "";
		logger.info("method realNameAuth 校验结算卡三要素结果 infoJson：{}",infoJson);
		if(StringUtils.notBlank(infoJson)){
			JSONObject resultJson = JSONObject.fromObject(infoJson);
			if(resultJson.containsKey("resultCode") && "0001".equals(resultJson.getString("resultCode"))){
				//返回成功
				result=Constants.SUCCESS;
				//logger.info("method realNameAuth 校验结算卡三要素成功 data = {}，result = {}",recommenderNo,resultJson.getString("resultCode")+"："+resultJson.getString("resultMessage"));
			}else if(resultJson.containsKey("resultCode") && "2009".equals(resultJson.getString("resultCode"))){
				//返回暂不支持该银行卡，不能影响用户实名认证流程，直接返回成功
				result=Constants.SUCCESS;
				logger.info("method realNameAuth 校验结算卡三要素异常 data = {}，result = {}",recommenderNo,resultJson.getString("resultCode")+"："+resultJson.getString("resultMessage"));
			}else {
				result="您提交的身份证、结算卡、实名信息不符，请核对后重新提交!";
				logger.info("method realNameAuth 校验结算卡三要素异常 data = {}，result = {}",recommenderNo,resultJson.getString("resultCode")+"："+resultJson.getString("resultMessage"));
			}
		}else{
			//返回失败
			logger.info("method checkSettleInfoSubmit 校验结算卡三要素失败返回结果为空 data = {}",recommenderNo );
			result = "校验结算卡信息异常!";
		}
		return result;
	}
	
	*//**
	 * 创建结算规则与结算卡
	 *//*
	@Override
	public void createSettleAndRule(SettleAccountInfoBean settleAccountInfoParam) {
		logger.info("method createSettleAndRule start data:{}",JsonUtils.toJsonString(settleAccountInfoParam));
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// settle接口参数
		String userNo = settleAccountInfoParam.getOwnerId();//合伙人编号
		if(StringUtils.isBlank(userNo)){
		}
		String settleAccountType = settleAccountInfoParam.getSettleAccountType();//结算卡类型
		//操作员信息
		OperParam operParam = new OperParam("National.system");
		//所有者信息
		OwnerParam ownerParam = new OwnerParam();
		ownerParam.setOwnerId(userNo);
		ownerParam.setOwnerRole(UserRole.RECOMMENDER.name());

		SettleCardParam settleCardParam = new SettleCardParam();
		settleCardParam.setCardType(settleAccountInfoParam.getSettleAccountType());
		settleCardParam.setBankAccountName(settleAccountInfoParam.getBankAccountName());
		settleCardParam.setBankAccountNo(settleAccountInfoParam.getBankAccountNo());
		settleCardParam.setBankCode(settleAccountInfoParam.getBankCode());
		settleCardParam.setBankName(settleAccountInfoParam.getOpenBankName());
		settleCardParam.setProvince(settleAccountInfoParam.getProvince());
		settleCardParam.setCity(settleAccountInfoParam.getCity());
		settleCardParam.setEffDate(settleAccountInfoParam.getEffTime() == null ? new Date() : settleAccountInfoParam.getEffTime());
		// 银联号
		settleCardParam.setAlliedBankCode(settleAccountInfoParam.getAlliedBankCode());
		// 清分行号
		settleCardParam.setSabkCode(settleAccountInfoParam.getSabkCode());
		settleCardParam.setPriority(0);
		settleCardParam.setStatus("ENABLE");
		try {
			// 结算规则
			// 创建结算周期之前进行查询，检查是否已存在
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("userNo", userNo);
			queryParams.put("userRole", UserRole.RECOMMENDER.name());
			queryParams.put("productNo", UserRole.RECOMMENDER.name());
			queryParams.put("cycle", 1);
			List<CycleBean> cycleBeans = cycleInterface.queryBy(queryParams);
			// 若不存在，则建立
			if (cycleBeans == null || cycleBeans.isEmpty()) {
				try {
					CycleBean cycleBean = new CycleBean();
					cycleBean.setCycle(1);
					cycleBean.setProductNo(UserRole.RECOMMENDER.name());
					cycleBean.setUserNo(userNo);
					cycleBean.setUserRole(com.pay.cycle.api.enums.UserRole.RECOMMENDER);
					// 创建付款周期
					cycleInterface.create(cycleBean);
				} catch (Exception e) {
					logger.error("【" + userNo + "代理创建入账周期失败】",e);
					logger.error(e.getMessage());
					throw new NationalAgentException("4001","新建入账周期错误！");
				}
				logger.info("创建入账周期完毕，userNo=" + userNo + ",userRole=" + UserRole.RECOMMENDER.toString()
						+ ",productNo=RECOMMENDER" + ",cycle=1");
			}
			CustomerSettleRuleBean result = settleQueryInterface.queryCustomerSettleRule(new OwnerParam(userNo,UserRole.RECOMMENDER.name()),
					new SettleOrgParam("PAY",null));
			String csrId = "";
			if (null == result ) {

				CustomerSettleRuleParam customerSettleRuleParam = new CustomerSettleRuleParam();
				customerSettleRuleParam.setEffDate(new Date());
				try {
					customerSettleRuleParam.setExpDate(fmt.parse("2099-6-19 18:00:00"));
				} catch (Exception e) {
				}
				// 对私优先,对公出款的成功要求次数
				customerSettleRuleParam.setExchange(1);
				// 起结金额
				customerSettleRuleParam.setStartSettleAmount(100.0);
				if (null != settleAccountInfoParam.getSettleAccountType()) {
					// 结算卡对公，规则只对公
					if ("OPEN".equals(settleAccountType)) {
						customerSettleRuleParam.setSelectType("ONLY_OPEN");
						// 对私结算限额设置为300000，对公结算限额不设置（不限）
						customerSettleRuleParam.setIndividulLimit(0.0);
					} else if ("INDIVIDUAL".equals(settleAccountType)) {
						// 结算卡对私，规则只对私
						customerSettleRuleParam.setSelectType("ONLY_INDIVIDUAL");
						customerSettleRuleParam.setIndividulLimit(300000.0);
					}
				}
				customerSettleRuleParam.setStatus("ENABLE");
				customerSettleRuleParam.setSettleProduct("RECMDR_COMM");
				csrId = settleManageInterface.createCustomerSettleRule(operParam, ownerParam, customerSettleRuleParam, null);
			} else {
				csrId = result.getId();
			}
		} catch (Exception e) {
			logger.error("【" + userNo + "代理创建结算规则失败】",e);
			logger.info(e.getMessage());
			throw new NationalAgentException("4002","新建结算规则错误！");
		}


		try {
			// 失效日期
			settleCardParam.setExpDate(fmt.parse("2099-6-19 18:00:00"));
			//结算卡查询
			List<SettleCardBean> settleCards = settleQueryInterface.querySettleCardByOwner(new OwnerParam(userNo,UserRole.RECOMMENDER.toString()),
					new SettleOrgParam("PAY",null));
			if (null != settleCards && !settleCards.isEmpty()) {
				for (SettleCardBean scb : settleCards) {
					if (!settleCardParam.getBankAccountNo().equals(scb.getBankAccountNo())) {
						// 调用接口，创建结算卡
						settleManageInterface.createSettleCard(operParam, ownerParam, settleCardParam);
					}
				}
			} else {
				// 调用接口，创建结算卡
				 settleManageInterface.createSettleCard(operParam, ownerParam, settleCardParam);
			}
			logger.info("【" + userNo + "成功创建代理结算卡信息】");
		} catch (Exception e) {
			logger.error("【" + userNo + "代理创建结算卡信息失败】",e);
			logger.info(e.getMessage());
			throw new NationalAgentException("4003","创建结算卡错误！");
		}
	}

	@Override
	public void insert(SettleCard settleCard) {
		settleCardMapper.insert(settleCard);
	}

	@Override
	public SettleCard findByownerId(String userNo) {
		return settleCardMapper.findByownerId(userNo);
	}

	*//**
	 * 修改结算信息
	 *//*
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void update(SettleCard settleCard) {
		settleCardMapper.updateByPrimaryKey(settleCard);
	}

	*//**
	 * 开户
	 *//*
	@Override
	public void openAccount(OpenV open) {
		//开户异常不往外抛，直接记录到表里。
		AccountOpenResponse accountResponseBean = null;
        //调用创建账户服务
        try {
        	accountResponseBean = accountServiceInterfaceV.open(open);
        	if(accountResponseBean.getResult().equals(HandlerResult.SUCCESS)){
        		LogUtil.info("创建账户成功 userNo:{}",open.getUserNo());
        	}else if(accountResponseBean.getResult().equals(HandlerResult.SUCCESS_01)){
        		LogUtil.info("创建账户成功账户已存在 userNo:{}",open.getUserNo());
        	}
		} catch (Exception e) {
			//将异常信息记录到表中
			OpenAccountFail openAccountFail =new OpenAccountFail();
			openAccountFail.setUserNo(open.getUserNo());//用户编号
			openAccountFail.setCreateTime(new Date());//创建时间
			openAccountFail.setStatus("FAIL");
			//插入数据
			openAccountFailMapper.insert(openAccountFail);
			LogUtil.info("创建账户成功异常 userNo:{}",open.getUserNo(),e);
			
		}
		
	}
	*/
}
