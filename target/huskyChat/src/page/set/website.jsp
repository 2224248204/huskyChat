
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 修改网站信息 -->
<div class="layui-card">
	<div class="layui-card-header">
		<i class="layui-icon layui-icon-note wrap-head-icon"></i>设置网站信息
	</div>
	<div class="layui-card-body">
		<form class="layui-form" action="" lay-filter="example">
			<div class="layui-form-item">
				<label class="layui-form-label">网站标题</label>
				<div class="layui-input-block">
					<input type="text" name="websize" lay-verify="title" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">副标题</label>
				<div class="layui-input-block">
					<input type="text" name="websize" lay-verify="title" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">网站关键词</label>
				<div class="layui-input-block">
					<input type="text" name="websize" lay-verify="title" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">网站描述信息</label>
				<div class="layui-input-block">
					<textarea class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">作者信息</label>
				<div class="layui-input-block">
					<input type="text" name="websize" lay-verify="title" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">版权信息</label>
				<div class="layui-input-block">
					<input type="text" name="websize" lay-verify="title" autocomplete="off" class="layui-input">
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
