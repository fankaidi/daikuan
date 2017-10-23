<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh">
<!-- Header部分 --><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="wokee">
<title><ex:static name="title"/>-旅行精英之选</title>
<meta name="keywords" content="<ex:static name="title"/>-旅行精英之选">
<meta name="description" content="<ex:static name="title"/>为客户层层筛选">
<meta name="robots" content="all">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<link media="all" type="text/css" rel="stylesheet" href="<ex:static name="context"/>/ly/show/asset/base.min.css">
<link media="all" type="text/css" rel="stylesheet" href="<ex:static name="context"/>/ly/show/asset/detail.css">
<script src="<ex:static name="context"/>/ly/show/asset/jquery.min.js"></script>
</head>
<!-- Header部分 -->

<body>

<style type="text/css">
    .zhuti{
        background:#49bfbf; background:rgba(73,191,191,0.95); filter:alpha(opacity = 95); padding:20px 10px 20px 30px; width:350px; position:absolute; top:40px; left:-10px;
    }
    .zhuti .g_menu_con01 span{ height: 24px; display: block; margin: 10px 100px 15px 0; float: left;}
    .zhuti .g_menu_con01 a{
      /*background: red;*/
        font-size: 15px;
        color: #fff;
        display: inline-block;
        float:left;
        height: 20px;
        line-height: 20px;
        width: 64px;
        text-align: center;
    }
    .zhuti .g_menu_con01 a:hover{
     border-bottom: 2px solid #fff;
    }

</style>
<script type="text/javascript">
$(function(){

$(".group_nav li").hover(function(){
  if($(this).find("div").size()>1){
    $(this).find(".group_menu").show();
    $(this).addClass("current");
    }
  },function(){
    $(this).find(".group_menu").hide();
    $(this).removeClass("current");
  })
  $(".group_menu").hide();
});
</script>
<div class="top_bg"> 
 		<jsp:include page="header.jsp" />
</div>

<!-- 页眉部分 -->
<div class="banner">
    
    <div class="banner">
        <div class="bd" style="width: 100%; height: 590px; background-size: 100% 100%; position: relative;">
                     <a href="http://www.wokee.com.cn/prod/150105104819158199" target="_self" class="banevent" style="background-image: url(&quot;http://img.wokee.com.cn/home/f_160829173619441843.jpg&quot;); width: 100%; height: 590px; background-size: 100% 100%; position: absolute; left: 0px; top: 0px; display: none;"></a>
                     <a href="http://www.wokee.com.cn/prod/150103145344328724" target="_self" class="banevent" style="background-image: url(&quot;http://img.wokee.com.cn/home/f_160829173741442799.jpg&quot;); width: 100%; height: 590px; background-size: 100% 100%; position: absolute; left: 0px; top: 0px; display: inline;"></a>
                     <a href="http://www.wokee.com.cn/prod/150105174346447638" target="_self" class="banevent" style="background-image: url(&quot;http://img.wokee.com.cn/home/f_160829173807443390.jpg&quot;); width: 100%; height: 590px; background-size: 100% 100%; position: absolute; left: 0px; top: 0px; display: none;"></a>
                     <a href="http://www.wokee.com.cn/prod/150104135246286616" target="_self" class="banevent" style="background-image: url(&quot;http://img.wokee.com.cn/home/f_160829173824443593.jpg&quot;); width: 100%; height: 590px; background-size: 100% 100%; position: absolute; left: 0px; top: 0px; display: none;"></a>
                    
        </div>
        
</div>

    <div class="hd">
        <ul><li class="">1</li><li class="on">2</li><li class="">3</li><li class="">4</li></ul>
      </div>
      <span class="prev" style="display: inline;"></span> <span class="next" style="display: inline;"></span>
</div>
<!-- 页眉部分 -->

<!-- 内容 -->
<script type="text/javascript">
    $(function(){
        $(".place_view ul li img,.rec_con ul li img").mouseover(function(){
             $(this).stop(1,0).animate({ 
                width: "110%",
                height: "110%", 
                left: "-5%",
                top: "-5%",
              }, 1500 );            
            }).mouseout(function(){
             $(this).stop(1,0).animate({ 
                width: "100%",
                height: "100%", 
                left: "0",
                top: "0",
              }, 1500 );            
            });
        });
