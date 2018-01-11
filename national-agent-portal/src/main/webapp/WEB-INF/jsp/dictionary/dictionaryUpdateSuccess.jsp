<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
	<head>
	<script type="text/javascript">
		$(document).ready(function(){
			window.parent.refreshRes();
		});
		function continueDictTypeAdd(){
				window.parent.addDictType();
			}
		function dictAdd(dictTypeId){
				window.parent.addDict(dictTypeId);
			}
		function closeAdd(){
				window.parent.closeDictTypeAddWin();
			}
	</script>
</head>

<body >
	<CENTER>
		<div style="position:relative;width:80%px;height:350px;;" class="popbox_cin">
			<div style="position:relative;top:30%;font-size:16px ;font-weight:bold;">
				数据字典修改成功
				<br/>
			</div>
			<%-- <div style="position:relative;top:35%;">
				<a href="javascript:continueDictTypeAdd();">继续添加数据字典类型</a>
				&nbsp;&nbsp;&nbsp;
				<a href="javascript:dictAdd('${dictTypeId }');">为其添加数据字典</a>
				&nbsp;&nbsp;&nbsp;
				<a href="javascript:closeAdd();">返回</a>
			</div> --%>
		</div>
	</CENTER>


</body>