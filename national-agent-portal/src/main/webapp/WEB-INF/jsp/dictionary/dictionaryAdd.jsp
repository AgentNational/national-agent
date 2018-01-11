<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
	<head>
		<script type="text/javascript">
			function cancelAddDict(){
				window.parent.parent.closeDialog();
			}
			
			function sub(){
				$("#form2").submit();
				window.parent.parent.closeDialog();
			}
		</script>
	</head>
	
<body>
		
	<div class="pop_tit"><h2>新增字典信息</h2></div>
		
	<form id="form2" action="addDictionary.action" modelAttribute="dictionary" method="post" >

		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="30%">所属字典类型：</th>
				<td width="70%" align="left">
					<input type="hidden" name="dictTypeId" value="<%=request.getParameter("dictTypeId") %>">
					<%=request.getParameter("dictTypeId")%>
				</td>							
			</tr>	
			<tr>
				<th>值：</th>
				<td>
					<input type="text" name="key" class="inp width180">
				</td>							
			</tr>				
			<tr>
				<th>名称：</th>
				<td>
					<input type="text" name="value" class="inp width180">
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<select name="status" class="inp width180">
						<option value="TRUE">可用</option>
						<option value="FALSE">禁用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>参考值：</th>
				<td>
					<input type="text" name="privilege" class="inp width180">
				</td>
			</tr>
			<tr>
				<th>顺序：</th>
				<td>
					<input type="text" name="order" class="inp width180">
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea rows="5" cols="70" name="remark" class="inp"></textarea>
				</td>
			</tr>				
		</table>
		
		<br>
		<center>
			<input type="button" value="创建" class="global_btn" onclick="sub();"/>
			<input type="button" value="取消" class="global_btn" onclick="cancelAddDict();"/>
		</center>			
			
	</form>
</body>
</html>
