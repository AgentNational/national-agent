package com.pay.national.agent.model.beans.results;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 查找个人资料结果bean
 * @see: 需要参考的类
 * @version 2017年9月12日 上午10:00:46
 * @author zhenhui.liu
 */
public class FindInformationResultBean implements Serializable{

	private static final long serialVersionUID = -4264015784776974252L;
	
	private String name;
	
	private String identityNo;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 持有者ID
	 */
	private String ownerId;
	/**
	 * 持有者角色
	 */
	private String ownerRole;
	/**
	 * 帐户类型(对公/对私)
	 */
	private String cardType;
	/**
	 * 开户名
	 */
	private String bankAccountName;
	/**
	 * 开户帐号
	 */
	private String bankAccountNo;
	/**
	 * 开户行号
	 */
	private String bankCode;
	/**
	 * 开户行名
	 */
	private String bankName;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 生效日期
	 */
	private Date effDate;
	/**
	 * 失效日期
	 */
	private Date expDate;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 联行号
	 */
	private String alliedBankCode;
	/**
	 * 清分行号
	 */
	private String sabkCode;
	/**
	 * 分行号
	 */
	private String ownBankCode;
	/**
	 * 优先级
	 */
	private Integer priority;
	/**
	 * 最后更新日期
	 */
	private Date lastUpdateDate;
	/**
	 * 账户编号
	 */
	@Deprecated
	private String accountNo;
	/**
	 * 可用额度
	 */
	private Double availablePosition;
	/**
	 * 修改原因
	 */
	private String memo;

	/**
	 * 卡bin
	 */
	private String cardBin;
	/**
	 * 卡类别(借记卡/贷记卡)
	 */
	private String cardCategory;
	/**
	 * 机构码
	 */
	@Deprecated
	private String orgId;
	/**
	 * 开户行总行
	 */
	private String headBankName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerRole() {
		return ownerRole;
	}

	public void setOwnerRole(String ownerRole) {
		this.ownerRole = ownerRole;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAlliedBankCode() {
		return alliedBankCode;
	}

	public void setAlliedBankCode(String alliedBankCode) {
		this.alliedBankCode = alliedBankCode;
	}

	public String getSabkCode() {
		return sabkCode;
	}

	public void setSabkCode(String sabkCode) {
		this.sabkCode = sabkCode;
	}

	public String getOwnBankCode() {
		return ownBankCode;
	}

	public void setOwnBankCode(String ownBankCode) {
		this.ownBankCode = ownBankCode;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAvailablePosition() {
		return availablePosition;
	}

	public void setAvailablePosition(Double availablePosition) {
		this.availablePosition = availablePosition;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(String cardCategory) {
		this.cardCategory = cardCategory;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getHeadBankName() {
		return headBankName;
	}

	public void setHeadBankName(String headBankName) {
		this.headBankName = headBankName;
	}

	@Override
	public String toString() {
		return "FindInformationResultBean [name=" + name + ", identityNo=" + identityNo + ", id=" + id + ", createDate="
				+ createDate + ", ownerId=" + ownerId + ", ownerRole=" + ownerRole + ", cardType=" + cardType
				+ ", bankAccountName=" + bankAccountName + ", bankAccountNo=" + bankAccountNo + ", bankCode=" + bankCode
				+ ", bankName=" + bankName + ", province=" + province + ", city=" + city + ", effDate=" + effDate
				+ ", expDate=" + expDate + ", status=" + status + ", alliedBankCode=" + alliedBankCode + ", sabkCode="
				+ sabkCode + ", ownBankCode=" + ownBankCode + ", priority=" + priority + ", lastUpdateDate="
				+ lastUpdateDate + ", accountNo=" + accountNo + ", availablePosition=" + availablePosition + ", memo="
				+ memo + ", cardBin=" + cardBin + ", cardCategory=" + cardCategory + ", orgId=" + orgId
				+ ", headBankName=" + headBankName + "]";
	}
}
