<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
<%@ include file="/WEB-INF/jsp/include/commons-include.jsp"%>
<html>
<head>

<script type="text/javascript">
	//根据useType的值决定是使用url还是使用className和properties.
	$(function(){
		var useType = "${appAdvertiseInfo.useType}";
		$("#useTypeSelect").val(useType);
		$("#useType").val(useType);

		if($("#useTypeSelect").val()== null ||$("#useTypeSelect").val()== '' ){
			$("#linkUrlTr").hide();
			$("#classNameTr").hide();
			$("#propertiesTr").hide();
		}

		if($("#useTypeSelect").val() == 'H5'){
			$("#useType").val('H5');
			$("#classNameTr").hide();
			$("#propertiesTr").hide();
		}

		if($("#useTypeSelect").val() == 'CLASS'){
			$("#linkUrlTr").hide();
		}

		$("#useTypeSelect").change(function(){
			if($("#useTypeSelect").val() == 'H5'){
				$("#useType").val('H5');
				$("#linkUrlTr").show();
				$("#classNameTr").hide();
				$("#propertiesTr").hide();
			}
			if($("#useTypeSelect").val() == 'CLASS'){
				$("#useType").val('CLASS');
				$("#classNameTr").show();
				$("#propertiesTr").show();
				$("#linkUrlTr").hide();
			}
		});
	});

	//提交表单
	function mySubmit(){
		if(checkInput()){
			var info = "";
			$.ajax({
				type : 'POST',
				url:ctx+'/appAdvertise/checkAdvertiseIndexNo.action',
				data : $('#form1').serialize(),
				async : false,
				dataType : 'text',
				error : function(request) {
					dialogMessage("操作失败！");
				},
				success : function(data) {
					if(data != ""){
						info = "此广告类型序号已存在，";
					}
					$( "#dialog").dialog( "destroy" );
					$( "#dialog-confirm-info").html(info+"确认提交该信息吗?");
					$( "#dialog-confirm" ).dialog({	resizable: false,height:140,modal: true,buttons:
						 { "确定": function() {
									$( this ).dialog( "close" );
									$("#form1").attr("action", "${ctx}/appAdvertise/appAdvertiseEditor.action");
					            	$("#form1").submit();
						   		   },
						   "取消": function() {$( this ).dialog( "close" );}
						 }
					});
				}
			});
		}
	}

	//校验必填项
	function checkInput() {
		var num = /^(0|[1-9][0-9]*)$/;


		if($("#advertiseType").find("option:selected").val() == ''){
			dialogMessage('广告类型不能为空');
			return false;
		}
		if($("#indexNo").val().trim() == ''){
			dialogMessage('序号不能为空');
			return false;
		}else{
			if(!num.test($("#indexNo").val().trim())){
				dialogMessage('序号格式不正确');
				return false;
			}
		}
		/* if($("#imagesUrlName").val().trim() == ''){
			dialogMessage('图片地址不能为空');
			return false;
		} */
		if($("#status").find("option:selected").val() == ''){
			dialogMessage('状态不能为空');
			return false;
		}
		if($("#description").val().trim() == ''){
			dialogMessage('描述不能为空');
			return false;
		}
		return true;
	}
</script>

</head>
<body>
	<div class="detail_tit"><h2>广告编辑</h2></div>
	<form id="form1" action="" method="post"  encType="multipart/form-data">
		<input type="hidden" name="id" value="${appAdvertiseInfo.id }">
		<table cellpadding="0" cellspacing="0" class="global_table">
			<tr>
				<th width="30%">广告类型：&nbsp;<font color="red">*</font></th>
				<%-- <td width="70%">
					<dict:select id="advertiseType" name="advertiseType" dictKeys="${appAdvertiseInfo.advertiseType }"
						style="width: 225px;" dictTypeCode="AppAdvertiseType" nullOption="true"></dict:select>
				</td> --%>
				<td>
					<select id="advertiseType"  name="advertiseType" value="${appAdvertiseInfo.advertiseType }">
						<option value="START_PAGE">启动页</option>
						<option value="HOME_PAGE">主页</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>序号：</th>
				<td>
					<input type="text" id="indexNo" name="indexNo" value="${appAdvertiseInfo.indexNo }" class="inp width220" />
				</td>
			</tr>
			<!-- <tr>
				<th id="imagesUrlName">图片地址：&nbsp;<font color="red">*</font></th>
				<td>
					<input type="text" id="imagesUrl" name="imagesUrl" value="${appAdvertiseInfo.imagesUrl }" class="inp width600">
				</td>
			</tr>-->
			<tr>
				<th id="imagesUrlName" width="10%">图片：&nbsp;<font color="red">*</font></th>
				<td width="15%">
					<input type="file" name="file" id="file" class="inp width150"/>
				</td>
			</tr>
			<tr>
				<th>系统类型：&nbsp;<font color="red">*</font></th>
				<%-- <td>
					<dict:select id="appType" name="appType" dictKeys="${appAdvertiseInfo.appType }"
					    style="width:225px;" dictTypeCode="AppClientType" nullOption="true"></dict:select>
				</td> --%>
				<td>
					<select id="appType" name="appType" value="${appAdvertiseInfo.appType }">
						<option value="IOS">IOS</option>
						<option value="Android">Android</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>使用方式：&nbsp;<font color="red">*</font></th>
				<td>
					<select id="useTypeSelect">
						<option value="H5">H5</option>
						<option value="CLASS">CLASS</option>
					</select>
					<input id="useType" type="hidden" name="useType" value="${appAdvertiseInfo.useType }">
				</td>
			</tr>
			<tr id="linkUrlTr">
				<th>链接地址：&nbsp;</th>
				<td>
					<input type="text" id="version" name="linkUrl" value="${appAdvertiseInfo.linkUrl }" class="inp width600" />
				</td>
			</tr>
			<tr id="classNameTr">
				<th>类名：&nbsp;</th>
				<td>
					<input type="text" id="className" name="className" value="${appAdvertiseInfo.className }" class="inp width200" />
				</td>
			</tr>
			<tr id="propertiesTr">
				<th>属性名：&nbsp;</th>
				<td>
					<input type="text" id="className" name="properties" value="${appAdvertiseInfo.properties }" class="inp width600"/>
					提示：有多个属性时，输入格式如：属性1,属性2,属性3
				</td>
			</tr>
			<tr>
				<th>状态：&nbsp;<font color="red">*</font></th>
				<!-- <td>
					<dict:select id="status" name="status" dictKeys="${appAdvertiseInfo.status }"
						style="width: 225px;" dictTypeCode="AbleStatus" nullOption="true"></dict:select>
				</td> -->
				<td>
					<select id="status" name="status" value="${appAdvertiseInfo.status }">
						<option value="ENABLE">可用</option>
						<option value="DISABLE">不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>描述：&nbsp;<font color="red">*</font></th>
				<td>
					<textarea rows="3" id="description" name="description" style="width:500px" >${appAdvertiseInfo.description }</textarea>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<center>
		<input type="button" class="global_btn" value="提交" onclick="mySubmit();" />
		<input type="button" class="global_btn" value="返回" onclick="location.href='javascript:history.go(-1);'" />
	</center>
</body>
</html>