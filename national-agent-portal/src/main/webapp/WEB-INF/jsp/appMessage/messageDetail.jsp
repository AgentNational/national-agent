<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="search_tit"><h2>app消息详细</h2></div>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th>消息标题：</th>
				<td>
					${appMessageInfo.title}
				</td>
			</tr>
			<tr>
				<th>消息类型：</th>
				<td>
					<c:if test="${appMessageInfo.msgType == 'COMMON' }">
					   普通消息
					</c:if>
					<c:if test="${appMessageInfo.msgType == 'IMPORTANT' }">
					   重要消息
					</c:if>
				</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>
					${appMessageInfo.operator}
				</td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td>
					<fmt:formatDate value="${appMessageInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>是否已读：</th>
				<td>
					${appMessageInfo.isRead}
				</td>
			</tr>
			<tr>
				<th>是否弹屏：</th>
				<td>
					<c:if test="${appMessageInfo.isPop == true }">弹屏</c:if>
					<c:if test="${appMessageInfo.isPop == false }">不弹屏</c:if>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<c:if test="${appMessageInfo.ableStatus == 'ENABLE' }">
					可用
					</c:if>
					<c:if test="${appMessageInfo.ableStatus == 'DISABLE' }">
					不可用
					</c:if>
					<c:if test="${appMessageInfo.ableStatus == 'DELETE' }">
					删除
					</c:if>
				</td>
				
			</tr>
			<tr>
				<th>接收用户编号：</th>
				<td>
					${appMessageInfo.userName}
				</td>
			</tr>
			<tr>
				<th>简要内容：</th>
				<td>
					${appMessageInfo.briefContent}
				</td>
			</tr>
			<tr>
				<th>消息内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" readonly="readonly" >${appMessageInfo.content}</textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	<br>
</body>
</html>