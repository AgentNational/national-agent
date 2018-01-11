<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
	 		initCoopAgency();
// 			$("#form1").submit();
		});

	 	// 公告新增
		function toAdd() {
			window.location.href='${ctx}/appMenu/toAppMenuAdd.action';
		}
		function initCoopAgency()
		{
			$.ajax({
				type : 'POST',
				url:ctx+'/coopAgency/findCoopAgencyList.action',
				dataType:"json",
				async : false,
				error : function(request) {
					dialogMessage("操作失败！");
				},
				success : function(data) {
					var datas  = eval(data);
					$("#agencyNo").empty();
					$("<option value=''></option>").appendTo("#agencyNo");
					$("<option value='Self'>普惠金融</option>").appendTo("#agencyNo");
					$.each(data, function(i, item){
						 $("#agencyNo").append('<option value='+item.agencyNo+'>'+item.agencyName+'</option>');
					 });
				}
			});
		}
	</script>

</head>
<body>
	<div class="search_tit"><h2>app菜单管理</h2></div>
	<form action="appMenuQuery.action" method="post" target="queryResult" id="form1">
		<table class="global_table" cellpadding="0" cellspacing="0">
		
			<tr>
				<th width="50%" align="right">菜单标题：</th>
				<td width="50%" align="left">
					<input class="inp" type="text" name="menuName" class="inp width150"/>
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
			<a href="javascript:toAdd();" style="text-decoration: none">添加菜单</a>
		</div>
	</div>
	<iframe name="queryResult" src="" width="100%" height="520px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>