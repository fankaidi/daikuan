/**
 * Created by houjf on 2017/7/28.
 */
/**
 * Created by houjf on 2017/7/6.
 */
$(function() {
    var baseUrl = $('#APP_PATH_ROOT').val();
    var bigangwanUrl = $('#BIGANGWAN_PATH_ROOT').val();
    var count = 0;
    var global = {
        url_register_send_sms: bigangwanUrl + '/promotion/register_send_sms',
        url_register_submit: bigangwanUrl + '/promotion/register_submit',
        url_download_app: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.phonebird',
        url_boolean_register: bigangwanUrl + '/promotion/boolean_register',
        url_promotion_save_message: bigangwanUrl + '/promotion/save/message_info'
    }
    //提示框
    function drawToast(message) {
        if ($('#toast').length > 0) {
            return;
        }
        var toastHTML = '<div id="toast" style="width: 66%;position: fixed;left: 0;right: 0;margin:auto;z-index: 999;word-warp: break-word;word-break: break-all;top: 40%;text-align: center;background-color: #333;border-radius: 10px;color: #f3f3f3;padding-bottom: 10px;"><p class="toastFt">提示</p><span class="toastSpan">' + message + '</span></div>';
        document.body.insertAdjacentHTML('beforeEnd', toastHTML);
        $('#toast').show(300).delay(3000).hide(300, function() {
            $(this).remove();
        });
    }

    //倒计时
    var countdown = 60;

    function timeLoop() {
        if (countdown == 0) {
            $('#sendSmsCode').text('重新获取');
            countdown = 60;
        } else {
            $('#sendSmsCode').text('重发(' + countdown + ')').css('text-decoration', 'none');
            countdown--;
            setTimeout(function() {
                timeLoop();
            }, 1000);
        }
    }

    function ajaxMobile(){
        var mobile=arguments[0] || '';
        var resMsg=arguments[1] || '';
        var channel =$('#channel').val();
        $.ajax({
            type:"post",
            data:{
                "mobile":mobile,
                "resMsg":resMsg,
                "channel":channel
            },
            url: global.url_promotion_save_message,
            success:function (data) {},
            error:function (data) {}
        })
    }

    //获取验证码
    $('#sendSmsCode').click(function() {
        if (countdown != 60)
            return;
        var mobile = $('#mobile').val();
        var channel =$('#channel').val();
        if (!/^0?1[3|4|5|7|8][0-9]\d{8}$/.test(mobile)) {
            drawToast("请输入正确的手机号码");
             ajaxMobile(mobile,"手机号码不正确");
            $('#sendSmsCode').css('text-decoration', 'none');
            return;
        }
        if(count >= 2){
            drawToast("收不到验证码，请下载软件尝试注册");
            setTimeout(function () {
                location.href = global.url_download_app;
            },3000);
        }else {
        	$.ajax({
		          type: "POST",
		          dataType: "html",
		          url: "/mobile/smsuser.do",
		          data: $('#adduser').serialize(),
		          success: function (data) {
		        	  var strresult = $.parseJSON(data);  
		              if(strresult.type == 'success'){
		            	  timeLoop();
		              }else{
		            	  alert(strresult.message);
		              }    
		          },
		          error: function(data) {
		              alert("error:"+data.responseText);
		           }
		      });
        }
    });

    //注册

    $('#register').click(function() {
    	actionSubmit();
    });
})