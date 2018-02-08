<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="HandheldFriendly" content="True">
    <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
</head>
<title>支付</title>
<body>
<script type="text/javascript" src="https://cache.cardinfo.com.cn/wx-resource/js/public/jquery-1.11.1.min.js"></script>
<div data-role="page">
    <div data-role="header" data-theme="e">
        <h1>业务付款</h1>
    </div>

    <div data-role="content">
        <form method="post" action="${ctx }/wxpay/pay">
            <div data-role="fieldcontain">
                支付金额：<input type="text" name="amount" id="amount"><br>
                openId: <input type="text" name="openId" id="openId" value="${openId}"><br>
                业务订单号:<input type="text" name="outOrderId" id="outOrderId" value="1234"><br>
                支付商户号： <input type="text" name="partner" id="partner" value="8623503565"><br>
                异步通知地址：<input type="text" name="notifyURL" id="notifyURL" value="http://10.10.129.76:8080/wxcustomer-front/easyBuy/publicPayNotify"><br>
                同步跳转地址<input type="text" name="redirectURL" id="redirectURL" value="http://10.10.129.75:8083/wx-resource/html/pageNew/payment.html"><br>
                渠道：<input type="text" name="returnParam" id="returnParam" value="SIMCARD"><br>
                <input type="submit" value="支付">
            </div>
        </form>
    </div>
</div>
</body>
</html>
