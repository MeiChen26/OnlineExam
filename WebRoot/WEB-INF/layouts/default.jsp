<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	Subject subject = SecurityUtils.getSubject();
	if(subject == null || !subject.isAuthenticated()){
		subject.logout();
	}
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>驾校综合信息管理系统</title>
<!-- reference 引入了一些基础的 样式和js插件，如需拓展 样式文件放到该标签上方，js脚本放该标签下方 -->
<!-- REFERENCES BEGIN -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/plugin/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/plugin/zxf_page/zxf_page.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/plugin/msg_layer/layer-animate.css" />
<!--引入弹出层信息 -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/assets/plugin/layer/skin/layer.css" />

<script type="text/javascript"
	src="${ctx}/assets/plugin/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/jquery/jquery.cokie.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/jquery-validation/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/jquery-validation/js/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/common.exam.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/highlight.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/zxf_page/zxf_page.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plugin/msg_layer/method.js"></script>
<!--引入弹出层信息 -->
<script type="text/javascript" src="${ctx}/assets/plugin/layer/layer.js"></script>
<!-- REFERENCES END -->
<sitemesh:head />
</head>
<body>
	<!-- PAGE HEADER BEGIN -->
	<div class="header-top-div">
		<div class="header-left-div" style="background-color: #5d8ffc;"> 
		</div>
		<div class="header-right-div">
			<span class="header-title">驾校综合信息管理系统</span>

			<div class="header-logout-div">
				<div class="header-logout-btn-div">
					<a href="${ctx}/system/logout"> <span>退出</span> <img
						src="${ctx}/assets/images/img_logout.png" alt="">
					</a>
				</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				<div class="header-logout-info-div">
					<span>您好:</span> <span class="header-jcName">${currentUser.userName }</span>
				</div>

			</div>
		</div>
	</div>
	<!-- PAGE HEADER END -->
	<!--LEFT MENU BEGIN-->
	<div class="left-menu-div">
		<div class="left-menu-bg-div">
			<ul class="menu-ul">
				<c:if test="${currentUser.type eq 1 }">
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">学员管理</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/student/list"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">学员管理</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">教练管理</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/coach/list"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">教练管理</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">习题集管理</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/question/list?subject=1"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目一</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/question/list?subject=2"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目二</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/question/list?subject=3"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目三</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/question/list?subject=4"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目四</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">考试成绩管理</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/exam/list"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">考试成绩管理</span>
								</a>
							</li>
						</ul>
					</li>
				</c:if>
				<c:if test="${currentUser.type eq 2 }">
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">练习题</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/exercise/doExercise?subject=1"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目一</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/exercise/list?subject=2"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目二</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/exercise/list?subject=3"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目三</span>
								</a>
							</li>
							<li class="">
								<a href="${ctx}/exercise/doExercise?subject=4"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">科目四</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">我的成绩</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/exam/myexam"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">我的成绩</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">我的教练</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/student/mycoach"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">我的教练</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="JavaScript:void(0);"> <span
							class="img-content "></span> <span
							class="menu-text">密码修改</span>
						</a>
						<ul class="menu-ul-second">
							<li class="">
								<a href="${ctx}/student/updatePwd"> <span
									class="img-content img-map-fengong"></span> <span
									class="menu-text">密码修改</span>
								</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
	<!--LEFT MENU END-->
	<!-- PAGE CONTENT BEGIN -->
	<sitemesh:body />
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
		  
	</script>
</body>
</html>