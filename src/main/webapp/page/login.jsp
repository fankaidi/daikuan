<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="./page/index.css">
	<link rel="stylesheet" href="./page/login/login.css" />
	<script type="text/javascript" src="./page/js/jquery.js"></script>
    <title>线上贷</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="page">
	<div class="loginwarrp">
		<div class="logo">管理员登陆</div>
        <div class="login_form">
			<form id="Login" name="Login" method="post" onsubmit="" action="">
				<li class="login-item">
					<span>电话号码：</span>
					<input type="text" id="username" name="username" class="login_input" >
                                        <span id="count-msg" class="error"></span>
				</li>
				<li class="login-item">
					<span>密　码：</span>
					<input type="password" id="password" name="password" class="login_input" >
                                        <span id="password-msg" class="error"></span>
				</li>
				
				<li class="login-sub">
					<input type="button" class="submit" value="确认无误并提交申请" onclick="actionSubmit()">
				</li>                      
           </form>
		</div>
	</div>
</div>
	<jsp:include page="footer.jsp" flush="true"/> 
<script>
  function actionSubmit() {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/userlogin.do",
          data: $('#Login').serialize(),
          success: function (data) {
              var strresult = $.parseJSON(data);  
              if(strresult.type == 'success'){
            	  location.href = "/home.do"
              }else{
            	  alert(strresult.message);
              }    
          },
          error: function(data) {
              alert("error:"+data.responseText);
           }
      });
  }
</script>


</body></html>