$().ready(function(){
	
	// 内容
	$("#content").focus(function(){
		$("#chatlist").css({"height" : " calc(100% - 80px - 250px)"});
		$(this).css({"padding-top" : "50px"});
		$(".c-right-chat").css("height", "250px");
		$(".c-right-chat-tools").css("height", "50px");
	}).blur(function(){
		$("#chatlist").css({"height" : " calc(100% - 80px - 60px)"});
		$(this).css({"padding-top" : "10px"});
		$(".c-right-chat-tools").css("height", "0");
		$(".c-right-chat").css("height", "60px");
	});
	
	/**
	 * 通知弹窗
	 * @param {Object} topicType
	 * @param {Object} topicTitle
	 * @param {Object} topicContent
	 */
	function setTopicMess(topicType, topicTitle , topicContent){
		if($(".husky-topic-list ul").children().length >= 1){
			return;
		}
		var $item = '<li class="husky-topic-item"> ' + 
						'<div class="husky-topic-item-icon fl"> ' + 
							'<img src="./img/icon/icon-' + topicType + '.png" width="100%" height="100%"> ' + 
						'</div> ' + 
						'<div class="husky-topic-item-group fl"> ' + 
							'<p class="husky-topic-item-title">' + topicTitle + '</p> ' + 
							'<p class="husky-topic-item-content">' + topicContent + '</p> ' + 
						'</div> ' + 
					'</li>';
		$(".husky-topic-list ul").append($item);
		var index = $(".husky-topic-list ul").children('li:last-child').index();
		
		setTimeout(function(){
			$(".husky-topic-list ul").find('li').eq(index).css('opacity', 0);
		}, 3000);
		setTimeout(function(){
			$(".husky-topic-list ul").find('li').eq(index).remove();
		}, 3600);
	}
	
	// 发送按钮点击事件
	$('#sendMess').click(function(){
		if($.trim($('#content').val()).length == 0){
			setTopicMess('error', '提示', '不能发送空白信息！');
		}
	})
	
	
});