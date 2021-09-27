$().ready(function() {

	// 加载菜单
	var $menuItemParent = $(".menu ul");
	$(".menu ul").html("");
	$.each(menuItem.item, function(i, obj) {
		$(".menu ul").append('<li class="menu-item" menu-item-type=' + obj.type + ' menu-item-url=' + obj.url + '>' +
			'<i class="layui-icon layui-icon-' + obj.icon + ' menu-item-icon"></i>' +
			'<span class="menu-item-title">' + obj.name + '</span>' +
			'</li>');
	});


	// 菜单导航栏控制
	var menuFirst;
	var menuSecend;
	var indexArr = new Array();


	// 左侧菜单样式效果
	$(".menu-item").eq(0).addClass("menu-item-style-default").attr("menu-item-status", "on"); // 默认选中第一行
	$(".menu-item").on("click", function() { // 每个列表项单击事件
		$(this).addClass("menu-item-style-default").siblings().removeClass("menu-item-style-default");
		var $thisItem = $(this);
		var i = $(this).index();
		var type = $thisItem.attr("menu-item-type");

		menuFirst = i; // 记录当前点击对象的下标
		indexArr[0] = menuFirst; // 存放到数组
		indexArr.splice(1, 1); // 删除第2,3个元素

		// 根据下标数组获取数据
		var headNavData = getMenuData(indexArr);

		// 临时导航栏数组数据
		var headNav;

		// 判断获得的数据的子导航栏数据的长度是否大于0
		if (headNavData.childItem.length > 0) {
			headNav = headNavData.childItem;
		} else {
			headNav = new Array();
			headNav[0] = {
				'name': headNavData.name,
				'url': headNavData.url
			};
		}

		// console.log(headNav);

		var $headerNav = $(".header-nav ul");
		$headerNav.html("");

		if (headNav[0].data != null && headNav[0].data[0] != null) {
			$(".wrap-left nav").html("");
			bindWrapLeftMenu(headNav[0].data, $(".wrap-left nav"));
			controlWrapLeft("on");
		} else {
			controlWrapLeft("off");
			$(".app").css("padding-top", "10px");
		}
		$.each(headNav, function(i, obj) {
			$headerNav.append("<li class='header-nav-item'>" +
				"<div class='header-nav-item-btn'" + "nav-item-url=" + obj.url + ">" +
				"<span>" + obj.name + "</span>" +
				"</div>" +
				"</li>");
			$headerNav.children("li").css("animation", "0.3s headerNavItemAnimate forwards")
				.find(".header-nav-item-btn").eq(0).addClass("header-nav-item-btn-active");
		});

		loadChildHTML($thisItem.attr("menu-item-url"), $headerNav.find(".header-nav-item-btn").eq(0).attr("nav-item-url"));
	});



	// 顶部导航列表项点击按钮
	$(".header-nav ul").on("click", ".header-nav-item-btn", function() {
		var parentItemIndex = Number($(this).attr("parentItem-index"));
		var itemType = $(this).parent().parent().attr("item-type");
		var index = $(this).parent().index();

		menuSecend = index;
		indexArr[1] = menuSecend;

		var menuLeftData = getMenuData(indexArr);

		// 判断子菜单栏数据是否为空
		if (menuLeftData[index].data != null) {
			var $wrapLiftChildItem = $(".wrap-left nav");
			$(".wrap-left nav").html("");
			bindWrapLeftMenu(menuLeftData[index].data, $wrapLiftChildItem);
			controlWrapLeft("on");
		} else {
			controlWrapLeft("off");
			// console.log(menuLeftData[index]);
		}


		$(this).addClass("header-nav-item-btn-active");
		$(this).parent().siblings().find(".header-nav-item-btn").removeClass(
			"header-nav-item-btn-active");

		loadChildHTML($('.menu ul').find('.menu-item-style-default').eq(0).attr('menu-item-url'), $(this).attr("nav-item-url"));
	});

	/**
	 * 加载HTML内容
	 * @param parentUrl
	 * @param childerUrl
	 * @param data
	 */
	function loadChildHTML(parentUrl, childerUrl, data){
		var common = '../../../src/page/';
		var $app = $('.app');
		$app.html("");
		$app.load(common + parentUrl + '/' + childerUrl + '.jsp', data, function (){
			var dropdown = layui.dropdown,
				form = layui.form,
				util = layui.util,
				layer = layui.layer,
				table = layui.table,
				$ = layui.jquery,
				laydate = layui.laydate;
			//监听指定开关
			form.on('switch(switchUsername)', function (data) {
				if (this.checked == true) {
					console.log("ok");
					$("input[name='username']").removeAttr("disabled");
				} else {
					$("input[name='username']").attr("disabled", "disabled");
				}
			});


			table.render({
				elem: '#officialVideo',
				url: '../../../src/js/data.json',
				toolbar: '#officialVideoToolbar',
				defaultToolbar: ['filter', 'exports'],
				title: '用户数据表',
				cols: [
					[ //表头
						{
							type: 'checkbox',
							fixed: 'left'
						}, {
						field: 'officialVideoId',
						width: 80,
						sort: true,
						title: 'ID'
					}, {
						field: 'officialVideoTitle',
						width: 80,
						title: '视频标题'
					}, {
						field: 'officialVideoDirector',
						width: 80,
						sort: true,
						title: '导演'
					}, {
						field: 'officialVideoToStar',
						title: '主演'
					}, {
						field: 'officialVideoIntro',
						title: '简介'
					}, {
						field: 'officialVideoScore',
						sort: true,
						title: '评分值'
					},
						{field:'officialVideoScoreCount', sort: true, title : '评分人数'},
						{field:'officialVideoTime', title : '上映时间'},
						{field:'officialVideoRegion', title : '地区'},
						{field:'officialVideoType', title : '类型'},
						{field:'officialVideoPlayCount', title : '播放次数'},
						{field:'officialVideoShareCount', title : '分享次数'},
						{field:'officialVideoDownLoadCount', title : '下载次数'},
						{field:'officialVideoIsFree', title : '是否付费'},
						{field:'officialVideoPrice', title : '价格'},
						{fixed: 'right', toolbar: '#officialVideobar', title : '操作'}
					]
				],
				page: true
			});



			//头工具栏事件
			table.on('toolbar(officialVideo)', function(obj) {
				var checkStatus = table.checkStatus(obj.config.id);
				switch (obj.event) {
					case 'getCheckData':
						var data = checkStatus.data;
						layer.alert(JSON.stringify(data));
						break;
					case 'search':
						var title = $("#officialVideoTitle").val();
						var dirctor = $("#officialVideoDirector").val();
						var tostar = $("#officialVideoToStar").val();
						var type = $("#officialVideoType").val();
						table.reload('officialVideo', {
							where: {
								officialVideoTitle : data,
								officialVideoDirector : dirctor,
								officialVideoToStar : tostar,
								officialVideoType : type
							}
						});
				};
			});

			//监听行工具事件
			table.on('tool(officialVideo)', function(obj) {
				var data = obj.data;
				console.log(obj)
				if (obj.event === 'del') {
					layer.confirm('真的删除行么', function(index) {
						obj.del();
						layer.close(index);
					});
				}
			});

			//触发行单击事件
			table.on('row(officialVideo)', function(obj) {
				// console.log(obj.tr) //得到当前行元素对象
				console.log(obj.data.officialVideoId) //得到当前行数据
				//obj.del(); //删除当前行
				//obj.update(fields) //修改当前行数据
			});
		});
		// $.post(common + parentUrl + '/' + childerUrl + '.html', data, function (result){
		// 	$app.append(result);
		// });
	}

	/**
	 * 绑定左侧子菜单
	 * @param data
	 * @param objTag
	 */
	function bindWrapLeftMenu(data, objTag) {
		$.each(data, function(i, obj) {
			console.log(data);
			objTag.append('<a href="javascript:void(0);">' + obj.name + '</a>');

			controlWrapLeft("on");
		});
	}

	// 左侧子菜单栏按钮点击事件
	$(".wrap-left nav").on("click", "a", function() {
		$(this).addClass("wrap-left-item-focus").siblings().removeClass("wrap-left-item-focus");
	});

	$(".wrap-left-control").children().click(function() {
		if ($(this).parent().attr("wrap-status") == "on") {
			controlWrapLeft("on");
			$(this).parent().css({
				"right": "-30px",
				"opacity": 1,
				"transform": "rotate(0)"
			});
		} else {
			controlWrapLeft("off");
			$(this).parent().css({
				"right": "-30px",
				"opacity": 1,
				"transform": "rotate(180deg)"
			});

		}
	});

	/**
	 * 获取Menu菜单栏JSON数据
	 * @param {Object} indexArr 下标数组
	 */
	function getMenuData(indexArr) {
		switch (indexArr.length) {
			case 1:
				return menuItem.item[indexArr[0]];
			case 2:
				return menuItem.item[indexArr[0]].childItem;
		}
	}

	/**
	 * 子菜单控制方法
	 * @param {Object} status 状态，on打开，off关闭
	 */
	function controlWrapLeft(status) {
		var $wrapLeftControl = $(".wrap-left-control");
		var wrapStatus = $wrapLeftControl.attr("wrap-status")
		if (wrapStatus == null) {
			wrapStatus = "on";
			$wrapLeftControl.attr("wrap-status", "on");
		}
		if (status == "on") {
			$wrapLeftControl.css({
				"right": "-30px",
				"opacity": 1
			}).siblings("nav").css("opacity", 1).parent().css({
				"width": "200px"
			});
			$wrapLeftControl.attr("wrap-status", "off");
			$wrapLeftControl.next().find("a").eq(0).addClass("wrap-left-item-focus");
			$(".app").addClass("app-wrap").css("padding-top", "30px");
		} else if (status == "off") {
			$(this).parent().css("border", "none");
			$(".app").removeClass("app-wrap");
			$wrapLeftControl.css({
				"right": "0px",
				"opacity": 0
			}).siblings("nav").css("opacity", 0).parent().css({
				"width": "0"
			});
			$wrapLeftControl.attr("wrap-status", "on");
		}
	}

});

