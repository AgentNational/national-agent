<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>
<script type="text/javascript">
/* $(function(){
    //全选和全不选
    $('#selectAll').bind('click',function(){
        alert(this.checked);//选中的时候返回true
        if(this.checked){    //全选
            $("input[name='roleIds']").each(function(){
                    $(this).prop('checked',true);
                })
        }else{    //全不选
            $("input[name='roleIds']").each(function(){
                $(this).prop('checked',false);
            })
        }
 }); */
 function checkAll(){//全选，全不选
	var status = $('#selectAll');
 	//alert(status.is(':checked'));
 	if(status.is(':checked')){
 		$("input[name='roleIds']").attr("checked",true);
 	}else{
 		$("input[name='roleIds']").attr("checked",false);
 	}
 }
 /*初始化该菜单已经拥有的角色*/
 $(function(){
	 var checkBoxs = document.getElementsByName("roleIds");
	 var roleIds = "${roleIds}";
	 var roleArray = roleIds.split(",");
	 //遍历数组
	 for(var i=0;i <checkBoxs.length;i++){ 
		 $.each(roleArray,function(j,roleId) {
			 if(checkBoxs[i].value == roleId){
				 checkBoxs[i].checked = true;
			 }
	     });  
	 }
 });
 
 function mySubmit()
 {	
 	$("#form1").attr("action", "${ctx}/appMenu/appMenuAuthorize.action");
 	$("#form1").submit();
 }
</script>
</head>
<body>
	<div class="search_tit"><h2>app菜单授权</h2></div>
	<form action="" method="post" id="form1">
	<input type="hidden" name="menuId" value="${menuId }" />
	<input type="hidden" name="appRoleIds" value="${appRoleIds }"/>
	<c:if test="${appRoles != null && appRoles.size() > 0}">
		
		<table cellpadding="0" cellspacing="1" class="global_tableresult">
			<tr>
				<th width="20" align="center"><input type="checkbox" id="selectAll" onclick="checkAll()"/></th>
				<th width="25%">角色ID</th>
				<th width="25%">角色名称</th>
				<th width="30%">角色描述</th>
			</tr>
			<c:forEach items="${appRoles}" var="appRole">
				<tr>
					<td>
						<input type="checkbox" name="roleIds" value="${appRole.id }"/>
					</td>
					<td>
						${appRole.id }
					</td>
					<td>
						${appRole.roleName }
					</td>
					<td>
						${appRole.remark }
					</td>
				<tr>
			</c:forEach>
		</table>
	</c:if>
	</form>
	<c:if test="${appRoles eq null or appRoles.size() == 0}">
			<br>
			<center>
			无符合条件的记录
			</center>
	</c:if>

	<center>
		<br>
		<input type="button" class="global_btn" value="提交" onclick="mySubmit()" />
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>