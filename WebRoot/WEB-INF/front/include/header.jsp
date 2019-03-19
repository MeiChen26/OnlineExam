<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
<meta name="author" content=""/>
<meta name="description" content="这里是网页的描述,是给搜索引擎看的,搜索引擎根据这个描述进行收录排名,一般是网页内的关键字。" />
<meta name="keywords" content="用来告诉搜索引擎你网页的关键字是什么" />
<link href="${ctxAssets}/front/css/css.css" rel='stylesheet' type='text/css' />
<link href="${ctxAssets}/front/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${ctxAssets}/front/css/style.css" rel='stylesheet' type='text/css' />
<link href="${ctxAssets}/front/css/application.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxAssets}/plugin/jquery/jquery-1.7.2.js" ></script>
<script type="text/javascript" src="${ctxAssets}/front/js/tab.js" ></script>

<link href="${ctxAssets}/plugin/jbox-v2.3/jBox/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxAssets}/plugin/jbox-v2.3/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>

<script type="text/javascript">

/* $(document).ready(function() {
	var reqpath='${reqpath}';
	$("#"+reqpath).addClass("active");
}); */


</script>
 <script type="text/javascript"> 
/*   $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(_result) { 
 	 if (remote_ip_info.ret == '1') { 
 	 	//alert('国家：' + remote_ip_info.country +'\n省：' + remote_ip_info.province + '\n市：' + remote_ip_info.city + '\n区：' + remote_ip_info.district + '\nISP：' + remote_ip_info.isp + '\n类型：' + remote_ip_info.type + '\n其他：' + remote_ip_info.desc); 
 	 	$("#locationSpan").html(remote_ip_info.city); 
 	 } 
  }); */
</script> 
<section class="head" id="header">
	<aside class="header_else_wap">
		<span class="chengshi" id="locationSpan"></span>
		<div class="header_else_right">
			<span class="a6Color">在线考试系统</span>
			<c:choose>
			<%-- <c:when test="${fns:getMember().id==null}">
			33333
			</c:when> --%>
			<c:when test="${memberSession.id != null}">
			<a href="${ctx}/index">${memberSession.loginName}</a>
			&nbsp;
			<a href="${ctx}/logout"><span class="e34b16Color ak2">退出登录 </span></a>
			</c:when>
			<c:otherwise>
			<a href="${ctx}/toRegister"><span class="e34b16Color">注 册</span></a>
			<span class="fenge_line">|</span>			
			<a href="${ctx}/toLogin"><span class="e34b16Color ak2">登 录</span></a>
			</c:otherwise>
			</c:choose>
			<span class="huColor" style="display: none;">帮助中心</span>
			<img src="${ctxAssets}/images/dianh.png" class="tel_icon">
			<span class="a6Color">服务热线:</span>			
			<span style="color: #707070;">400-619-1166</span>
		</div>
	</aside>
	<!-- 头部 -->
</section>
<!-- 导航 -->
<nav class="fh5co-nav">
	<div class="container">
		<div class="mid">
<!--            	<div class="logo"> -->
<%-- 				<a href="${ctx }/"><img src="${ctxStatic}/front/images/logo.png"></a> --%>
<!--                </div> -->
			<div id="nav">
				<ul>
					   <li id="toLogin"><a href="${ctx }/toLogin">登录</a></li>
                       <li id="score"><a href="${ctx }/score">成绩查询</a></li>
                       <li id="onlineExam"><a href="${ctx }/onlineExam">在线考试</a></li>
                       <li id="usAbout"><a href="${ctx }/selfCenter">个人中心</a></li>
				</ul>
			</div>
		</div> 
		
	</div>
</nav>
<style>
	@charset "utf-8";
	/* CSS Document */
	*{ margin:0; padding:0;}
	body{ font-size:12px; font-family:Arial, Helvetica, sans-serif,"微软雅黑"; color:#333;}
	body,ul,li,p,input,img,h1,h2,h3,h4,h5,h6,p{ margin:0; padding:0;}
	input,img{ border:none;}
	ul,li,ol{ list-style:none;}
	a{font-family:Arial, Helvetica, sans-serif,"微软雅黑","宋体","黑体"; color:#333; text-decoration:none;}
	i{ font-style:normal; font-weight:normal;}

	.shezhi{ width:150px; height:50px;background: -webkit-linear-gradient(#fff, #dedede);
			position:absolute;left:50%;top:50%;
  			background: -o-linear-gradient(#fff, #dedede);
  			background: -moz-linear-gradient(#fff, #dedede); 
  			background: linear-gradient(#fff, #dedede); border:1px solid #dedede;
			font-size:18px; color:#333; line-height:50px; border-radius:7px;}
	
	.shezhi img{ width:30px; height:30px; margin-left:10px; vertical-align:middle;}


</style>


<div id="tipMsg" class="shezhi" style="display:none;">
	<img src="${ctxStatic}/images/successTip.png">
    操作成功！
</div>

<script type="text/javascript">
function showTip(){
	$("#tipMsg").show();
	 //设置关闭时间  
    window.setTimeout('hideEle()',3000);   
}

function hideEle(){
	$("#tipMsg").hide();
}
</script>