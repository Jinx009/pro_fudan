var data = {};
var giftArray = new Array();
$(function(){
	initData();
	getSize();
})
$(window).on("hashchange", function() {//兼容ie8+和手机端
    func();
});
/**
 * 页面缩放效果
 */
window.onresize = function (){
	getSize();
} 

function initData(){
	$.ajax({
		url:'/gift/list.html',
		type:'post',
		dataType:'json',
		success:function(res){
			data = res.data;
		}
	})
}
/**
 * 定位抽奖按钮
 */
function getSize(){
	var width = $(window).width();
	var height = $(window).height();
	var startImgWidth = width*0.4;
	var startImgLeftWidth = width*0.3;
	var startImgTopHeight = height*0.45;
	$('.start-img').css('width',startImgWidth);
	$('.start-img').css('margin-top',startImgTopHeight);
}
/**
 * 开始抽奖
 * @param index
 */
function changeHash(index){
	window.location.hash = index;
	$('.start-bg').hide();
	$('#gift'+index).show();
//	$('#gift1').show();
}
/**
 * 真实抽奖
 */
function func(){
	console.log('func----gift');
	var hash = window.location.hash;
    hash = hash.substr(1);
    var index = parseInt(hash);
    $('#gift'+hash).attr('onclick','stopGift("'+index+'")');
    giftArray = new Array();
    if('3'==hash){
    	getIndex(12);
    	startGift('3',12);
    }else if('2'==hash){
    	getIndex(4);
    	startGift('2',4);
    }else{
    	getIndex(1);
    	startGift('1',1);
    }
}
/**
 * 抽奖结果
 * @param index
 * @param num
 */
var timeId;
function startGift(index,num){
	console.log('start----gift');
	var length = data.length-1;
	for(var i = 1;i<=num;i++){
		 var number = Math.floor(Math.random()*length)
		 $('#name'+index+i).text(data[number].realName);
		 $('#num'+index+i).text(data[number].number);
	 }
	timeId = setTimeout('startGift("'+index+'","'+num+'")',100);  
}
/**
 * 抽取结果
 * @returns
 */
function getIndex(num){
	console.log('get----index');
	var length = data.length - 1;
	var index = Math.floor(Math.random()*length)
	if(giftArray.length<num){
		var exit = 0;
		for(var i in giftArray){
			if(giftArray[i]==index){
				exit = 1;
			}
		}
		if(0===exit){
			giftArray.push(index);
		}
		getIndex(num);
	}else{
		var giftValue = '阳光普照奖';
		if(num==12){
			giftValue = '三等奖';
		}else if(num==4){
			giftValue = '二等奖';
		}else if(num==1){
			giftValue = '一等奖';
		}
		
		for(var i = 0;i<giftArray.length;i++){
			var params = 'id='+data[giftArray[i]].id+'&giftValue='+giftValue;
			$.ajax({
				url:'/gift/update.html',
				data:params,
				type:'post',
				dataType:'json',
				success:function(res){
					console.log(res);
				}
			})
		}
	}
}
/**
 * 停止抽奖
 * @param index
 */
function stopGift(index){
	clearTimeout(timeId);
	console.log('stop------gift'+index);
	$('#gift'+index).attr('onclick','showStart("'+index+'")');
	for(var i = 0;i<giftArray.length;i++){
		$('#name'+index+(i+1)).text(data[parseInt(giftArray[i])].realName);
		$('#num'+index+(i+1)).text(data[parseInt(giftArray[i])].number);
	}
	if(1==index){
		$('#gift'+index).attr('onclick','');
	}
	initData();
}
/**
 * 再次抽奖
 * @param index
 */
function showStart(index){
	$('#gift'+index).hide();
	index = parseInt(index)-1;
	if(index>0){
		$('#start').attr('onclick','changeHash("'+index+'")');
		$('.start-bg').show();
	}
}