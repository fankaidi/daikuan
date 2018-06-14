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
    <title>缓存管理</title>
</head>
<body>
<div id="fanye">
<table>
<tr><th>编号</th> <th>名称</th><th>操作</th></tr>
<tr><td>1</td> <td>主键缓存</td><td><input type="button" value="清空" onclick="clear('bkClear.do')"/></td></tr>
<tr><td>2</td> <td>配置缓存清除</td><td><input type="button" value="初始化" onclick="clear('myConfigInit.do')"/></td></tr>
</table>
</div>
</body>
</html>

<script>  
	function clear(action) {
		$.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<%=context%>/cache/"+action,
			data : {},
			success : function(data) {
				var strresult = $.parseJSON(data);
				if (strresult.type == 'success') {
					alert("成功");
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