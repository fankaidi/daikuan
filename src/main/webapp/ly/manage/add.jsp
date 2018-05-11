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
<link rel="stylesheet" href="<%=context%>/common/imgpic/image-picker.css" />
<link rel="stylesheet" href="<%=context%>/jqtable/bootstrap-table.css" />
<script type="text/javascript" src="<%=context%>/jqtable/jquery.min.js"></script>
<script type="text/javascript" src="<%=context%>/jqtable/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="<%=context%>/common/http.js"></script>
<script type="text/javascript" src="<%=context%>/jqtable/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=context%>/common/move/tuozhuai.js"></script>
<script type="text/javascript" src="<%=context%>/common/imgpic/image-picker.min.js"></script>
</head>

<style type="text/css">
*{margin:0; padding:0;list-style: none}
#ul1{width:800px;position:relative;margin:10px auto;}
#ul1 li{width:800px;height:150px;float:left;margin:5px;-moz-border-radius:25px;border-radius:25px;}
#ul1 li:hover{border-color:#9a9fa4; box-shadow:0 0 6px 0 rgba(0, 0, 0, 0.85);}
#ul1 .active{border:1px dashed red;}
#ul1 li img{-moz-border-radius:15px;border-radius:15px;}
#ul1 li textarea{width:700px;height:130px;}
</style>

<body>

	<div  id="divall">
		<ul id="ul1">
		</ul>
		<div id="month" style="left: 22px; top: 21px; position: absolute;z-index: 9999">	
		<a onclick="add()" title="新增文字"><img src='<%=context%>/common/images/icon64/add.png' width="32" /></a>
		<a onclick="importone()" title="插入选中图片"><img src='<%=context%>/common/images/icon64/pic.png' width="32" /></a>
		<a onclick="deletenode()" title="删除"><img src='<%=context%>/common/images/icon64/delete.png' width="32" /></a>
		</div>
	</div>
	<div>
	<ul>
		<li class="login-sub">
						<input type="button" class="submit" value="保存" onclick="save()"> 
					</li> 
	</ul>
	上传图片:
	 <form method="post" id="infoLogoForm" enctype="multipart/form-data">
	    <input type="file" name="file" />
		<input type="hidden" name="id" value="<%=id%>"/>
		
	    <input type="button" onclick="upload()" value="上传图片" />	
	    <br/>  <br/>
	    <input type="button" onclick="importall()" value="插入所有图片到页面" />	
	    <input type="button" onclick="logoset()" value="设为主题图片" />	
    </form>
	</div>
	<div id="pics" class="picker">
		
	</div>
</body>
</html>

<script type="text/javascript">

	$('#month').hide();
	var liid = null;
	
	function upload(){
		var url = "<%=context%>/yj/importpic.do";
		var data = new FormData($('#infoLogoForm')[0]);
		$.ajax({
	        type: "POST",
	        dataType:"json",
	        url: url,
			data : data,
			processData: false,
	        contentType: false,
			cache: false,
			success : function(data) {
				var strresult = data;
				if (strresult.type == 'success') {
					alert("上传成功");
					initpic();
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
	
	function add(){
		 if(liid){
			 var $liid = $('#'+liid); 
			 var lih = gettexthtml("新增文字");
			 $liid.after(lih); 
			 initmove();
			 staticbt();
		 } 	   
	}
	
	function save(){
		 var $ull = $('#ul1'); 
		 var lis = $ull.children("li");
		 if(!lis){
			 return;	
		 }
		 //需要提交的数据
		 var dataarr = [];
		 for(var i=0;i<lis.length;i++){
			 var oli = lis[i];
			 var d = oli.firstChild;
			 
			 var content = {};
			 if(d.localName == "textarea"){
				 content.type = 0;
				 content.content = d.value;
			 }else{
				 content.type = 1;
				 content.content = d.src;
			 }
			 dataarr.push(content);
		 }
		 var url = "<%=context%>/yj/addContent.do";
		 var data = {id:id,content:JSON.stringify(dataarr)};
		 var successdo = function(strresult){
			 alert("保存成功");
		 }
		 postdo(url,data,successdo);		
		 
	}
	
	//主题图片设置
	function logoset(){
		var pic = $('#pic').val();
		if(!pic){	
			alert("请选择图片");
			return false;
		}
		 var url = "<%=context%>/yj/updatelogo.do";
		 var data = {id:id,url:pic};
		 var successdo = function(strresult){
			 alert("设置成功");
		 }
		 postdo(url,data,successdo);			 
	}
	
	function deletenode(){
		 if(liid){
			 var $liid = $('#'+liid); 
			 $liid.remove(); 
			 initmove();
		 } 	   
	}
	
	var id = "<%=id%>";
	function init(){
		var url = "<%=context%>/yj/content.do";
		var data = {id:id};
		var successdo = function(strresult){
			insertRecords(strresult);
		}
		postdo(url,data,successdo);		
	}
	
	var last = 0;
	
	
	function insertRecords(strresult){
		var rows = strresult.resultData.rows;
		var html = "";
		if(rows && rows.length > 0){		
			for(var i=0;rows.length > i; i++){
				last = i;
				var row = rows[i];
				var lih = "";
				if(row.type==0){
					lih+=gettexthtml(row.content);
				}else{
					lih+=getpichtml(row.content);
				}
				html+=lih;
			}
			
		}else{
			html+=gettexthtml("新增文字");
		}
		var $goumai = $('#ul1');
		$goumai.html(html);
		$().ready(initmove);
		staticbt();
	}
	init();
	
	//初始化按钮
	function staticbt(){
		$('.ulcs').mousemove(function(e){
			var ps = $('#'+this.id).position();
			liid = this.id;
	        $('#month').css({
	            "top": ps.top+65,
	            "left": $(window).width()/2+300
	        }).show();
	    });
		$('#divall').mouseleave(function(e){
			$('#month').hide();
			liid = null;
	    });
	}
	
	function initpic(){
		var url = "<%=context%>/yj/getpics.do";
		var data = {id:id};
		var successdo = function(strresult){
			var html = "<select id='pic' class='image-picker'>";
			var rows = strresult.resultData.rows;
			for(var i=0;i<rows.length;i++){
				var row = rows[i];
				html+="<option data-img-src='"+row+"' value='"+row+"'>  Page "+(i)+"  </option>";
			}
			html+="</select>";
			$('#pics').html(html);
			$().ready(function(){
				$("select.image-picker").imagepicker({
					hide_select:false
				});
			});		
		}
		postdo(url,data,successdo);		
	}
	
	initpic();
	
	function importall(){
		var list = $('#pic option');
		var html = "";
		for(var i=0;i<list.length;i++){
			var op = list.get(i);
			var url = op.value;
			html+=getpichtml(url);
		}
		var $ull = $('#ul1'); 
		html+=$ull.html();
		$ull.html(html);
		initmove();
		staticbt();
	}
	
	function importone(){
		var url = $('#pic').val();
		if(!url){	
			alert("请选择图片");
			return false;
		}
		if(liid){
			 var $liid = $('#'+liid); 
			 var lih = getpichtml(url);
			 $liid.after(lih); 
			 initmove();
			 staticbt();
		 } 
	}
	
	function getpichtml(url){
		last++;
		var html = "<li class='ulcs' id='rn"+last+"'><img src='"+url+"' width='200' height='150'  /></li>";
		return html;
	}
	
	function gettexthtml(url){
		last++;
		var html = "<li class='ulcs' id='rn"+last+"'><textarea>"+url+"</textarea></li>";
		return html;
	}
	
	
</script>