<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<title>注册账户`靠信誉身份证手机就可以借钱！</title>
<link type="text/css" rel="stylesheet" href="./page/adduser/style.css">
<script type="text/javascript" src="./page/js/jquery.js"></script>
</head>
<body>
<div id="header"><div class="logo"><img src="./page/img/logoheader.png"></div></div>
<div id="container">
	<div class="index">
		<div class="area">
			<p class="title">请选择你要借款的金额</p>
			<ul>
				<li><a  onclick="actionSubmit(1000)">1000元</a></li>
				<li><a  onclick="actionSubmit(2000)">2000元</a></li>
				<li><a onclick="actionSubmit(3000)">3000元</a></li>
				<li><a onclick="actionSubmit(5000)">5000元</a></li>
			</ul>
		</div>
	</div>
</div>
</body></html>
  <script>
  function actionSubmit(money) {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/commitmoney.do",
          data: {"money":money},
          success: function (data) {
        	  var strresult = $.parseJSON(data);  
              if(strresult.type == 'success'){
            	  location.href = "/moneylist.do"
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

     