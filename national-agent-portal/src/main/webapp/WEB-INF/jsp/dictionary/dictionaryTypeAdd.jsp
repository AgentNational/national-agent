<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
	<head>
		<script type="text/javascript">
			function cancelAddDictType(){
				window.parent.closeDictTypeAddWin();
			}
		</script>
	</head>
	
	<body>		
		<form id="form2" name="form2" action="addDictionaryType.action" modelAttribute="dictionaryType" method="post">

			<div class="add_tit">
				<div class="scroll_tableresult" style="width: 650px;">
						
					<table class="global_table" cellpadding="0" cellspacing="0">
						<tr>
							<th width="20%">值：</th>
							<td width="80%">
								<input class="inp" type="text" name="code" />
							</td>						
						</tr>
						<tr>
							<th>名称：</th>
							<td>
								<input class="inp" type="text" name="name" />
							</td>						
						</tr>
						<tr>
							<th>备注	</th>
							<td>
								<textarea rows="10" cols="60" name="remark"></textarea>
							</td>
						</tr>					
					</table>
				</div>
				
				<center>					
					<input type="submit" value="创建" class="global_btn"/>
					<input type="button" value="取消" class="global_btn" onclick="cancelAddDictType();"/>
				</center>
			</div>
		</form>
	</body>

</html>
