<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit(){
	var title = $("#title").val().trim();
	var username = $("#username").val().trim();
	var content = $("#content").val().trim();
	var briefContent = $("#briefContent").val().trim();
	if(title == '')
	{
		dialogMessage('标题不能为空');
		return false;
	}
	
	if(username == ''){
		dialogMessage('接收用户编号不能为空');
		return false;
	}
	if(content == '')
	{
		dialogMessage('消息内容不能为空');
		return false;
	}
	if(briefContent == '')
	{
		dialogMessage('消息简要不能为空');
		return false;
	}
	
	$.ajax({
		type : 'POST',
		url:'${ctx}/message/update.action',
		data:{
			'id':$("#messageId").val(),
			'msgType':$("#messageType").val(),
			'ableStatus':$("#AbleStatus").val(),
			'title':title,
			'userName':username,
			'briefContent':briefContent,
			'isPop':$('input:radio[name="isPop"]:checked').val(),
			'content':content
		},
		error : function(request) {
			dialogMessage("修改异常！");
		},
		success : function(data) {
			console.log(data);
			if(data == 'SUCCESS'){
				dialogMessage("修改成功！");
				window.close();
			}else{
				dialogMessage("修改失败！");
			}
		},
		dataType:"text"
	}); 
}

$(document).ready(function(){
	//消息类型
	var msgType = '${appMessageInfo.msgType}';
	if(typeof(msgType) != "undefined"){
		$("#messageType option").each(function(){
			if(msgType == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	}
	
	//消息状态
	var ableStatus = '${appMessageInfo.ableStatus}';
	if(typeof(ableStatus) != "undefined"){
		$("#AbleStatus option").each(function(){
			if(ableStatus == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	}
});
</script>
</head>
<body>
	<div class="search_tit"><h2>编辑个人消息</h2></div>
	 	<input type="hidden" id="messageId" name="id" value="${appMessageInfo.id }"/>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">消息类型：</th>
				<td width="25%">
					<select id="messageType" name="msgType">
					 	<option value="COMMON">普通消息</option>
					 	<option value="IMPORTANT">重要消息</option>
					</select>
				</td>
				<th width="20%">状态：</th>
				<td width="25%">
					<select id="AbleStatus" name="ableStatus" value="${appMessageInfo.ableStatus }">
					 	<option value="ENABLE">可用</option>
					 	<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">消息标题：</th>
				<td width="25%">
					<input type="text" id="title" name="title" value="${appMessageInfo.title}"/>
				</td>
				<th width="20%">接收用户编号：</th>
				<td width="25%">
				<input type="text" id="username" name="userName" value="${appMessageInfo.userName}"/>
				</td>
			</tr>
			<tr>
				<th width="20%">简要内容：</th>
				<td width="25%">
				<input type="text" style="width:300px" id="briefContent" name="briefContent" value="${appMessageInfo.briefContent}"/>
				</td>
				<th>是否弹屏：</th>
				<td>
					<c:if test="${appMessageInfo.isPop == true}">
						<input name="isPop" type="radio" value="true" checked="true">弹屏
						<input name="isPop" type="radio" value="false">不弹屏
					</c:if>
					<c:if test="${appMessageInfo.isPop == false}">
						<input name="isPop" type="radio" value="true" >弹屏
						<input name="isPop" type="radio" value="false" checked="true">不弹屏
					</c:if>
					
				</td>
			</tr>
			<tr>
				<th width="15%">公告内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" >${appMessageInfo.content}</textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	<br>
	<center>
		<input type="button" class="global_btn" value="修改" onclick="mySubmit()" />
	</center>
</body>
</html>