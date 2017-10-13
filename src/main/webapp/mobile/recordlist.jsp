<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	String fromdate = (String) request.getAttribute("fromdate");
	String todate = (String) request.getAttribute("todate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>流量统计</title>
<link type="text/css" rel="stylesheet" href="<%=context%>/ly/skin/css/taglist.css" />
<link rel="stylesheet" href="<%=context%>/jqtable/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=context%>/jqtable/bootstrap-table.css" />
<script type="text/javascript" src="<%=context%>/jqtable/jquery.min.js"></script>
<script type="text/javascript" src="<%=context%>/jqtable/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=context%>/jqtable/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=context%>/jqtable/bootstrap-table-zh-CN.js"></script>
</head>

<body>

	<div>
		<form name="tjform" id="tjform">
			<div class="form-group" class="col-sm-12">
				开始：<input type="date" value="<%=fromdate %>" name="fromdate"/>	
				截止：<input type="date" value="<%=todate %>" name="todate"/>	
				<button class="btn btn-default" type="button" onclick="getRecord()">查询</button>			
			</div>	
		</form>	
		<table id="records">
		</table>

	</div>
</body>
</html>

<script type="text/javascript">
	var $table = $('#records');
	$table.bootstrapTable({
		data : [],
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		singleSelect : false,
		pagination : true, //分页
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 100, //每页的记录行数（*）
		search : false, //显示搜索框
		columns : [
				{
					field : 'cid',
					title : '渠道'
				},
				{
					field : 'cnt',
					title : '访问量'
				},
				{
					field : 'succnt',
					title : '注册量'
				}]
	});
 	
 	//下面获取record
	function getRecord() {
		 var data = $('#tjform').serialize();
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/mobile/channellist.do",
			data : data,
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
		$table.bootstrapTable('load',data); 
	}

</script>