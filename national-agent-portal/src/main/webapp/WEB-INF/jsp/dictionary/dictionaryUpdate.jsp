<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
	<head>
		<script type="text/javascript">
				function cancelUpdateDict(){
					window.parent.parent.closeDialog();
				}
		</script>
	</head>

	<body>
		<div class="pop_tit"><h2>修改字典信息</h2></div>

		<form id="form2" action="dictionaryUpdate.action" method="post" modelAttribute="dictionary" onsubmit="cancelUpdateDict()">
			<input type="hidden" name="id" value="${dictionary.id}">
			<table class="global_table" cellpadding="0" cellspacing="0">
				<tr>
					<th width="30%">字典类型值：</th>
					<td width="70%" align="left">
						<input type="hidden" name="dictTypeId" value="${dictionary.dictTypeId}">
						${dictionary.dictTypeId}
					</td>
				</tr>
				<tr>
					<th>字典值：</th>
					<td >
						<input type="text" name="key" value="${dictionary.key}"  class="inp width150">
					</td>
				</tr>
				<tr>
					<th>字典名称：</th>
					<td>
						<input type="text" name="value" value="${dictionary.value}" class="inp width150">
					</td>
				</tr>
				<tr>
					<th>状态：</th>
					<td>
						<select name="status" class="inp width150">
							<option value="TRUE">可用</option>
							<option value="FALSE">禁用</option>
						</select>
					</td>
				</tr>
				<%-- <tr>
					<th>参考值：</th>
					<td>
						<input type="text" name="privilege" value="${privilege}" class="inp width150">
					</td>
				</tr> --%>
				<tr>
					<th>显示顺序：</th>
					<td>
						<input type="text" name="order" value="${dictionary.order}" class="inp width150">
					</td>
				</tr>
				<tr>
					<th>描述：</th>
					<td>
						<textarea rows="5" cols="70" name="remark">${dictionary.remark}</textarea>
					</td>
				</tr>
			</table>
			<br>
			<center>
				<input type="submit" value="修改" class="global_btn"/>
				<input type="button" value="取消" class="global_btn" onclick="cancelUpdateDict();"/>
			</center>

		</form>

	</body>
</html>