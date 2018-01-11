<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
// 	 		$("#form1").submit();
		});
	</script>
</head>
<body>
	<div class="search_tit"><h2>反馈意见管理</h2></div>
	<form action="feedbackQuery.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="10%">用户手机号：</th>
				<td width="15%">
					<input class="inp" type="text" name="phone" class="inp width150"/>
				</td>
				<th width="10%">处理状态：</th>
				<td width="15%">
					<dict:select id="status" name="status" style="width: 150px;"
						dictTypeCode="FeedbackStatus" nullOption="true"></dict:select>
				</td>
			</tr>
			<tr>
				<th width="10%">系统类型：</th>
				<td width="15%">
					<dict:select id="appClientType" name="appClientType" style="width: 150px;"
						dictTypeCode="AppClientType" nullOption="true"></dict:select>
				</td> 
				<th>反馈时间：</th>
				<td>
					<input type="text" name="createTimeStart" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate width115" /> -
					<input type="text" name="createTimeEnd" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate width115" />
				</td>
			</tr>
		</table>
		<br>
		<center>
			<input type="submit"  class="global_btn" value="查 询" />
			<input type="reset" class="global_btn" value="重 置" />
		</center>
	</form>
	<br>
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>