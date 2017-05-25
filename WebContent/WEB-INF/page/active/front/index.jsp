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
    <script src="/themes/active/js/front/index.js"></script>
</head>
<body>
	<input type="hidden" value="${type}" id="type" >
	<!--导航-->
	<div class="swiper-container" id="bannerDiv" >
		<div class="swiper-wrapper" >
			<div class="swiper-slide" v-for="model in banners" ><a v-bind:href="model.url"><img v-bind:src="model.img" /></a></div>
		</div>
		<div class="swiper-pagination"></div>
	</div>
	<!--分类-->
<!-- 	<div class="activity-slide" id="categoryDiv" >
		<div class="slide-item" v-for="model in categorys" >
			<img v-bind:src="model.img" onclick="getByType('{{model.name}}')" /> 
			<p v-text="model.name"  onclick="getByType('{{model.name}}')" ></p>
		</div>
	</div> -->
	<!--活动-->
	<div class="list" id="activeDiv" >
		<div class="list-search">
			<select id="typeDiv" style="height: 26px;font-size: 16px;line-height: 26px;" onchange="getByType('')" ></select>
			<select id="addressDiv" style="height: 26px;font-size: 16px;line-height: 26px;margin-left: 10px;" onchange="getByType('')"  ></select>
		</div>
		<a href="/active/page/detail.html?id={{model.id}}" v-for="model in actives" >
			<div class="list-item">
				<div class="list-left">
					<img v-bind:src="model.topImg">
					<p class="end" v-text="model.status" ></p>
				</div>
				<div class="list-right">
					<h1 v-text="model.name" ></h1>
					<p v-html="'<lable></lable>时间：'+model.openTime"></p>
					<p v-html="'<lable></lable>地点：'+model.address"></p>
				<!-- 	<button class="list-rigth-button" v-html="'报名：'+model.num"></button> -->
				</div>
			</div>
		</a>
	</div>
	<!--list     end-->
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
	function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}
		$(function(){
			/*日历*/
			var opt = {  
		        theme: "android-ics light", 
		        display: 'modal', //显示方式  
		        lang: "zh",  
		        setText: '确定', //确认按钮名称
		        cancelText: "取消",  
		        dateFormat: 'yyyy-mm-dd', //返回结果格式化为年月格式  
		        dateOrder: 'yyyymmdd', //面板中日期排列格式
		        onBeforeShow: function (inst) {
		          }, 
		        headerText: function (valueText) { //自定义弹出框头部格式  
		            array = valueText.split('-');  
		            $("#text-val-one").val(array[0] + "-" + array[1] + "-" + array[2]);
		            return array[0] + "年" + array[1] + "月" + array[2] + "日";  
		        }  
		    };
			
		  $("#text-val").mobiscroll().date(opt); 

		})
	</script>
</body>
</html>