<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>Login Page</title>
    <script src="/themes/dist/js/jquery.js" ></script>
    <script src="/themes/dist/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="/themes/dist/css/bootstrap-theme.min.css" >
    <link rel="stylesheet" type="text/css" href="/themes/dist/css/bootstrap.min.css" >
    <script>
        $(function(){
            var width = parseInt($(window).width());
            console.log(width)
            if(width>420){
                $('.inner-div').css({'left':(width-380)/2,'width':'380px'});
            }
        })
        /**
         * login
         */
        function doLogin(){
            var userName = $('#userName').val();
            var password = $('#password').val();
            var params = 'userName='+userName+'&password='+password;
            $.ajax({
            	url:'/active/doLogin.html',
            	type:'POST',
            	dataType:'json',
            	data:params,
            	success:function(res){
            		if('success'==res.status){
            			location.href = '/active/activeController/list.do';
            		}else{
            			alert('Login Message Error!');
            		}
            	}
            })
        }
    </script>
    <style>
        .login-input{
            margin-bottom: 10px;
        }
        .inner-div{
            position: absolute;
            top: 10%;
            left: 2%;
            width: 96%;
            text-align: center;
        }
        .inner-div h2{
            margin-bottom: 30px;
        }
        .login-btn{
            width: 90%;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="inner-div">
        <h2>登录</h2>
        <input type="text" class="form-control login-input" id="userName" >
        <input type="password" class="form-control login-input" id="password" >
        <a class="btn btn-info login-btn" onclick="doLogin();" >后台登录</a>
    </div>
</body>
</html>