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
    <title>心仪列表</title>
    <script src="/themes/js/jquery.js" ></script>
    <script src="/themes/js/common.js" ></script>
    <script src="http://g.tbcdn.cn/mtb/lib-flexible/0.3.4/??flexible_css.js,flexible.js"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/Swiper/3.1.7/css/swiper.min.css">
    <script type="text/javascript" src="http://cdn.bootcss.com/Swiper/3.1.7/js/swiper.jquery.min.js"></script>
    <script src="/themes/js/common.js" ></script>
    <script src="/themes/js/vue.min.js" ></script>
     <link rel="stylesheet" type="text/css" href="/themes/css/buildDate.css">
    <script>
    var ress = {};var index = 0,thisShowStatus = false;;
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
    	var newStatus = getStorage("newStatus");
    	if(userId==null||userId==''){
    		userId = getStorage("userId");
    	}
    	$.ajax({
    		url:'/getUserList.html?id='+userId,
    		type:'GET',
    		dataType:'JSON',
    		success:function(res){
    			if(res!=null&&res.data!=null){
	    			ress.data = new Array();
	    			for(var i = res.data.length-1;i>=0;i--){
	    				res.data[i].picStatus = 3;
	    				res.data[i].style = 10+parseInt(res.data[i].id);
	    				if(res.data[i].pic2=='null'||res.data[i].pic2==''){
	    					res.data[i].picStatus = 1;
	    					res.data[i].pic1 = '/themes/data/'+res.data[i].pic1;
	    				}
	    				if((res.data[i].pic3=='null'||res.data[i].pic3=='')&&res.data[i].pic2!='null'&&res.data[i].pic2!=''){
	    					res.data[i].picStatus = 2;
	    					res.data[i].pic1 = '/themes/data/'+res.data[i].pic1;
	    					res.data[i].pic2 = '/themes/data/'+res.data[i].pic2;
	    				}
	    				if(3==res.data[i].picStatus){
	    					res.data[i].pic1 = '/themes/data/'+res.data[i].pic1;
	    					res.data[i].pic2 = '/themes/data/'+res.data[i].pic2;
	    					res.data[i].pic3 = '/themes/data/'+res.data[i].pic3;
	    				}
	    				ress.data.push(res.data[i]);
	    			}
	    			console.log(ress);
	    			new Vue({
	   				  el: 'body',
	   				  data:{items:res.data}
	    			})
	    			showStatus();
	    			setTimeout(function(){
	    				/**
	        	    	 * 绑定监听事件
	        	    	 */
	        	    	 for(var i = 0;i<ress.data.length;i++){
	        	    		 document.getElementById(res.data[i].id).addEventListener('touchstart', touchSatrt,false);
	        	    	     document.getElementById(res.data[i].id).addEventListener('touchmove', touchMove,false);
	        	    	     document.getElementById(res.data[i].id).addEventListener('touchend', touchEnd,false);
	        	    	 }
	    			},1000)
	    		}
    			else{
    				//showDiv('noDiv');
    				thisShowStatus = true;
        		}
    			if(1==newStatus){
		    		showMain();
		    	}else{
		    		$('.bg-img').show();
		    	}
    		}
    	}) 
    })
        var startX,x,aboveX=0,startY,aboveY = 0;
	/**
	 * 开始滑动
	 * @param e
     */
	function touchSatrt(e){//触摸
		left = $('#'+ress.data[index].id).css('left').split('px')[0];
		e.preventDefault();
		var touch=e.touches[0];
		startX = touch.pageX;   //刚触摸时的坐标
		startY = touch.pageY;
	}

	/**
	 * 滑动过程中
	 * @param e
     */
	function touchMove(e){//滑动
		e.preventDefault();
		var  touch = e.touches[0];
		x = parseInt(touch.pageX - startX);//滑动的距离
		y = parseInt(touch.pageY - startY);
		left = parseInt(left);
		$('#'+ress.data[index].id).css('top',60+y+'px');
		$('#'+ress.data[index].id).css('left',left+x+'px');
		if(x<-10){
		}else if(x>10){
		}else{
		}
	}

	/**
	 * 滑动结束
	 * @param e
     */
	function touchEnd(e){//手指离开屏幕
		if(x<-30){
			 $('#'+ress.data[index].id).animate({ left: '-850px',top:'1300px' },'slow',function(){
				 $('#'+ress.data[index].id).hide();
				 var id = ress.data[index].id;
					var userId = $('#userId').val();
					var params = 'likeUserId='+id+'&userId='+userId;
					index++;
					$.ajax({
						url:'/insertDisConn.html',
						data:params,
						dataType:'json',
						type:'post',
						success:function(res){
						}
					})
			 });
		}else if(x>30){
			$('#'+ress.data[index].id).animate({ left: '850px',top:'-1300px' },'slow',function(){
				$('#'+ress.data[index].id).hide();
				var id = ress.data[index].id;
				var userId = $('#userId').val();
				var params = 'likeUserId='+id+'&userId='+userId;
				index++;
				$.ajax({
					url:'/insertConn.html',
					data:params,
					dataType:'json',
					type:'post',
					success:function(res){
						if('1'==res.code){
							$('#loveImg').attr('src',ress.data[index].pic1);
							showDiv('successDiv');
						}
					}
				})
			});
		}else{
		}
		console.log(index);
		showStatus();
		aboveX = 0;
		aboveY = 0;
		e.preventDefault();
	}
	/**
	*讨厌
	*/
	function hate(){
		$('#'+ress.data[index].id).animate({ left: '-850px',top:'1300px' },'slow',function(){
			 $('#'+ress.data[index].id).hide();
			 var id = ress.data[index].id;
				var userId = $('#userId').val();
				var params = 'likeUserId='+id+'&userId='+userId;
				$.ajax({
					url:'/insertDisConn.html',
					data:params,
					dataType:'json',
					type:'post',
					success:function(res){
						console.log(res);
					}
				})
		 });
		index++;
		console.log(index);
		 showStatus();
	}
	/**
	*喜欢
	*/
	function like(){
		$('#'+ress.data[index].id).animate({ left: '850px',top:'-1300px' },'slow',function(){
			$('#'+ress.data[index].id).hide();
			var id = ress.data[index].id;
			var userId = $('#userId').val();
			var params = 'likeUserId='+id+'&userId='+userId;
			$.ajax({
				url:'/insertConn.html',
				data:params,
				dataType:'json',
				type:'post',
				success:function(res){
					if('1'==res.code){
						$('#loveImg').attr('src',ress.data[index].pic1);
						showDiv('successDiv');
					}
				}
			})
			index++;
			console.log(index);
			showStatus();
		});
	}
	function showStatus(){
		if(index===ress.data.length){
			showDiv('noDiv');
		}
	}
	function showDiv(id){
		$('#main').hide();
		$('#noDiv').hide();
		$('#successDiv').hide();
		$('#'+id).css('display','-webkit-flex');
		$('#'+id).css('display','flex');
		$('#'+id).css('display','-moz-flex');
	}
	function goPerson(id,userId){
		console.log(id);
		location.href = '/person.html?userId='+id+'&id='+userId;
	}
	function showMain(){
		setStorage("newStatus","1");
		$('.bg-img').hide();
		if(thisShowStatus){
			showDiv('noDiv');
		}
	}
    </script>
    <style type="text/css">
    .index-div{
    	position: absolute;
    	top: 60px;
    	left: 2%;
    	width: 96%;
    }
    .bg-img{
    	position: fixed;
    	top: 0px;
    	left: 0px;
    	width: 100%;
    	height: 100%;
    	z-index: 9999;
    	display: none;
    }
    </style>
