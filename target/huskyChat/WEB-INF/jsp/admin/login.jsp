<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="../../../src/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="../../../src/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="../../../src/css/login.css"/>
	<link rel="stylesheet" type="text/css" href="../../../src/css/verify.css"/>
	<title>登录 - Husky后台管理 - ldev</title>
</head>
<body>
	
	<div id="wrap">
		<div class="box">
			
			<form id="loginform" action="" method="post">
				<div class="login-t">登录Husky后台管理</div>
				
				<div class="input">
					<fieldset>
						<legend>请输入管理员账号</legend>
						<i class="layui-icon layui-icon-username" style="color: #eee;"></i>   
					</fieldset>
					<input type="text" name="admin" id="admin" value="" />
				</div>
				
				<div class="input">
					<fieldset>
						<legend>密码</legend>
						<i class="layui-icon layui-icon-password" style="color: #eee;"></i>   
					</fieldset>
					<input type="password" name="pass" id="pass" value="" />
				</div>
				
				<div class="input-code">
					<button type="submit">登录</button>
				</div>
				
				<!-- <div class="input">
					<button type="button" class="layui-btn layui-btn-radius">登&nbsp;录</button>
				</div> -->
			</form>

		</div>
	</div>
	
	<script src="../../../src/js/layui.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../../src/js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../../src/js/verify.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		
		// 输入框效果
		$(".input input").focus(function(){
			$(this).parent().find("fieldset").css({"top" : "0", "height" : "50px"}).find("legend").css({"position" : "static"}).parent().find(".layui-icon").css({"top" : "17px"});
			$(this).css("padding-top" , "8px");
			
		}).blur(function(){
			// 如果没有输入内容，则返回到原来的样式
			if($.trim($(this).val()).length == 0){
				$(this).val("");
				$(this).parent().find("fieldset").css({"top" : "5px", "height" : "40px"}).find("legend").css({"position" : "absolute"}).parent().find(".layui-icon").css({"top" : "8px"});
				$(this).css("padding-top" , "0");
			}
		});
		
		
		$("#loginform").submit(function(){
			layer.alert('登录成功',{
				icon : 1
			});
			return false;
		});
		
	</script>
	
</body>
</html>