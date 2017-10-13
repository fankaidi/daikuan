<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String context = request.getContextPath();
	String id = (String)request.getAttribute("id");
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
</head>

<body>

	<div>
		<form id="addrecord" name="addrecord" method="post" action="" class="form-horizontal formwidth">
		
			<div class="form-group">
			    <label class="col-sm-2 control-label">标题</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="name" placeholder="请输入标题">
			    </div>
			</div>
		
			<div class="form-group">
			    <label class="col-sm-2 control-label">价格</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="desc0">
			    </div>
			</div>
			
			<div class="form-group">
			    <label class="col-sm-2 control-label">标签</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="tagid">
			    </div>
			</div>
					
			<div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="button" class="btn btn-default" onclick="add()">保存</button>
			    </div>
			</div>
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
	var id = "<%=id%>";
	function add(){
		var data = $('#addrecord').serialize();
		data.id = id;
		$.ajax({
		    type: "POST",
		    dataType: "html",
		    url: "<%=context%>/lymanagepost/save.do",
		    data: data,
		    success: function (data) {
		  	  var strresult = $.parseJSON(data);  
		        if(strresult.type == 'success'){
		      	  location.href = "/mobile/chenggong.jsp";
		      	 // alert("借款资料提交成功，请等待我们的客服和您联系，或者你也可以主动联系我们的QQ");
		        }else{
		      	  alert(strresult.message);
		        }    
		    },
		    error: function(data) {
		        alert("error:"+data.responseText);
		     }
		});	
	}
	
	
	
</script>