layui.use(['dropdown', 'util', 'layer', 'table', 'form', 'laydate'], function() {
	var dropdown = layui.dropdown,
		form = layui.form,
		util = layui.util,
		layer = layui.layer,
		table = layui.table,
		$ = layui.jquery,
		laydate = layui.laydate;

	//右键菜单
	var inst = dropdown.render({
		elem: '' //也可绑定到 document，从而重置整个右键
			,
		trigger: 'contextmenu' //contextmenu
			,
		isAllowSpread: false //禁止菜单组展开收缩
			,
		style: 'width: 200px' //定义宽度，默认自适应
			,
		id: 'test777' //定义唯一索引
			,
		data: [{
				title: '<i class="layui-icon layui-icon-theme"></i>主题',
				id: 'theme',
				child: [{
					title: '白天',
					id: 'themewhite'
				}, {
					title: '酷炫黑',
					id: 'themeblack'
				}]
			}, {
				type: '-'
			}, {
				title: '刷新',
				id: 'reload'
			},
			{
				title: '关闭本页',
				id: 'closethis'
			}
		],
		click: function(obj, othis) {
			if (obj.id === 'reload') {
				location.reload();
			} else if (obj.id === 'themewhite') {
				$("#theme").attr("href", "./css/Theme/theme-white.css");
			} else if (obj.id === 'themeblack') {
				$("#theme").attr("href", "./css/Theme/theme-black.css");
			}
		}
	});






	//日期
	  laydate.render({
	    elem: '#date'
	  });
});
