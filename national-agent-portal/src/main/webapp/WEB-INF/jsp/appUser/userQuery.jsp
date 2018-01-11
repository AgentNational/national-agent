<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
		});

	 	// 用户新增
		function toAdd() {
			window.location.href='${ctx}/appUser/toAppUserAdd.action';
		}
	</script>

</head>
<body>
	<div class="search_tit"><h2>app用户管理</h2></div>
	<form action="appUserQuery.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
		
			<tr>
				<th width="25%" align="right">用户手机号：</th>
				<td width="25%" align="left">
					<input class="inp" type="text" name="userName" class="inp width150"/>
				</td>
				<th width="25%" align="right">用户编号：</th>
				<td width="25%" align="left">
					<input class="inp" type="text" name="userNo" class="inp width150"/>
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
	<!-- <div class="total_panel" id="addPanel">
		<div class="add">
			<a href="javascript:toAdd();" style="text-decoration: none">添加用户</a>
		</div>
	</div> -->
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>