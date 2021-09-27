
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

	// 左侧菜单栏小图标按钮点击事件
	$('.c-left-menu-item').click(function (){
		var type = $(this).attr('menuType');
		$(this).addClass('c-left-menu-item-focus').siblings().removeClass('c-left-menu-item-focus');

		$('.c-center-item').each(function (i, obj){
			if($('.c-center-item').eq(i).attr('panelType') == type){
				$('.c-center-item').eq(i).show(0).siblings('.c-center-item').hide();
				return;
			}
		});
	})

	$('#chatlist').hide();
	// 左侧消息列表点击事件
	$('.c-center-chat-item').click(function (){
		var account = $(this).attr('c-chat-item-account');
		var nickName = $(this).find('.c-center-chat-item-info p').eq(0).html();
		$.ajax({
			url : 'userMess/getMess',
			data : {
				accountB : account
			},
			async : false,
			success : function (result){
				result = eval(result);
				if(result.data != null){
					$('.c-right-text').hide();
					$('#chatlist').show();
					$('#chatlist').html('');
					$('.c-right-head-nickname a').attr('sessionAccount', account).html(nickName);
					$.each(result.data, function (i, obj) {
						bindNewMessageList(obj);
					});
				}else{
					alert(result.msg);
				}
			}
		});

		// $('#chatlist').scrollTop($('#chatlist').prop("scrollHeight"),200);
		$('#chatlist').animate({scrollTop : $('#chatlist').prop("scrollHeight")}, 0);
	});

	// huskyChat 文本
	$('.c-right-text').css('line-height', $('.c-right-text').parent().height() + 'px');
});

window.onresize = function (){
	$('.c-right-text').css('line-height', $('.c-right-text').parent().height() + 'px');
}