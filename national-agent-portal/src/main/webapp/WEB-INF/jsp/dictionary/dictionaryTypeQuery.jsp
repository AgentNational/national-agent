<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
	<script type="text/javascript">
		function refresh(frameId,url){
			$("#"+frameId).attr("src",url);
		}
		function cacheRefresh(){
			$.post( "${ctx}/excuteAllDict.action",null,
			  		function (data){			  			
			  			if("ok"==$.trim(data)){
			  				dialogMessage("缓存已刷新！");
			  				refreshRes();
			  			}else{
			  				dialogMessage("刷新失败["+data+"]");
			  			}
			  		}
			  	);
		}
		function cacheRefreshConfirm(){
			$( "#dialog").dialog( "destroy" );	
			$( "#dialog-confirm-info").html("刷新缓存,请确认");
			$( "#dialog-confirm" ).dialog({
				resizable: false,
				height:140,
				modal: true,
				
				buttons: { "确定": function() {
						   $( this ).dialog( "close" );
						   cacheRefresh();
						 },
						   "取消": function() { $( this ).dialog( "close" );}}
			});
		}
		function addDictType(){
			refresh("dictFrame","");
			refresh("dictTypeFrame","toDictionaryTypeAdd.action");
			$("#tabs ul").idTabs("tab_dicttype");
		}
		function showDictDiv(){
			//alert("showDictDiv");
		}
		function showDictTypeDiv(){
			//alert("showDictTypeDiv");
		}
		$(document).ready(function(){
			$("#form1").submit();
			$("#tabs").tabs();	
		});
		function deleteDictTypeConfirm(dictTypeId){
			$( "#dialog").dialog( "destroy" );	
			$( "#dialog-confirm-info").html("确认是否删除该信息?");
			$( "#dialog-confirm" ).dialog({
				resizable: false,
				height:140,
				modal: true,
				
				buttons: { "确定": function() {
						   $( this ).dialog( "close" );
						   deleteDictType(dictTypeId);
						 },
						   "取消": function() { $( this ).dialog( "close" );}}
			});
			}
		function deleteDictType(dictTypeId){	
			  location = "${ctx}/deleteDictType.action?dictTypeId="+dictTypeId;
		}
		function refreshRes(){
			$("#form1").submit();
		}
		function closeDictTypeAddWin(){
			refresh("dictTypeFrame","");
		}
		function addDict(dictTypeId){
			$("#tabs ul").idTabs("tab_dict");
			refresh("dictFrame","<%=request.getContextPath()%>/jsp/dictionary/dictionaryAdd.jsp?dictTypeId="+dictTypeId);
			refresh("dictTypeFrame","");
		}
		function closeDictAddWin(){
			refresh("dictFrame","");
		}
		function refreshDetail1(dictTypeId){
			//alert(dictTypeId);
			}
	</script>
</head>
	
<body>

<br>

<div class="container">

	<div style="float:left;width:30%;height:590px;border:1px solid #ccc;padding:8px">
	
		<form id="form1" action="dictTypeQuery.action" method="get" target="queryResult">			
			<div style="background-color:#eee;padding:5px;margin-bottom:5px;">
				字典标识： 
				<input type="text" name="code" class="width130 inp" />
				<input type="submit" value=" 查 询 " class="button_s" style="float:right;margin-right:5px;"/>
			</div>				
			
			<iframe name="queryResult" src="" width="98%" height="520px" scrolling="no" frameborder="0" noresize></iframe>	
			
			<div>
				<input type="button" class="global_btn" value="刷新缓存"  onclick="javascript:cacheRefreshConfirm();" />
				&nbsp;
				<input type="submit" class="global_btn" value="新增类型 " onclick="javascript:addDictType();"/>
			</div>	
		</form>	
	</div>
	
	<div style="float:right;background-color:#eee; width:68%;height:560px;">	
		<div id="tabs">
			<ul>
				<li><a href="#tab_dict">字典管理</a></li>
				<li><a href="#tab_dicttype">字典类型管理</a></li>
			</ul>
			<div id="tab_dict" style="padding:0px;">
				<iframe id="dictFrame" name="dictFrame" src="" width="100%" height="580px;" scrolling="no" frameborder="0" noresize >
				</iframe>
			</div>
			<div id="tab_dicttype" style="padding:0px;">
				<iframe id="dictTypeFrame" name="dictTypeFrame" src="" width="100%" height="580px;" scrolling="no" frameborder="0" noresize >
				</iframe>
			</div>
		</div>	
	</div>
</div>
<br>

</body>

</html>
