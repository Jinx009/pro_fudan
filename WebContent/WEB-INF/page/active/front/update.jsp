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
    <script src="/themes/active/js/front/info.js"></script>
    <style type="text/css">
    select{
		-webkit-appearance:none;appearance:none;outline:none;-webkit-tap-highlight-color:rgba(0,0,0,0);
	}
    </style>
</head>
<body>
	<h1 class="title"><span></span>完善信息</h1>
	<ul class="inform">
		<li>
			<label>真实姓名</label>
			<input type="text" placeholder="请输入真实姓名" v-bind:value="user.name"  id="name" />
		</li>
		<li>
			<label>性别</label>
			<select id="sex" >
				<option value="{{user.sex}}" v-text="user.sex" ></option>
				<option value="女" v-if="'男'===user.sex" >女</option>
				<option value="男" v-if="'女'===user.sex" >男</option>
			</select>
		</li>
		<li style="display: none;" >
			<label>所在地区</label>
			<select id="fatherAddress" onchange="getAddress();" ></select>
		</li>
		<li>
			<label>所在校区</label>
			<select id="address" ><option>请选择</option></select>
		</li>
	</ul>
	<h1 class="title"><span></span>联系方式</h1>
	<ul class="inform">
		<li>
			<label>手机号码</label>
			<input type="text" v-bind:value="user.mobilePhone" style="color: gray;"  readonly="readonly" placeholder="请输入手机号码" />
		</li>
		<li>
			<label>工号</label>
			<input type="text" placeholder="请输入您的工号" id="email" v-bind:value="user.email"  />
		</li>
		<li>
			<label>单位</label>
			<input type="text" placeholder="请输入您的单位" id="openId" v-bind:value="user.openId"  />
		</li>
	</ul>
	<a class="btn" onclick="update();" >保存</a>
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
</body>
</html>