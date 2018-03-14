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
	<script type="text/javascript" src="<%=context%>/liuliang/js/fanye.js"></script>
    <title>流量超市</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>

	<div class="container1">
	<div class="indexcontainer">
		<div class="breadcrumb">当前位置：订单查询 </div>
        <div style="margin-top:10px">
        	<table class="table" id="records" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
			</table>	
		</div>
		<div id="fanye">

		</div>
	</div>
</div>
<script>

  
	var fanye = new FanYe("fanye","getRecord",0,20,1);
	//流量数据表格生成
	function getRecord(current) {
		if(!fanye.check(current)){
			return;
		}
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/xiaofei.do",
			data : {
				"sessionid" : $.cookie('sessionid'),
				"current" : current,
				"limit" : 20
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					insertRecords(strresult);
					fanye.init(strresult.resultData.total,current);	
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}

	getRecord(1);

	var tabledata;
	function insertRecords(returndata) {
		tabledata = returndata.resultData.rows;
		var $table = $('#records');
		
		var trthhtml ="<tr><th>编号</th> <th>套餐名称</th><th>单价</th>"
						 +"<th>份数</th><th>金额</th><th>总条数</th><th>剩余条数</th><th>购买时间</th>"
						 +"<th>有效期</th><th>状态</th></tr> ";
		var html = trthhtml;
		for(var i = 0;i<tabledata.length;i++){
			var row = tabledata[i];
			var status = "未付款";
			var optstatus = "<input type=\"button\" value=\"确认付款\" onclick=\"mealsale(tabledata["+i+"])\"/>";
			if(row.status == 1){
				status = "已付款";
				optstatus = status
			}
			var statusid = "status"+row.id;
			var goumaiid = "goumai"+row.id;
			var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.mealName+"</td><td>"+row.danjia+"</td><td>"+row.fenshu+"</td>"
						+"<td>"+row.jine+"</td><td>"+row.total+"</td><td>"+row.sytiaoshu+"</td><td>"+row.createDateStr+"</td><td>"+row.validityDateStr+"</td>"
						+"<td id="+statusid+">"+status+"</td>"
						+"</tr> "
			html+=trtdhtml;
		}
		$table.html(html);
	}
	
  
</script>


</body></html>