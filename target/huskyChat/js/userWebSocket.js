
$().ready(function (){

    // 连接服务器的地址
    var webSocketServers = "ws://127.0.0.1:8080/huskychat/user/";

    // 保存WebSocket对象
    var websocket = null;

    // 当关闭窗口时，触发 closeServersConnection 回调函数
    window.onbeforeunload = closeServersConnection;

    // 建立 WebSocket 连接
    function connectionServers(){
        if(websocket == null){
            if('WebSocket' in window){
                // 创建WebSocket
                websocket = new WebSocket(webSocketServers + serversAccount);

                // 当连接打开后的回调函数
                websocket.open = openWebSocketConnection;

                // 当关闭连接时的回调函数
                websocket.close = closeWebSocketConnection;

                // 建立WebSocket出现异常的回调函数
                websocket.onerror = connectionServersError;

                // 监听服务器响应的消息
                websocket.onmessage = listenServersMessage;
            }else{
                alert('您的浏览器版本过低，不支持WebScoket。');
            }
        }else{
            alert("您已建立过连接，请勿重复建立！");
        }
    }

    // 打开WebSocket后的操作
    function openWebSocketConnection(){
        console.log("建立连接成功！");
    }

    // 关闭WebSocket后的操作
    function closeWebSocketConnection(){
        console.log("当前连接已关闭！")
    }

    // WebSocket 连接异常的操作
    function connectionServersError(){
        console.log("连接出现异常！");
    }

    // 监听服务器响应的信息擦欧洲哦
    function listenServersMessage(message){
        // 调用消息处理器
        handleMessage(JSON.parse(message.data));
    }

    // 关闭 WebSocket 函数
    function closeServersConnection(){
        if(websocket != null){
            websocket.close();
            websocket = null;
        }else{
            console.log("连接关闭失败，从未打开过连接！");
        }
    }

    // 建立连接
    connectionServers();

    /**
     * 消息处理方法
     * @param data
     */
    function handleMessage(data){
        console.log(data)
        var type = data.messageType;
        var message = data.message;
        var target = data.targetAccount;
        var receive = data.receiveAccount;
        switch (type){
            case "online":
                handleOnlineMessage(message, target);
                break;
            case "message":
                console.log("处理消息");
                handleNewMessage(message, target);
                break;
        }

    }

    /**
     * 新消息处理函数
     * @param message
     * @param target
     */
    function handleNewMessage(message, target){
        var userInfo = getUserInfo(target);
        var isSelf = target == serversAccount ? true : false;
        $('#chatlist').append('<li style="width: 100%;height: auto;" class="clear">' +
            '						<div class="chatlist-item chatlist-item-' + (isSelf == true ? 'b' : 'a') + '">' +
            '							<div class="chatlist-item-headimg ' + (isSelf == true ? 'fr' : 'fl') + '">' +
            '								<img src="' + userInfo.data.headImg + '" width="100%" height="100%">' +
            '							</div>' +
            '						<div class="chatlist-item-content ' + (isSelf ? 'fr' : 'fl') + '">' +
            '							<p class="chatlist-item-nickname">' + userInfo.data.nickName + '</p>' +
            '							<p class="chatlist-item-text">' + message + '</p>' +
            '						</div>' +
            '					</div>' +
            '				</li>');

        $('#chatlist').scrollTop($('#chatlist').prop("scrollHeight"),200);
    }

    /**
     * 处理用户上线下线的消息函数
     * @param message
     * @param target
     */
    function handleOnlineMessage(message, target){
        // 在线状态显示
        var $online_dott = $('.c-center-chat-item-online');
        var $online_img = $('.c-center-chat-item-img img');

        $('.c-center-chat-item').each(function (i, obj){
            var $thisItem = $('.c-center-chat-item');
            if($thisItem.eq(i).attr('c-chat-item-account') == target){
                if(message == "yes"){
                    $thisItem.eq(i).find('.c-center-chat-item-online').addClass('c-center-chat-item-online-yes');
                }else if(message == "no"){
                    $thisItem.eq(i).find('.c-center-chat-item-online').removeClass('c-center-chat-item-online-yes');
                }
            }
        });
    }

    /**
     * 发送消息的函数
     * @param message
     * @param targetType
     */
    function sendMessage(message, targetType, receiveAccount, targetObjectType){
        var handleMessage = "{" +
            "            'message' : '" + message + "'," +
            "            'targetType' : '" + targetType + "'," +
            "            'targetAccount' : '" + serversAccount + "'," +
            "            'receiveAccount' : '" + receiveAccount + "'," +
            "            'targetObjectType' : '" + targetObjectType + "'" +
            "        }";

        if(websocket != null){
            websocket.send(handleMessage);
        }else{
            console.log("为创建连接，无法发送消息！");
        }
    }


    // 发送消息按钮点击事件
    $('#sendMess').click(function (){
        if($.trim($('#content').val()).length == 0){
            setTopicMess('error', '提示', '不能发送空白信息！');
            return;
        }
        $.ajax({
            url : 'userMess/addMess',
            type : 'POST',
            data : {
                accountA : serversAccount,
                accountB : $('.c-right-head-nickname a').attr('sessionAccount'),
                content : $('#content').val(),
                messType : 0
            },
            success : function (result){
                if(result.status == 'ok'){
                    sendMessage($('#content').val(), 'message', $('.c-right-head-nickname a').attr('sessionAccount'), 0);
                }else if(result.status == 'fail' || result.status == 'err'){
                    alert(result.msg);
                }
            }
        });
    });

});