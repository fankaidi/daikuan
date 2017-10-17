<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>91米贷注册</title>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport" />
<link type="text/css" rel="stylesheet" href="./skin/css/basic.css" />
<script type="text/javascript" src="./skin/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="./skin/js/jquery.placeholder.min.js"></script>
<script>
$(window).load(function() {
	$("#status").fadeOut();
	$("#preloader").delay(350).fadeOut("slow");
})
</script>
</head>
<% Long uuid = (Long)request.getAttribute("uuid"); %>
<body class="mobilebody">
<div class="w">
  <div id="preloader">
    <div id="status">
      <p class="center-text">加载中…<em>网速有点不给力哦!</em> </p>
    </div>
  </div>

  <header>
    <div class="header">
    <a class="new-a-back" href="javascript:history.back();"> <span>返回</span> </a>
      <h2>91米贷注册</h2>
    </div>
  </header>
  <div>
    	<img src="./skin/images/head.png" style="width:100%">
    </div>
  <div class="page">
  	
    <div class="main">
      <form id="adduser" name="adduser" method="post" action="">
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="常用手机号" value="" name="mobile" maxlength="11" size="11">
       </div>
		<div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="预借金额" value="" name="money">
         </div>
        <div class="item item-captcha">
          <div class="input-info">
            <input class="txt-input txt-captcha" type="text" placeholder="验证码" name="smscode" autocomplete="off" maxlength="6" size="11">
           <span id="captcha-img" style="width:px;height:25px;"><a href="#" onclick="sendsms()"> 发送验证码</a> </span> </div>
        </div>
	
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-primary" href="#" onclick="actionSubmit()">用户注册</a> </div>
		<div class="ui-btn-wrap">联系QQ:1524246578 </div>
      </form>
    </div>
    <script type="text/javascript" >
  
	</script>
  </div>
</div>
</body>
</html>

<script>
	$(function(){ $('input, textarea').placeholder(); });

	function sendsms(){
		 var f = document.adduser;
		if(!f.mobile.value){
			  alert("请输入手机");
			  return;
		  }
		if(f.mobile.value.length != 11){
			  alert("手机号码必须为11位");
			  return;
		  }
		
		 $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "/mobile/smsuser.do",
	          data: $('#adduser').serialize(),
	          success: function (data) {
	        	  var strresult = $.parseJSON(data);  
	              if(strresult.type == 'success'){
	            	  $('#captcha-img').text("已发送");
	              }else{
	            	  alert(strresult.message);
	              }    
	          },
	          error: function(data) {
	              alert("error:"+data.responseText);
	           }
	      });	
	}
	
	function actionSubmit() {
		  var f = document.adduser;
		  if(!f.mobile.value){
			  alert("请输入手机");
			  return;
		  }
		  if(!f.money.value){
			  alert("请输入预借金额");
			  return;
		  }
		  
		  if(!f.smscode.value){
			  alert("请输入验证码");
			  return;
		  }
		  var data = $('#adduser').serialize();
		  data = data+'&uuid=<%=uuid%>';
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "/mobile/commituser.do",
	          data: data,
	          success: function (data) {
	        	  var strresult = $.parseJSON(data);  
	              if(strresult.type == 'success'){
	            	  location.href = "/mobile/userinfo.do?id="+strresult.resultData.row;
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