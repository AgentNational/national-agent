package com.pay.national.agent.model.entity;

import java.io.Serializable;

public class AppMenu implements Serializable{
	private static final long serialVersionUID = 6823328425810841585L;

	/**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 父菜单ID
     *
     * @mbggenerated
     */
    private Long parentMenuId;

    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * 菜单级别
     *
     * @mbggenerated
     */
    private String menuLevel;

    /**
     * 跳转页面(NATIVE:原生,H5)
     *
     * @mbggenerated
     */
    private String jumpType;

    /**
     * 菜单图片URL
     *
     * @mbggenerated
     */
    private String imgUrl;

    /**
     * 菜单h5触发URl
     *
     * @mbggenerated
     */
    private String h5Url;

    /**
     * 菜单安卓触发URl
     *
     * @mbggenerated
     */
    private String androidUrl;

    /**
     * 菜单IOS触发URl
     *
     * @mbggenerated
     */
    private String iosUrl;

    /**
     * 菜单排序
     *
     * @mbggenerated
     */
    private Integer menuOrder;

    /**
     * 菜单描述
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 可用状态
     *
     * @mbggenerated
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "AppMenu [id=" + id + ", parentMenuId=" + parentMenuId + ", menuName=" + menuName + ", menuLevel="
				+ menuLevel + ", jumpType=" + jumpType + ", imgUrl=" + imgUrl + ", h5Url=" + h5Url + ", androidUrl="
				+ androidUrl + ", iosUrl=" + iosUrl + ", menuOrder=" + menuOrder + ", remark=" + remark + ", status="
				+ status + "]";
	}
}