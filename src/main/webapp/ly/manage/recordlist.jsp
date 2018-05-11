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
	<script type="text/javascript" src="<%=context%>/common/http.js"></script>
    <title>记录</title>
</head>
<body>

	<div class="container1">
	<div class="indexcontainer">
        <div style="margin-top:10px">
        	<div><input type="button" value="新增" onclick="add()" /></div>
        	<table class="table" id="records" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
			</table>	
		</div>
		<div id="fanye">

		</div>
	</div>
</div>
<script>
  
   //流量数据表格生成
    var fanye = new FanYe("fanye","getRecord",0,20,1);
	function getRecord(current) {
		if(!fanye.check(current)){
			return;
		}
		var basedata = {
				sessionid:$.cookie('sessionid'),
				current:current,
				limit:20
		}
		var url = "<%=context%>/yj/titlelist.do";
		var data = $.param(basedata);
		var successdo = function(strresult){
			insertRecords(strresult);
			fanye.init(strresult.resultData.total,current);	
		}
		postdo(url,data,successdo);		
	}

	getRecord(1);
	
	var tabledata;

	function insertRecords(returndata) {
		tabledata = returndata.resultData.rows;
		var $table = $('#records');	
		var trthhtml ="<tr><th>编号</th> <th>标题</th><th>图片</th><th>状态</th><th>排序（小的排前面）</th><th>操作</th></tr> ";
		var html = trthhtml;
		if(tabledata){
			for(var i = 0;i<tabledata.length;i++){
				var row = tabledata[i];
				var statusname = row.status==1?"已发布":"未发布";
				var fabubutton = "<input id=\"fabu"+row.id+"\" type=\"button\" value=\"发布\" onclick=\"fabu(tabledata["+i+"])\" />";
				var quxiaobutton = "<input id=\"quxiao"+row.id+"\" type=\"button\" value=\"取消发布\" onclick=\"quxiao(tabledata["+i+"])\" />";
				var editbutton = "<input type=\"button\" value=\"编辑\" onclick=\"edit(tabledata["+i+"])\" />";
				var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.name+"</td><td><img width=\"100\" src=\""+row.pic+"\"/></td><td id=\"status"+row.id+"\">"+statusname+"</td>"
							+"<td >"+row.dorder+"</td><td>"+fabubutton+quxiaobutton+editbutton+"</td></tr> "
				html+=trtdhtml;
			}
		}
		$table.html(html);
	}
	
	function fabu(row) {
		if(row.status == 1){
			alert("您已发布!");
			return false;
		}
		var url = "<%=context%>/yj/fabu.do";
		var data = {
				"sessionid" : $.cookie('sessionid'),
				"id" : row.id
			};
		var successdo = function(strresult){
			row.status = 1;	
			var goumaiid = "status"+row.id;
			var $goumai = $('#'+goumaiid);
			$goumai.html("已发布");
			alert("发布成功");
		}
		postdo(url,data,successdo);
	}
	
	function quxiao(row) {
		if(row.status == 0){
			alert("当前状态是未发布!");
			return false;
		}
		var url = "<%=context%>/yj/quxiao.do";
		var data = {
				"sessionid" : $.cookie('sessionid'),
				"id" : row.id
			};
		var successdo = function(strresult){
			row.status = 0;	
			var goumaiid = "status"+row.id;
			var $goumai = $('#'+goumaiid);
			$goumai.html("未发布");
			alert("取消成功");
		}
		postdo(url,data,successdo);
		
	}
	
	function add(){
		var messageStr = "新增路线";
		var defaultStr = "请输入名称";	
		var content = window.prompt(messageStr, defaultStr);
		if(content == null){
			return;
		}		
		var url = "<%=context%>/yj/addTitle.do";
		var data = {
				"sessionid" : $.cookie('sessionid'),
				"name":content
			};
		var successdo = function(strresult){
			alert("新增成功");
			getRecord(1);
		}
		postdo(url,data,successdo);
	}
	
	function edit(row){
		window.open("<%=context%>/lymanage/add.do?id="+row.id);     
	}
  
</script>


</body></html>