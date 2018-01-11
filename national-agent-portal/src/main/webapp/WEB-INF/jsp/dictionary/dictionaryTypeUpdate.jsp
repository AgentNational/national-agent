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
		<form action="updateDictType.action" method="post" modelAttribute="dictionaryType" onsubmit="cancelUpdateDict()">
			<table class="global_table" cellpadding="0" cellspacing="0" >
				<tr>
					<th width="25%" style="height:25px;" >标识：</th>
					<td  width="75%">${dictionaryType.code}<input type="hidden" name="code" value="${dictionaryType.code}"/></td>
				</tr>
				<tr>
					<th>名称：</th>
					<td><input type="text" name="name" value="${dictionaryType.name }" class="inp width150"></td>
				</tr>
				<tr>
					<th>描述：</th>
					<td>
						<textarea rows="5" cols="50" name="remark">${dictionaryType.remark}</textarea>
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