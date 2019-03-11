<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/common.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>
<!--                 <i class="icon-logo"></i> -->
                <span class="text">驾校综合信息管理系统</span>
            </h1>
        </div>
        <div class="form">
            <form class="login-form" action="${ctx}/system/loginpage" method="post">
            <div class="input-name">
                <i class="icon-name"></i>
                <input type="text" placeholder="请输入用户" id="inputName" name="username">
                <span class="warn name-warn" style="color:red;" id="nameWarn">${nameWarn }</span>
            </div>
            <div class="input-pwd">
                <i class="icon-pwd"></i>
                <input type="password" placeholder="请输入密码" id="inputPwd" name="password">
                <span class="warn pwd-warn" style="color:red;" id="pwdWarn">${pwdWarn }</span>
            </div>
            <%-- <div class="input-pwd">
                <i class=""></i>
                <input type="text" placeholder="请输入验证码" id="inputCode" name="randomcode" style="width:191px;">
                <a href="javascript:void(0);" onclick="changeCode()"><img style="height:50px;width:124px;display:inline-block;vertical-align:bottom;" alt="" src="" id="codeImg" /></a>
                <span class="warn code-warn" style="color:red;" id="codeWarn">${codeWarn }</span>
            </div> --%>
            <div class="remember pt10">
                <span class="flag" id="remPwd"></span>
                <span class="text">记住密码</span>
            </div>
            <div class="btn tocenter pt30">
                <button id="btnLogin" type="submit">立即登陆</button>
            </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/assets/plugin/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${ctx}/assets/plugin/jquery/jquery-migrate.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/plugin/jquery/jquery.cokie.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/plugin/jquery/jquery.base64.js"></script>
   <script>
   		var path = '${pageContext.request.contextPath}';
   		var userName = "";
        var userPwd = "";
      // var userCode = "";
        $(function() {
            // 填充数据
            getCookie();
            
           // changeCode();
            
            // 账户、密码检测
            $("#inputName").focus(function() {
                $("#nameWarn").text("");
            })
            $("#inputPwd").focus(function() {
                $("#pwdWarn").text("");
            })
           /*  $("#inputCode").focus(function() {
                $("#codeWarn").text("");
            }) */
                // 记住密码选框
            $("#remPwd").click(function() {
                $(this).toggleClass("active");
            })
            $("#btnLogin").click(function() {
                // 账户
                userName = $("#inputName").val().replace(/(^\s*)|(\s*$)/g, ""); //用户名
                
                if (!userName) {
                    $("#nameWarn").text("用户名不能为空");
                    return false;
                }
                userPwd = $("#inputPwd").val().replace(/(^\s*)|(\s*$)/g, ""); //登陆密码     
                // 密码
                if (!userPwd) {
                    $("#pwdWarn").text("密码输入错误");
                    return false;
                }
                /* userCode = $("#inputCode").val().replace(/(^\s*)|(\s*$)/g, ""); //验证码 
                if (!userCode) {
                    $("#pwdWarn").text("验证码输入错误");
                    return false;
                } */
                
                setCookie();
                return true;
            })

        })

        // 设置cookie
        function setCookie() {
            var checked = $("#remPwd").hasClass("active"); //是否记住密码  
            console.log(checked);
            if (checked) {
                //设置用户名
                $.cookie("lastName", userName, {
                    expires: 7
                });
                //设置cookie登陆密码，并加密
                $.cookie("lastPwd", $.base64.encode(userPwd), {
                    expires: 7
                });
                // var lastName = $.cookie("lastName"); //获取用户名    
                // var lastPwd = $.cookie("lastPwd"); //获取登陆密码 
                // var lastName = $.cookie().lastName; //获取用户名    
                // var lastPwd = $.cookie().lastPwd; //获取登陆密码 
                //console.log(lastName, lastPwd);
            } else {
                $.cookie("lastPwd", null);
            }
        }
        //获取cookie
        function getCookie() {
            var lastName = $.cookie("lastName"); //获取用户名
            var lastPwd = $.cookie("lastPwd"); //获取登陆密码
            //console.log(lastName, lastPwd);
            if (lastPwd) { //密码存在，选中记住密码    
                $("#remPwd").addClass("active");
            }
            if (lastName) { //填充用户名    
                $("#inputName").val(lastName);
            }
            if (lastPwd) { //填充密码    
                $("#inputPwd").val($.base64.decode(lastPwd));
            }
        }
        
       /*  function genTimestamp() {    
            var time = new Date();    
            return time.getTime();    
        }
        function changeCode() {    
            $("#codeImg").attr("src", path+"/code?t=" + genTimestamp());    
        }  */
    </script>
</body>

</html>