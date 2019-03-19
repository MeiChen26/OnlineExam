<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>登录</title>
	<link href="${ctxAssets}/front/css/geren.css" rel='stylesheet' type='text/css' />
</head>
<body>
<div class="dl_bg" style="height:70%;">
<section id="mima_login">

			<!-- 密码登录 -->	
			<form id="mimaForm" action="${ctx}/login" onsubmit="return loginPostCheck();">	
			<article class="login_list1" style="display: block;">
			   
				<div class="row2">
                	<img src="${ctxAssets}/images/1.png" width="30" class="aaa">
					<div class="fengexian1"></div>
					
					<input type="text"  name="studentNo" id="studentNo" placeholder="学号" onblur="validation('loginName')">
                    <div class="studentNo" id="mmStudentNoMsg"></div>
				</div>
				<div class="row3">
                	<img src="${ctxAssets}/images/a2.png" width="30" class="aaa">
					<div class="fengexian1"></div>
					<input type="password" id="password" name="password" placeholder="密码" onblur="validation('password')">
                    
                    <div class="passwordMsg" id="mmpwdPassword"></div>
				</div>
				<span style="color:red">${message }</span>
				<input id="btnSubmit" class="row4" type="submit" value="登录"  />&nbsp;
				
			</article>
          </form>
		</section>       
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#toLogin").addClass("active");
	}); 
//账号密码验证
function validation(logo) {

   
    if (logo == "studentNo") {

        var photo = $("#studentNo").val();
        $(".studentNoMsg").html("");
        if (photo == "") {
            $(".studentNoMsg").html("学号不能为空！");
            return false;
        }
        return true;
    }

    if (logo == "password") {
		if($.browser.msie){
		 $(".passwordMsg").css({"margin-top":"24px"});
		}
        var password = $("#password").val();
        $(".passwordMsg").html("");
        if (password == "") {
             $(".passwordMsg").html("密码不能为空！");
			if($.browser.msie){
             $("#prompt1").show();
		    }
            return false;
        }
//         if (password.length < 6 || 15 < password.length) {
//             $(".passwordMsg").html("输入的密码必须为6~15位！");
//             return false;
//         }
        return true;
    }

}
     function loginPostCheck(){
    	 var studentNo = $("#studentNo").val();
         $(".studentNoMsg").html("");
         if (photo == "") {
             $(".studentNoMsg").html("学号不能为空！");
             return false;
         }
         if($.browser.msie){
    		 $(".passwordMsg").css({"margin-top":"24px"});
    		}
            var password = $("#password").val();
            $(".passwordMsg").html("");
            if (password == "") {
                 $(".passwordMsg").html("密码不能为空！");
    			if($.browser.msie){
                 $("#prompt1").show();
    		    }
                return false;
            }
            if (password.length < 6 || 15 < password.length) {
                $(".passwordMsg").html("输入的密码必须为6~15位！");
                return false;
            }
            return true;
     }
</script>
</body>
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</html>
