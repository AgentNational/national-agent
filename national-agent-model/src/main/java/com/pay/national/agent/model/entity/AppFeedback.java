/**
 *
 */
package com.pay.national.agent.model.entity;

import java.util.Arrays;
import java.util.Date;

import com.pay.national.agent.model.annotation.NoJson;


/**
 * @ClassName:  AppFeedback
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午1:59:09
 *
 */
public class AppFeedback {
	@NoJson
	private static final long serialVersionUID = 1L;

	private String id;//主键
	private String userId;//用户id，即发回馈和回应反馈的人id
	private String phone;//发送反馈所使用的手机
	private String status;//馈消息处理状态
	private String type;//反馈消息类型，意见、回复？
	private String content;// 反馈的具体内容
	private String isRead;//是否已读。
	private String operator;//反馈处理人Id。
	private Date createTime;//反馈发送时间。
	private Date operatorTime;//反馈意见处理时间。
	private String remark; // 处理意见。
	private String returnType;//处理意见推送方式。
	private String clientType;//客户端类型:Android Ios
	private String [] returnTypes;//处理意见数组。

	/**
	 * @Description 将字符串数组转换为以,号分隔每个元素的字符串
	 * @param array
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String arrayToString(String [] array){
		if(array != null && array.length > 0){
			StringBuilder s = new StringBuilder();
			for(String str: array){
				if(null != returnType){
					s.append(",").append(str);
				}else{
					s = s.append(str);
				}
			}
			return s.toString();
		}
		return null;
	}

	/**
	 * @Description 将以逗号分隔的字符串转换为数组。
	 * @param s
	 * @return
	 * @see 需要参考的类或方法
	 */
	public String[] stringToArray(String s){
		String [] strs= null ;
		if(null != s && s.length()>0){
			strs = s.split(",");
		}
		return strs;
	}


	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String[] getReturnTypes() {
		return returnTypes;
	}
	public void setReturnTypes(String[] returnTypes) {
		this.returnTypes = returnTypes;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppFeedback [id=" + id + ", userId=" + userId + ", phone=" + phone + ", status=" + status + ", type="
				+ type + ", content=" + content + ", isRead=" + isRead + ", operator=" + operator + ", createTime="
				+ createTime + ", operatorTime=" + operatorTime + ", remark=" + remark + ", returnType=" + returnType
				+ ", clientType=" + clientType + ", returnTypes=" + Arrays.toString(returnTypes) + "]";
	}

}
