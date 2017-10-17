<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>91米贷注册</title>
		<script src="./assets/hm.js"></script>
		<script src="./assets/fix.js"></script>
		<meta name="viewport" content="width=750,initial-scale=2.0, minimum-scale = 0.5, maximum-scale = 0.5,user-scalable=no, target-densitydpi=device-dpi">
		<link rel="stylesheet" href="./assets/default.css">
		<link rel="stylesheet" href="./assets/page2.css">
		<script src="./assets/jquery-1.11.1.min.js"></script>
		<script src="./assets/dfp_js_3.3.2.js"></script>
		<script src="./assets/pageS2.js"></script>
		<script src="./assets/cityjson"></script>
		<script src="./assets/jquery.cookie.js"></script>
		<script src="./assets/statistics.js"></script>
	</head>


<% Long uuid = (Long)request.getAttribute("uuid"); %>
<body>
		<input type="hidden" id="APP_PATH_ROOT" value="https://api.zanfq.com:38087">
		<input type="hidden" id="BIGANGWAN_PATH_ROOT" value="https://api.bigangwan.com:38091">
		<input id="channel" class="inp_log" type="hidden" value="034" name="channel">
		<div class="top_img">
			<img class="header" src="./assets/201.jpg" alt="">
		</div>
		<form id="adduser" name="adduser" method="post" action="">
			<div class="bg_warp">
				<div class="mainPading">
					<div class="bgImg">
						<img src="./assets/203.png">
						<div class="login_input_wrap">
							<img src="./assets/204.png" alt="" class="login_img">
							<input type="tel" id="mobile" name="mobile" class="moilePhone borderDaius" placeholder="请输入您的手机号" maxlength="11">
							<input type="tel" class="moilePhone borderDaius" name="money" placeholder="请输入预借金额" maxlength="11" style="margin-top:20px;">
							<div class="YZ_wrap">
								<input type="text" id="smsCode" class="YZ_input borderDaius" name="smscode"  placeholder="请输入验证码" maxlength="6">
								<div id="sendSmsCode" class="btn_YZ">获取验证码</div>
							</div>
							<div class="btnGo" id="register">马上申请</div>
						</div>
	
					</div>
					<div class="button_img">
						<img src="./assets/207.png" alt="">
					</div>
					<div class="footer">
						<span class="footFt">91米贷---专业的借款平台</span>
						<span class="footFt">北京心向优势信息技术有限公司 </span>
						<span class="footFt">市场有风险，借款需谨慎。具体放款时间视个人情况而定。</span>
					</div>
				</div>
			</div>

		</form>
		<div id="userdata_el" style="visibility: hidden; position: absolute;"></div>
		<div id="ads"></div>
	</body>
</html>

<script>
	
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