<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<style type="text/css">
	.cut{word-wrap:normal;overflow: hidden; white-space:nowrap; -o-text-overflow:ellipsis; text-overflow:ellipsis;}
</style>
</head>
<body>
	<c:if test="${appRoleList != null && appRoleList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20%">角色ID</th>
				<th width="25%">角色名称</th>
				<th width="20%">角色类型</th>
				<th width="15%">角色状态</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach items="${appRoleList}" var="appRole">
				<tr>
					<td>
						${appRole.id }
					</td>
					<td>
						${appRole.roleName }
					</td>
					<td>
						${appRole.roleType }
					</td>
					<td>
						<dict:write dictTypeCode="AppRoleStatusType" dictKey="${appRole.status }"></dict:write>
					</td>
					<td>
						<a href="javascript:toAppRoleEditor('${appRole.id}')">编辑</a>
					</td>
				<tr>
			</c:forEach>
		</table>
	<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${appRoleList eq null or appRoleList.size() == 0}">
			无符合条件的记录
	</c:if>

	<script type="text/javascript">

		//分页
		$(function() {
			initPage('${page.currentPage}', '${page.totalResult}',
					'${page.totalPage}', '${page.entityOrField}', parent.document
							.getElementById("form1"));
		});

		//编辑
		function toAppRoleEditor(roleId) {
			window.parent.location.href ='${ctx}/appRole/toAppRoleEdit.action?roleId='+roleId;
		}
	</script>
</body>
</html>