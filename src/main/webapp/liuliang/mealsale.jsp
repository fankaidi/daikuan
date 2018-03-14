<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
    String id = (String)request.getAttribute("id");
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
		<div class="breadcrumb">当前位置：套餐定义  <span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/baseinfo" class="zicaidan">流量数据管理</a> 
		<a href="<%=context%>/liuliang/mealsalelist" class="zicaidan">套餐销售记录</a>
		<a href="<%=context%>/liuliang/meallist" class="zicaidan">套餐定义</a></span></div>
		<div class="loginwarrp">
		<div class="logo">套餐销售</div>
	       <div class="login_form">
				<form id="sale" method="post" >
			    	<li class="login-item">
						<span>套餐名称：</span>
						<span id="name"></span>
	                    <input type="hidden" name="id" value="<%=id%>" >   
	                    <input type="hidden" name="sessionid" id="sessionid" value=""/>         
					</li>
					<li class="login-item">
						<span>购买人：</span>
						<select id="userId" name="userId">
						</select>
	                                       
					</li>
					
					<li class="login-item">
						<span>单价：</span>
						<span id="danjia"></span>                                  
					</li>
					
					<li class="login-item">
						<span>购买数量：</span>
						<input type="text" name="shuliang" class="login_input" >                                   
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
  
   //套餐定义数据获取
	function getMealInfo() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangmanagerpost/mealinfo.do",
			data : {
				"id" : "<%=id%>"
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					initForm(strresult);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}

	function initForm(returndata) {
		var data = returndata.resultData.row;
		var $name = $('#name');
		$name.html(data.name);
		var $danjia = $('#danjia');
		$danjia.html(data.danjia);
	}
	
	function getUserList() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangmanagerpost/userlist.do",
			data : {
				"sessionid" : $.cookie('sessionid')
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					initSelect(strresult);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	function initSelect(returndata) {
		var data = returndata.resultData.rows;
		var $userId = $('#userId');
		var html = "";
		for(var i in data){
			var row = data[i];
			var ophtml = "<option value=\""+row.id+"\">"+row.name+"</option>";
			html+=ophtml;
		}
		$userId.html(html);
	}
	getMealInfo();
	getUserList();


	function addMealSale() {
		  $('#sessionid').val($.cookie('sessionid'));
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangmanagerpost/mealsale.do",
	          data: $('#sale').serialize(),
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					alert("销售成功，请去销售记录中确认付款");
					 location.href ="/liuliang/mealsalelist";
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	
</script>


</body></html>