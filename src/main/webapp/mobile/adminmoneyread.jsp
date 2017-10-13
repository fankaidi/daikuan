<%@page import="com.kensure.ktl.user.model.UserInfo"%>
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
<meta http-equiv="refresh" content="300">
   <style>
        table,table tr th, table tr td { border:1px solid #0094ff; }
        table { min-height: 25px; line-height: 25px; text-align: right; border-collapse: collapse; padding:2px;}   
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
<a href="/mobile/admin.do">查看未读</a>
</form>

<div class="w">
    <table >
	    <tr><th>名字</th><th>电话</th><th>QQ</th><th>预借金额</th><th>芝麻分</th><th>花呗额度</th><th>借呗额度</th><th>借贷宝</th><th>年龄</th><th>借款时间</th></tr>
	    <%
	    for(LoanMoney m : list){
	    	UserInfo u = m.getUserinfo();
	    %>
        <tr><td><%=u.getName() %></td>
            <td><%=u.getMobile() %></td>
            <td><%=u.getQq() %></td>
            <td><%=m.getMoney() %></td>
            <td><%=u.getZhimafen() %></td>
            <td><%=u.getHuabeiedu() %></td>
            <td><%=u.getJiebeiedu() %></td>
            <td><%=u.getJiedaibao() %></td>
            <td><%=u.getYear() %></td>
            <td><%=DateUtils.format(m.getCreateDate())%></td>
        </tr>
      <%} %>
    </table>
</div>
</body>
</html>

<script>
	
	
</script>