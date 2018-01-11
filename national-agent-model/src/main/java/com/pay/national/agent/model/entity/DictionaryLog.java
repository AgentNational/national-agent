/**
 *
 */
package com.pay.national.agent.model.entity;

import java.io.Serializable;

import com.pay.national.agent.model.annotation.NoJson;


/**
 * @ClassName:  DictionaryLog
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月25日 上午10:19:35
 *
 */
public class DictionaryLog implements Serializable {


	private static final long serialVersionUID = -9048991948148392751L;

	private Long id;
	/**
	 * 版本号
	 */
	@NoJson
	private Integer optimistic;
	/**
	 * 字典表 dictionary dictionary_Type
	 */
	private String type;
	/**
	 * 字典ID
	 */
	private String dictId;
	/**
	 * 操作 insert update
	 */
	private String operate;
	/**
	 *
	 */
	private java.util.Date createTime;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "DictionaryLog [id=" + id + ", optimistic=" + optimistic
				+ ", type=" + type + ", dictId=" + dictId + ", operate="
				+ operate + ", createTime=" + createTime + "]";
	}



}
