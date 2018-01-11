<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
	 		
		 	
		});

	 	// 公告新增
		function toAdd() {
			window.location.href='${ctx}/message/toAddMessage.action';
		}
	 	
	 	
	</script>

</head>
<body>
	<div class="search_tit"><h2>查询个人消息</h2></div>
	<form action="queryMessageList.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th>消息标题:</th>
				<td>
					<input class="inp" type="text" name="title" class="inp width150"/>
				</td>
				<th>消息类型:</th>
				<td>
					<select id="messageType" name="msgType">
						<option selected="selected"></option>
					 	<option value="COMMON">普通消息</option>
					 	<option value="IMPORTANT">重要消息</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>接收用户编号:</th>
				<td>
					<input class="inp" type="text" name="userName" class="inp width150"/>
				</td>
				<th>状态:</th>
				<td>
					<select id="AbleStatus" name="ableStatus">
						<option selected="selected"></option>
					 	<option value="ENABLE">可用</option>
					 	<option value="DISABLE">不可用</option>
					 	<option value="DELETE">删除</option>
					</select>
				</td>
			<tr>
				<th>创建日期：</th>
				<td>
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
			<a href="javascript:toAdd();" style="text-decoration: none">添加消息</a>
		</div>
	</div>
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>