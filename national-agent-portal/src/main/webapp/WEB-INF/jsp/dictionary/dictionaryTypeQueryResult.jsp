<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>

<html>

	<script type="text/javascript">
		function changeTheFrameSrcValue(frameId, questURL) {
			window.parent.refresh(frameId, questURL);
		}
		function showDetail(dictTypeId) {
			var dictURL="dictDetail.action?dictTypeId="+dictTypeId;
			var dictTypeURL="findDictTypeDetail.action?dictTypeId="+dictTypeId;
			
			changeTheFrameSrcValue("dictFrame",dictURL);
			changeTheFrameSrcValue("dictTypeFrame",dictTypeURL);
		}
		
		function deleteDictType(dictTypeId) {
			window.parent.deleteDictTypeConfirm(dictTypeId);
		}
	</script>
	
	<body>
		<c:if test="${dictionaryTypeList != null && dictionaryTypeList.size() > 0}">
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="9%">字典标识</th>
				<th width="15%">字典名称</th>
				<th width="10%">操作</th>
			</tr>
			<c:forEach items="${dictionaryTypeList}" var="dictionaryType">
				<tr>
					<%-- <td>${bill.processInstanceId }</td> --%>
					<td>${dictionaryType.code }</td>
					<td>${dictionaryType.name }</td>
					<td>
						<a href="javascript:showDetail('${dictionaryType.code }')">详细</a>
								&nbsp;
						<a href="javascript:deleteDictType('${dictionaryType.code }');">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="page" align="right" style="margin: 20px 20px;"></div>
	</c:if>
	<c:if test="${dictionaryTypeList eq null or dictionaryTypeList.size() == 0}">
			无符合条件的记录
	</c:if>

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
