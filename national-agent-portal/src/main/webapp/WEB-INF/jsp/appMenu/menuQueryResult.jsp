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
	<c:if test="${appMenuList != null && appMenuList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20%">菜单名称</th>
				<th width="25%">菜单图片URL</th>
				<th width="20%">菜单描述</th>
				<th width="15%">排序</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach items="${appMenuList}" var="appMenu">
				<tr>
					<td>
						${appMenu.menuName }
					</td>
					<td>
						${appMenu.imgUrl }
					</td>
					<td>
						${appMenu.remark }
					</td>
					<td>
						${appMenu.menuOrder }
					</td>
					<td>
						<a href="javascript:toAppMenuEditor('${appMenu.id}')">编辑</a>
						<a href="javascript:toAuthorize('${appMenu.id}')">授权</a>
					</td>
				<tr>
			</c:forEach>
		</table>
	<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${appMenuList eq null or appMenuList.size() == 0}">
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
		function toAppMenuEditor(menuId) {
			window.parent.location.href ='${ctx}/appMenu/toAppMenuEdit.action?menuId='+menuId;
		}
		//删除
		function toDelete(menuId) {
			window.parent.location.href ='${ctx}/appMenu/deleteAppMenu.action?menuId='+menuId;
		}
		//授权
		function toAuthorize(menuId){
			window.parent.location.href ='${ctx}/appMenu/toAppMenuAuthorize.action?menuId='+menuId;
		}
	</script>
</body>
</html>