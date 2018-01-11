<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	$("#form1").attr("action", "${ctx}/appMenu/appMenuEdit.action");
	$("#form1").submit();
}
</script>
</head>
<body>
	<div class="search_tit"><h2>app用户详情</h2></div>
	 <form action="" method="post" id="form1">
	 	<input type="hidden" id="id" name="id" value="${appUser.id }"/>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">用户手机号：</th>
				<td width="25%">
					<input type="text" readonly="readonly" id="userName" name="userName"value="${appUser.userName }"/>
				</td>
				<th width="20%">图片URL：</th>
				<td width="25%">
					<input type="text" id="parentUserNo" name="parentUserNo"value="${appUser.parentUserNo }"/>
				</td>
			</tr>
			<tr>
				<th width="20%">链接URL：</th>
				<td width="25%">
					<dict:select id="ableStatus" name="ableStatus" dictKeys="${appUser.ableStatus }"
						style="width: 225px;" dictTypeCode="AppUserStatusType" nullOption="true"></dict:select>
				</td>
				<th width="20%">用户邮箱：</th>
				<td width="25%">
					<input type="text" id="email" name="email"value="${appUser.email }"/>
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