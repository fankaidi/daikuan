<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=context%>/liuliang/index.css">
	<link rel="stylesheet" href="<%=context%>/liuliang/login.css" />
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=context%>/jqtable/jquery.cookie.js"></script>
    <title>流量超市</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>

	<div class="container1">
	<div class="indexcontainer">
		<div class="breadcrumb">当前位置：首页</div>
        <div style="margin-top:10px">
			<div class="shouyeshekuai" style="width:400px;height:140px; ">
				基础流量余额:<br/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="basetotal">0</span>条。<br/><br/>
				
				如需购买，请联系客服QQ435872352
			</div>
			<div class="shouyeshekuai" style="margin-left:10px;width:400px;height:140px;">
				套餐情况:<br/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				您还有<span id="mealtotal">0</span>条的免费流量<br/>	
				最近过期时间：<span id="mealguoqi">无</span>	<br/>	
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/liuliang/tuijian">推荐套餐>></a>		  
			</div>
			<div class="shouyeshekuai" style="margin-left:10px;width:380px;height:140px;">
				使用情况:<br/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				您还可购买流量<span id="totalliuliang">0</span>条<br/>	
				您累计购买流量<span id="leijiliuliang">0</span>条		  
			</div>
			<div class="clearfloat" style="margin-top:20px;"></div>
			<div class="shouyeshekuai" style="width:810px;height:140px;">
				最新通知:<br/>
				<span id="newnotice"></span>
			</div>
			<div class="shouyeshekuai" style="margin-left:10px;width:380px;height:140px;">
				消费情况:<br/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				您累计采购基本流量<span id="salebase">0</span>条<br/>	
				您累计采购套餐<span id="salemeal">0</span>个			  
			</div>
		</div>
	</div>
</div>
<script>
  function actionSubmit() {
	  $.ajax({
          type: "POST",
          dataType: "html",
          url: "/liuliangpost/userlogin.do",
          data: $('#Login').serialize(),
          success: function (data) {
              var strresult = $.parseJSON(data);  
              if(strresult.type == 'success'){
            	  location.href = "/home.do"
              }else{
            	  alert(strresult.message);
              }    
          },
          error: function(data) {
              alert("error:"+data.responseText);
           }
      });
  }
    
  //基础流量
  function baseliuliang() {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/baseliuliang.do",
			data : {
				"sessionid" : $.cookie('sessionid')
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					var $basetotal = $('#basetotal');
					$basetotal.html(strresult.resultData.row);
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
  baseliuliang();
  
  //套餐流量
  function mealliuliang() {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/mealliuliang.do",
			data : {
				"sessionid" : $.cookie('sessionid')
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					var $mealtotal = $('#mealtotal');
					$mealtotal.html(strresult.resultData.row);
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
  mealliuliang();
  
 //套餐最近过期时间
  function mealguoqi() {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/meallastone.do",
			data : {
				"sessionid" : $.cookie('sessionid')
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					var $mealguoqi = $('#mealguoqi');
					if(strresult.resultData.row){
						$mealguoqi.html(strresult.resultData.row.validityDateStr);
					}				
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
  mealguoqi();
  
  //可购买流量
  function totalliuliang() {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/totalliuliang.do",
			data : {
				"sessionid" : $.cookie('sessionid')
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					var $totalliuliang = $('#totalliuliang');
					$totalliuliang.html(strresult.resultData.row);
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
    totalliuliang();
    
    //累计购买流量
    function leijiliuliang() {
  		$.ajax({
  	          type: "POST",
  	          dataType: "html",
  	          url: "<%=context%>/liuliangpost/leijiliuliang.do",
  			data : {
  				"sessionid" : $.cookie('sessionid')
  			},
  			success : function(data) {
  				var strresult = $.parseJSON(data);
  				if (strresult.type == 'success') {
  					var $leijiliuliang = $('#leijiliuliang');
  					$leijiliuliang.html(strresult.resultData.row);
  				}else{
  					 alert(strresult.message);
  				}
  			},
  			error : function(data) {
  				alert("error:" + data.responseText);
  			}
  		});	
  	}
    leijiliuliang();
    
    
  	//最新消息
	function newnotice() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/newnotice.do",
			data : {
				"sessionid" : $.cookie('sessionid'),
				"limit" : 3
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					newnoticespan(strresult);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	newnotice();
	function newnoticespan(returndata) {
		var tabledata = returndata.resultData.rows;
		var $table = $('#newnotice');	
		var html = "";	
		if(tabledata){
			for(var i = 0;i<tabledata.length;i++){
				var row = tabledata[i];
				var trtdhtml = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+row.title+"【"+row.createDateStr+"】 <br/>"
				html+=trtdhtml;
			}
		}	
		$table.html(html);
	}
	
	 //累计采购基础订单
    function salebase() {
  		$.ajax({
  	          type: "POST",
  	          dataType: "html",
  	          url: "<%=context%>/liuliangpost/salebase.do",
  			data : {
  				"sessionid" : $.cookie('sessionid')
  			},
  			success : function(data) {
  				var strresult = $.parseJSON(data);
  				if (strresult.type == 'success') {
  					var $salebase = $('#salebase');
  					$salebase.html(strresult.resultData.row);
  				}else{
  					 alert(strresult.message);
  				}
  			},
  			error : function(data) {
  				alert("error:" + data.responseText);
  			}
  		});	
  	}
    salebase();
    
    //累计采购套餐订单
    function salemeal() {
  		$.ajax({
  	          type: "POST",
  	          dataType: "html",
  	          url: "<%=context%>/liuliangpost/salemeal.do",
  			data : {
  				"sessionid" : $.cookie('sessionid')
  			},
  			success : function(data) {
  				var strresult = $.parseJSON(data);
  				if (strresult.type == 'success') {
  					var $salemeal = $('#salemeal');
  					$salemeal.html(strresult.resultData.row);
  				}else{
  					 alert(strresult.message);
  				}
  			},
  			error : function(data) {
  				alert("error:" + data.responseText);
  			}
  		});	
  	}
    salemeal();
</script>


</body></html>