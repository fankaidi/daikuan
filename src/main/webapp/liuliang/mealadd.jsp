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
		<div class="breadcrumb">当前位置：流量大厅-套餐新增  <span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/baseinfo" class="zicaidan">流量数据管理</a> 
		<a href="<%=context%>/liuliang/mealsalelist" class="zicaidan">套餐销售记录</a>
		<a href="<%=context%>/liuliang/meallist" class="zicaidan">套餐定义</a></span></div>
		<div class="loginwarrp">
		<div class="logo">套餐新增</div>
	       <div class="login_form">
				<form id="sale" method="post" >
			    	<li class="login-item">
						<span>套餐名称：</span>
						<input type="text" name="name" class="login_input" >            
	                    <input type="hidden" name="sessionid" id="sessionid" value=""/>         
					</li>
					<li class="login-item">
						<span>是否推荐：</span>
						<select id="tuijian" name="tuijian">
							<option value ="0">不推荐</option>
							<option value ="1">推荐</option>
						</select>
	                                       
					</li>
					<li class="login-item">
						<span>条数：</span>
						<input type="text" name="tiaoshu" class="login_input" >                                   
					</li>
					
					<li class="login-item">
						<span>套餐价格：</span>
						<input type="text" name="danjia" class="login_input" >                                  
					</li>
					<li class="login-item">
						<span>有效期（单位月）：</span>
						<input type="text" name="youxiaoqi" class="login_input" value="12" >                                  
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
	          url: "<%=context%>/liuliangmanagerpost/mealadd.do",
	          data: $('#sale').serialize(),
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					alert("新增成功");
					 location.href ="/liuliang/meallist";
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	
</script>


</body></html>