</script>
<!-- 家庭朋友出境旅行首选部分 -->
<div class="preferred">
    <div class="preferred_tit">家庭朋友出境旅行首选</div>
    <div class="preferred_img"><img src="<ex:static name="context"/>/ly/show/asset/group_preferred.png"></div>
</div>
<!-- 精选目的地部分-->
 <div class="beeline">
    <div class="beeline_tit01"></div>
    <div class="g_line_l"></div>
    <div class="g_line_r"></div>
</div>
<div class="chosen_con">
    <!-- 六大洲 -->
    <ul class="place_name">
        <li>
            <a class="right_wid" href="http://www.wokee.com.cn/category?q=1"><h2>大洋洲</h2><p>OCEANIA</p>
            </a>
        </li>
        <li>
            <a href="http://www.wokee.com.cn/category?q=2"><h2>亚洲</h2><p>ASIA</p></a>
        </li>
        <li>
            <a href="http://www.wokee.com.cn/category?q=3"><h2>欧洲</h2><p>EUROPE</p>
            </a>
        </li>
        <li>
            <a href="http://www.wokee.com.cn/category?q=4"><h2>美洲</h2><p>NORTH AMERICE</p>
            </a>
        </li>
       
        <li>
            <a href="http://www.wokee.com.cn/category?q=6"><h2>海岛</h2><p>ISLAND</p>
            </a>
        </li>

    </ul>
    <!-- 六大洲对应的图片 -->
    <div class="place_view">
        <ul>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150103131816262168" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_170707182533476164.jpg"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>美国经典玩法</h3> 
                    <p>￥129000/人起</p>
                </div>
            </li>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150105154009361678" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150109113605191344.cclx"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>日本藏王温泉</h3> 
                    <p>￥51500/人起</p>
                </div>
            </li>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150104150516336399" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150110141901304396.cclx"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>法国+瑞士·雪山与湖</h3> 
                    <p>￥99000/人起</p>
                </div>
            </li>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150105174346447638" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150110140505295343.cclx"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>马尔代夫甜蜜之旅</h3> 
                    <p>￥43000/人起</p>
                </div>
            </li>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150109105822165390" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150110142612309753.cclx"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>澳洲精华之旅</h3> 
                    <p>￥81500/人起</p>
                </div>
            </li>
                        <li>
                <a href="http://www.wokee.com.cn/prod/150104113904193565" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_160829175249453523.jpg"></a>
                <div class="place_txts">
                    <!-- <h3>澳·特慢-澳大利亚过暖冬9天之旅</h3> -->
                    <h3>俄罗斯北国风光</h3> 
                    <p>￥69000/人起</p>
                </div>
            </li>
                       
        </ul>
    </div>
</div>
<!-- 推荐主题 -->
<div class="beeline">
    <div class="beeline_tit02"></div>
    <div class="g_line_l"></div>
    <div class="g_line_r"></div>
