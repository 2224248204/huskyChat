<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/25
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-panel">
    <fieldset class="layui-elem-field layui-field-title"
              style="margin-top: 20px;margin-bottom: 20px;">
        <legend>添加用户</legend>
    </fieldset>

    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">视频标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" autocomplete="off"
                       placeholder="请输入标题" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">导演</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" autocomplete="off"
                       placeholder="导演" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">主演</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" autocomplete="off"
                       placeholder="主演" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">简介</label>
            <div class="layui-input-block">
                <textarea placeholder="简介" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">日期选择</label>
                <div class="layui-input-block">
                    <input type="text" name="date" id="date" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地区</label>
            <div class="layui-input-block">
                <select name="interest" lay-filter="aihao">
                    <option value=""></option>
                    <option value="0">写作</option>
                    <option value="1" selected="">阅读</option>
                    <option value="2">游戏</option>
                    <option value="3">音乐</option>
                    <option value="4">旅行</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类型</label>
            <div class="layui-input-block">
                <select name="interest" lay-filter="aihao">
                    <option value=""></option>
                    <option value="0">写作</option>
                    <option value="1" selected="">阅读</option>
                    <option value="2">游戏</option>
                    <option value="3">音乐</option>
                    <option value="4">旅行</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">是否付费</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="免费" title="免费" checked="">
                <input type="radio" name="sex" value="会员" title="会员">
                <input type="radio" name="sex" value="付费" title="付费">
                <input type="radio" name="sex" value="用券" title="用券">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" name="title" lay-verify="title" autocomplete="off"
                       placeholder="价格" class="layui-input">
            </div>
        </div>

    </form>
</div>