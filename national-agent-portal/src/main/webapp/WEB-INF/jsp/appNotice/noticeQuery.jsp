<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
//	 		initCoopAgency();
// 			$("#form1").submit();
		});

	 	// 公告新增
		function toAdd() {
			window.location.href='${ctx}/appNotice/toAppNoticeAdd.action';
		}
	</script>

</head>
<body>
	<div class="search_tit"><h2>查询公告</h2></div>
	<form action="appNoticeQuery.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="25%" align="right">公告标题:</th>
				<td width="25%" align="left">
					<input class="inp" type="text" name="title" class="inp width150"/>
				</td>
				<th width="25%" align="right">公告类型:</th>
				<td width="25%" align="left">
					<select name="noticeType">
						<option value="COMMON">普通公告</option>
						<option value="IMPORTANT">重要公告</option>
					</select>
				</td>
			</tr>
			<tr>
			<th>状态:</th>
			<td>
				<select name="usableStatus">
					<option value="ENABLE">可用</option>
					<option value="DISABLE">不可用</option>
				</select>
			</td>
			<th width="10%">创建时间：</th>
				<td width="15%">
					<input type="text" name="startDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate width80" /> ——
					<input type="text" name="endDate" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate width80" />
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
			<a href="javascript:toAdd();" style="text-decoration: none">添加公告</a>
		</div>
	</div>
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>