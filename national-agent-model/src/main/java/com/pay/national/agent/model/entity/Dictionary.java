
package com.pay.national.agent.model.entity;

import java.io.Serializable;

import com.pay.national.agent.model.annotation.NoJson;


/**
 * @ClassName:  Dictionary
 * @Description:字典项
 * @author: xiaodi.fu
 * @date:   2017年9月27日 上午10:46:19
 *
 */
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 7576693403155121534L;

	private Long id;
	/**
	 * 版本号
	 */
	@NoJson
	private Integer optimistic;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 字典项ID
	 */
	private String dictId;
	/**
	 * 字典项名称
	 */
	private String dictName;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 排序号
	 */
	private Integer displayOrder;
	/**
	 * 权限
	 */
	private String privilege;
	/**
	 * 字典类型ID
	 */
	private String dictTypeId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getDictTypeId() {
		return dictTypeId;
	}
	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", optimistic=" + optimistic
				+ ", status=" + status + ", dictId=" + dictId + ", dictName="
				+ dictName + ", remark=" + remark + ", displayOrder="
				+ displayOrder + ", privilege=" + privilege + ", dictTypeId="
				+ dictTypeId + "]";
	}


}
