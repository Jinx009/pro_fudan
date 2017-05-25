$(function(){
	$.ajax({
		url:'/active/front/likeOrJoin.html?type=0',
		type:'get',
		dataType:'json',
		success:function(res){
			if('success'==res.status){
				new Vue({
					el:'body',
					data:{actives:res.data}
				})
			}
		}
	})
})
function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}
function cancle(id){
	var params = 'type=1&activeId='+id;
	$.ajax({
		url:'/active/front/updateLikeOrJoin.html',
		type:'post',
		dataType:'json',
		data:params,
		success:function(res){
			if('success'==res.status){
				alert('取消成功！');
				location.reload();
			}
		}
	})
}