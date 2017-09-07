<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>线上贷</title>
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

<body>
<div class="w">
  <div id="preloader">
    <div id="status">
      <p class="center-text">加载中…<em>网速有点不给力哦!</em> </p>
    </div>
  </div>

  <header>
    <div class="header">
      <h2>用户信用审核</h2>
    </div>
  </header>

  <div class="page">
    <div class="main">
      <form id="adduser" name="adduser" method="post" action="">
       <input type="hidden" value="  <%=request.getAttribute("id")%>" name="sessionid">  
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="姓名*" value="" name="name" >
       </div>
       
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="QQ*" name="qq">        
          </div>
          
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="身份证*" name="card">        
          </div>
          
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="芝麻分*" value="" name="zhimafen">
         </div>
                
         <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="花呗额度*" value="" name="huabeiedu">
         </div>
         
         <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="借呗额度*" value="" name="jiebeiedu">
         </div>
          
		<div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="借贷宝" value="" name="jiedaibao">
         </div>
         
        <div class="item item-username">
          <input class="txt-input txt-username" type="text" placeholder="年龄" value="" name="year">
         </div>
         
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-primary" href="#" onclick="actionSubmit()">提交审核</a> </div>
      </form>
    </div>

  </div>
</div>
</body>
</html>

<script>
$(function(){ $('input, textarea').placeholder(); });
	function actionSubmit() {
		  var f = document.adduser;
		  if(!f.name.value){
			  alert("请输入姓名");
			  return;
		  }
		  if(!f.qq.value){
			  alert("请输入qq");
			  return;
		  }
		  if(!f.card.value){
			  alert("请输入身份证");
			  return;
		  }
		  
		  if(!f.zhimafen.value){
			  alert("请输入芝麻分");
			  return;
		  }
		 
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "/mobile/updateuser.do",
	          data: $('#adduser').serialize(),
	          success: function (data) {
	        	  var strresult = $.parseJSON(data);  
	              if(strresult.type == 'success'){
	            	  location.href = "/mobile/moneylist.do?id="+strresult.resultData.row
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