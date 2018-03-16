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
		<div class="breadcrumb">当前位置：流量大厅-最新流量<span style="padding-left:100px;">
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
		    	<input type="button" value="全部购买" onclick="goumaiall()"/>	
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
		var data = $.param(basedata)+"&"+$('#search').serialize()
		 $.ajax({
            type: "POST",
            dataType: "html",
            url: "<%=context%>/liuliangpost/newbaseinfo.do",
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
						 +"<th>操作</th></tr> ";
		var html = trthhtml;
		if(tabledata){
			for(var i = 0;i<tabledata.length;i++){
				var row = tabledata[i];
				var goumaiid = "goumai"+row.id;
				var trtdhtml = "<tr><td>"+(i+1)+"</td><td>"+row.name+"</td><td>"+row.mobile+"</td><td>"+row.weixin+"</td><td>"+row.qq+"</td>"
							+"<td>"+row.area+"</td><td>"+row.year+"</td><td>"+row.zhimafen+"</td><td>"+row.qudao+"</td><td>"+row.shebei+"</td>"
							+"<td>"+row.publishDateStr+"</td><td>"+row.lixi+"</td><td>"+row.jkfs+"</td><td>"+row.hkje+"</td><td>"+row.fuzai+"</td>"
							+"<td id="+goumaiid+"><input type=\"button\" value=\"购买\" onclick=\"goumai(tabledata["+i+"])\" />	</td></tr> "
				html+=trtdhtml;
			}
		}
		$table.html(html);
	}
	
	function goumai(row) {
		if(row.goumaistate == 1){
			alert("您已购买!");
			return false;
		}
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/goumailiuliang.do",
			data : {
				"sessionid" : $.cookie('sessionid'),
				"id" : row.id
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					row.goumaistate = 1;	
					var goumaiid = "goumai"+row.id;
					var $goumai = $('#'+goumaiid);
					$goumai.html("已购买");
					alert("成功付款");
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
	
	function goumaiall() {
		var basedata = {
				sessionid:$.cookie('sessionid'),
				current:1,
				limit:10000
		}
		var data = $.param(basedata)+"&"+$('#search').serialize()
		$.ajax({
            type: "POST",
            dataType: "html",
            url: "<%=context%>/liuliangpost/newbaseinfo.do",
            data: data,
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					var allrows = strresult.resultData.rows;
					if(!allrows || allrows.length == 0){
						 alert("没有需要购买的流量");
					}
					if(window.confirm('你确定要购买'+allrows.length+'条流量吗？')){   
						var ids = "";
						for(var i in allrows){
							var r = allrows[i];
							ids += r.id+",";
						}
						ids = ids.substring(0, ids.length-1);
						goumaibyids(ids);
		            }
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});
	}
	
	function goumaibyids(ids) {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/liuliangpost/goumailiuliangs.do",
			data : {
				"sessionid" : $.cookie('sessionid'),
				"ids" : ids
			},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					alert("成功付款");
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
  
</script>


</body></html>