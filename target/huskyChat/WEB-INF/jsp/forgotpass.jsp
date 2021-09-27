<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/27
  Time: 0:33
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
  <p style="font-size: 44px;line-height: 80px;">找回密码&nbsp;huskyChat</p>
  <p style="font-size: 28px;line-height: 1.2;color: #333;margin-bottom: 30px;">每一天，乐在沟通。</p>
  <form action="" method="post">
    <div class="input">
      <input type="text" name="" id="" value="" placeholder="昵称" />
    </div>
    <div class="input">
      <input type="text" name="" id="" value="" placeholder="邮箱">
      <span>通过邮箱找回密码</span>
    </div>
    <div class="input">
      <input type="text" name="" id="" value="" placeholder="请填写验证码">
      <div class="email-code">
        <a id="btn" href="javascript:;">获取邮箱验证码</a>
      </div>
    </div>
    <div class="input checkpass">
      <input type="text" name="" id="" value="" placeholder="新密码	" />
    </div>
    <div class="input checkpass">
      <input type="text" name="" id="" value="" placeholder="确认密码	" />
    </div>
    <button type="button" id="login">确定</button>
    <p style="overflow: hidden;margin-top: 30px;">
      <span style="float: left;"><a href=""></a></span>
      <span style="float: right;"><a href=""></a></span>
    </p>
  </form>
</div>

</body>
<script type="text/javascript">
  (function(){
    var btn = document.getElementById("btn");
    var handler = function(){
      doClick();
      removeEvent(btn,'click',handler);//取消绑定该事件
    }

    function addEvent(obj,type,handler){
      if(obj.addEventListener){
        obj.addEventListener(type,handler,false);
      }else if(obj.attachEvent){
        obj.attachEvent('on'+type,handler);
      }
    }

    function removeEvent(obj,type,handler){
      if(obj.removeEventListener){
        obj.removeEventListener(type,handler,false);
      }else if(obj.detachEvent){
        obj.detachEvent("on"+type,handler);
      }
    }

    function doClick(){
      removeClass(btn,'btn-on');
      addClass(btn,'btn-off');
      btn.innerHTML="60s后重新获取";
      var clickTime = new Date().getTime();
      var Timer = setInterval(function(){
        var nowTime = new Date().getTime();
        var    second  = Math.ceil(60-(nowTime-clickTime)/1000);
        if(second>0){
          btn.innerHTML = second+"s后重新获取";
        }else{
          clearInterval(Timer);
          removeClass(btn,'btn-off');
          addClass(btn,'btn-on');
          btn.innerHTML = "获取验证码";
          addEvent(btn,"click",handler);
        }
      },1000);
    }


    function hasClass(obj, cls) {
      return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
    }
    function addClass(obj, cls) {
      if (!hasClass(obj, cls)) obj.className += " " + cls;
    }
    function removeClass(obj, cls) {
      if (hasClass(obj, cls)) {
        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
        obj.className = obj.className.replace(reg, ' ');
      }
    }

    addEvent(btn,"click",handler);

  })();
</script>
</html>

