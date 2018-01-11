<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
// 			$("#form1").submit();
		});

	 	//产品编辑
		function toAdvertiseEditor() {
			window.location.href='${ctx}/appAdvertise/toAppAdvertiseEditor.action';
		}
	</script>

</head>
<body>
	<div class="search_tit"><h2>App广告管理</h2></div>
	<form action="appAdvertiseQuery.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<!-- <tr>
				<th>广告类型：</th>
				<td>
					<dict:select id="advertiseType" name="advertiseType" style="width: 150px;"
						dictTypeCode="AppAdvertiseType" nullOption="true"></dict:select>
				</td>
				<th>状态：</th>
				<td>
					<dict:select id="status" name="status" style="width: 150px;"
						dictTypeCode="AbleStatus" nullOption="true"></dict:select>
				</td>

			</tr>
			<tr>
				<th>系统类型：</th>
				<td>
					<dict:select id="appType" name="appType" style="width: 150px;"
						dictTypeCode="AppClientType" nullOption="true"></dict:select>
				</td>
				<th>使用方式：</th>
				<td>
					<dict:select id="useType" name="useType" style="width: 150px;"
						dictTypeCode="UseType" nullOption="true"></dict:select>
				</td>
			</tr> -->
			<tr>
				<th>广告类型：</th>
				<td width="25%" align="left">
					<select name="advertiseType">
						<option value="START_PAGE">启动页</option>
						<option value="HOME_PAGE">主页</option>
					</select>
				</td>
				<th>状态:</th>
				<td>
					<select name="status">
						<option value="ENABLE">可用</option>
						<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>系统类型：</th>
				<td width="25%" align="left">
					<select name="appType">
						<option value="IOS">IOS</option>
						<option value="Android">Android</option>
					</select>
				</td>
				<th>使用方式：</th>
				<td width="25%" align="left">
					<select name="useType">
						<option value="H5">H5</option>
						<option value="CLASS">CLASS</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
		<center>
			<input type="submit"  class="global_btn" value="查 询" />
			<input type="reset" class="global_btn" value="重 置" />
		</center>
	</form>
	<br>
	<div class="total_panel" id="addPanel">
		<div class="add">
			<a href="javascript:toAdvertiseEditor();" style="text-decoration: none">添加</a>
		</div>
	</div>
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>