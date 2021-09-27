<script>var serversAccount = ${sessionScope.get("user").account};</script>
<%@ page import="cn.huskychat.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>主页 - huskyChat</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/index.css"/>
	<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/function.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/userWebSocket.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

	<!-- app -->
	<div class="app">
		
		<!-- 内容 -->
		<div class="content">
			
			<!-- 内容容器 -->
			<div class="c-contain">
				
				<!-- 左侧 -->
				<div class="c-left">
					<!-- 用户信息 -->
					<div class="c-left-user">
						<div class="c-left-user-headimg">
							<img src="${sessionScope.get("user").headImg}" width="100%" height="100%">
						</div>
					</div>
					
					<!-- 菜单栏 -->
					<div class="c-left-menu">
						<nav>
							<div class="c-left-menu-item c-left-menu-item-focus" menuType="messageses">
								<div class="c-left-menu-icon">
									<img src="img/icon/icon-mess.png" width="100%" height="100%">
								</div>
							</div>
							<div class="c-left-menu-item" menuType="friends">
								<div class="c-left-menu-icon">
									<img src="img/icon/icon-relation.png" width="100%" height="100%">
								</div>
							</div>
							<div class="c-left-menu-item">
								<div class="c-left-menu-icon">
									<img src="img/icon/icon-pyq.png" width="100%" height="100%">
								</div>
							</div>
						</nav>
					</div>
				</div>
				
				<!-- 关系列表 -->
				<div class="c-center">
					<!-- 搜索 -->
					<div class="c-center-form">
						<form action="" method="post">
							<div class="c-center-search">
								<input type="text" value="" class="" placeholder="Search"/>
								<div class="c-center-search-icon">
									<img src="img/icon/icon-search.png" width="100%" height="100%">
								</div>
							</div>
						</form>
					</div>

					<!-- 聊天记录 -->
					<div class="c-center-item c-center-chat" panelType="messageses">
						<ul class="clear">
							<c:forEach items="${userMesses}" var="item" varStatus="status">
								<c:choose>
								<c:when test="${user.account == item.accountA}">
									<c:set var="itemAccount" value="${item.accountB}"/>
								</c:when>
								<c:otherwise>
									<c:set var="itemAccount" value="${item.accountA}"/>
								</c:otherwise>
								</c:choose>

							<li class="c-center-chat-item" c-chat-item-account="${itemAccount}">
								<div class="c-center-chat-item-left fl">
									<span class="c-center-chat-item-online"></span>
									<div class="c-center-chat-item-img">
										<img src="img/head.gif" width="100%" height="100%">
									</div>
								</div>
								<div class="c-center-chat-item-info fl">
									<p></p>
									<p>${item.content}</p>
								</div>
								<div class="c-center-chat-item-right fl">
										<span class="c-center-chat-item-time">
											<fmt:formatDate value="${item.time}" pattern="HH:mm:ss" timeZone="GMT"/>
										</span>
								</div>
							</li>
								<script>
									bindMessageList(getUserInfo(${itemAccount}), ${status.index});
								</script>
							</c:forEach>
					</div>

<%--					好友列表--%>
					<div class="c-center-item c-center-friends" panelType="friends">
						<ul class="clear">
							<%--<li c-account="2224248204">
								<div class="c-center-friends-img fl"><img src="\public\user\2224248204\2224248204.png" width="100%" height="100%"></div>
								<div class="c-center-friends-nickname fl">nickName</div>
							</li>--%>
							<c:forEach items="${friends}" var="item" varStatus="status">
								<li c-account="${item.accountB}">
									<div class="c-center-friends-img fl"><img src="\public\user\2224248204\2224248204.png" width="100%" height="100%"></div>
									<div class="c-center-friends-nickname fl">nickName</div>
								</li>

								<script>
									bindFriendsList(getUserInfo(${item.accountB}), ${status.index});
								</script>
							</c:forEach>
						</ul>
					</div>

				</div>
				
				<!-- 聊天框 -->
				<div class="c-right">

					<div class="c-right-text">
						huskyChat
					</div>

<%--					消息面板--%>
					<div class="c-right-panel">

						<!-- 头部 -->
						<div class="c-right-head">
							<p class="c-right-head-nickname">
								<a href="javascript:void(0);" sessionAccount="">${sessionScope.get("user").nickName}</a>
							</p>
						</div>

						<!-- 消息列表 -->
						<div class="c-right-mess" id="chatlist">
							<ul class="clear">
								<li>
									<div class="chatlist-item chatlist-item-a">
										<div class="chatlist-item-headimg fl">
											<img src="img/head.gif" width="100%" height="100%">
										</div>
										<div class="chatlist-item-content fl">
											<p class="chatlist-item-nickname">nickname</p>
											<p class="chatlist-item-text">asfasgasgas</p>
										</div>
									</div>
								</li>
								<li>
									<div class="chatlist-item chatlist-item-b">
										<div class="chatlist-item-headimg fr">
											<img src="img/head.gif" width="100%" height="100%">
										</div>
										<div class="chatlist-item-content fr">
											<p class="chatlist-item-nickname">nickname</p>
											<p class="chatlist-item-text">asgasggsdghsdhgdh</p>
										</div>
									</div>
								</li>
							</ul>
						</div>

						<!-- 消息内容输入 -->
						<div class="c-right-chat">
							<!-- 消息输入 -->
							<form>
								<!-- 消息内容工具栏 -->
								<div class="c-right-chat-tools">
									<nav>
										<div class="c-right-chat-tools-item">
											<img src="img/icon/icon-bq.png" width="30px" height="30px">
										</div>
									</nav>
								</div>
								<textarea name="content" id="content" wwidth="100%" height="100%" placeholder="Please input your message..."></textarea>

								<button type="button" id="sendMess">发送</button>
							</form>
						</div>
<%--					消息面板--%>
					</div>


				</div>
				
			</div>
			
		</div>
	</div>
	
	
	<!-- 通知弹窗 -->
	<div class="husky-topic-list">
		<ul class="clear">
			<!-- <li class="husky-topic-item">
				<div class="husky-topic-item-icon fl">
					<img src="./img/icon/icon-error.png" width="100%" height="100%">
				</div>
				<div class="husky-topic-item-group fl">
					<p class="husky-topic-item-title">提示</p>
					<p class="husky-topic-item-content">不能发送空白信息</p>
				</div>
			</li> -->
		</ul>
	</div>


<%--	<div class="userinfo-wrap">
		<div class="userinfo-box">
			<div class="userinfo-close"></div>
			<div class="userinfo">

			</div>
		</div>
	</div>--%>

</body>
</html>