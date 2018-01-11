<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	var title = $("#title").val().trim();
	var beginTime = $("#beginTime").val().trim();
	var endTime = $("#endTime").val().trim();
	var content = $("#content").val().trim();
	var briefContent = $("#briefContent").val().trim();
	if(title == '')
	{
		dialogMessage('标题不能为空');
		return false;
	}
	if(beginTime == '')
	{
		dialogMessage('开始时间不能为空');
		return false;
	}
	if(endTime == '')
	{
		dialogMessage('结束时间不能为空');
		return false;
	}
	if(content == '')
	{
		dialogMessage('公告内容不能为空');
		return false;
	}
	if(briefContent == '')
	{
		dialogMessage('公告简要不能为空');
		return false;
	}
	
	$.ajax({
		type : 'POST',
		url:'${ctx}/appNotice/appNoticeEdit.action',
		data:{
			'id':$("#noticeId").val(),
			'title':title,
			'noticeType':$("#noticeType").val(),
			'beginTime':beginTime,
			'endTime':endTime,
			'usableStatus':$("#usableStatus").val(),
			'isPop':$('input:radio[name="isPop"]:checked').val(),
			'userGroup':$("#userGroup").val(),
			'briefContent':briefContent,
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

$(document).ready(function() {
	//公告类型
	var noticeType = '${appNotice.noticeType}';
	if(typeof(noticeType) != "undefined"){
		$("#noticeType option").each(function(){
			if(noticeType == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	}
	
	//公告状态
	var usableStatus = '${appNotice.usableStatus}';
	if(typeof(usableStatus) != "undefined"){
		$("#usableStatus option").each(function(){
			if(usableStatus == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	}
	
	//用户群体
	var userGroup = '${appNotice.userGroup}';
	if(typeof(userGroup) != "undefined"){
		$("#userGroup option").each(function(){
			if(userGroup == $(this).val()){
				$(this).attr("selected",true);
			}
		});
	}
});
</script>
</head>
<body>
	<div class="search_tit"><h2>编辑公告</h2></div>
	 <form action="" method="post" id="form1">
	 <input type="hidden" id="noticeId" name="id" value="${appNotice.id }"/>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">公告标题：</th>
				<td width="25%">
					<input type="text" id="title" value="${appNotice.title }"name="title"/>
				</td>
				<th width="20%">公告类型：</th>
				<td width="25%">
					<select id="noticeType" name="noticeType" value="${appNotice.noticeType }" >
						<option value="COMMON">普通公告</option>
						<option value="IMPORTANT">重要公告</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">有效时间：</th>
				<td width="25%" >
					<input id="beginTime" type="text" name="beginTime" 
					value='<fmt:formatDate value="${appNotice.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' 
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate width100" /> -
					<input id="endTime" type="text" name="endTime" 
					value='<fmt:formatDate value="${appNotice.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'  
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate width100" />
				</td>
				<th width="20%">状态：</th>
				<td width="25%">
					<select  id="usableStatus" name="usableStatus" value="${appNotice.usableStatus }">
						<option value="ENABLE">可用</option>
						<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否弹屏：</th>
				<td>
					<c:if test="${appNotice.isPop == true}">
						<input name="isPop" type="radio" value="true" checked="true">弹屏
						<input name="isPop" type="radio" value="false">不弹屏
					</c:if>
					<c:if test="${appNotice.isPop == false}">
						<input name="isPop" type="radio" value="true" >弹屏
						<input name="isPop" type="radio" value="false" checked="true">不弹屏
					</c:if>
				</td>
				<th>用户群体:</th>
				<td>
					<select id="userGroup" name="userGroup" value="${appNotice.userGroup }">
						<option value="NATIONAL_AGENT">普通代理人</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>简要内容:</th>
				<td>
					<input id="briefContent" name="briefContent" type="text" style="width:300px" value="${appNotice.briefContent}" />
				</td>
			</tr>
			<tr>
				<th width="15%">公告内容：</th>
				<td colspan="3">
					<textarea rows="5" id="content" name="content"  style="width:400px" >${appNotice.content}
					</textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</form>
	<br>
	<center>
		<input type="button" class="global_btn" value="修改" onclick="mySubmit()" />
	</center>
</body>
</html>