</div>
<!-- 推荐主题下的图片 -->
<div class="rec_subject">
       <div class="rec_subli">
       
        <div class="line_love tit01">家庭亲子</div>
   
        <div class="line_slip"><a href="http://www.wokee.com.cn/category?sub=1"><strong>7</strong>条旅游线路 <span>&gt;</span></a></div>
        <div class="rec_con">
            <ul>
                                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150103145344328724" target="_self">
                    <img src="<ex:static name="context"/>/ly/show/asset/f_150110144614323299.cclx"></a>
                    <div class="rec_txts">
                        <!-- <h3>澳世奇观·澳大利亚世界文化遗产9天之旅</h3> -->
                        <h3>美国东海岸名城之旅</h3>
                        <p><span>￥99000</span>/人起</p>
                    </div>
                </li>
                                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150103223935652162" target="_self">
                    <img src="<ex:static name="context"/>/ly/show/asset/f_150106190926506832.jpg"></a>
                    <div class="rec_txts">
                        <!-- <h3>澳世奇观·澳大利亚世界文化遗产9天之旅</h3> -->
                        <h3>英国·探访梦中学府</h3>
                        <p><span>￥112000</span>/人起</p>
                    </div>
                </li>
                                                                
            </ul>
        </div>
    </div>
            <div class="rec_subli">
        <div class="line_love tit02">蜜月之行</div>
        <div class="line_slip"><a href="http://www.wokee.com.cn/category?sub=2"><strong>14</strong>条旅游线路 <span>&gt;</span></a></div>
        <div class="rec_con">
            <ul>
                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150103205305578980" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150112114949201953.cclx"></a>
                    <div class="rec_txts">
                        <h3>夏威夷双岛游8天5晚</h3>
                        <p><span>￥57000</span>/人起</p>
                    </div>
                </li>
                                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150105172234432514" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_160829180001458919.png"></a>
                    <div class="rec_txts">
                        <h3>斐济·天堂之爱</h3>
                        <p><span>￥68000</span>/人起</p>
                    </div>
                </li>
                                                
            </ul>
        </div>
    </div>
            <div class="rec_subli">
        <div class="line_love tit03">畅游经典</div>
        <div class="line_slip"><a href="http://www.wokee.com.cn/category?sub=3"><strong>50</strong>条旅游线路 <span>&gt;</span></a></div>
        <div class="rec_con">
            <ul>
                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150105210021583600" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_160830093223105594.jpg"></a>
                    <div class="rec_txts">
                        <h3>泰国曼谷养生6天</h3>
                        <p><span>￥44900</span>/人起</p>
                    </div>
                </li>
                                                             <li>
                    <a href="http://www.wokee.com.cn/prod/150103213434607922" target="_self"><img src="<ex:static name="context"/>/ly/show/asset/f_150110135442287170.cclx"></a>
                    <div class="rec_txts">
                        <h3>加拿大西海岸经典极光</h3>
                        <p><span>￥115000</span>/人起</p>
                    </div>
                </li>
                                         </ul>
        </div>
    </div>
    
         <div class="rec_subli">
        <div class="line_love tit04">美食美酒</div>
        <div class="line_slip"><a href="http://www.wokee.com.cn/category?sub=6"><strong>5</strong>条旅游线路 <span>&gt;</span></a></div>
        <div class="rec_con">
            <ul>
                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150104161407384728"><img src="<ex:static name="context"/>/ly/show/asset/f_150106202410558153.jpg"></a>
                    <div class="rec_txts">
                        <h3>伦敦+巴黎·米其林</h3>
                        <p><span>￥79000</span>/人起</p>
                    </div>
                </li>
                                                                 <li>
                    <a href="http://www.wokee.com.cn/prod/150104140327294134"><img src="<ex:static name="context"/>/ly/show/asset/f_150106202433558256.jpg"></a>
                    <div class="rec_txts">
                        <h3>法国·细品顶级酒庄</h3>
                        <p><span>￥115000</span>/人起</p>
                    </div>
                </li>
                                             </ul>
        </div>
    </div>
    

        <div class="rec_subli">
        <div class="line_love tit05">运动探险</div>
        <div class="line_slip"><a href="http://www.wokee.com.cn/category?sub=7"><strong>3</strong>条旅游线路 <span>&gt;</span></a></div>
        <div class="rec_con">
            <ul>
                                                <li>
                    <a href="http://www.wokee.com.cn/prod/150103225024660551"><img src="<ex:static name="context"/>/ly/show/asset/f_160830094054111838.jpg"></a>
                    <div class="rec_txts">
                        <h3>瑞士·湖光山色</h3>
                        <p><span>￥89000</span>/人起</p>
                    </div>
                </li>
                                                                 <li>
                    <a href="http://www.wokee.com.cn/prod/150103145953333527"><img src="<ex:static name="context"/>/ly/show/asset/f_160830094220112379.jpg"></a>
                    <div class="rec_txts">
                        <h3>美国西海岸公路之旅</h3>
                        <p><span>￥112000</span>/人起</p>
                    </div>
                </li>
                                             </ul>
        </div>
    </div>
    </div>
<div class="us_img" style="background-image:url(http://img.wokee.com.cn/home/fo.jpg);filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src=&#39;http://img.wokee.com.cn/home/fo.jpg&#39;,sizingMethod=&#39;scale&#39;);">
        <div class="us_imgbox">
            <p><i class="foot_welcome"></i></p>
        </div>
</div>
<div class="back_box" style="left: 1295.5px; top: 570px;">
    <ul>
      <li class="topbtn" style="display: none;"><a href="http://www.wokee.com.cn/#"></a></li>
      </ul>
</div>




