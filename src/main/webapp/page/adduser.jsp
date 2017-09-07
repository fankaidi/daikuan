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
	<div class="add">
		<div class="area">
			<p class="title1">我们将在第一时间为您完成审批</p>
			<p class="title2">已有 1000000+ 人审请</p>
			<div id="title3" class="title3">
				<div class="left">以下内容需为申请人本人名下真实有效的信息。若填写虚假信息，会影响您的审批结果。</div>
				<div class="right">X</div>
			</div>
			<form method="post" id="adduser" name="adduser">
				<input type="hidden" name="money" value="1">
				<dl style="border:none">
					<dt>姓名</dt>
					<dd><input type="text" name="name" maxlength="20" class="input" placeholder="请输入真实姓名（必填）">*</dd>
				</dl>
				<dl>
					<dt>手机</dt>
					<dd><input type="text" name="mobile" maxlength="11" class="input" placeholder="请输入手机号码（必填）">*</dd>
				</dl>
				<dl>
					<dt>密码</dt>
					<dd><input type="password" name="pwd" maxlength="20" class="input" placeholder="请输入密码（必填）">*</dd>
				</dl>
				<dl>
					<dt>确认密码</dt>
					<dd><input type="password" name="repwd" maxlength="20" class="input" placeholder="请输入密码（必填）">*</dd>
				</dl>				
				<dl>
					<dt>QQ</dt>
					<dd><input type="text" name="qq" maxlength="12" class="input" placeholder="请输入常用QQ（必填）">*</dd>
				</dl>
				<dl>
					<dt>性别</dt>
					<dd><select name="xb" class="select"><option value="0" selected="">先生</option><option value="1">女士</option></select></dd>
				</dl>
				<dl>
					<dt>年龄</dt>
					<dd><input type="text" name="year" maxlength="8" class="input" placeholder="请输入年龄（必填）">*</dd>
				</dl>
				<dl>
					<dt>工作单位</dt>
					<dd><input type="text" name="danwei" maxlength="100" class="input" placeholder="请如实填写工作单位（必填）">*</dd>
				</dl>
				<dl>
					<dt>岗位职务</dt>
					<dd><input type="text" name="zhiwu" maxlength="20" class="input" placeholder="请填写当前岗位职务（必填）">*</dd>
				</dl>
				<dl>
					<dt>单位电话</dt>
					<dd><input type="text" name="phone" maxlength="20" class="input" placeholder="单位电话格式：088-88888888"></dd>
				</dl>
				<dl>
					<dt>芝麻分</dt>
					<dd><input type="text" name="zhimafen" maxlength="8" class="input" placeholder="请如实填写芝麻信用分（必填）">*</dd>
				</dl>
				<dl>
					<dt>花呗额度</dt>
					<dd><input type="text" name="huabeiedu" maxlength="8" class="input" placeholder="请如实填写花呗额度（必填）">*</dd>
				</dl>
				<dl>
					<dt>借呗额度</dt>
					<dd><input type="text" name="jiebeiedu" maxlength="8" class="input" placeholder="如有 请填写借呗额度"></dd>
				</dl>
				<dl>
					<dt>信用卡</dt>
					<dd><input type="text" name="xinyongka" maxlength="8" class="input" placeholder="如有 请填写信用卡最高额度"></dd>
				</dl>
				<dl>
					<dt>借贷宝</dt>
					<dd><input type="text" name="jiedaibao" maxlength="8" class="input" placeholder="如有 请填写借贷宝已借额度"></dd>
				</dl>
				<dl>
					<dt> </dt>
					<dd></dd>
				</dl>
				<dl>
					<dt> </dt>
					<dd></dd>
				</dl>
				<div id="addsub"><input type="button" class="submit" value="确认无误并提交申请" onclick="actionSubmit()"></div>
			</form>
		</div>
	</div>
</div>
</body></html>
  <script>
  function actionSubmit() {
	  var f = document.adduser;
	  if(!f.name.value){
		  alert("请输入姓名");
		  return;
	  }
	  if(!f.mobile.value){
		  alert("请输入手机");
		  return;
	  }
	  if(!f.pwd.value){
		  alert("请输入密码");
		  return;
	  }
	  if(!f.qq.value){
		  alert("请输入qq");
		  return;
	  }
	  if(!f.year.value){
		  alert("请输入年龄");
		  return;
	  }
	  if(!f.danwei.value){
		  alert("请输入工作单位");
		  return;
	  }
	  if(!f.zhiwu.value){
		  alert("请输入岗位职务");
		  return;
	  }
	  if(!f.zhimafen.value){
		  alert("请输入芝麻分");
		  return;
	  }
	  if(!f.huabeiedu.value){
		  alert("请输入花呗额度");
		  return;
	  }

	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/commituser.do",
          data: $('#adduser').serialize(),
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

     