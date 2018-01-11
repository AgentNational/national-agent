<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
	 	$(document).ready(function() {
 	 		var result = ${uploadResult};
 	 		console.log(result);
 	 		if(typeof(result) == "undefined"){
 	 			
 	 		}else{
 	 			if(result){
 	 	 			dialogMessage("上传成功！");
 	 	 		}else{
 	 	 			dialogMessage("上传失败！");
 	 	 		}
 	 		}
		});
	</script>
</head>
<body>
	<div class="search_tit"><h2>信用卡客户数据导入</h2></div>
	<form action="upload.action" method="post"  encType="multipart/form-data" >
		<input type="hidden" name="type" value="CREDIT_CARD">
		<table class="global_table" cellpadding="0" cellspacing="0">
			<tr>
				<th width="10%">文件：</th>
				<td width="15%">
					<select name="creditCardType" >
						<option value="XINGYE">兴业银行</option>
						<option value="PUFA">浦发银行</option>
						<option value="PINGAN">平安银行</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="10%">文件：</th>
				<td width="15%">
					<input class="inp" type="file" name="file" class="inp width150"/>
				</td>
			</tr>
		</table>
		<br>
		<center>
			<input type="submit"  class="global_btn" value="提交" />
			<input type="reset" class="global_btn" value="重 置" />
		</center>
	</form>
	<a id="downloadModel" href="download.action?type=CREDIT_CARD">下载模板</a>
	<!-- <input type="radio" name="radiobutton" value="CREDIT_CARD" checked>信用卡 
	<input type="radio" name="radiobutton" value="LOAN"> 贷款 -->
	<!-- <br>
	<a href="downloadEmployee.action">下载文件</a>
	<br> -->
</body>
</html>