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

</head>

<style type="text/css">
*{margin:0; padding:0;list-style: none}
#ul1{width:400px;position:relative;margin:10px auto;}
#ul1 li{width:400px;min-height:150px;float:left;margin:5px;-moz-border-radius:25px;border-radius:25px;}
#ul1 li:hover{border-color:#9a9fa4;}
#ul1 .active{border:1px dashed red;}
#ul1 li textarea{width:350px;height:180px;}
.pc {
	width: 34px;
    height: 34px;
    border: 1px solid #e1e2e3;
    cursor: pointer;
    background: #f2f8ff;
    border: 1px solid #38f;
    display: block;
    text-align: center;
    line-height: 34px;
}
.bianhao {
    background: red;
}


</style>

<body>

	<div  id="divall">
		<ul id="ul1">
		</ul>
	</div>
	<div>
	<ul>
		<li class="login-sub">
						<input type="button" class="submit" value="保存" onclick="save()"> 
					</li> 
	</ul>
	上传图片:
	 <form method="post" id="infoLogoForm" enctype="multipart/form-data">
	    <input type="file" name="file" multiple="multiple" onchange="upload()"/>
		<input type="hidden" name="id" value="<%=id%>"/>
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
					var rows = strresult.resultData.rows;
					importall(rows);		
				}else{
					 alert(strresult.message);
				}
			},
			error : function(data) {
				alert("error:" + data.responseText);
			}
		});	
	}
	
	function add(id){
		 var $liid = $('#'+id); 
		 var lih = gettexthtml("新增文字");
		 $liid.after(lih); 
	}
	
	function deletenode(id){
		 var $liid = $('#'+id); 
		 $liid.remove();  
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
			 if(d && d.localName == "textarea"){
				 content.type = 0;
				 content.content = d.value;
			 }else if(d && d.localName == "img"){
				 content.type = 1;
				 content.content = d.src;
			 }else{
				 continue;
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
	function logoset(cid){
		var $lic = $('#'+cid); 
		var d = $lic[0].firstChild;
		if(d && d.localName == "img"){
			 var pic = d.src;
			 var url = "<%=context%>/yj/updatelogo.do";
			 var data = {id:id,url:pic};
			 var successdo = function(strresult){
				 alert("设置成功");
			 }
			 postdo(url,data,successdo);		
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
	}
	init();
	

	
	//插入所有图片
	function importall(list){
		var html = "";
		for(var i=0;i<list.length;i++){
			var url = list[i];
			html+=getpichtml(url);
		}
		var $ull = $('#ul1'); 
		html+=$ull.html();
		$ull.html(html);
	}
	
	//移动，向上移动
	function moveup(dept,cid){
		var oUl= document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");
		//获取当前元素所在的位置
		var moveindex = 0;
		for(var i=0;i<aLi.length;i++){
			var liobj = aLi[i];
			if(liobj.id == cid){
				moveindex = i;
				break;			
			}
		}
		
		//计算移动到哪个位置
		var targetmove = moveindex-dept;
		if(targetmove < 0){
			targetmove = 0;
		}else if(targetmove > aLi.length){
			targetmove = aLi.length;
		}
		
		if(targetmove == moveindex){
			return;
		}
		var targetobj = aLi[targetmove]
		var moveobj = aLi[moveindex]; 
		oUl.insertBefore(moveobj,targetobj);	
		$("#"+cid)[0].scrollIntoView();
	}
	
	
	//移动，向下移动
	function movedown(dept,cid){
		var oUl= document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");
		//获取当前元素所在的位置
		var moveindex = 0;
		for(var i=0;i<aLi.length;i++){
			var liobj = aLi[i];
			if(liobj.id == cid){
				moveindex = i;
				break;			
			}
		}
		
		//计算移动到哪个位置
		var targetmove = moveindex+dept;
		if(targetmove < 0){
			targetmove = 0;
		}else if(targetmove > aLi.length){
			targetmove = aLi.length;
		}
		
		if(targetmove == moveindex){
			return;
		}
		var targetobj = aLi[targetmove]
		var moveobj = aLi[moveindex];
		insertAfter(moveobj,targetobj);
		$("#"+cid)[0].scrollIntoView();
	}
	
	function getli(val){
		last++;
		var id = "rn"+last;
		var html = "<li class='ulcs' id='"+id+"'>"+val+getTable(id)+"</li>";
		return html;
	}
	
	function getTable(cid){
		var bianhaoid = cid+"bianhao";
		var html = "<table><tr><td>上移</td>"+gettdspan(cid,1,"moveup")+gettdspan(cid,3,"moveup")+gettdspan(cid,10,"moveup");
		html += gettdspan2(cid,"add","新增")+gettdspan2(cid,"deletenode","删除")+gettdspan2(cid,"logoset","主题")
		html += "<td>下移</td>"+gettdspan(cid,10,"movedown")+gettdspan(cid,3,"movedown")+gettdspan(cid,1,"movedown")+"</tr></table>"
		return html;
	}
	
	function gettdspan(cid,index,fname){
		var html = "<td onclick='"+fname+"("+index+",\""+cid+"\")'><span class=\"pc\">"+index+"</span></td>";
		return html;	
	}
	
	function gettdspan2(cid,fname,name){
		var html = "<td onclick='"+fname+"(\""+cid+"\")'><span class=\"pc\">"+name+"</span></td>";
		return html;	
	}
	
	function getpichtml(url){
		var html = getli("<img src='"+url+"' width='350' />");
		return html;
	}
	
	function gettexthtml(url){
		var html = getli("<textarea>"+url+"</textarea>");
		return html;
	}
	
	function insertAfter(newElement, targetElement){
		var parent = targetElement.parentNode;
		if (parent.lastChild == targetElement) {
		  // 如果最后的节点是目标元素，则直接添加。因为默认是最后
		  parent.appendChild(newElement);
		}else {
		  parent.insertBefore(newElement, targetElement.nextSibling);
		 //如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面
		}
	}
	
	
</script>