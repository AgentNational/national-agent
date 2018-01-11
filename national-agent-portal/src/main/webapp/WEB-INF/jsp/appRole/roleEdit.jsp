<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	$("#form1").attr("action", "${ctx}/appRole/appRoleEdit.action");
	$("#form1").submit();
}
</script>
</head>
<body>
	<div class="search_tit"><h2>app角色详情</h2></div>
	 <form action="" method="post" id="form1">
	 	<input type="hidden" id="id" name="id" value="${appRole.id }"/>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">角色类型：</th>
				<td width="25%">
					<input type="text" id="roleType" name="roleType"value="${appRole.roleType }"/>
				</td>
				<th width="20%">角色名称：</th>
				<td width="25%">
					<input type="text" id="roleName" name="roleName"value="${appRole.roleName }"/>
				</td>
			</tr>
			<tr>
				<th width="20%">角色状态：</th>
				<td width="25%">
					<dict:select id="status" name="status" dictKeys="${appRole.status }"
						style="width: 225px;" dictTypeCode="AppRoleStatusType" nullOption="true"></dict:select>
				</td>
				<th width="20%">角色描述：</th>
				<td width="25%">
					<input type="text" id="remark" name="remark"value="${appRole.remark }"/>
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