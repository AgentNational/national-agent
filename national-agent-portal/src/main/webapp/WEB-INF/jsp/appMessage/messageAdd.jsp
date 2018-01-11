<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
});
	

function mySubmit()
{
	var title = $("#title").val().trim();
	var username = $("#username").val().trim();
	var content = $("#content").val().trim();
	if(title == '')
	{
		dialogMessage('标题不能为空');
		return false;
	}
	
	if(username == ''){
		dialogMessage('用户编号不能为空');
		return false;
	}
	if(content == '')
	{
		dialogMessage('消息内容不能为空');
		return false;
	}
	$("#form1").attr("action", "${ctx}/message/add.action");
	$("#form1").submit();
}
</script>
</head>
<body>
	<div class="search_tit"><h2>新增个人消息</h2></div>
	 <form action="" method="post" id="form1">
		<input type="hidden" id="operator" name="operator" value="${operator}">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th>消息类型：</th>
				<td>
					<select id="messageType" name="msgType">
					 	<option value="COMMON" selected="selected">普通消息</option>
					 	<option value="IMPORTANT">重要消息</option>
					</select>
				</td>
				<th>状态：</th>
				<td>
					<select id="AbleStatus" name="ableStatus">
					 	<option value="ENABLE" selected="selected">可用</option>
					 	<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>消息标题：</th>
				<td>
					<input type="text" id="title" name="title"/>
				</td>
				<th>接收用户编号：</th>
				<td>
					<input type="text" id="username" name="userName"/>
				</td>
			</tr>
			<tr>
				<th>简要内容：</th>
				<td>
					<input type="text" style="width:300px" id="briefContent" name="briefContent"/>
				</td>
				<th>是否弹屏：</th>
				<td>
					<input name="isPop" type="radio" value="true" checked="true">弹屏
					<input name="isPop" type="radio" value="false">不弹屏
				</td>
			</tr>
			<tr>
				<th>消息内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" ></textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</form>
	<br>
	<center>
		<input type="button" class="global_btn" value="保存" onclick="mySubmit()" />
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>