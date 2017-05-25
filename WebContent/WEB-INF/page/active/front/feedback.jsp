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
    <script src="/themes/active/js/front/feedback.js"></script>
</head>
<body>
		<nav class="feed-back-nav">
		<ul>
			<li class="actived" >我的反馈<span></span></li>
			<li>我要反馈<span ></span></li>
		</ul>
	</nav>
	<div class="feed-back-content">
		<div  id="list" >
			<textarea v-for="model in data" v-text="model.content" style="min-height:1.1rem;" readonly="readonly" ></textarea>
		</div>
		<div style="display: none">
			<textarea placeholder="请输入您的反馈内容"  style="min-height:1.1rem;" id="text" ></textarea>
		</div>
	</div>
	<a class="btn" style="display: none" onclick="add()" >发送</a>
	<div class="blank"></div>
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
	<script type="text/javascript">
		$(function(){
			$(".feed-back-nav ul li").on("click",function(){
				var index = $(this).index();
				if(index>0){
					$('.btn').show();
				}else{
					$('.btn').hide();
				}
				$(this).addClass('actived').siblings().removeClass('actived');
				$(".feed-back-content div").hide();
				$(".feed-back-content div").eq(index).show();
			});
		})
	</script>
</body>
</html>