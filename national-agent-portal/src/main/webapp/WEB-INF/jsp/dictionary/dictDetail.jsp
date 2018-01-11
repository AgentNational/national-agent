<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>
<head>
<script type="text/javascript">
	function toUpdateDict(dictTypeId,dictId) {
		var url = "${ctx}/toDictionaryUpdate.action?dictTypeId="+dictTypeId+"&dictId="+dictId;
		window.parent.parent.refresh("popframe-1", url);
		window.parent.parent.showDialog("popdiv-1");
	}
	function deleteDict(dictTypeId, id) {
		//alert("deleteDict["+dictTypeId+","+dictId+"]");
		location = "/boss/deleteDict.action?dictionary.id=" + id;
	}
	function deleteDictConfirm(dictTypeId, dictId) {
		$("#dialog").dialog("destroy");
		$("#dialog-confirm-info").html("确认删除数据字典?");
		$("#dialog-confirm").dialog({
			resizable : false,
			height : 140,
			modal : true,

			buttons : {
				"确定" : function() {
					$(this).dialog("close");
					deleteDict(dictTypeId, dictId);
				},
				"取消" : function() {
					$(this).dialog("close");
				}
			}
		});
	}
	function addDict(dictTypeId) {
		var url="${ctx}/toDictionaryAdd.action?dictTypeId=" + dictTypeId;
		window.parent.parent.refresh("popframe-1", url);
		window.parent.parent.showDialog("popdiv-1");
	}
</script>
</head>

<body>
<input type="hidden" id="dictTypeId" name="dictTypeId" value="${dictTypeId }" />
	<c:if test="${dictionaryList != null && dictionaryList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="9%">字典类型值</th>
				<th width="15%">字典值</th>
				<th width="15%">字典名称</th>
				<th width="15%">状态</th>
				<th width="15%">顺序</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${dictionaryList}" var="dictionary">
				<tr>
					<td>${dictionary.dictTypeId }</td>
					<td>${dictionary.key }</td>
					<td>${dictionary.value }</td>
					<td>${dictionary.status }</td>
					<td>${dictionary.order }</td>
					<td><a
						href="javascript:toUpdateDict('${dictionary.dictTypeId }','${dictionary.key}')">修改</a>
						&nbsp;
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${dictionaryList eq null or dictionaryList.size() == 0}">
			无符合条件的记录
	</c:if>
	<br>
	<br>
	<div align="left">
			<a href="javascript:addDict('${dictTypeId}');">添加字典</a>
	</div>
	<script type="text/javascript">
		//分页
		$(function() {
			initPage('${page.currentPage}', '${page.totalResult}',
					'${page.totalPage}', '${page.entityOrField}', parent.document
							.getElementById("searchFrom"));
		});
	</script>
</body>
</html>
