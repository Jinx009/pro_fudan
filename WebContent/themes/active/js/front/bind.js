$(function(){
//	var code = $('#code').val(),mobilePhone = $('#mobilePhone').val(),url = $('#url').val(),id = $('#id').val();
//	alert(url);
	var _mobilePhone =  getStorage('_mobilePhone');
	if(_mobilePhone!=null&&''!=_mobilePhone){
		$.ajax({
			url:'/active/front/_login.html',
			type:'POST',
			data:'mobilePhone='+_mobilePhone,
			dataType:'json',
			success:function(res){
				if('success'==res.status){
					location.href= $('#url').val();
				}
			}
		})
	}
})
function getMsg(){
	var mobilePhone = $('#mobilePhone').val();
	$('#yzm').attr('onclick','');
	if(validateTel(mobilePhone)){
		$.ajax({
			url:'/active/front/sendBindMsg.html',
			type:'POST',
			data:'mobilePhone='+mobilePhone,
			dataType:'json',
			success:function(res){
				if('success'==res.status){
					changeStatus();
				}
			}
		})
	}else{
		$('#yzm').attr('onclick','getMsg();');
		alert(errorMsg);
	}
}
/**
 * 绑定
 */
function bind(){
	var code = $('#code').val(),mobilePhone = $('#mobilePhone').val(),url = $('#url').val(),id = $('#id').val();
	if(code==null||code==''){
		alert('验证码不能为空！');
	}else if(mobilePhone==null||mobilePhone==''){
		alert('手机号码不能为空！');
	}else if(!validateTel(mobilePhone)){
		alert('手机号码格式有误！');
	}else{
		var params = 'code='+code+'&mobilePhone='+mobilePhone;
		$.ajax({
			url:'/active/front/bind.html',
			type:'post',
			data:params,
			dataType:'json',
			success:function(res){
				if('success'==res.status){
					 setStorage('_mobilePhone',mobilePhone);
					alert('登录成功！');
					location.href= url;
				}else{
					alert(res.status);
				}
			}
		})
	}
}
var errorMsg = '';
var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
/**
 * 手机号码校验
 * @param mobile
 * @returns {Boolean}
 */
function validateTel(mobile){
    if(mobile.length==0){
       errorMsg = "请输入手机号码!";
       return false;
    }    
    if(mobile.length!=11){
    	errorMsg = "请输入有效的手机号码";
        return false;
    }
    if(!myreg.test(mobile)){
    	errorMsg = "请输入有效的手机号码";
        return false;
    }
    
    return true;
}
/**
 * 更改获取邀请码按钮
 * @param btnId
 */
function changeStatus(){
	var time=60;
	
	var timeFun=setInterval(function(){
		time--;
		if(time>0){
			$("#yzm").html(time+"秒后重发").attr("onclick","");
		}
		else{
			time=60;
			$("#yzm").html("获取验证码").attr("onclick",'getMsg();');
			clearInterval(timeFun);
		}
	},1000)
}