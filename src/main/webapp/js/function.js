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
/**
 * 获取用户信息
 * @param account
 * @returns {*}
 */
function getUserInfo(account){
    var dataResult;
    $.ajax({
        url : 'user/getByAccount',
        data : {
            'account' : account
        },
        async : false,
        success : function(result){
            dataResult = eval(result);
        }
    });
    return dataResult;
}
/**
 * 绑定消息列表
 * @param data
 * @param index
 */
function bindMessageList(data, index){
    $('.c-center-chat-item .c-center-chat-item-info').eq(index).find('p').eq(0).html(data.data.nickName);
    $('.c-center-chat-item').eq(index).find('.c-center-chat-item-img img').eq(0).attr("src", data.data.headImg);
}

/**
 * 绑定用户列表
 * @param data
 * @param index
 */
function bindFriendsList(data, index){
    $('.c-center-friends ul li').eq(index).find('.c-center-friends-nickname').html(data.data.nickName);
    $('.c-center-friends ul li').eq(index).find('.c-center-friends-img img').attr('src', data.data.headImg);
}

/**
 * 绑定实时消息内容
 * @param data
 */
function bindNewMessageList(data){
    var isSelf = data.accountA == serversAccount ? true : false;
    $('#chatlist').append('<li style="width: 100%;height: auto;" class="clear">' +
        '						<div class="chatlist-item chatlist-item-' + (isSelf == true ? 'b' : 'a') + '">' +
        '							<div class="chatlist-item-headimg ' + (isSelf == true ? 'fr' : 'fl') + '">' +
        '								<img src="' + data.headImgA + '" width="100%" height="100%">' +
        '							</div>' +
        '						<div class="chatlist-item-content ' + (isSelf ? 'fr' : 'fl') + '">' +
        '							<p class="chatlist-item-nickname">' + data.nickNameA + '</p>' +
        '							<p class="chatlist-item-text">' + data.content + '</p>' +
        '						</div>' +
        '					</div>' +
        '				</li>');

}