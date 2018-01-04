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
<title>记录管理</title>
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

		<div id="tags" class="c-select"></div>

		<div>

			<div class="form-group" class="col-sm-12">
				<div class="col-sm-1"> <label class="control-label">名字:</label></div>
				<div class="col-sm-2"><input type="text" class="form-control" id="firstname"></div>
				<button class="btn btn-default" type="submit">查询</button>
				<button class="btn btn-default" type="submit"><a target="_blank" href="<%=context%>/lymanage/add.do">新增</a></button>
			</div>

			
		</div>
		<table id="records">
		</table>

	</div>
</body>
</html>

<script type="text/javascript">
 	//获取标签
	function gettags() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/lymanagepost/taglist.do",
	          data: {},
	          success: function (data) {
	        	  var strresult = $.parseJSON(data);  
	              if(strresult.type == 'success'){
	            	  inserttags(strresult.resultData.rows);
	              }    
	          },
	          error: function(data) {
	              alert("error:"+data.responseText);
	           }
	      });
	  }
 	
 	//标签html插入
 	function inserttags(rows) {	
 		var tags =$("#tags");
 		tags.on('click', 'a', function(e){
 		    $('div a').removeClass('checked');
 		    $(e.target).addClass('checked');
 		});
 		
 		var tagsdiv=$("<div >目的地：</div>");
 		
 		for(var i in rows){	
 			var tag = rows[i];
 			var a = $("<a href='#' onclick='getRecord("+tag.id+")'></a>");
 			a.text(tag.name);	
 			tagsdiv.append(a);
 		}
 		tags.append(tagsdiv);	
 	}
 	
 	gettags();
 	
 	
 	//下面获取record
	function getRecord(tagId) {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/lymanagepost/recordlist.do",
			data : {
				"tagId" : tagId,
				"pageNo" : 1
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
		$table.bootstrapTable({
			data : data,
			toolbar : '#toolbar', //工具按钮用哪个容器
			striped : true, //是否显示行间隔色
			singleSelect : false,
			pagination : true, //分页
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 100, //每页的记录行数（*）
			search : true, //显示搜索框
			columns : [
					{
						field : 'name',
						title : '标题'
					},
					{
						field : 'desc0',
						title : '价格'
					},
					{
						field : 'publishDate',
						title : '时间'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a href="<%=context%>/lymanage/add.do?id=' + row.id +'" target="_blank">编辑</a> ';
							var d = '<a href="#" mce_href="#" onclick="del(\''
									+ row.id + '\')">删除</a> ';
							return e + d;
						}
					} ]
		});
	}

	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
	Date.prototype.Format = function(fmt) { //author: meizz 
		var o = {
			"M+" : this.getMonth() + 1, //月份 
			"d+" : this.getDate(), //日 
			"h+" : this.getHours(), //小时 
			"m+" : this.getMinutes(), //分 
			"s+" : this.getSeconds(), //秒 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
			"S" : this.getMilliseconds()
		//毫秒 
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
</script>