<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
	<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
	<title>登录 - huskyChat</title>
</head>
<body>
	<div class="app">
		<p style="font-size: 44px;line-height: 80px;">登录&nbsp;huskyChat</p>
		<p style="font-size: 28px;line-height: 1.2;color: #333;margin-bottom: 30px;">每一天，乐在沟通。</p>
		<form action="./user/login" method="post" id="form">
			<div class="input">
				<input type="text" name="account" id="account" value="" placeholder="账号"/>
			</div>
			<div class="input">
				<input type="password" name="password" id="password" value="" placeholder="密码	"/>
			</div>
			<button type="submit" id="login">立即登录</button>
			<p style="overflow: hidden;margin-top: 30px;">
				<span style="float: left;"><a href="">立即注册</a></span>
				<span style="float: right;"><a href="">找回密码</a></span>
			</p>
		</form>
	</div>

	<script>
		$(function (){
			$('#account').focus();
			$('#form').submit(function (){
				var $account = $('#account');
				var $password = $('#password');
				if($.trim($account.val()).length == 0 || $.trim($password.val()).length == 0){
					alert("请填写完整！");
					return false;
				}
				var formdata = $(this).serialize();
				$.post('./user/login', formdata, function (result){
					var data =  eval(result);
					if(data.code == "200"){
						alert(data.msg);
						switch (data.status){
							case 'ok':
								window.location.href = "./";
								break;
						}
					}
				});
				return false;
			});
		});
	</script>
</body>
</html>