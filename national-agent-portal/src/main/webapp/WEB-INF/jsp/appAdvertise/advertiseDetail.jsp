<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>

<script type="text/javascript">


</script>

</head>
<body>
	<div class="detail_tit"><h2>广告信息</h2></div>
	<table cellpadding="0" cellspacing="0" class="global_table">
		<tr>
			<th width="30%">广告类型：</th>
			<!--  <td width="70%">
				<dict:write dictTypeCode="AppAdvertiseType" dictKey="${appAdvertiseInfo.advertiseType }"></dict:write>
			</td> -->
			<td>
				<c:if test="${appAdvertiseInfo.advertiseType == 'START_PAGE' }">启动页</c:if>
				<c:if test="${appAdvertiseInfo.advertiseType == 'HOME_PAGE' }">主页</c:if>
			</td>
		</tr>
		<tr>
			<th>序号：</th>
			<td>
				${appAdvertiseInfo.indexNo }
			</td>
		</tr>
		<tr>
			<th id="imagesUrlName">图片地址：</th>
			<td>
				${appAdvertiseInfo.imagesUrl }
			</td>
		</tr>
		<tr>
			<th>使用方式：</th>
			<td>
				${appAdvertiseInfo.useType }
			</td>
		</tr>
		<c:if test="${appAdvertiseInfo.useType == 'H5' }">
		<tr>
			<th>链接地址：</th>
			<td>
				${appAdvertiseInfo.linkUrl }
			</td>
		</tr>
		</c:if>
		<c:if test="${appAdvertiseInfo.useType == 'CLASS' }">
		<tr>
			<th>类名：</th>
			<td>
				${appAdvertiseInfo.className }
			</td>
		</tr>
		<tr>
			<th>属性名：</th>
			<td>
				${appAdvertiseInfo.properties }
			</td>
		</tr>
		</c:if>
		<tr>
			<th>状态：</th>
			<!--  <td>
				<dict:write dictTypeCode="AbleStatus" dictKey="${appAdvertiseInfo.status }"></dict:write>
			</td>-->
			<td>
				<c:if test="${appAdvertiseInfo.status == 'ENABLE' }">可用</c:if>
				<c:if test="${appAdvertiseInfo.status == 'DISABLE' }">不可用</c:if>
			</td>
		</tr>
		<tr>
			<th>描述：</th>
			<td>
				${appAdvertiseInfo.description }
			</td>
		</tr>
	</table>
	<br>
	<center>
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>