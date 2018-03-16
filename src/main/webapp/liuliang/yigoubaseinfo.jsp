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
		<div class="breadcrumb">当前位置：流量大厅-已购流量<span style="padding-left:100px;">
		<a href="<%=context%>/liuliang/newbaseinfo" class="zicaidan">最新流量</a> 
		<a href="<%=context%>/liuliang/yigou" class="zicaidan">已购流量</a>
		<a href="<%=context%>/liuliang/tuijian" class="zicaidan">推荐套餐</a>
		</span></div>
		<div> 
			<form id="search" method="post" >
		    	姓名：<input type="text" name="name" />
		    	地区：<input type="text" name="area" />
		    	最小芝麻分：<input type="text" name="minzhimafen" />
		    	最大芝麻分：<input type="text" name="maxzhimafen" />
		    	<br>
		    	最小年龄：<input type="text" name="minyear" />
		    	最大年龄：<input type="text" name="maxyear" />
		    	渠道：<select  name="qudao" >
		    	 	  <option value ="">不选择</option>
					  <option value ="百度头条">百度头条</option>
					  <option value ="谷歌头条">谷歌头条</option>
					</select>
		    	申请设备：
		    	<select  name="shebei" >
		    		  <option value ="">不选择</option>
					  <option value ="苹果手机">苹果手机</option>
					  <option value ="安卓手机">安卓手机</option>
					  <option value ="网页">网页</option>
					</select>
		    	<br>
		    	开始时间：<input type="date" name="startTime" />
		    	结束时间：<input type="date" name="endTime" />
		    	<input type="button" value="查询" onclick="getRecord(1)"/>	
	    	</form>
    	</div>
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
		var basedata = {
				sessionid:$.cookie('sessionid'),
				current:current,
				limit:20
		}
		var data = $.param(basedata)+"&"+$('#search').serialize()
		 $.ajax({
            type: "POST",
            dataType: "html",
            url: "<%=context%>/liuliangpost/goumaibaseinfo.do",
            data: data,
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
		
		var trthhtml ="<tr><th>编号</th> <th>姓名</th><th>手机</th><th>微信</th><th>QQ</th>"
						 +"<th>地区</th><th>年龄</th><th>芝麻分</th><th>渠道</th><th>申请设备</th>"
						 +"<th>申请时间</th><th>利息</th><th>借款方式</th><th>还款金额</th><th>负债情况</th> "
						 +"<th>采购时间</th></tr> ";
		var html = trthhtml;
		if(tabledata){
			for(var i = 0;i<tabledata.length;i++){
				var row = tabledata[i];
				var goumaiid = "goumai"+row.id;
				var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.llBaseInfo.name+"</td><td>"+row.llBaseInfo.mobile+"</td><td>"+row.llBaseInfo.weixin+"</td><td>"+row.llBaseInfo.qq+"</td>"
							+"<td>"+row.llBaseInfo.area+"</td><td>"+row.llBaseInfo.year+"</td><td>"+row.llBaseInfo.zhimafen+"</td><td>"+row.llBaseInfo.qudao+"</td><td>"+row.llBaseInfo.shebei+"</td>"
							+"<td>"+row.llBaseInfo.publishDateStr+"</td><td>"+row.llBaseInfo.lixi+"</td><td>"+row.llBaseInfo.jkfs+"</td><td>"+row.llBaseInfo.hkje+"</td><td>"+row.llBaseInfo.fuzai+"</td>"
							+"<td>"+row.createDateStr+"</td></tr> "
				html+=trtdhtml;
			}
		}	
		$table.html(html);
	}
	
  
</script>


</body></html>