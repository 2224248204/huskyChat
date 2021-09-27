//上传头像
		document.querySelector('img').onclick = function(){
		
		　　　　document.querySelector('input[type=file]').click();
		
		　　}
		
		//验证码
			var btn = document.getElementById("btn");
			var handler = function(){
				doClick();
				removeEvent(btn,'click',handler);//取消绑定该事件
			}
			
			function addEvent(obj,type,handler){
				if(obj.addEventListener){
					obj.addEventListener(type,handler,false);
				}else if(obj.attachEvent){
					obj.attachEvent('on'+type,handler);
				}
			}
		
			function removeEvent(obj,type,handler){
				if(obj.removeEventListener){
					obj.removeEventListener(type,handler,false);
				}else if(obj.detachEvent){
					obj.detachEvent("on"+type,handler);
				}
			}
		
			function doClick(){
				removeClass(btn,'btn-on');
				addClass(btn,'btn-off');
				btn.innerHTML="60s后重新获取";
				var clickTime = new Date().getTime();
				var Timer = setInterval(function(){
					var nowTime = new Date().getTime();
					var    second  = Math.ceil(60-(nowTime-clickTime)/1000);
					if(second>0){
						btn.innerHTML = second+"s后重新获取";
					}else{
						clearInterval(Timer);
						removeClass(btn,'btn-off');
						addClass(btn,'btn-on');
						btn.innerHTML = "获取邮箱验证码";
						addEvent(btn,"click",handler);
					}
				},1000);
			}
		
		
			function hasClass(obj, cls) {  
				return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));  
			}        
			function addClass(obj, cls) {  
				if (!hasClass(obj, cls)) obj.className += " " + cls;  
			}        
			function removeClass(obj, cls) {  
				if (hasClass(obj, cls)) {  
					var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');  
					obj.className = obj.className.replace(reg, ' ');  
				}  
			}
			
			addEvent(btn,"click",handler);
			
			//上传图片
			var input = document.getElementById("file"); // 获取input节点
			
			　　input.onchange = function() {
			　　　　var file = this.files[0];
			　　　　if (!!file) {
			　　　　　　var reader = new FileReader(); // 图片文件转换为base64
			　　　　　　reader.readAsDataURL(file);//用文件加载器加载文件
			　　　　　　reader.onload = function() { // 显示图片
			　　　　　　　　document.getElementById("file_img").src = this.result;//file_img是图片展示载体
			　　　　　　}
			　　　　}
			　　}
			
			const file = this.$refs.upload.files[0];
			　　const reader = new FileReader();
			　　let _this = this;
			　　reader.onload = function() {
			　　　　_this.fileArr.push(reader.result);//fileArr是存储图片转换为base64的地址，然后可以通过v-for展示
			　　};
			　　reader.readAsDataURL(file);//用文件加载器加载文件