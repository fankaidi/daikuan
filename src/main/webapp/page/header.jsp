<%@page import="com.kensure.ktl.user.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	UserInfo user = (UserInfo)request.getAttribute("user");
	
%>
<div class="header">
	<div class="head clearfix">
		<%if(user == null){ %>
		<a class="tohome" href="login.do">登录</a> 
		<a class="tohome" href="adduser.do">注册</a> 
		<%}else{ %>
		<a class="tohome" >你好，<%=user.getName() %></a> 
		<%} %>
		<a class="tohome" href="addmoney.do">申请贷款</a> 
		<a
			class="tohome <%=url.indexOf("index") != -1 ? "now" : ""%>" href="home.do">线上贷首页</a>

		<a class="help <%=url.indexOf("help") != -1 ? "now" : ""%>" href="help.do">帮助中心</a>

		<a class="toabout <%=url.indexOf("aboutus") != -1 ? "now" : ""%>"
			href="aboutus.do">关于我们</a>
	</div>
</div>