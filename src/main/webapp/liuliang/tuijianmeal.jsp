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
		<div class="breadcrumb">当前位置：流量大厅-推荐套餐<span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/newbaseinfo" class="zicaidan">最新流量</a> 
		<a href="<%=context%>/liuliang/yigou" class="zicaidan">已购流量</a>
		<a href="<%=context%>/liuliang/tuijian" class="zicaidan">推荐套餐</a>
		</span></div>
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
            url: "<%=context%>/liuliangpost/tuijian.do",
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
		
		var trthhtml ="<tr><th>编号</th> <th>套餐名称</th><th>单价</th><th>条数</th><th>有效期</th>"
						 +"<th>操作</th></tr> ";
		var html = trthhtml;
		if(tabledata){
			for(var i = 0;i<tabledata.length;i++){
				var row = tabledata[i];
				var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.name+"</td><td>"+row.danjia+"</td><td>"+row.tiaoshu+"</td><td>"+row.youxiaoqi+"个月</td>"
				+"<td><a href=\"tencent://Message/?Uin=435872352&websiteName=q-zone.qq.com&Menu=yes\">客服QQ435872352<img border=\"0\" SRC=\"http://wpa.qq.com/pa?p=1:1360011:14\"></a></td></tr>"
				html+=trtdhtml;
			}
		}	
		$table.html(html);
	}
	
  
</script>


</body></html>