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
<script type="text/javascript" src="./skin/js/jquery-1.7.2.min.js"></script>
   <style>
        table,table tr th, table tr td { border:1px solid #0094ff; }
        table { min-height: 25px; line-height: 25px; text-align: right; border-collapse: collapse; padding:2px;}   
    </style>
</head>

<body>
<div class="w">
<%List<LoanMoney> list = (List<LoanMoney>)request.getAttribute("list");%>
		<div class='car-txt'> 
			<%=list.size()%>个未读，将在<button id="btn" type="button">300s</button>后刷新,<a href="/mobile/adminread.do">查看已读</a>
		</div>
    <table >
	    <tr><th>名字</th><th>电话</th><th>性别</th><th>QQ</th><th>预借金额</th><th>芝麻分</th><th>花呗额度</th><th>借呗额度</th><th>借贷宝</th><th>年龄</th><th>借款时间</th><th>操作</th></tr>
	    <%
	    for(LoanMoney m : list){
	    	UserInfo u = m.getUserinfo();
	    %>
        <tr><td><%=u.getName() %></td>
            <td><%=u.getMobile() %></td>
            <td><%=u.getXb() %></td>
            <td><%=u.getQq() %></td>
            <td><%=m.getMoney() %></td>
            <td><%=u.getZhimafen() %></td>
            <td><%=u.getHuabeiedu() %></td>
            <td><%=u.getJiebeiedu() %></td>
            <td><%=u.getJiedaibao() %></td>
            <td><%=u.getYear() %></td>
            <td><%=DateUtils.format(m.getCreateDate())%></td>
            <td><input type="button" id="test2" onclick="sendsms(<%=m.getId() %>)" value="设为已读"/>  </td>
        </tr>
      <%} %>
    </table>
</div>
</body>
</html>

<script>
	function sendsms(id){
		 $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "/mobile/userdo.do",
	          data: {"id":id},
	          success: function (data) {
	        	  
	          },
	          error: function(data) {
	              alert("error:"+data.responseText);
	           }
	      });	
	}
	
	function invokeSettime(obj){
	    var countdown=300;
	    settime(obj);
	    function settime(obj) {
	        if (countdown > 0) {
	            $(obj).attr("disabled",true);
	            $(obj).text("(" + countdown + ") s");
	            countdown--;
	        }
	        setTimeout(function() {settime(obj) },1000)
	    }
	}
	new invokeSettime("#btn");
	
</script>