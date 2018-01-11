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
	<c:if test="${appNoticeList != null && appNoticeList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20%">公告标题</th>
				<th width="10%">公告类型</th>
				<th width="15%">用户群体</th>
				<th width="10%">状态</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach items="${appNoticeList}" var="appNotice">
				<tr id="TR${appNotice.id }">
					<td>
						${appNotice.title }
					</td>
					<td>
						<c:if test="${appNotice.noticeType == 'COMMON' }">普通公告</c:if>
						<c:if test="${appNotice.noticeType == 'IMPORTANT' }">重要公告</c:if>
					</td>
					<td>
						<c:if test="${appNotice.userGroup == 'NATIONAL_AGENT' }">普通代理人</c:if>
					</td>
					<td>
						<c:if test="${appNotice.usableStatus == 'ENABLE' }">可用</c:if>
						<c:if test="${appNotice.usableStatus == 'DISABLE' }">不可用</c:if>
					</td>
					
					<td>
						<a href="javascript:toAppNoticeEditor('${appNotice.id}')">编辑</a>
						<a href="javascript:toAppNoticeDetail('${appNotice.id}')">详情</a>
						<a href="javascript:deleteNotice('${appNotice.id}')">删除</a>
					</td>
				<tr>
			</c:forEach>
		</table>
	<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${appNoticeList eq null or appNoticeList.size() == 0}">
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
		function toAppNoticeEditor(noticeId) {
			window.open('${ctx}/appNotice/toAppNoticeEdit.action?noticeId='+noticeId);
		}
		//详情
		function toAppNoticeDetail(noticeId) {
			window.open('${ctx}/appNotice/appNoticeDetail.action?noticeId='+noticeId);
		}
		//删除公告
		function deleteNotice(noticeId){
			var msg = '确定删除这条公告吗?';
			if (confirm(msg)==true){ 
				 return true; 
			}else{ 
			     return false; 
			} 
			
			$.ajax({
				type : 'POST',
				url:'${ctx}/appNotice/deleteNotice.action',
				data:{'noticeId':noticeId},
				error : function(request) {
					dialogMessage("操作异常！");
				},
				success : function(data) {
					console.log(data);
					if(data == 'SUCCESS'){
						console.log("进入成功了");
						$("#TR"+noticeId).remove();
					}else{
						dialogMessage("操作失败！");
					}
				},
				dataType:"text"
			}); 
		}
		function toAppNoticePublish(noticeId){
			$.ajax({
				type : 'POST',
				url:'${ctx}/appNotice/publishNotice.action',
				data:{"noticeId":noticeId},
				async : false,
				error : function(request) {
					dialogMessage("操作失败！");
				},
				success : function(data) {
					if("success" == data){
						dialogMessage("发布成功！");
					}else if("fail" == data){
						dialogMessage("发布失败！");
					}
				}
			});
		}
	</script>
</body>
</html>