</head>
<body>
	<img alt="" src="/themes/img/notice.png" class="bg-img" onclick="showMain();" >
	<input type="hidden" id="userId" value="${userId }" >
	<div id="main" >
    <header>
     <a href="javascript:;" class="menu-btn"><img src="/themes/img/321.png"/></a>
        <span class="center">选择对象</span>
        <a href="/select.html?userId=${userId }" class="right">
        	 <img src="/themes/img/home.png" /> 
        </a>
    </header>
    <div id="{{model.id}}" v-for="model in items"  class="index-div" style="z-index:{{model.style}}" onclick="goPerson('{{model.id}}','${userId}')" >
	    <div class="choose-list"  v-if="3==model.picStatus" >
	        <div class="choose-col choose-col-one" id="{{model.id}}1" onclick="goPerson('{{model.id}}','${userId}')"></div>
	    	<div class="choose-col choose-col-two" id="{{model.id}}2" onclick="goPerson('{{model.id}}','${userId}')"></div>
	         <div class="choose-col choose-col-three" id="{{model.id}}now" onclick="goPerson('{{model.id}}','${userId}')">
	         		<div class="header-img" onclick="goPerson('{{model.id}}','${userId}')">
	         			<img alt="" v-bind:src="model.pic3" onclick="goPerson('{{model.id}}','${userId}')" >
	         		</div>
	                <p class="inform">
	                	<span v-text="model.realName" ></span>
	                	<span v-text="model.sex" ></span>
	                	<span class="num">嘉宾号</span>
	                	<span v-text="model.num" ></span>
	                </p>
	                <p class="age">
	                	<b v-text="model.age+'岁'" ></b>
	                </p>
	         </div>
	    </div>
	    <div class="choose-list" v-if="2==model.picStatus" onclick="goPerson('{{model.id}}','${userId}')" >
	        <div class="choose-col choose-col-one" id="{{model.id}}1" onclick="goPerson('{{model.id}}','${userId}')" ></div>
	    	<div class="choose-col choose-col-two" id="{{model.id}}now" onclick="goPerson('{{model.id}}','${userId}')" >
	    		<div class="header-img" onclick="goPerson('{{model.id}}','${userId}')">
	         			<img alt="" v-bind:src="model.pic2"  onclick="goPerson('{{model.id}}','${userId}')">
	         		</div>
	                <p class="inform">
	                	<span v-text="model.realName" ></span>
	                	<span v-text="model.sex" ></span>
	                	<span class="num">嘉宾号</span>
	                	<span v-text="model.num" ></span>
	                </p>
	                <p class="age">
	                	<b v-text="model.age+'岁'" ></b>
	                </p>
	    	</div>
	    </div>
	    <div class="choose-list"  v-if="1==model.picStatus" onclick="goPerson('{{model.id}}','${userId}')" >
	        <div class="choose-col choose-col-one" id="{{model.id}}now" onclick="goPerson('{{model.id}}','${userId}')" >
	        	<div class="header-img" onclick="goPerson('{{model.id}}','${userId}')">
	         			<img alt="" v-bind:src="model.pic1" onclick="goPerson('{{model.id}}','${userId}')">
	         		</div>
	                <p class="inform">
	                	<span v-text="model.realName" ></span>
	                	<span v-text="model.sex" ></span>
	                	<span class="num">嘉宾号</span>
	                	<span v-text="model.num" ></span>
	                </p>
	                <p class="age">
	                	<b v-text="model.age+'岁'" ></b>
	                </p>
	        </div>
	    </div>
    </div>
    <div class="choose-btn">
          <div class="btn one" id="hate"><img src="/themes/img/hate_icon.png" onclick="hate();"  /></div>
          <div class="btn" id="like"><img src="/themes/img/like_icon.png" onclick="like();" /></div>
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
	        <li><a href="javascript:newUser('${userId }');"><img src="/themes/img/sad_song.png"/>新手引导</a></li>
	    </ul>
	</nav>
    </div>
    <div class="g-dialog-contianer" id="successDiv" style="display: none;">
        <div class="dialog-window dialog-love">
            <div class="dialog-content"><img src="/themes/img/banner.png" id="loveImg" class="love-img"/></div>
            <div class="dialog-footer">
                <a class="green" href="/list.html?userId=${userId }" >去看我的心仪</a>
            </div>
            <div class="dialog-close" onclick="javascript:location.reload();" ></div>
        </div>
    </div>
    <div class="g-dialog-contianer" id="noDiv" style="display: none;" >
        <div class="dialog-window ">
            <div class="dialog-header"></div>
            <div class="dialog-content">没人可约，待会再来</div>
            <div class="dialog-footer">
                <a class="green" href="/list.html?userId=${userId }">去看我的心仪对象</a>
            </div>
        </div>
    </div>
</body>
</html>