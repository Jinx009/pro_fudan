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
    <script type="text/javascript">
    $(function(){
    	var width = $(window).width();
    	var minWidth = parseInt(width/5);
    	$('.img').css('width',minWidth);
    	$('.img').css('border-radius',minWidth);
    })
    function goUrl(url){
    	location.href = url;
    }
    function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}
    </script>
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
	a{
		color: black;
		font-family: "微软雅黑";
	}
	a:visited, a:link, a:hover {
    	color: black;
    	font-family: "微软雅黑";
	}
    </style>
</head>
<body>
<div id="main">
	<table style="width: 100%;margin-top: 25px;">
		<tr>
			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://shanghaicity.openservice.kankanews.com/')" >
				<img alt="" src="/themes/active/img/shizheng.png" class="img">
				<br><br>
				<a>上海市政大厅</a>
			</td>
			<td style="width: 33%;vertical-align: middle;"  onclick="goUrl('http://www.shzbh.org.cn/fwdt/fwdt3.php?winzoom=1')"  >
				<img alt="" src="/themes/active/img/shengonghui.png" class="img"><br><br>
				<a>申工社</a>
			</td>
			<td style="width: 33%;vertical-align: middle;"  onclick="goUrl('http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzA5MTA3NjE3OQ==#wechat_webview_type=1&wechat_redirect')"  >
				<img alt="" src="/themes/active/img/zhibaohui.png" class="img"><br><br>
				<a>职保会</a>
			</td>
		</tr>
		<tr style="height: 20px;"></tr>
		<tr>
			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI5NjUzNzU1Ng==#wechat_webview_type=1&wechat_redirect')" >
				<img alt="" src="/themes/active/img/yangpujiaoyu.png"  class="img"><br><br>
				<a>上海杨浦教育</a>
			</td>
			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://weixin.shypzgh.org/weixin/index.php?s=/addon/MemberApply/MemberApply/fwdt/publicid/1.html&winzoom=1')" >
				<img alt="" src="/themes/active/img/yangpuzhigong.png" class="img">
				<br><br>
				<a>杨浦职工之家</a>
			</td>
			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://wx.wy.guahao.com/fastorder/hospital?_=1436950800611&_channel=/')" >
				<img alt="" src="/themes/active/img/guahao.png"  class="img"><br><br>
				<a>微医（挂号）</a>
			</td>
<!-- 			<td style="width: 33%;vertical-align: middle;"  onclick="goUrl('http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzAwMDAzMTI1Nw==#wechat_webview_type=1&wechat_redirect')"  >
				<img alt="" src="/themes/active/img/jiaoshi.png" class="img"><br><br>
				<a>教育博雅</a>
			</td> -->
		</tr>
		<tr style="height: 20px;"></tr>
		<tr>
			
			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://shanghaicity.openservice.kankanews.com/public/traffic/jtkye')" >
				<img alt="" src="/themes/active/img/jiaotong.png" class="img">
				<br><br>
				<a>交通卡余额查询</a>
			</td>
			<td style="width: 33%;" >
			</td>
			<td style="width: 33%;" >
			</td>
<!-- 			<td style="width: 33%;vertical-align: middle;" onclick="goUrl('http://jzzjf.12333sh.gov.cn/jzzjf/pingfen/index.jsp')" >
				<img alt="" src="/themes/active/img/juzhuzheng.png"  class="img"><br><br>
				<a>居住证模拟打分</a>
			</td> -->
		</tr>
	</table>

</div>	
</body>
</html>