<script type="text/javascript">
function skipIm(imUrl, return_msg, remind, str) {

	var string=imUrl + "?remind=" + encodeURIComponent(remind) + "&return_msg=" + encodeURIComponent(return_msg) + str;
	if(getCookie("userId")){
		string+="&user_id="+getCookie("userId")+"&type=5";
	}else{
		addCookie("userId", 100000000 + Math.round(899999999 * Math.random()), 10);
		string+="&user_id="+getCookie("userId")+"&type=5";
	}
	window.open(string);
}
$('.back_box li').each(function(){
    var li = $(this);
    if(li.hasClass('tel')){
        var box = $('.open_box'),btn = $('.btn',li);
        function show(){
            box.show();
            var ofset = box.offset(),left = ofset.left + 20,top = ofset.top + 6;

            $('.lxb-container').css({'left':left,top:top,'position':'absolute','background':'transparent'}).show();
            $('.lxb-cb-input').css({height:'39px',color: '#fff','background':'transparent'});
            if($.browser.msie && parseInt($.browser.version || 0 , 10) < 9){
                $('.lxb-cb-input').css({'line-height':'35px'});
            }
            $('.lxb-cb-input-btn').css({height: '30px',
                                            left: 'auto',
                                            position: 'absolute',
                                            right: '-59px',
                                            top: '4px',
                                            width: '33px','background':'transparent'}).val('通话');
            $('.lxb-cb-tip').remove();

        }

        function bindmouseover(){
            $(window).bind('mouseover',function(e){
                var target = $(e.target);
                if(target.hasClass('tel') || target.parents('.tel').length > 0 ||
                target.hasClass('open_box') || target.parents('.open_box').length > 0 ||
                 target.hasClass('lxb-container') || target.parents('.lxb-container').length > 0
                ){
                   show();
                }else{
                    box.hide();
                    $(window).unbind('mouseover');
                    $('.lxb-container').hide()
                }

            });
        }
        if($.browser.msie && parseInt($.browser.version || 0 , 10) < 9){
            var TIMEKEY = undefined;
            li.mouseover(function(){
               clearTimeout(TIMEKEY);
               $('.lxb-container').mouseover(function(){
                   clearTimeout(TIMEKEY);
                    //menu.show();
               }).mouseout(function(){
                   clearTimeout(TIMEKEY);
                   li.trigger('mouseout');
               });
               show();
            }).mouseout(function(){
                  TIMEKEY = setTimeout(function(){
                       box.hide();
                        $('.lxb-container').hide();
                  },500);
              });
              box.mouseover(function(){
                  clearTimeout(TIMEKEY);
                   //menu.show();
              }).mouseout(function(){
                  clearTimeout(TIMEKEY);
                  li.trigger('mouseout');
              });

        }else{
            li.mouseover(function(){
               bindmouseover();
            });
        }



    }else if(li.hasClass('topbtn')){
        $('a',li).click(function(e){
        e.preventDefault();
            $('html,body').animate({scrollTop:0},150);
        });
    }
});


$(window).resize(function(){
    var l = $(window).width() / 2 + 504;
    var t  = $(window).height()  - 150;
    var TTMMERKEYS = undefined;
    $('.back_box').css({'left':l,top:t});
    if($(window).width() < 1024){
        $('.back_box').css({'left':'auto','right':'0px'});
     }
    setTimeout(function(){

    },1000);
     if($.browser.msie && parseInt($.browser.version || 0 , 10) < 9){
        setTimeout(function(){
            $('.lxb-container').hide();
        },1000);
    }else{
        TTMMERKEYS = setInterval(function(){
            if($('.lxb-container').is(':visible')){
                 $('.lxb-container').hide();
                 clearInterval(TTMMERKEYS);
            }
        },500);
    }
}).trigger('resize');

$(window).scroll(function(){
    var top = $(this).scrollTop();
     var t  = $(window).height() / 2 - 120 / 2;
     if(top > $(window).height()){
        $('.topbtn','.back_box').show();
     }else{
         $('.topbtn','.back_box').hide();
     }

    if(top > $(window).height() / 2){
        $('.back_box').stop(1,0).animate({top:t},1000);
    }else{
        t  = $(window).height() - 150;
        $('.back_box').stop(1,0).animate({top:t},1000);
    }
}).trigger('scroll');
</script>

                
<style type="text/css">
.map-icon{position: absolute;background:transparent;width: 23px;height:23px;display: block;top: 192px;left: 170px;}
.line_show{overflow: hidden;}
.line_show a:hover img{transform: none;}
</style>
<!-- 内容 -->

<!-- 页脚内容 -->

