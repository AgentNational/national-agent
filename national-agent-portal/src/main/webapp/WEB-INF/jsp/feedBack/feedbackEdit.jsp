<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	var remark = $("#remark").val().trim();
	if(remark == '')
	{
		dialogMessage('处理意见不能为空');
		return false;
	}
	$("#form1").attr("action", "${ctx}/appFeedBack/feedbackModify.action");
	$("#form1").submit();
}
</script>
</head>
<body>
	<div class="search_tit"><h2>反馈详情</h2></div>
	 <form action="" method="post" id="form1">
	 	<input type="hidden" id="feedId" name="feedId" value="${feedback.id }"/>
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
					<select id="statusSelect">
						<option value="INIT">未处理</option>
						<option value="SUCCESS">已处理</option>
					</select>
					<input id="status" type="hidden" name="status" value="${feedback.status }">
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
			<%-- <tr>
				<th width="15%">处理人：</th>
				<td width="20%">
					${feedback.operator }
				</td>
				<th>处理时间：</th>
				<td><fmt:formatDate value="${feedback.operatorTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr> --%>
			<tr>
				<th width="15%">处理意见：</th>
				<td colspan="3">
					<textarea rows="5" id="remark" name="remark" style="width:400px" ></textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<th width="15%">处理反馈：</th>
				<td colspan="3">
					<input type="checkbox" name="type" checked="checked"  value="message"/>推送消息
					<input type="checkbox" name="type" value="sms"/>发送短信
				</td>
			</tr>
		</table>
	</form>
	<br>
	<center>
		<input type="button" class="global_btn" value="提交" onclick="mySubmit()" />
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>