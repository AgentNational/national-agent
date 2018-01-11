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

		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="40%">描述</th>
				<th width="10%">顺序</th>
				<th width="10%">类型</th>
				<th width="10%">状态</th>
				<th width="10%">操作</th>
			</tr>
			<c:if test="${advertiseList != null && advertiseList.size() > 0}">
				<c:forEach items="${advertiseList}" var="advertise">
					<tr>
						<td>
							<div >
								${advertise.description }
							</div>
						</td>
						<td>${advertise.indexNo }</td>
						<%-- <td><dict:write dictTypeCode="AppAdvertiseType" dictKey="${advertise.advertiseType }"></dict:write></td>
						<td><dict:write dictTypeCode="AbleStatus" dictKey="${advertise.status }"></dict:write></td> --%>
						<td>
							<c:if test="${advertise.advertiseType == 'START_PAGE' }">启动页</c:if>
							<c:if test="${advertise.advertiseType == 'HOME_PAGE' }">主页</c:if>
						</td>
						<td>
							<c:if test="${advertise.status == 'ENABLE' }">可用</c:if>
							<c:if test="${advertise.status == 'DISABLE' }">不可用</c:if>
						</td>
						<td>
							<a href="javascript:toAdvertiseEditor('${advertise.id}')">编辑</a>
							&nbsp;&nbsp;
							<a href="javascript:toDetail('${advertise.id}')">详细信息</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${advertiseList != null && advertiseList.size() > 0}">
			<div id="page" align="right" style="margin: 20px 20px;"></div>
		</c:if>
		<c:if test="${advertiseList eq null or advertiseList.size() == 0}">
			<center>
				<br/><br/>
				无符合条件的记录
			</center>
		</c:if>

	<script type="text/javascript">

		//分页
		$(function() {
			initPage('${page.currentPage}', '${page.totalResult}',
					'${page.totalPage}', '${page.entityOrField}', parent.document
							.getElementById("searchFrom"));
		});

		//编辑
		function toAdvertiseEditor(id) {
			window.parent.location.href ='${ctx}/appAdvertise/toAppAdvertiseEditor.action?id='+id;
		}

		//详细信息
		function toDetail(id) {
			window.parent.location.href ='${ctx}/appAdvertise/toAdvertiseDetail.action?id='+id;
		}

	</script>
</body>
</html>