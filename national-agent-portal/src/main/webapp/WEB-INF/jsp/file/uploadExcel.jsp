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
	<form action="importEmployee.action" method="post"  encType="multipart/form-data" >
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="10%">文件：</th>
				<td width="15%">
					<input class="inp" type="file" name="file" class="inp width150"/>
				</td>
			</tr>
		</table>
		<br>
		<center>
			<input type="submit"  class="global_btn" value="提交" />
			<input type="reset" class="global_btn" value="重 置" />
		</center>
	</form>
	<a href="downloadEmployeeModel.action">下载模板</a>
	<br>
	<a href="downloadEmployee.action">下载文件</a>
	<br>
</body>
</html>