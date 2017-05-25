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
    <script src="/themes/active/js/front/detail.js"></script>
</head>
<body>
	<input type="hidden" value="${id }" id="id" >
	<div class="activity-banner"><img v-bind:src="data.topImg" /></div>
	<div class="activity-title">
		<h1 v-text="data.name" ></h1>
		<p><b>报名时间:</b><span v-text="data.startTime" ></span><span>~</span><span v-text="data.endTime"></span></p>
	</div>
	<ul class="inform activity-inform">
		<li><p v-text="'时间：'+data.openTime+'——'+data.closeTime" ></p></li>
		<li><p v-text="'地址：'+data.address+''+data.detailAddress"></p></li>
		<li><p>类型：<span v-text="data.type" ></span></p></li>
		<li v-if="'3'===data.detailImg" ><p style="color: red;" onclick="saveLike('{{data.id}}')" >点击收藏</p></li>
		<li v-if="'2'===data.detailImg" ><p style="color: red;" onclick="cancleLike('{{data.id}}')" >取消收藏</p></li>
	</ul>
	<h1 class="title activity-detail-title">活动详情</h1>
	<div class="activity-detail-content">
		<div v-for="model in materials" >
			<p v-if="2===model.type" v-text="model.content" class="detail-p" ></p>
			<img alt="" v-if="1===model.type" v-bind:src="model.content" class="detail-img"  >
		</div>
	</div>
	<a class="btn" v-if="'1'===data.addUser" onclick="cancle('{{data.id}}')" >取消报名</a>
	<a class="btn" v-if="'0'===data.addUser" onclick="save('{{data.id}}')" >马上报名</a>
	<a class="btn" v-if="'2'===data.addUser" >报名未开始</a>
	<a class="btn" v-if="'3'===data.addUser" >报名已结束</a>
	<div class="blank"></div>
	<footer>
		<dl>
			<dt><a onclick="goIndex();" ><img src="/themes/active/img/home.png" /></a></dt>
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