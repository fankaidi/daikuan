<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=context%>/liuliang/index.css">
	<link rel="stylesheet" href="<%=context%>/liuliang/login.css" />
	<link rel="stylesheet" href="<%=context%>/liuliang/table.css" />
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.cookie.js"></script>
    <title>流量超市</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>

	<div class="container1">
	<div class="indexcontainer">
		<div class="breadcrumb">当前位置：用户信息-修改密码  </div>
		<div class="loginwarrp">
		<div class="logo">密码修改</div>
	       <div class="login_form">
				<form id="sale" method="post" >
			    	<li class="login-item">
						<span>原密码：</span>
						<input type="password" name="oldpwd" class="login_input" >            
	                    <input type="hidden" name="sessionid" id="sessionid" value=""/>         
					</li>
					<li class="login-item">
						<span>新密码：</span>
						<input type="password" name="newpwd" class="login_input" >                                   
					</li>
					
					<li class="login-item">
						<span>确认密码：</span>
						<input type="password" name="copypwd" class="login_input" >                                  
					</li>			
					<li class="login-sub">
						<input type="button" class="submit" value="提交" onclick="addMealSale()"> 
					</li> 
		    	</form>
	    	</div>
	    </div>
	</div>
</div>
<script>
  
   

	function addMealSale() {
		  $('#sessionid').val($.cookie('sessionid'));
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/updatepwd.do",
	          data: $('#sale').serialize(),
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					alert("密码修改成功");
					location.href ="/liuliang/index";
				}else{
					alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	
</script>


</body></html>