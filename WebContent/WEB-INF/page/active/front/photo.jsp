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
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/Swiper/3.1.7/css/swiper.min.css">
    <script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/Swiper/3.1.7/js/swiper.jquery.min.js"></script>
    <script src="/themes/active/js/front/mobiscroll.custom-2.6.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/themes/active/css/front/reset.css">
    <link rel="stylesheet" type="text/css" href="/themes/active/css/front/mobiscroll.custom-2.6.2.min.css">
    <link rel="stylesheet" type="text/css" href="/themes/active/css/front/jgy.css">
    <script src="/themes/active/js/front/photo.js"></script>
    <style type="text/css">
    .box {
	 	text-align:center;
	 	width:100%;
	 	height: 100%;
	 	vertical-align: middle;
	 	position: absolute;
	 	top:0px;
	 	left: 0px;
	}
	.box img {
	 	width:100%;
	 	left: 0px;
	}
    </style>
</head>
<body>
<div id="main">
	<div class="search">
		<input placeholder="请输入相关活动信息" id="key"  />
		<button onclick="getData();" >确定</button>
	</div>
	<ul class="photo-album-list" id="photo" >
	</ul>
	<footer>
		<dl>
			<dt><a onclick="goIndex();"  ><img src="/themes/active/img/home.png" /></a></dt>
			<dd>活动</dd>
		</dl>
		<dl>
			<dt><a href="/active/page/photo.html" ><img src="/themes/active/img/ablum.png" /></a></dt>
			<dd>相册</dd>
		</dl>
		<dl>
			<dt><a href="/active/page/feedback.html" ><img src="/themes/active/img/comments.png" /></a></dt>
			<dd>反馈</dd>
		</dl>
		<dl>
			<dt><a href="/active/page/my.html" ><img src="/themes/active/img/data.png" /></a></dt>
			<dd>我的</dd>
		</dl>
	</footer>
</div>	
<div id="photo_"  style="display: none;" class="box" >
	<img alt="" src=""  id="img" onclick="hide();" >
</div> 
</body>
</html>