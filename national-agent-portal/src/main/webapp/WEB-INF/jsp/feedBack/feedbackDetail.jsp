<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.pay.national.agent.model.entity.AppFeedback" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
</head>
<body>
	<div class="search_tit"><h2>反馈详情</h2></div>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">用户手机号：</th>
				<td width="25%">
					${feedback.phone }
				</td>
				<th width="20%">状态：</th>
				<%-- <td width="25%">
					<dict:write dictTypeCode="FeedbackStatus" dictKey="${feedback.status }"></dict:write>
				</td> --%>
				<td>
				<c:if test="${feedback.status == 'INIT' }">未处理</c:if>
				<c:if test="${feedback.status == 'SUCCESS' }">已处理</c:if>
			</td>
			</tr>
			<tr>
				<th width="15%">反馈内容：</th>
				<td width="20%">
					${feedback.content }
				</td>
				<th>创建时间：</th>
				<td><fmt:formatDate value="${feedback.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<th width="15%">处理人：</th>
				<td width="20%">
					${feedback.operator }
				</td>
				<th>处理时间：</th>
				<td><fmt:formatDate value="${feedback.operatorTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<th width="15%">处理意见：</th>
				<td colspan="3">
					${feedback.remark }
				</td>
			</tr>
			<tr>
				<th width="15%">处理反馈：</th>
				<td colspan="3">
					<%String  returnType = ((AppFeedback)request.getAttribute("feedback")).getReturnType() ;%>
					<input disabled ="disabled" type="checkbox" <%if(null != returnType){if(returnType.indexOf("message") != -1 ){ out.print(" checked='checked' ");	}} %>name="type" value="message"/>推送消息
					<input disabled ="disabled" type="checkbox" <%if(null != returnType){if(returnType.indexOf("sms") != -1 ){ out.print(" checked='checked' ");	}} %> name="type" value="sms"/>发送短信
				</td>
			</tr>
		</table>
	<br>
	<center>
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>