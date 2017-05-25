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
    <script src="/themes/active/js/front/my.js"></script>
    <script>
    function openUrl(url){
    	location.href = url;
    }
    </script>
</head>
<body>
	<header class="inform-banner">
		<div><img src="/themes/active/img/logo.png" /></div>
	</header>
	<h1 class="title"><span></span>基本信息</h1>
	<ul class="inform">
		<li><label>真实姓名</label><p v-text="user.name" ></p></li>
		<li><label>工号</label><p v-text="user.email" ></p></li>
		<li><label>单位</label><p v-text="user.openId" ></p></li>
<!-- 		<li><label>所在地区</label><p v-text="user.address.fatherName" ></p></li> -->
		<li><label>所在校区</label><p v-text="user.address.name" ></p></li>
		<!-- <li><label>职业性质</label><p></p></li>
		<li><label>出生日期</label><p>1993-07-12</p></li> -->
	</ul>
	<ul class="inform inform-click">
		<li onclick="openUrl('/active/page/update.html')" ><b><img src="/themes/active/img/paper.png" /></b><p>个人资料</p></li>
		<li onclick="openUrl('/active/page/index.html')" ><b><img src="/themes/active/img/Shape.png" /></b><p>活动中心</p></li>
		<li onclick="openUrl('/active/page/like.html')" ><b><img src="/themes/active/img/star.png" /></b><p>我的收藏</p></li>
		<li onclick="openUrl('/active/page/join.html')" ><b><img src="/themes/active/img/label.png" /></b><p>我的参与</p></li>
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
</body>
</html>