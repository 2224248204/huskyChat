<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">

		<link rel="stylesheet" type="text/css" href="../../../src/css/common.css" />
		<link rel="stylesheet" type="text/css" href="../../../src/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../../../src/css/index.css" />
		<link rel="stylesheet" type="text/css" href="../../../src/css/verify.css" />
		<link rel="stylesheet" type="text/css" id="theme" href="../../../src/css/Theme/theme-white.css" />

		<script src="../../../src/js/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../src/js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="../../../src/js/verify.js" type="text/javascript" charset="utf-8"></script>

		<script src="../../../src/js/json.js"></script>
		<script src="../../../src/js/index.js" type="text/javascript" charset="utf-8"></script>
		<title>Husky后台管理 - ldev</title>
	</head>
	<body>

		<!-- 容器 -->
		<div class="container">
			<!-- 左侧内容 -->
			<div class="left fl">

				<!-- 头部 -->
				<div class="l-head">
					<div class="l-head-logo fl"><img src="../../../src/img/husky.png" width="100%" height="100%" alt="logo"></div>
					<div class="l-head-title fl"><span class="entitle">Husky&nbsp;</span>后台管理</div>
				</div>

				<!-- 菜单 -->
				<nav class="menu">
					<!-- 菜单列表项 -->
					<ul class="clear">
						<li class="menu-item">
							<i class="layui-icon layui-icon-console menu-item-icon"></i>
							<span class="menu-item-title">监控台</span>
						</li>

						<li class="menu-item">
							<i class="layui-icon layui-icon-set menu-item-icon"></i>
							<span class="menu-item-title">设置</span>
						</li>

						<li class="menu-item">
							<i class="layui-icon layui-icon-edit menu-item-icon"></i>
							<span class="menu-item-title">内容管理</span>
						</li>

						<li class="menu-item">
							<i class="layui-icon layui-icon-gift menu-item-icon"></i>
							<span class="menu-item-title">优惠券管理</span>
						</li>

						<li class="menu-item">
							<i class="layui-icon layui-icon-cart menu-item-icon"></i>
							<span class="menu-item-title">订单管理</span>
						</li>

						<li class="menu-item">
							<i class="layui-icon layui-icon-dialogue menu-item-icon"></i>
							<span class="menu-item-title">消息管理</span>
						</li>
					</ul>
				</nav>
			</div>

			<!-- 右侧内容 -->
			<div class="right fl">
				<!-- 头部 -->
				<header>
					<!-- 导航条 -->
					<div class="header-nav fl">
						<ul class="clear">
							<li class="header-nav-item">
								<div class="header-nav-item-btn header-nav-item-btn-active">
									<span>监控台</span>
								</div>
							</li>
						</ul>
					</div>

					<!-- 管理员 -->
					<div class="header-admin fr">
						<div class="header-admin-menu">
						</div>
						<div class="header-admin-info">
							<div class="header-admin-img"><img src="../../../src/img/head.gif" width="100%" height="100%"></div>
							<div class="header-admin-name"><strong>Husky管理员</strong></div>
						</div>
					</div>
				</header>

				<!-- 主体内容 -->
				<section class="content">

					<!-- 框架 -->
					<div class="wrap clear">

						<!-- 左侧子菜单 -->
						<div class="wrap-left fl">
							<div class="wrap-left-control" wrap-status="on">
								<i class="layui-icon layui-icon-return"></i>
							</div>
							<nav>
								<a href="javascript:void(0);" class="wrap-left-item-focus">视频管理</a>
								<a href="javascript:void(0);">上传视频</a>
								<a href="javascript:void(0);">频道管理</a>
								<a href="javascript:void(0);">标签管理</a>
							</nav>
						</div>

						<!-- 主要内容 -->
						<div class="app fl">



						</div>
						<!-- 主要内容 -->
					</div>
					<!-- 框架 结束 -->



				</section>

			</div>
		</div>


	</body>
</html>
