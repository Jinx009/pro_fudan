$(function(){
	getData();
})
function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}
/**
 * 获取数据
 */
function getData(){
	$.ajax({
		url:'/active/front/activeDetail.html?id='+$('#id').val(),
		type:'POST',
		dataType:'json',
		success:function(res){
			res.data.startTime = res.data.startTime.substr(0,10);
			res.data.endTime = res.data.endTime.substr(0,10);
			res.data.openTime = res.data.openTime.substr(0,13)+'时';
			res.data.closeTime = res.data.closeTime.substr(0,13)+'时';
			new Vue({
   				  el: 'body',
   				  data:{data:res.data,materials:res.materials}
    		})
		}
	})
}
/**
 * 报名
 * @param id
 */
function save(id){
	var params = 'type=0&activeId='+id;
	$.ajax({
		url:'/active/front/updateLikeOrJoin.html',
		data:params,
		dataType:'json',
		type:'post',
		success:function(res){
			if('success'==res.status){
				alert('报名成功！');
				location.reload();
			}else{
				alert('请先完善个人信息！');
				location.href = '/active/page/update.html';
			}
		}	
	})
}
/**
 * 取消报名
 * @param id
 */
function cancle(id){
	var params = 'type=1&activeId='+id;
	$.ajax({
		url:'/active/front/updateLikeOrJoin.html',
		data:params,
		dataType:'json',
		type:'post',
		success:function(res){
			if('success'==res.status){
				alert('取消报名成功！');
				location.reload();
			}
		}	
	})
}

/**
 * 收藏
 * @param id
 */
function saveLike(id){
	var params = 'type=2&activeId='+id;
	$.ajax({
		url:'/active/front/updateLikeOrJoin.html',
		data:params,
		dataType:'json',
		type:'post',
		success:function(res){
			if('success'==res.status){
				alert('收藏成功！');
				location.reload();
			}
		}	
	})
}
/**
 * 取消收藏
 * @param id
 */
function cancleLike(id){
	var params = 'type=3&activeId='+id;
	$.ajax({
		url:'/active/front/updateLikeOrJoin.html',
		data:params,
		dataType:'json',
		type:'post',
		success:function(res){
			if('success'==res.status){
				alert('取消收藏成功！');
				location.reload();
			}
		}	
	})
}