
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 修改管理员信息 -->
<div class="layui-card">
	<div class="layui-card-header">
		<i class="layui-icon layui-icon-user wrap-head-icon"></i>修改管理员信息
	</div>
	<div class="layui-card-body">
		<form class="layui-form" action="" lay-filter="example">
			<div class="layui-form-item">
				<label class="layui-form-label">账号</label>
				<div class="layui-input-block">
					<input type="text" name="username" lay-verify="title" autocomplete="off" value="admin"
						class="layui-input" disabled="disabled">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">修改账号</label>
				<div class="layui-input-block">
					<input type="checkbox" lay-text="ON|OFF" lay-skin="switch" lay-filter="switchUsername">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
					<button type="submit" class="layui-btn layui-btn-normal">提交</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="../../../src/js/layui.js"/>
