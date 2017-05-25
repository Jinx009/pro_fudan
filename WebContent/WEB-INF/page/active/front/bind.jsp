<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>复旦教工活动</title>
	<meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <script src="/themes/js/vue.min.js"></script>
    <script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/themes/active/css/front/reset.css">
    <link rel="stylesheet" type="text/css" href="/themes/active/css/front/jgy.css">
    <script src="/themes/js/common.js"></script>
    <script src="/themes/active/js/front/bind.js?version=1.01"></script>
    <style type="text/css">
    a:visited, a:link, a:hover {
    	color: #7c65b0;
	}
	.yzm{
		line-height: .67rem;
	}
    </style>
</head>
<body>
	<input type="hidden" value="${url }" id="url" >
	<form class="bind-form">
		<div class="form-control">
			<label><img src="/themes/active/img/phone.png" /></label>
			<input placeholder="输入手机号码" type="tel"  id="mobilePhone" />
		</div>
		<div class="form-control">
			<label><img src="/themes/active/img/Pencil.png" /></label>
			<input placeholder="输入验证码" type="text" id="code" />
			<a class="yzm" id="yzm" onclick="getMsg();" >获取验证码</a>
		</div>
		<a class="btn" onclick="bind();" >登录</a>
	</form>
	<div class="blank"></div>
</body>
</html>