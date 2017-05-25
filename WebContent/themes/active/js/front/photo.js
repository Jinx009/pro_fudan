$(function(){
	getData();
})
function goIndex(){
		window.sessionStorage.setItem('address','');
		window.sessionStorage.setItem('type','');
		location.href = '/active/page/index.html'
	}
var imgs;
function getData(){
	$.ajax({
		url:'/active/front/photoList.html',
		type:'POST',
		data:'key='+$('#key').val(),
		dataType:'json',
		success:function(res){
			var html = '';
			imgs = res.data;
			if(res.data!=null){
				for(var i in res.data){
					html+= '<li><img src="'+res.data[i].img+'" onclick=show('+i+') /></li>';
				}
			}
			$('#photo').html(html);
		}
	})
}
function show(index){
	$('#img').attr('src',imgs[parseInt(index)].img);
	var height = $('#photo_').height();
    var width = $('#photo_').width();
    var imgHeight = $('#img').height();
    var imgWidth = $('#img').width();
    var limitHeght = height/width*imgHeight;
    if(0==imgHeight||0==imgWidth){
    	setTimeout('showThis()',100); 
    }
    if(limitHeght<height){
        $('#img').css('position','absolute');
        $('#img').css('top',(height-limitHeght)/2);
    }else{
    	$('#img').css('position','absolute');
        $('#img').css('top','0px');
    }
	$('#main').hide();
	$('#photo_').show();
}
function showThis(){
	var height = $('#photo_').height();
    var width = $('#photo_').width();
    var imgHeight = $('#img').height();
    var imgWidth = $('#img').width();
    var limitHeght = height/width*imgHeight;
    if(0==imgHeight||0==imgWidth){
    	setTimeout('showThis()',1000); 
    }
    if(imgHeight<height){
        $('#img').css('position','absolute');
        $('#img').css('top',(height-imgHeight)/2);
    }else{
    	$('#img').css('position','absolute');
        $('#img').css('top','0px');
    }
	$('#main').hide();
	$('#photo_').show();
}
function hide(){
	$('#photo_').hide();
	$('#main').show();
}