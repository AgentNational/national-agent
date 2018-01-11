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
	<c:if test="${feedbackList != null && feedbackList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="8%">用户手机号</th>
				<th width="8%">系统类型</th>
				<th width="15%">反馈内容</th>
				<th width="15%">处理意见</th>
				<th width="8%">短信</th>
				<th width="6%">状态</th>
				<th width="12%">创建时间</th>
				<th width="8%">处理人</th>
				<th width="10%">处理时间</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${feedbackList}" var="feedback">
				<tr>
					<td>${feedback.phone }</td>
					<%-- <td><dict:write dictTypeCode="AppClientType" dictKey="${feedback.clientType }"></dict:write></td> --%>
					<td>
						<c:if test="${feedback.clientType == 'IOS' }">IOS</c:if>
						<c:if test="${feedback.clientType == 'Android' }">Android</c:if>
					</td>
					<td>
						<div class="cut" title="${feedback.content }">
							${feedback.content }
						</div>
					</td>
					<td>
						<div class="cut" title="${feedback.remark }">
							${feedback.remark }
						</div>
					</td>
					<td>
						<c:choose>
							<c:when test="${feedback.isRead eq 'Y'  }">
								已发送
							</c:when>
							<c:otherwise>
								未发送
							</c:otherwise>
						</c:choose>
					</td>
					<td><dict:write dictTypeCode="FeedbackStatus" dictKey="${feedback.status }"></dict:write></td>
					<td> <fmt:formatDate value="${feedback.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<td>${feedback.operator }</td>
					<td> <fmt:formatDate value="${feedback.operatorTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<td>
						<c:if test="${feedback.status == 'INIT' }">
							<a href="javascript:toModify('${feedback.id}')">处理</a>
							&nbsp;&nbsp;
						</c:if>
						<c:if test="${feedback.status != 'INIT' }">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
						<a href="javascript:toDetail('${feedback.id}')">详细信息</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${feedbackList eq null or feedbackList.size() == 0}">
			无符合条件的记录
	</c:if>

	<script type="text/javascript">

		//分页
		$(function() {
			initPage('${page.currentPage}', '${page.totalResult}',
					'${page.totalPage}', '${page.entityOrField}', parent.document
							.getElementById("form1"));
		});

		//产品编辑
		function toModify(msgId) {
			window.parent.location.href ='${ctx}/appFeedBack/toFeedbackModify.action?id='+msgId;
		}

		function toDetail(msgId) {
			console.info(msgId);
			window.parent.location.href ='${ctx}/appFeedBack/feedbackDetail.action?id='+msgId;
		}

	</script>
</body>
</html>