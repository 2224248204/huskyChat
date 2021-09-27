<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/25
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="./css/common.css" />
    <link rel="stylesheet" type="text/css" href="./css/login.css"/>
    <title></title>
</head>
<body>
<div class="app">
    <p style="font-size: 44px;line-height: 80px;">欢迎注册&nbsp;huskyChat</p>
    <p style="font-size: 28px;line-height: 1.2;color: #333;margin-bottom: 30px;">每一天，乐在沟通。</p>
    <form action="" method="post">
        <div class="headimg">
            <input type="file" id="file" style="display:none" name="" id="">
            <img id="file_img" src="img/head.gif" alt="" class="headimg">
            <span style="margin-left: 18px;color: #999999;">上传头像</span>
        </div>
        <div class="input">
            <input type="text" name="" id="" value="" placeholder="昵称" />
        </div>
        <div class="input">
            <input type="text" name="" id="" value="" placeholder="密码	" />
        </div>
        <div class="input">
            <input type="text" name="" id="" value="" placeholder="邮箱">
            <span>可以通过该邮箱可以找回账号密码</span>
        </div>
        <div class="input">
            <input type="text" name="" id="" value="" placeholder="请填写验证码">
            <div class="email-code">
                <a id="btn" href="javascript:;">获取邮箱验证码</a>
            </div>
        </div>
        <button type="button" id="login">立即注册</button>
        <p style="overflow: hidden;margin-top: 30px;">
            <span style="float: left;"><a href=""></a></span>
            <span style="float: right;"><a href=""></a></span>
        </p>
    </form>
</div>

</body>
<script type="text/javascript" src="js/register.js">
</script>
</html>
