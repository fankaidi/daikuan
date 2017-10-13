<%@page import="com.kensure.ktl.user.model.UserInfo"%>
<%@page import="co.kensure.mem.DateUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.kensure.ktl.user.model.LoanMoney"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>91米贷</title>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport" />
<script type="text/javascript" src="./skin/js/jquery-1.7.2.min.js"></script>
  <style>
.car-txt {
    overflow: hidden;
    border-bottom: 1px solid #ececec;
}

.name {
    color: #FF6D02;
    font-size: 14px;
	
}

.time {
    color: #999;
    font-size: 12px;
    height: 20px;
    overflow: hidden;
    line-height: 20px;
}


em.ico-ding {
    width: 32px;
    line-height: 16px;
    text-align: center;
    font-size: 11px;
    overflow: hidden;  
    background-color: #FF6D02;
    color: #fff;
    display: inline-block;
    margin-right: 3px;
}
</style>
</head>

<body>
<%
List<LoanMoney> list = (List<LoanMoney>)request.getAttribute("list");
String fromdate = (String)request.getAttribute("fromdate");
String todate = (String)request.getAttribute("todate");
%> 
<form action="/mobile/adminread.do">
 		开始：<input type="date" value="<%=fromdate %>" name="fromdate"/>	
		截止：<input type="date" value="<%=todate %>" name="todate"/>	
<input id="btnconfirm" type="submit" value="查询" name="btnconfirm">
</form>
 		<div class='car-txt'> 
			<a href="/mobile/admin.do">查看未读</a>
		</div>
 		
		<%
	    for(LoanMoney m : list){
	    	UserInfo u = m.getUserinfo();
	    %>
		<div class='car-txt'>  
			<p class='name'> <em class="ico-ding">已读</em><a href="/mobile/userdo.do?id=<%=m.getId()%>"> <%=u.getName() %> <%=u.getMobile() %>  <%=m.getMoney() %>￥ </a></p>
			<p class='time'><%=DateUtils.format(m.getCreateDate(),DateUtils.MOBILE_TIME)%> <span><%=u.getYear() %>岁</span> <span>性别:<%=u.getXb() %></span> QQ:<%=u.getQq() %></p>		
		</div>
		  <%} %>
</body>
</html>

