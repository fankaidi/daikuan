<%@page import="com.kensure.ktl.user.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>91米贷</title>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport" />
<meta http-equiv="refresh" content="300">
<script type="text/javascript" src="./skin/js/jquery-1.7.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="./skin/css/basic.css" />
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
<div class="header">
    <a class="new-a-back" href="javascript:history.back();"> <span>返回</span> </a>
      <h2>用户详情</h2>
    </div>
 <%UserInfo u = (UserInfo)request.getAttribute("userinfo");%>

		<div class='car-txt'> 
			名字:<%=u.getName() %>
		</div>
		 <div class='car-txt'> 
			电话:<%=u.getMobile() %>
		</div>
			<div class='car-txt'> 
			年龄:<%=u.getYear() %>
		</div>
			<div class='car-txt'> 
			QQ:<%=u.getQq() %>
		</div>
		<div class='car-txt'> 
			性别:<%=u.getXb() %>
		</div>

		<div class='car-txt'> 
			芝麻分:<%=u.getZhimafen() %>
		</div>
		
		<div class='car-txt'> 
			花呗额度:<%=u.getHuabeiedu() %>
		</div>
		
		<div class='car-txt'> 
			借呗额度:<%=u.getJiebeiedu() %>
		</div>
		
		<div class='car-txt'> 
			借贷宝:<%=u.getJiedaibao()%>
		</div>
 	
</body>
</html>

<script type="text/javascript"> 
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