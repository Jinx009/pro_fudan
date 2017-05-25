<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <title>个人页</title>
    <script src="/themes/js/jquery.js" ></script>
    <script src="/themes/js/common.js" ></script>
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/Swiper/3.1.7/css/swiper.min.css">
    <script type="text/javascript" src="http://cdn.bootcss.com/Swiper/3.1.7/js/swiper.jquery.min.js"></script>
    <script src="/themes/js/common.js" ></script>
    <script src="/themes/js/vue.min.js" ></script>
     <link rel="stylesheet" type="text/css" href="/themes/css/buildDate.css">
    <script>
    $(function(){
    	var menuStatus = 1;
        $('.menu-btn').on('click',function(){
            toggleMenu();
        });
        function toggleMenu(){
            if(1==menuStatus){
                $('.menu').show();
                menuStatus = 2;
            }else{
                $('.menu').hide();
                menuStatus = 1;
            }
        }
    	var userId = $('#userId').val();
    	var id = $('#id').val();
    	 $.ajax({
    		url:'/data/user/byId.html?id='+userId,
    		type:'GET',
    		dataType:'JSON',
    		success:function(res){
    			if(res!=null&&res.data!=null){
    				res.data.imgList = new Array();
    				res.data.imgList.push('/themes/data/'+res.data.pic1);
    				if(res.data.pic2!='null'){
    					res.data.imgList.push('/themes/data/'+res.data.pic2);
    				}
    				if(res.data.pic3!='null'){
    					res.data.imgList.push('/themes/data/'+res.data.pic3);
    				}
    				if(res.data.tag!=null&&res.data.tag!=''){
    					var tags = res.data.tag.split('，');
    					if(tags!=null&&tags.length>0){
    						res.data.tag = '';
    						for(var i = 0;i<tags.length;i++){
    							res.data.tag += '<span>'+tags[i]+'</span>';
    						}
    					}
    				}
    			}
    			new Vue({
   				  el: 'body',
   				  data:{model:res.data}
    			})
    		}
    	}) 
  /*   	var res = {};
    	res.data = {};
    	res.data.likes = '123';
    	res.data.sex = '男';
    	res.data.address = '上海宝山';
    	res.data.num = '001';
    	res.data.realName = 'Jinx';
    	res.data.age = '22';
    	res.data.tag = '<span>旅游</span><span>会做饭</span><span>暖男</span>';
    	res.data.jobInfo = '程序员';
    	res.data.edu = '本科';
    	res.data.info = '吾虽浪迹天涯，却未迷失本心。';
    	res.data.imgList = ['/themes/img/banner.png','/themes/img/banner.png'];
    	new Vue({
			el: 'body',
			data:{model:res.data}
		}) */
    })
    </script>
</head>
<body>
	<input type="hidden" id="id" value="${id }" >
	<input type="hidden" id="userId" value="${userId }" >
	<header>
       <a href="javascript:;" class="menu-btn"><img src="/themes/img/321.png"/></a>
        <span class="center">个人主页</span>
         <a href="/select.html?userId=${userId }" class="right"><img src="/themes/img/home.png" /></a>
    </header>

	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div v-for="item in model.imgList" class="swiper-slide"><a href="javascript:;"><img v-bind:src="item" /></a></div>
		</div>
		<div class="swiper-pagination"></div>
	</div>

	<div class="person-inform-content">
		<div class="list-col">
			<p class="inform">
				<span v-text="model.realName" ></span>
				<img v-if="'男'==model.sex" src="/themes/img/male_icon.png" />
				<img v-if="'女'==model.sex" src="/themes/img/female_icon.png" />
				<span v-text="model.sex" ></span>
				<span  class="num">编号</span>
				<span v-text="model.num" ></span>
			</p>
			<p class="age">
				<b v-text="model.age+'岁'" ></b>
			</p>
		</div>
		<div class="list-col inform-col">
			<p class="left">户籍：</p>
			<p class="right" v-text="model.address" ></p>
		</div>
		<div class="list-col inform-col">
			<p class="left">职业：</p>
			<p class="right" v-text="model.jobInfo" ></p>
		</div>
		<div class="list-col inform-col">
			<p class="left">兴趣爱好：</p>
			<p class="right" v-text="model.likes" ></p>
		</div>
		<div class="list-col inform-col">
			<p class="left">学历：</p>
			<p class="right" v-text="model.edu" ></p>
		</div>
		<div class="list-col">
			<p class="inform">我的标签</p>
			<p class="tag" v-html="model.tag" ></p>
		</div>
		<div class="person-introduction">
			<p class="title"><img src="/themes/img/personal_icon.png"/><span>个人简介</span></p>
			<p class="text" v-text="model.info" ></p>
		</div>
		   <nav class="menu">
    <div class="menu-logo">
        <img src="/themes/data/${sessionScope.pic}" onclick="javascript:location.href='/person.html?id=${userId}&userId=${userId }';"/>
        <p>${sessionScope.name}</p>
    </div>
	     <ul>
	        <li><a href="javascript:location.href='/list.html?userId=${userId }';"><img src="/themes/img/love_icon.png"/>我心仪的</a></li>
	        <li><a href="javascript:location.href='/listGo.html?userId=${userId }';"><img src="/themes/img/123.png"/>互相心仪</a></li>
	        <li><a href="javascript:location.href='/person.html?userId=${userId }&id=${userId }';"><img src="/themes/img/person.png"/>个人主页</a></li>
	    	<li><a href="javascript:location.href='/select.html?userId=${userId }&id=${userId }';"><img src="/themes/img/home.png"/>返回筛选</a></li>
	    </ul>
	</nav>
	</div>
	<script type="text/javascript">
		$(function(){
			var mySwiper = new Swiper('.swiper-container', {
				  loop: true, //循环播放
				  autoplay: 5000,
				  lazyLoading: true, //延迟加载图片
				  lazyLoadingInPrevNext: true, //延迟加载当前图片之前和之后一张图片
				  lazyLoadingOnTransitionStart: true, //过渡一开始就加载
				  pagination: '.swiper-pagination', //导航分页
				  paginationClickable: true //导航点击切换
			});
		})
	</script>
</body>
</html>