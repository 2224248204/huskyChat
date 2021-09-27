<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/25
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="officialVideo" lay-filter="officialVideo"></table>

<script type="text/html" id="officialVideoToolbar">
    <div class="tableSearch">
        <input type="text" placeholder="账号" id="officialVideoTitle" class="layui-input inputTableTool">
        <input type="text" placeholder="用户名" id="officialVideoDirector" class="layui-input inputTableTool">
        <input type="text" placeholder="邮箱" id="officialVideoToStar" class="layui-input inputTableTool">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <select name="modules" lay-verify="required" lay-search="" id="officialVideoType">
                    <option value="">性别</option>
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </div>
        </div>
        <button type="button" class="layui-btn layui-btn-green" style="margin-left: 10px;"
                lay-event="search">&nbsp;搜索&nbsp;</button>
    </div>
    </div>
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">删除选中行</button>
    </div>
</script>
<script type="text/html" id="officialVideobar">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="../../../src/js/layui.js"/>