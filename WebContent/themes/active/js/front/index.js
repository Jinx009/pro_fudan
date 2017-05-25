$(function(){
	address = window.sessionStorage.getItem("address");
	type = window.sessionStorage.getItem("type");
	if(type==null){
		type = '';
	}
	if(address==null){
		address = '';
	}
	getBanner();
	getCategory();
	getAddress();
	getActive();
})
var address='',type='';
/**
 * banner
 */
function getBanner(){
	$.ajax({
		url:'/active/front/bannerList.html',
		type:'POST',
		dataType:'json',
		success:function(res){
			for(var i in res.data){
				res.data[i].option = address;
			}
			new Vue({
   				  el: '#bannerDiv',
   				  data:{banners:res.data}
    		})
			var mySwiper = new Swiper('.swiper-container', {
				  loop: true, //循环播放
				  autoplay: 5000,
				  lazyLoading: true, //延迟加载图片
				  lazyLoadingInPrevNext: true, //延迟加载当前图片之前和之后一张图片
				  lazyLoadingOnTransitionStart: true, //过渡一开始就加载
				  pagination: '.swiper-pagination', //导航分页
				  paginationClickable: true //导航点击切换
			});
		}
	})
}
/**
 * 地址
 */
function getAddress(){
	$.ajax({
		url:'/active/front/address.html',
		type:'POST',
		data:'fatherId=1',
		dataType:'json',
		success:function(res){
			var htmlStr = '<option value="" >活动区域</option>';
			for(var i in res.data){
				if(address!=''&&address!=null&&address==res.data[i].name){
					htmlStr += '<option value='+res.data[i].name+' selected=selected >'+res.data[i].name+'</option>';
				}else{
					htmlStr += '<option value='+res.data[i].name+' >'+res.data[i].name+'</option>';
				}
			}
			$('#addressDiv').html(htmlStr);
		}
	})
}

function getByType(content){
	type = $('#typeDiv').val();
	address = $('#addressDiv').val();
	if(content!=''){
		type = content;
	}
	window.sessionStorage.setItem('type',type);
	window.sessionStorage.setItem('address',address);
	location.reload();
}
/**
 * 分类
 */
function getCategory(){
	$.ajax({
		url:'/active/front/categoryList.html',
		type:'POST',
		dataType:'json',
		success:function(res){
			var htmlStr = '<option value="" >活动类型</option>';
			for(var i in res.data){
				if(type!=''&&type!=null&&type==res.data[i].name){
					htmlStr += '<option value='+res.data[i].name+' selected=selected >'+res.data[i].name+'</option>';
				}else{
					htmlStr += '<option value='+res.data[i].name+' >'+res.data[i].name+'</option>';
				}
			}
			$('#typeDiv').html(htmlStr);
			new Vue({
   				  el: '#categoryDiv',
   				  data:{categorys:res.data}
    		})
		}
	})
}
/**
 * 活动
 */
function getActive(){
	$.ajax({
		url:'/active/front/activeList.html',
		type:'POST',
		data:'type='+type+'&address='+address,
		dataType:'json',
		success:function(res){
			if(res.data!=null){
				for(var i in res.data){
					res.data[i].openTime = res.data[i].openTime.substr(0,10);
				}
			}
			new Vue({
   				  el: '#activeDiv',
   				  data:{actives:res.data}
    		})
		}
	})
}



Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}