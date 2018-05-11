//封装post请求方法
function postdo(url,data,sucessdo) {
	$.ajax({
        type: "POST",
        dataType: "html",
        url: url,
		data : data,
		success : function(data) {
			var strresult = $.parseJSON(data);
			if (strresult.type == 'success') {
				sucessdo(strresult);
			}else{
				 alert(strresult.message);
			}
		},
		error : function(data) {
			alert("error:" + data.responseText);
		}
	});	
};

