$(function(){
	$.ajax({
		url:'/active/front/activeUser.html',
		type:'post',
		dataType:'json',
		success:function(res){
			new Vue({
 				  el: 'body',
 				  data:{user:res.data}
			})
		}
	})
})
function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}