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
	<c:if test="${appUserList != null && appUserList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20%">用户名称</th>
				<th width="25%">手机号</th>
				<th width="20%">用户编号</th>
				<th width="15%">用户状态</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach items="${appUserList}" var="appUser">
				<tr>
					<td>
						${appUser.realname }
					</td>
					<td>
						${appUser.userName }
					</td>
					<td>
						${appUser.userNo }
					</td>
					<td>
						<dict:write dictTypeCode="AppUserStatusType" dictKey="${appUser.status }"></dict:write>
					</td>
					<td>
						<a href="javascript:toAppUserEditor('${appUser.id}')">编辑</a>
						<a href="javascript:toAuthorize('${appUser.id}')">授权</a>
						<c:if test="${appUser.ableStatus == 'TRUE' }">
							<a href="javascript:updateUserStatus('${appUser.id}','FALSE')">禁用</a>
						</c:if>
						<c:if test="${appUser.ableStatus == 'FALSE' }">
							<a href="javascript:updateUserStatus('${appUser.id}','TRUE')">启用</a>
						</c:if>
						<c:if test="${appUser.ableStatus == '' }">
							<a href="javascript:updateUserStatus('${appUser.id}','TRUE')">启用</a>
						</c:if>
					</td>
				<tr>
			</c:forEach>
		</table>
	<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${appUserList eq null or appUserList.size() == 0}">
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
		function toAppUserEditor(userId) {
			window.parent.location.href ='${ctx}/appUser/toAppUserEdit.action?userId='+userId;
		}
		//禁用
		function updateUserStatus(userId,status){
			$.ajax({
				type : 'POST',
				url:'${ctx}/appUser/updateUserStatus.action',
				data : {
					'userId':userId,
					'status':status
		        },
				dataType : 'text',
				error : function(request) {
					dialogMessage("操作失败！");
				},
				success : function(data) {
					var resultJson = JSON.parse(data);
					dialogMessage(resultJson.msg);
				}
			});
		}
		//授权
		function toAuthorize(userId){
			window.parent.location.href ='${ctx}/appUser/toAppUserAuthorize.action?userId='+userId;
		}
	</script>
</body>
</html>