
package com.pay.national.agent.model.entity;

import java.io.Serializable;

import com.pay.national.agent.model.annotation.NoJson;


/**
 * @ClassName:  DictionaryType
 * @Description:字典类型
 * @author: xiaodi.fu
 * @date:   2017年9月27日 上午10:49:13
 *
 */
public class DictionaryType implements Serializable {

	private static final long serialVersionUID = 5404612983433239234L;

	/**
	 * 版本号
	 */
	@NoJson
	private Integer optimistic;
	/**
	 * 字典项ID
	 */
	private String dictTypeId;
	/**
	 * 字典项名称
	 */
	private String dictTypeName;
	/**
	 * 备注说明
	 */
	private String remark;
	public Integer getOptimistic() {
		return optimistic;
	}
	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}
	public String getDictTypeId() {
		return dictTypeId;
	}
	public void setDictTypeId(String dictTypeId) {
		this.dictTypeId = dictTypeId;
	}
	public String getDictTypeName() {
		return dictTypeName;
	}
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "DictionaryType [optimistic=" + optimistic + ", dictTypeId="
				+ dictTypeId + ", dictTypeName=" + dictTypeName + ", remark="
				+ remark + "]";
	}



}
