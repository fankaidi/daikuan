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
		<div class="breadcrumb">当前位置：后台管理-流量数据管理  <span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/baseinfo" class="zicaidan">流量数据管理</a> 
		<a href="<%=context%>/liuliang/mealsalelist" class="zicaidan">套餐销售记录</a>
		<a href="<%=context%>/liuliang/meallist" class="zicaidan">套餐定义</a>
		<a href="<%=context%>/lymanage/page1.do" class="zicaidan">页面编辑</a></span></div>
		<div> 
			<form id="uploadfile" method="post" enctype="multipart/form-data">
		    	<input type="file" name="file" />
		    	<input type="hidden" name="sessionid" id="sessionid" value=""/>
		    	<input type="button" value="提交流量数据" onclick="uploadfile()"/>	
		    	<a href="/liuliang/model.xlsx" target="_blank">下载xlsx模板</a>
	    	</form>
    	</div>
        <div style="margin-top:10px;">
        	<table class="table" id="records" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
			</table>	
		</div>
		<div id="fanye">

		</div>
		
	</div>
</div>
<script>

  //信息上传
  var uploading = false;
  function uploadfile() {
	  if(uploading){
	        alert("文件正在上传中，请稍候");
	        return false;
	   }
	  uploading = true;
	  $('#sessionid').val($.cookie('sessionid'));
	  $.ajax({
	        url: "/liuliangmanagerpost/importliuliang.do",
	        type: 'POST',
	        cache: false,
	        data: new FormData($('#uploadfile')[0]),
	        processData: false,
	        contentType: false,
	        dataType:"json",
	        beforeSend: function(){
	            uploading = true;
	        },
	        success : function(data) {
	        	if(data.type == 'success'){
	            	alert("上传成功");  	 
	            }else{
	            	alert("上传失败");
	            }
	            uploading = false;
	        }
	    });	 
   }
  
   var fanye = new FanYe("fanye","getRecord",0,20,1);
   //流量数据表格生成
	function getRecord(current) {
		if(!fanye.check(current)){
			return;
		}
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangmanagerpost/recordlist.do",
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

	function insertRecords(returndata) {
		var data = returndata.resultData.rows;
		var $table = $('#records');
		
		var trthhtml ="<tr><th>编号</th> <th>姓名</th><th>手机</th><th>微信</th><th>QQ</th>"
						 +"<th>地区</th><th>年龄</th><th>芝麻分</th><th>渠道</th><th>申请设备</th>"
						 +"<th>申请时间</th><th>利息</th><th>借款方式</th><th>还款金额</th><th>负债情况</th> "
						 +"<th>身份证</th></tr> ";
		var html = trthhtml;
		for(var i = 0;i<data.length;i++){
			var row = data[i];
			var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.name+"</td><td>"+row.mobile+"</td><td>"+row.weixin+"</td><td>"+row.qq+"</td>"
						+"<td>"+row.area+"</td><td>"+row.year+"</td><td>"+row.zhimafen+"</td><td>"+row.qudao+"</td><td>"+row.shebei+"</td>"
						+"<td>"+row.publishDateStr+"</td><td>"+row.lixi+"</td><td>"+row.jkfs+"</td><td>"+row.hkje+"</td><td>"+row.fuzai+"</td>"
						+"<td>"+row.card+"</td></tr> "
			html+=trtdhtml;
		}
		$table.html(html);
	}
  
</script>


</body></html>