<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>龙鹏天下</title>
	<meta author="caijl">
	<link rel="stylesheet" type="text/css" href="../css/register-login.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box register-box">
	<div class="cent-box-header">
		<h1 class="main-title hide">龙鹏</h1>
		<h2 class="sub-title">生活热爱分享  - Thousands Find</h2>
	</div>
	<form method="post" id="submigRegForm" action="<%=request.getContextPath()%>/hello/registers.html">  
	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="welcome.html">登录</a>
				<a href="register.html" class="active">注册</a>
				<div class="slide-bar slide-bar1"></div>				
			</div>
		</div>

		<div class="login form">
			<div class="group">
				<div class="group-ipt user">
					<input type="text" name="phone" id="phone" class="ipt" required="required"  placeholder="移动电话" required>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" required="required"  class="ipt" placeholder="设置登录密码" >
				</div>
				<div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" >
				</div>
				<input type="button" style="width: 22.4em;height: 3em;" id="sendMessage" onclick="sendMess()" value="发送验证码"/>
			</div>
		</div>
		<input type="hidden" value="0" id="messageCode"/>
		<div class="button">
			<button type="button" onclick="regSubmits()"  class="login-btn register-btn" id="button">注册</button>
		</div>
	</div>
	</form>
</div>

<div class="footer">
	<p>龙鹏天下</p>
	<p>Designed By LongPeng & <a href="#">ddsay.com</a> 2017</p>
</div>

<script src='../js/particles.js' type="text/javascript"></script>
<script src='../js/background.js' type="text/javascript"></script>
<script src='../js/jquery.min.js' type="text/javascript"></script>
<script src='../js/layer/layer.js' type="text/javascript"></script>
<script src='../js/index.js' type="text/javascript"></script>
<script>
	$('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
        		time: 6000,
        		tips: [2, "#3c3c3c"]
    		})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
	})

	$(".login-btn").click(function(){
		var email = $("#email").val();
		var password = $("#password").val();
		var verify = $("#verify").val();
		// $.ajax({
		// url: 'http://www.zrong.me/home/index/userLogin',
		// type: 'post',
		// jsonp: 'jsonpcallback',
  //       jsonpCallback: "flightHandler",
		// async: false,
		// data: {
		// 	'email':email,
		// 	'password':password,
		// 	'verify':verify
		// },
		// success: function(data){
		// 	info = data.status;
		// 	layer.msg(info);
		// }
		// })

	})
	$("#remember-me").click(function(){
		var n = document.getElementById("remember-me").checked;
		if(n){
			$(".zt").show();
		}else{
			$(".zt").hide();
		}
	})
	
</script>
<script>
function sendMess(){
		 	 $.ajax({
						url : "<%=request.getContextPath()%>/hello/sendMessage.html",
						data : {
							phone : $("#phone").val()
						},
						success : function(data){
						console.log(data);
						var dataObj=eval("("+data+")");
							 if(dataObj.errorCode == "null"||dataObj.errorCode == null ||dataObj.errorCode ==''){
							 	$("#messageCode").val(dataObj.messageCode);
								$("#sendMessage").attr("disabled",true);
							 }else{
							 		alert("请核对信息重新发送！");
								 return;
							 } 
					  }
					});   
	}
	$(document).ready(function(){  
   /*  $("#submigRegForm").bind("submit", function(){  
    console.log($("#messageCode").val());
     console.log($("#verify").val());
      
        
        }) */ 
    })  
    function regSubmits(){
     if($("#verify").val()==$("#messageCode").val()){
       		console.log("匹配成功")
       }else{
       alert("验证码不正确，请重新输入");
       			return false;
       } 
       $.ajax({
                url:"<%=request.getContextPath()%>/hello/registers.html",
                data:$('#submigRegForm').serialize(),
                async: false,
                error: function(request) {
                	alert("服务器异常，请联系管理员");
                },
                success: function(data) {
                	var dataObj=eval("("+data+")");
                	if(dataObj.code=="false"){
                		alert(dataObj.message);
                	}else{
                		window.location.href="<%=request.getContextPath()%>/hello/welcome.html";
                	}
                	
                }
            });
    }
</script>
</body>
</html>