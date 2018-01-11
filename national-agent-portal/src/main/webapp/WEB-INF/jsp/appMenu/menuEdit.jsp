<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
function mySubmit()
{
	var menuName = $("#menuName").val().trim();
	var imgUrl = $("#imgUrl").val().trim();
	var menuUrl = $("#menuUrl").val().trim();
	if(menuName == '')
	{
		dialogMessage('菜单名称不能为空');
		return false;
	}
	if(imgUrl == '')
	{
		dialogMessage('图片地址不能为空');
		return false;
	}
	if(menuUrl == '')
	{
		dialogMessage('链接地址不能为空');
		return false;
	}
	$("#form1").attr("action", "${ctx}/appMenu/appMenuEdit.action");
	$("#form1").submit();
}
$(function(){
	$("input[name='status'][value='${appMenu.status}'").attr("checked",true);
});
</script>
</head>
<body>
	<div class="search_tit"><h2>app菜单详情</h2></div>
	 <form action="" method="post" id="form1">
	 	<input type="hidden" id="id" name="id" value="${appMenu.id }"/>
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="20%">菜单名称：</th>
				<td width="25%">
					<input type="text" id="menuName" name="menuName"value="${appMenu.menuName }"/>
				</td>
				<th width="20%">图片URL：</th>
				<td width="25%">
					<input type="text" id="imgUrl" name="imgUrl"value="${appMenu.imgUrl }"/>
				</td>
			</tr>
			<tr>
				<th width="20%">链接URL：</th>
				<td width="25%">
					<input type="text" id="h5Url" name="h5Url"value="${appMenu.h5Url }"/>
				</td>
				<th width="20%">排序：</th>
				<td width="25%">
					<input type="text" id="menuOrder" name="menuOrder"value="${appMenu.menuOrder }"/>
				</td>
				
			</tr>
			<tr>
				<th width="">状态：</th>
				<td width="">
					可用<input type="radio" name="status" value="ENABLE"/>&nbsp;&nbsp;
					禁用<input type="radio" name="status" value="DISABLE"/>
				</td>
			</tr>
			<tr>
				<th width="15%">菜单描述：</th>
				<td colspan="3">
					<textarea rows="5" id="remark" name="remark"  style="width:400px" >${appMenu.remark }</textarea>&nbsp;<font color="red">*</font>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</form>
	<br>
	<center>
		<input type="button" class="global_btn" value="提交" onclick="mySubmit()" />
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>