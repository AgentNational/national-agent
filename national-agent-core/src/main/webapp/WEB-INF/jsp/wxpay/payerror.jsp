<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="HandheldFriendly" content="True">
    <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" type="text/css" href="https://cache.cardinfo.com.cn/wx-resource/css/public/base.css">
    <style type="text/css">
        body,html{
            height: 100%;
            width: 100%;
            background-color: #F2F2F2;
        }
        .main{
            position: relative;
            height: 100%;
            width: 100%;
            max-width: 800px;
            min-height: 480px;
            margin: auto;
        }
        .error-img{
            position: absolute;
            top: 1rem;
            left: 50%;
            width: 14rem;
            height: 11rem;
            margin-left: -7rem;
            background-image: url(https://cache.cardinfo.com.cn/wx-resource/image/public/img_kaixiaocha.png);
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }
        .error-p{
            position: absolute;
            top: 14rem;
            left: 0;
            width: 100%;
            height: 1rem;
            color: #A0A0A0;
            text-align: center;
            font-size: 0.92rem;
        }


    </style>
</head>
<body>
<div class="main">
    <p class="error-img"></p>
    <p class="error-p">支付异常：${returnMsg }</p>

</div>
</body>
</html>