<div class="footer">
    <div class="foot_in">

      <div class="group_footer">
      <div class="group_foot_in">
          <ul class="foot_explain">
              <li>
                  <i class="i01"></i>
                  <h2>专属定制</h2>
                    <p>一对一服务按需定制<br>提供最切实旅行服务</p>
                </li>
              <li>
                  <i class="i02"></i>
                  <h2>贴心服务</h2>
                    <p>7x24小时管家式服务<br>旅程360°贴心体验</p>
                </li>
              <li>
                  <i class="i03"></i>
                  <h2>隐私承诺</h2>
                    <p>全程隐私承诺服务<br>打造完全私密行程</p>
                </li>
            </ul>
            <div class="group_foot_link">
                <div class="link_nav">
                    <a href="http://www.wokee.com.cn/serv/info">关于我们</a>|
                    <a href="javascript:;" class="licence">营业资质</a>|
                    <a href="http://www.wokee.com.cn/serv/contact">联系我们 </a>|
                    <a href="http://www.wokee.com.cn/serv/what">加入卧客</a>|
                    <a href="http://www.wokee.com.cn/serv/help">品牌合作</a>|
                    <a href="http://www.wokee.com.cn/serv/pay">支付方式</a>
                </div>
                
                <p class="link_copy">Copyright©2013-2016 wokee.com.cn 京ICP备17003195号-1版权所有</p>
            </div>
        </div>
    </div>
    <!-- 营业资质 2016/12/08   Begin-->
    <div class="license">
        <div class="license-close">
            <a href="javascript:;" title="关闭" class="close-a">ｘ</a>
        </div>
        <div class="license-content">
            <p><img src="<ex:static name="context"/>/ly/show/asset/zhizhao.jpg"></p>
        </div>
    </div>
    <div class="license-big"></div>
    
    <script>
        jQuery(document).ready(function($) {
            $('.licence').click(function(){
                $('.license-big').fadeIn(100);
                $('.license').slideDown(200);
            })
            $('.license-close .close-a').click(function(){
                $('.license-big').fadeOut(100);
                $('.license').slideUp(200);
            })
        })
    </script>
   
    </div>
</div>
<div style="display:none;">
</div>

<script type="text/javascript" src="<ex:static name="context"/>/ly/show/asset/jquery.comm.js"></script>
<script type="text/javascript">
 var LOGINURL = "http://www.wokee.com.cn/login",REGURL = "http://www.wokee.com.cn/reg",REGEDITS = 'http://www.wokee.com.cn/regiest';
 var BASICURL = 'http://www.wokee.com.cn';
 var WECHARTLOGINURL = 'http://www.wokee.com.cn';
