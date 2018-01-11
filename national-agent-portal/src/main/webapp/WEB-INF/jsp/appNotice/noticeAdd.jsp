<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	var title = $("#title").val().trim();
	var beginTime = $("#beginTime").val().trim();
	var endTime = $("#endTime").val().trim();
	var content = $("#content").val().trim();
	var briefContent = $("#briefContent").val().trim();
	if(title == '')
	{
		dialogMessage('标题不能为空');
		return false;
	}
	if(beginTime == '')
	{
		dialogMessage('开始时间不能为空');
		return false;
	}
	if(endTime == '')
	{
		dialogMessage('结束时间不能为空');
		return false;
	}
	if(content == '')
	{
		dialogMessage('公告内容不能为空');
		return false;
	}
	if(briefContent == '')
	{
		dialogMessage('公告简要不能为空');
		return false;
	}
	$("#form1").attr("action", "${ctx}/appNotice/appNoticeAdd.action");
	$("#form1").submit();
}
</script>
</head>
<body>
	<div class="search_tit"><h2>新增公告</h2></div>
	 <form action="" method="post" id="form1">
	 	<input type="hidden"  name="operator" value="${operator}">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">公告标题：</th>
				<td width="25%">
					<input type="text" id="title" name="title"/>
				</td>
				<th width="20%">公告类型：</th>
				<td width="25%">
					<select name="noticeType">
						<option value="COMMON" selected="selected">普通公告</option>
						<option value="IMPORTANT">重要公告</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">有效时间：</th>
				<td width="25%" >
					<input id="beginTime" type="text" name="beginTime" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate width80" /> -
					<input id="endTime" type="text" name="endTime" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate width80" />
				</td>
				<th width="20%">状态：</th>
				<td>
					<select name="usableStatus">
						<option value="ENABLE" selected="selected">可用</option>
						<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否弹屏：</th>
				<td>
					<input name="isPop" type="radio" value="true" checked="true">弹屏
					<input name="isPop" type="radio" value="false">不弹屏
				</td>
				<th>用户群体:</th>
				<td>
					<select name="userGroup">
						<option value="NATIONAL_AGENT" selected="selected">普通代理人</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>公告简称:</th>
				<td>
					<input id="briefContent" name="briefContent" type="text" style="width:300px" />
				</td>
			</tr>
			<tr>
				<th width="15%">公告内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" ></textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
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