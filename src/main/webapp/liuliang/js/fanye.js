//divid：回写的divid；fanyemd:翻页回写方法；total：总条数；limit：每页多少条；current第一次是第几页
function FanYe(divid,fanyemd,total,limit,current) {
  this.divid = divid;
  this.fanyemd = fanyemd;
  this.total = total;
  this.limit = limit;
  this.current = current;
  this.totalPage = 0;
  /**
   * 初始化的时候，可以重新定义total
   */
  this.init = function (total,current) {
	  this.total = total;
	  this.current = current;
	  this.totalPage =  Math.ceil(this.total/this.limit);
 
	  var html ="<div style=\"margin-top:10px\">"
		  +"总记录"+this.total+"条,每页"+this.limit+"条,&nbsp;&nbsp;&nbsp;当前第"+this.current+"页,&nbsp;&nbsp;&nbsp;共"+this.totalPage+"页"
		  +"&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\""+this.fanyemd+"(1)\">首页</a>"
		  +"&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\""+this.fanyemd+"("+(this.current-1)+")\">上一页</a>"
		  +"&nbsp;&nbsp;&nbsp;<select onchange=\""+this.fanyemd+"(this.value)\"\">";
	  	  for(var i=1;i<=this.totalPage;i++){
	  		var selected = this.current==i?"selected":"";
	  		html+="<option value =\""+i+"\""+selected+" >"+i+"</option>"
	  	  };
	  	  html+="</select>"
		  +"&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\""+this.fanyemd+"("+(this.current+1)+")\">下一页</a>"
		  +"&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\""+this.fanyemd+"("+this.totalPage+")\">尾页</a>"
		  +" </div>"
		  +"<div style=\"margin-top:100px;\"></div>";
	  var $table = $('#'+this.divid);
	  $table.html(html);
  };
  
  this.check = function(current){
	  if(current <= 0){
		  alert("没有上一页");
		  return false;
	  }else if(this.totalPage > 0 && current > this.totalPage){
		  alert("没有下一页");
		  return false;
	  }
	  return true;
  }
}