$(function(){
    $('.nav_where').each(function(){
         var a = $(this),menu = $('.mdesl');
        function mouseover(){
            $(window).bind('mouseover',function(e){
                var target = $(e.target);

                if(target.hasClass('nav_where') || target.parents('.nav_where').length > 0 || target.parents('.menu').length > 0 || target.hasClass('menu')){
                    menu.show();
                }else{
                    mouseout();
                }

            });

        };
        function mouseout(){
            $(window).unbind('mouseover');
            menu.hide();
        };



        a.css('padding-bottom','9px');
        if($.browser.msie && parseInt($.browser.version || 0 , 10) < 9){
            var TIMEKEY = undefined;
            a.mouseover(function(){
             clearTimeout(TIMEKEY);
                 var l = a.getOffsetLeft() - a.width() / 2,t = a.getOffsetTop() + a.height() + 8;
                menu.css({left:l,top:t,position:'absolute'});
               menu.show();
            }).mouseout(function(){
                TIMEKEY = setTimeout(function(){
                     menu.hide();
                },1000);
            });
            menu.mouseover(function(){
                clearTimeout(TIMEKEY);
                 //menu.show();
            }).mouseout(function(){
                clearTimeout(TIMEKEY);
                a.trigger('mouseout');
            });
        }else{
            a.mouseover(function(){
                 var l = a.getOffsetLeft() - a.width() / 2,t = a.getOffsetTop() + a.height() + 8;
                menu.css({left:l,top:t,position:'absolute'});
               mouseover();

            });
        }
    });
    $('.nav_login').each(function(){
             var a = $(this),menu = $('.islogins');
            function mouseovers(){
                $(window).bind('mouseover',function(e){
                    var target = $(e.target);
                    if(target.hasClass('nav_login') || target.parents('.nav_login').length > 0 || target.parents('.menu').length > 0 || target.hasClass('menu')){
                        menu.show();
                    }else{
                        mouseout();
                    }

                });

            };
            function mouseout(){
                $(window).unbind('mouseover');
                menu.hide();
            };



            a.css('padding-bottom','7px');


            if($.browser.msie && parseInt($.browser.version || 0 , 10) < 9){
                var TIMEKEY = undefined;
                a.mouseover(function(){
                if($('.islogin',a).length > 0){
                  clearTimeout(TIMEKEY);
                     var l = a.getOffsetLeft() - a.width() / 2 + 30,t = a.getOffsetTop() + a.height() - 12;
                    menu.css({left:l,top:t,position:'absolute'});
                   menu.show();
                   }
                }).mouseout(function(){
                    TIMEKEY = setTimeout(function(){
                         menu.hide();
                    },1000);
                });
                menu.mouseover(function(){
                    clearTimeout(TIMEKEY);
                     //menu.show();
                }).mouseout(function(){
                    clearTimeout(TIMEKEY);
                    a.trigger('mouseout');
                });
            }else{
               a.mouseover(function(){
                 if($('.islogin',a).length > 0){
                   var l = a.getOffsetLeft(),t = a.getOffsetTop() + a.height() - 12;
                   menu.css({left:l,top:t,position:'absolute'});
                   mouseovers();
                  }
               });
            }

        });
});
</script>
<script type="text/javascript">
$(function(){
$(".group_nav li").hover(function(){
    if($(this).find("div").size()>1){
        $(this).find(".group_menu").show();
        $(this).addClass("current");
        }
    },function(){
        $(this).find(".group_menu").hide();
        $(this).removeClass("current");
    })
    $(".group_menu").hide();
});
</script>
        

<!-- 联系我们 -->
<script type="text/javascript">
$(function(){
$(".group_nav li").hover(function(){
  if($(this).find("div").size()>1){
    $(this).find(".group_menu").show();
    $(this).addClass("current");
    }
  },function(){
    $(this).find(".group_menu").hide();
    $(this).removeClass("current");
  })
  $(".group_menu").hide();
});
</script>
<!-- 加入卧客 -->
<script type="text/javascript">
$(function(){
$(".group_nav li").hover(function(){
  if($(this).find("div").size()>1){
    $(this).find(".group_menu").show();
    $(this).addClass("current");
    }
  },function(){
    $(this).find(".group_menu").hide();
    $(this).removeClass("current");
  })
  $(".group_menu").hide();
});
</script>


<script type="text/javascript">
$(function(){
  $(".dept_tit h1").toggle(function(){
    $(this).addClass("on");
    $(this).parents(".us_list").find(".up").slideDown();
  }
  ,function(){
    $(this).removeClass("on");
    $(this).parents(".us_list").find(".up").slideUp();
    }
  );
  $(".dept_tit .up").hide();
  $(".dept_tit h1").eq(0).click();  
});
</script>



<script src="<ex:static name="context"/>/ly/show/asset/superslide.2.1.js"></script>

<script type="text/javascript">
$(function(){
$(".banner").slide({
    		titCell: ".hd ul",
    		mainCell: ".bd",
    		effect: "fold",
    		easing:'swing',
    		autoPlay: true,
    		interTime:5000,
    		autoPage: true,
    		trigger: "click",
    		startFun: function(i) {
    			var curLi = jQuery(".banner .bd a").eq(i);
    			if ( !! curLi.attr("_src")) {
    				curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
    			}
    		}
    	});
});


</script>
<script src="<ex:static name="context"/>/ly/show/asset/jquery.cclx.index.js"></script>


<script type="text/javascript">
$(window).resize(function(){
    var w = $(this).width(), h = $(this).height();
    var target = $('a.banevent,.bd','.banner');
    //target.addClass('loading');
    target.each(function(){
        var a = $(this);
        if (w < 1600) {
            a.css({width:'100%',height:'590px','background-size': '100% 100%'});
            return true;
        }
        var f = 1600 / w;
        a.css({'width': 1600 / f, 'height': 590 / f,'background-size': '100% 100%'});
    });


}).trigger('resize');
$(".banner").mouseover(function(){
$('.prev,.next').show();
}).mouseout(function(){
$('.prev,.next').hide();
});
</script>


</body></html>