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
		<div class="breadcrumb">当前位置：套餐定义  <span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/baseinfo" class="zicaidan">流量数据管理</a> 
		<a href="<%=context%>/liuliang/mealsalelist" class="zicaidan">套餐销售记录</a>
		<a href="<%=context%>/liuliang/meallist" class="zicaidan">套餐定义</a></span></div>
		<div> 
			<input type="button" value="新增套餐" onclick="addmeal()"/>	
    	</div>
        <div style="margin-top:10px">
        	<table class="table" id="records" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
			</table>	
		</div>
	</div>
</div>
<script>
  
   //流量数据表格生成
	function getRecord() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangmanagerpost/meallist.do",
			data : {
				"sessionid" : $.cookie('sessionid'),
				"current" : 1
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					insertRecords(strresult);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}

	getRecord();

	function insertRecords(returndata) {
		var data = returndata.resultData.rows;
		var $table = $('#records');
		
		var trthhtml ="<tr><th>编号</th> <th>名称</th><th>类型</th><th>单价</th><th>金额</th>"
						 +"<th>条数</th><th>有效期</th><th>推荐</th><th>操作</th></tr> ";
		var html = trthhtml;
		for(var i = 0;i<data.length;i++){
			var row = data[i];
			var typename = row.type==2?"固定单价":"固定条数";
			var tuijianname = row.tuijian==1?"推荐":"不推荐";
			var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.name+"</td><td>"+typename+"</td><td>"+row.danjia+"</td><td>"+row.jine+"</td>"
						+"<td>"+row.tiaoshu+"</td><td>"+row.youxiaoqi+"个月</td><td>"+tuijianname+"</td><td><input type=\"button\" value=\"销售套餐\" onclick=\"mealsale("+row.id+")\"/></td></tr> "
			html+=trtdhtml;
		}
		$table.html(html);
	}
	
	function mealsale(id) {
		 location.href = "/liuliang/mealsale?id="+id;
	}
	
	function addmeal() {
		 location.href = "/liuliang/mealadd";
	}
	
	
</script>


</body></html>