<%@ page language="java" pageEncoding="UTF-8" import="java.util.ResourceBundle"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/commons-include.jsp" %>

<html>
	<head>
	<style type="text/css">
		.rootclass{ border:none;text-align: center;}
	</style>
		<script type="text/javascript">
			//显示第一张图片
			function showFirstImg(path){
				path = path.split(",");
				var url = "${pageContext.request.contextPath}/file/showImg.action?path=" + path[0];
				$("#billImg").attr("src", url);
				$("#imgA").attr("href", url);
				//初始化第一张照片数量下表为0;
				$("#imgIndex").val(0);
			}

			//下一张
			function next(){
				var num = imgs();
				var path = $("#imgPath").val();
				path = path.split(",");

				var imgIndex = parseInt($("#imgIndex").val())+1;

				 if(parseInt(imgIndex) +1 >(parseInt(num))){
					 dialogMessage("这已经是最后一张了");
					return;
				}

				var url = "${pageContext.request.contextPath}/file/showImg.action?path=" + path[imgIndex];
				$("#billImg").attr("src", url);
				$("#imgA").attr("href", url);

				//赋值图片下标
				$("#imgIndex").val(imgIndex);
			}

			//上一张
			function previous(){
				var path = $("#imgPath").val();
				path = path.split(",");

				var imgIndex = parseInt($("#imgIndex").val()) - 1;

				 if(parseInt(imgIndex) < 0){
					 dialogMessage("这已经是第一张了");
					return;
				}

				var url = "${pageContext.request.contextPath}/file/showImg.action?path=" + path[imgIndex];
				$("#billImg").attr("src", url);
				$("#imgA").attr("href", url);

				//赋值图片下标
				$("#imgIndex").val(imgIndex);
			}
			$(function(){
				showFirstImg($("#imgPath").val());
				$("billImg").draggable();
				$(".rootclass").mousemove(function(e){
					var positionX=e.originalEvent.x||e.originalEvent.layerX||0;
					if(positionX<=$(this).width()/2){
						$(this).attr('title','点击查看上一张');
						$(this).parent().attr('href',$(this).attr('left'));
					}else{
						$(this).attr('title','点击查看下一张');
						$(this).parent().attr('href',$(this).attr('right'));
					}
				});
			});
			function imgs(){
				var num=0;
				var imgPath = $("#imgPath").val();
				num = imgPath.split(",").length;
				return num;
			}
		</script>
	</head>
	<body style="background-color: transparent;width: auto;">
		<div>
			<a href="#" id="imgA" target="_blank"><font color="red">查看原图</font></a>
		</div>
		<div id="showBillDiv" style="width: auto;">
		<input type="hidden" id="imgPath" value="${imgPath }">
		<input type="hidden" id="imgIndex">
			<a href="#">
				<img src="" alt="" id="billImg" left="javascript:previous();" right="javascript:next();" width="100%" height="100%" style="max-width:1000px;max-height: 600px;" class="rootclass"/>
			</a>
		</div>
	</body>
</html>
