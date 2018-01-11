/**
 *
 */
package com.pay.national.agent.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 单点登录用户信息
 * @see: 需要参考的类
 * @version 2017年9月4日 下午5:26:29
 * @author zhenhui.liu
 */
public class Authorization implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 174248001542598672L;

	private String userName; // 用户登录名

	private String realName; // 真实姓名

	private String staffNo; // 工号

	private String orgCode; // 组织机构编码
	
	private List<String> roles;	//角色名称

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Authorization [userName=" + userName + ", realName=" + realName
				+ ", staffNo=" + staffNo + ", orgCode=" + orgCode + ", roles="
				+ roles + "]";
	}


}
