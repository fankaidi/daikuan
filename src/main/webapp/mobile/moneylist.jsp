<%@page import="co.kensure.mem.DateUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.kensure.ktl.user.model.LoanMoney"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>线上贷</title>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport" />
<link type="text/css" rel="stylesheet" href="./skin/css/basic.css" />
<script type="text/javascript" src="./skin/js/jquery-1.7.2.min.js"></script>
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
      <h2>借款记录</h2>
    </div>
  </header>

  <div class="page">
    <div class="main">  
    	<% List<LoanMoney> loanlist = (List<LoanMoney>)request.getAttribute("list");
    	for(LoanMoney m:loanlist){
    	%> 
        <div class="ui-btn-wrap">时间：<%=DateUtils.format(m.getCreateDate()) %> 金额:<%=m.getMoney() %>元</div>
     	<%} %>
    </div>
    <script type="text/javascript" >
  
	</script>
  </div>
</div>
</body>
</html>

<script>
	
	
</script>