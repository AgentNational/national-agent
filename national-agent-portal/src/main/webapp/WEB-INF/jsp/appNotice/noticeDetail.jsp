<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="search_tit"><h2>公告详细</h2></div>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th>公告标题：</th>
				<td>
					${appNotice.title }
				</td>
			</tr>
			<tr>
				<th>公告类型：</th>
				<td>
					<c:if test="${appNotice.noticeType == 'COMMON' }">普通公告</c:if>
					<c:if test="${appNotice.noticeType == 'IMPORTANT' }">重要公告</c:if>
				</td>
			</tr>
			<tr>
				<th>有效时间：</th>
				<td>
					<fmt:formatDate value="${appNotice.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> —
					<fmt:formatDate value="${appNotice.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<c:if test="${appNotice.usableStatus == 'ENABLE' }">可用</c:if>
					<c:if test="${appNotice.usableStatus == 'DISABLE' }">不可用</c:if>
				</td>
			</tr>
			<tr>
				<th>是否弹屏</th>
				<td>
					<c:if test="${appNotice.isPop == true }">弹屏</c:if>
					<c:if test="${appNotice.isPop == false }">不弹屏</c:if>
				</td>
			</tr>
			<tr>
				<th>用户群体</th>
				<td>
					<c:if test="${appNotice.userGroup == 'NATIONAL_AGENT' }">普通代理人</c:if>
				</td>
			</tr>
			<tr>
				<th>简要内容</th>
				<td>
					${appNotice.briefContent }
				</td>
			</tr>
			<tr>
			<th>公告内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" readonly="readonly" >${appNotice.content}
					</textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	<br>
</body>
</html>