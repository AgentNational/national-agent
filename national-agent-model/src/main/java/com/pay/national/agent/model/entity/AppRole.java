package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.List;

public class AppRole implements Serializable{
	private static final long serialVersionUID = -2834060982751556146L;

	/**
     * 角色id
     *
     * @mbggenerated
     */
    private Integer id;
    
    /**
     * 角色标示
     *
     * @mbggenerated
     */
    private String roleType;

    /**
     * 角色名称
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * 角色描述
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * app菜单集合
     */
    private List<AppMenu> appMenus;
    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<AppMenu> getAppMenus() {
		return appMenus;
	}

	public void setAppMenus(List<AppMenu> appMenus) {
		this.appMenus = appMenus;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", roleType=" + roleType + ", roleName=" + roleName + ", remark=" + remark
				+ ", appMenus=" + appMenus + ", status=" + status + "]";
	}
}