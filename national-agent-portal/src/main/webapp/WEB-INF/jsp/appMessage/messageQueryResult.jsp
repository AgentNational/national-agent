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
	<c:if test="${appMessageInfoList != null && appMessageInfoList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20%">消息标题</th>
				<th width="10%">消息类型</th>
				<th width="20%">接收用户编号</th>
				<th width="10%">是否读取</th>
				<th width="10%">状态</th>
				<th width="20%">操作</th>
			</tr>
			
			<c:forEach items="${appMessageInfoList}" var="appMessage">
				<tr id="TR${appMessage.id}">
					<td>
						${appMessage.title }
					</td>
					<td>
						<c:if test="${appMessage.msgType  == 'COMMON'}">
							普通
						</c:if>
						<c:if test="${appMessage.msgType  == 'IMPORTANT'}">
							重要
						</c:if>
					</td>
					<td>
						${appMessage.userName }
					</td>
					<td>
						<c:if test="${appMessage.isRead  == 'Y'}">
							已读
						</c:if>
						<c:if test="${appMessage.isRead  == 'N'}">
							未读
						</c:if>
					</td>
					<td>
						<c:if test="${appMessage.ableStatus  == 'ENABLE'}">
							可用
						</c:if>
						<c:if test="${appMessage.ableStatus  == 'DISABLE'}">
							不可用
						</c:if>
						<c:if test="${appMessage.ableStatus  == 'DELETE'}">
							删除
						</c:if>
					</td>
					<td>
						<a href="javascript:toAppMessageEditor('${appMessage.id}')">编辑</a>
						<a href="javascript:toAppMessageDetail('${appMessage.id}')">详情</a>
						<a href="javascript:deleteMessage('${appMessage.id}')">删除</a>
						<c:if test="${appMessage.ableStatus == 'ENABLE'}">
							<a href="javascript:messageSend('${appMessage.id}')">推送</a>
						</c:if>
					</td>
				<tr>
			</c:forEach>
		
		</table>
	<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${appMessageInfoList eq null or appMessageInfoList.size() == 0}">
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
		function toAppMessageEditor(messageId) {
			window.open('${ctx}/message/toUpdateMessage.action?messageId='+messageId);
		}
		//详情
		function toAppMessageDetail(messageId) {
			window.open('${ctx}/message/getMessageDetail.action?messageId='+messageId);
		}
		//删除消息
		function deleteMessage(messageId){
			var msg = '确定删除这条消息吗?';
			if (confirm(msg)==true){ 
				 return true; 
			}else{ 
			     return false; 
			} 
			
			$.ajax({
				type : 'POST',
				url:'${ctx}/message/deleteMessage.action',
				data:{'messageId':messageId},
				error : function(request) {
					dialogMessage("操作异常！");
				},
				success : function(data) {
					console.log(data);
					if(data == 'SUCCESS'){
						console.log("进入成功了");
						$("#TR"+messageId).remove();
					}else{
						dialogMessage("操作失败！");
					}
				},
				dataType:"text"
			}); 
		}
		function messageSend(messageId){
			$.ajax({
				type : 'POST',
				url:'${ctx}/message/sendMessage.action',
				data:{'messageId':messageId},
				error : function(request) {
					dialogMessage("推送异常！");
				},
				success : function(data) {
					console.log(data);
					if(data == 'true'){
						console.log("进入成功了");
						dialogMessage("推送成功！");
					}else{
						dialogMessage("推送失败！");
					}
				},
				dataType:"text"
			}); 
		}
	</script>
</body>
</html>