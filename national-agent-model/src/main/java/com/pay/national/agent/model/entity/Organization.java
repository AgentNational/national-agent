package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuyan.qi
 */
public class Organization extends BaseEntity{
    private static final long serialVersionUID = -5190767908534146874L;
    /**
     *地区编码
     */
    private String code;

    /**
     *地区名称
     */
    private String name;

    /**
     *父编码
     */
    private String parentCode;

    /**
     *是否叶子节点
     */
    private String isLeaf;

    /**
     *状态
     */
    private String status;

    /**
     *备注
     */
    private Date remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRemark() {
        return remark;
    }

    public void setRemark(Date remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return super.toString()+"Organization{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", isLeaf='" + isLeaf + '\'' +
                ", status='" + status + '\'' +
                ", remark=" + remark +
                '}';
    }
}