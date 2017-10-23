<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

       <div class="top_nav">
           <a class="group_logo" href="http://www.wokee.com.cn/">logo</a>
           <div class="group_tel">4006-186-966</div>
             <ul class="group_nav">
                 <li><a href="http://www.wokee.com.cn/">首页</a></li>
                 <li><a href="http://www.wokee.com.cn/serv/private">私人定制</a></li>
                 <li>
                       <a href="http://www.wokee.com.cn/#">目的地<i class="group_i01"></i></a>
                       <div class="group_menu"  id="group_menu" style="display:none;">
                          						                            
                       </div>
                   </li>
                 <li>
                   <a href="http://www.wokee.com.cn/#">主题旅行<i class="group_i02"></i></a>
                   <div class="group_menu zhuti" style="display:none;">
                         
                           <div class="g_menu_con01" id="g_menu_con01">    
                              
                           </div>
                       

                          
                       </div>
                 </li>
             </ul>
       </div>


<script type="text/javascript">
 	//获取目的地
	function getarea() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<ex:static name="context"/>/showpost/taglist.do",
	          data: {"name":"desc9","val":"area"},
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
 	
 	//获取主题
	function getzhuti() {
		  $.ajax({
	          type: "POST",
	          dataType: "html",
	          url: "<ex:static name="context"/>/showpost/taglist.do",
	          data: {"name":"desc9","val":"zhuti"},
	          success: function (data) {
	        	  var strresult = $.parseJSON(data);  
	              if(strresult.type == 'success'){
	            	  insertzhuti(strresult.resultData.rows);
	              }    
	          },
	          error: function(data) {
	              alert("error:"+data.responseText);
	           }
	      });
	  }
 	
 	//标签html插入
 	function inserttags(rows) {	
 		var tags =$("#group_menu");

 		for(var i in rows){	
 			var tagtype = rows[i];
 			var tag1=$(" <h2 class=\"g_menu_tit\">"+tagtype.name+"</h2>");
 			tags.append(tag1);
 			
 			var tag2=$(" <div class=\"g_menu_con\"></div>");			
 			var tagrows = tagtype.tagList;
 			inserttag(tag2,tagrows);
 			
            tags.append(tag2);
 		}
 	}
 	
 	//主题标签插入
 	function insertzhuti(rows) {	
 		var tag2 =$("#g_menu_con01");
 		var tagrows = rows[0].tagList;
 		inserttag(tag2,tagrows);		
 	}
 	
 	//插入标签
 	function inserttag(tag2,tagrows) {	
		var html = "";
		for(var j in tagrows){
			var tag = tagrows[j];
			html+="<span><a href=\"http://www.wokee.com.cn/category?q=1&amp;c=1\">"+tag.name+"</a></span>";
		}
	    tag2.append(html);
 	}
 	
 	
 	
 	getarea();
 	getzhuti();
</script>
