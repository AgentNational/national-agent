<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="HandheldFriendly" content="True">
    <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <script type="text/javascript" src="http://git.javams.com/na-source/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
        $(function(){
            alert('afdasf');
            $.ajax({
                type : "get",
                url : "${ctx}/weiXin/jssdkConfig",
                dataType : "json",
                async : false,
                data : {
                    "currentUrl" : location.href
                },
                success : function(data) {
                    alert('afdasf1');
                    console.log(data.signature);
                    wx.config({
                        debug : false,
                        appId : data.appId,
                        timestamp : data.timestamp,
                        nonceStr : data.nonceStr,
                        signature : data.signature,
                        jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
                            'onMenuShareAppMessage', 'onMenuShareQQ',
                            'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems',
                            'onMenuShareQZone', 'hideAllNonBaseMenuItem',
                            'showAllNonBaseMenuItem', 'translateVoice', 'startRecord',
                            'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice',
                            'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage',
                            'previewImage', 'uploadImage', 'downloadImage',
                            'getNetworkType', 'openLocation', 'getLocation',
                            'hideOptionMenu', 'showOptionMenu', 'closeWindow',
                            'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
                            'addCard', 'chooseCard', 'openCard' ]
                    });
                    wx.ready(function() {
                        wx.chooseWXPay({
                            timestamp: "${timeStamp}", // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                            nonceStr:"${nonceStr}", // 支付签名随机串，不长于 32 位
                            package: "${packageValue}", // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                            signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                            paySign: "${sign}", // 支付签名
                            success: function (res) {
                                window.location.href = "${ctx}/wxpay/success?outerTradeNo=${outerTradeNo}";
                            },
                            cancel:function(res){
                                alert(res);
                                window.location.href = "${cancelUrl}";
                            }
                        })

                    });
                }
            });

        })
    </script>
    <title>订单-支付</title>
</head>
<body>
</body>
</html>
