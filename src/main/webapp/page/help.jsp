<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="./page/help/index.css">
    <link rel="stylesheet" href="./page/index.css">
    <title>线上贷</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/> 
     <div class="main">
        <ul class="tab-box clearfix">
            <li class="now">产品介绍</li>
            <li>账户安全</li>
            <li>信用评估</li>
            <li class="lastli">借款流程</li>
        </ul>
        <div class="main-content">
            <div class="help-content help-content-one">
                <p class="title">线上贷是什么？</p>
                <p class="info">线上贷是一款依托移动互联网线上信用借款APP产品，主要解决年轻人日常小额消费借款需求。具有随借随用随还的灵活便捷性，提供满足500--3000元额度，“7-30天”灵活周期的信用借款服务。线上贷借用手机客户端的便捷，从申请借款到还款全流程人性化线上操作，借用互联网大数据及智能审批系统，一键申请，快速审批，即刻到账。</p>
                <p class="title">线上贷的优势在哪？</p>
                <p class="info">申请0成本，7天24小时随时申请，无需繁琐的证明材料，最快1分钟放款，可循环借款~满足您的应急之需！</p>
                <p class="title">如何联系线上贷？</p>
                <p class="info">您可以直接联系微信公众号（haoqianbao）进行客户咨询。</p>
            </div>
            <div class="help-content help-content-two">
                <p class="title">如何进行注册？</p>
                <p class="info">下载线上贷APP后，输入手机号即可注册。</p>
                <p class="title">注册时，进行手机验证，收不到短信怎么办？</p>
                <p class="info">（1）重启手机或清理手机缓存；
                    <br>（2）检查一下是否被手机安全软件拦截了；；
                    <br>（3）检查一下是否退订过短信，如果退订过短信，联系客服重新接受短信。
                </p>
                <p class="title">如果忘记登录密码怎么办？</p>
                <p class="info">进入APP后，输入手机号点击下一步，而后点击“忘记密码”即可进行密码找回操作。</p>
                <p class="title">如何修改登录密码？</p>
                <p class="info">登录线上贷APP后，点击下方的“我的”，点击“设置”，即可找到“密码设置”功能。</p>
                <p class="title">手机丢失怎么办？</p>
                <p class="info">如果您的手机不幸丢失，我们用于提现的银行账户，必须是您本人名下的账户，以充分保障您的资金安全，而且每一笔操作，都是需要您的操作密码认证的。如发生遗失您可以第一时间与我们取得联系，我们的客服专员会帮助您处理。</p>
            </div>
            <div class="help-content help-content-three">
                <p class="title">什么是信用额度？</p>
                <p class="info">信用额度是系统对您所提供材料综合评分后，给出的单笔借款的上限。例：信用额度为3000元的借款人，可以发起总额最高为3000元的借款请求。</p>
            </div>
            <div class="help-content help-content-four">
                <p class="title">借款需要哪些步骤？</p>
                <p class="info">1）下载线上贷APP，手机号注册。
                    <br>2）资料提交齐全。
                    <br>3）提交借款申请，等待审核。
                    <br>4）审核放款</p>
                <p class="title">借款多久到账？</p>
                <p class="info">当天提交的审核会在5分钟内出审核结果，具体到账时间由不同的银行卡接受款项时间为准，一般来说2-3小时以内。</p>
            </div>
        </div>
    </div>

	<jsp:include page="footer.jsp" flush="true"/> 
   
       <script>
    window.onload = function() {
        var tab = document.querySelectorAll(".tab-box>li"),
            helpContent = document.querySelectorAll(".help-content");

            for (var i = 0; i < tab.length; i++) {
                tab[i].onclick = (function(i) {
                    return function() {
                        for (var j = 0; j < helpContent.length; j++) {
                            helpContent[j].style.display = "none";
                            if (j === 3) {
                                tab[j].setAttribute("class", "lastli");
                            } else {
                                tab[j].setAttribute("class", "");
                            }

                        }
                        var tabcla = tab[i].getAttribute("class");
                        if (tabcla) {
                            tab[i].setAttribute("class", "lastli now");
                        } else {
                            tab[i].setAttribute("class", "now");
                        }
                        helpContent[i].style.display = "block";
                    }

                })(i);
            }
        }
    </script>



</body></html>