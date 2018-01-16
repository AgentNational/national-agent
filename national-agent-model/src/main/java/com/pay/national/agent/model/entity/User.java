package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	
	private static final long serialVersionUID = -418493373106683501L;

	/**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private Integer optimistic;

    /**
     * 用户的登录的名称手机号
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 状态TRUE，可用，FALSE，禁用，DELETE删除
     *
     * @mbggenerated
     */
    private String ableStatus;
    /**
     * 状态INIT初始化SUCCESS可用FALSE禁用
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 用户的登录密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 当前错误登录次数
     *
     * @mbggenerated
     */
    private Integer maxErrorTimes;

    /**
     * 上次登录失败的时间
     *
     * @mbggenerated
     */
    private Date lastLoginErrTime;

    /**
     * 密码修改间隔(单位:天)
     *
     * @mbggenerated
     */
    private Integer modifyPasswdCycle;

    /**
     * 密码最后修改时间
     *
     * @mbggenerated
     */
    private Date passwdModifyTime;

    /**
     * 登录许可起始时间(每天的登录时间段)
     *
     * @mbggenerated
     */
    private String allowBeginTime;

    /**
     * 登录许可截止时间(每天的登录时间段)
     *
     * @mbggenerated
     */
    private String allowEndTime;

    /**
     * 操作员真实姓名
     *
     * @mbggenerated
     */
    private String realname;

    /**
     * 操作员创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 最后更新时间
     *
     * @mbggenerated
     */
    private Date lastUpdateTime;

    /**
     * 密码到期时间
     *
     * @mbggenerated
     */
    private Date passwdExpireTime;

    /**
     * 密码生效时间
     *
     * @mbggenerated
     */
    private Date passwdEffectTime;

    /**
     * 用户编号用户唯一标识
     *
     * @mbggenerated
     */
    private String userNo;

    /**
     * 用户编号用户唯一标识
     *
     * @mbggenerated
     */
    private String parentUserNo;

    /**
     * 用户手机号
     *
     * @mbggenerated
     */
    private String phoneNo;

    /**
     * 用户email
     *
     * @mbggenerated
     */
    private String email;
    
    /**
     * 用户身份证号
     */
    private String identityNo;
    
    /**
     * 注册来源
     */
    private String source;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getAbleStatus() {
		return ableStatus;
	}

	public void setAbleStatus(String ableStatus) {
		this.ableStatus = ableStatus;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxErrorTimes() {
        return maxErrorTimes;
    }

    public void setMaxErrorTimes(Integer maxErrorTimes) {
        this.maxErrorTimes = maxErrorTimes;
    }

    public Date getLastLoginErrTime() {
        return lastLoginErrTime;
    }

    public void setLastLoginErrTime(Date lastLoginErrTime) {
        this.lastLoginErrTime = lastLoginErrTime;
    }

    public Integer getModifyPasswdCycle() {
        return modifyPasswdCycle;
    }

    public void setModifyPasswdCycle(Integer modifyPasswdCycle) {
        this.modifyPasswdCycle = modifyPasswdCycle;
    }

    public Date getPasswdModifyTime() {
        return passwdModifyTime;
    }

    public void setPasswdModifyTime(Date passwdModifyTime) {
        this.passwdModifyTime = passwdModifyTime;
    }

    public String getAllowBeginTime() {
        return allowBeginTime;
    }

    public void setAllowBeginTime(String allowBeginTime) {
        this.allowBeginTime = allowBeginTime;
    }

    public String getAllowEndTime() {
        return allowEndTime;
    }

    public void setAllowEndTime(String allowEndTime) {
        this.allowEndTime = allowEndTime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getPasswdExpireTime() {
        return passwdExpireTime;
    }

    public void setPasswdExpireTime(Date passwdExpireTime) {
        this.passwdExpireTime = passwdExpireTime;
    }

    public Date getPasswdEffectTime() {
        return passwdEffectTime;
    }

    public void setPasswdEffectTime(Date passwdEffectTime) {
        this.passwdEffectTime = passwdEffectTime;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getParentUserNo() {
        return parentUserNo;
    }

    public void setParentUserNo(String parentUserNo) {
        this.parentUserNo = parentUserNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", optimistic=" + optimistic + ", userName=" + userName + ", status=" + status
				+ ", password=" + password + ", maxErrorTimes=" + maxErrorTimes + ", lastLoginErrTime="
				+ lastLoginErrTime + ", modifyPasswdCycle=" + modifyPasswdCycle + ", passwdModifyTime="
				+ passwdModifyTime + ", allowBeginTime=" + allowBeginTime + ", allowEndTime=" + allowEndTime
				+ ", realname=" + realname + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", passwdExpireTime=" + passwdExpireTime + ", passwdEffectTime=" + passwdEffectTime + ", userNo="
				+ userNo + ", parentUserNo=" + parentUserNo + ", phoneNo=" + phoneNo + ", email=" + email
				+ ", identityNo=" + identityNo + ", source=" + source + "]";
	}
}