<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=context%>/liuliang/index.css">
	<link rel="stylesheet" href="<%=context%>/liuliang/login.css" />
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.cookie.js"></script>
    <title>流量超市</title>
</head>
<body>
	<div class="top_img">
			<img class="header" src="<%=context%>/liuliang/images/logo-big.jpg" alt="">
	</div>
	<div class="page">
	<div class="loginwarrp">
		<div class="logo">商家登陆</div>
        <div class="login_form">
			<form id="Login" name="Login" method="post" onsubmit="" action="">
				<li class="login-item">
					<span>用户名：</span>
					<input type="text" id="username" name="username" class="login_input" onkeydown="keyActionSubmit()">
                                        <span id="count-msg" class="error"></span>
				</li>
				<li class="login-item">
					<span>密　码：</span>
					<input type="password" id="password" name="password" class="login_input" onkeydown="keyActionSubmit()">
                                        <span id="password-msg" class="error"></span>
				</li>
				
				<li class="login-sub">
					<input type="button" class="submit" value="登录" onclick="actionSubmit()" /> 
					<input type="button" class="submit" value="注册" onclick="zhuce()">
				</li>                      
           </form>
		</div>
	</div>
</div>
<script>
  function actionSubmit() {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/liuliangpost/userlogin.do",
          data: $('#Login').serialize(),
          success: function (data) {
              var strresult = $.parseJSON(data);  
              if(strresult.type == 'success'){
            	  $.cookie('sessionid', strresult.resultData.row.sessionid, {expires: 7}); 
            	  location.href = "/liuliang/index";	  
              }else{
            	  alert(strresult.message);
              }    
          },
          error: function(data) {
              alert("error:"+data.responseText);
           }
      });
  }
  
  function keyActionSubmit(){
	  if (event.keyCode == 13) {
		  actionSubmit();
	   }
  }
  
  function zhuce() {
	  location.href = "/liuliang/zhuce";
  }
</script>


</body></html>