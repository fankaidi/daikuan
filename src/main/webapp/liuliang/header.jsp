<%@page import="com.kensure.ktl.user.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>
<div class="header">
	<div class="head clearfix">
		<div class="top_img">
		
		<img class="header1" src="<%=context%>/liuliang/images/logo-small.jpg" alt="">
	
		<a class="tohome" style="padding-left:200px;" id="userinfo">你好, </a>	
		<a class="tohome" href="/liuliang/index">首页</a> 
		<a class="tohome" href="/liuliang/newbaseinfo">流量大厅</a>
		<a class="tohome" href="/liuliang/xiaofei">订单查询</a>
		<a class="tohome" href="/liuliang/newnotice">最新通知</a>
		<a class="tohome" href="tencent://Message/?Uin=435872352&websiteName=q-zone.qq.com&Menu=yes">客服QQ435872352<img border="0" SRC="http://wpa.qq.com/pa?p=1:1360011:14" alt="点击这里给我发消息"></a>
		<a class="tohome" href="/liuliang/baseinfo" id="houtaiguanli"></a>
		</div>
	</div>
</div>

<script>
  function setheaderinfo() {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/liuliangpost/userinfo.do",
          data: "&sessionid="+$.cookie('sessionid'),
          success: function (data) {
              var strresult = $.parseJSON(data);  
              if(strresult.type == 'success'){
            	  $('#userinfo').html("你好，<span class=\"tuichu\" onclick=\"yonghuxinxi()\">"+strresult.resultData.row.name+"</span><span class=\"tuichu\" onclick=\"tuichu()\">退出</span>"); 
            	  if(strresult.resultData.row.id < 0){
            		  $('#houtaiguanli').html("后台管理"); 
            	  }   	 
              }else{
            	  location.href = "/liuliang/login";
              }    
          },
          error: function(data) {
        	  location.href = "/liuliang/login";
           }
      });
  }
  function tuichu() {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/liuliangpost/userlogout.do",
          data: "&sessionid="+$.cookie('sessionid'),
          success: function (data) {
              var strresult = $.parseJSON(data);  
              $.cookie('sessionid',null);
              location.href = "/liuliang/login";    
          },
          error: function(data) {
        	  $.cookie('sessionid',null);
        	  location.href = "/liuliang/login";
           }
      });
  }
  //进入修改页面
  function yonghuxinxi() {
	  location.href = "/liuliang/updatepwd";
  }
  
  setheaderinfo();
</script>