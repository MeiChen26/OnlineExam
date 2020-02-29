// 当页面加载完成时加载数据
var loadOk=false;
var reportId;
var customerId;
var currentCount;

var questionId;

var type = getUrlParam("type");
$(document).ready(function () {
	questionId = getUrlParam("questionId");
	reportId = getUrlParam("reportId");
	customerId = getUrlParam("customerId");
	currentCount = getUrlParam("currentCount");
	if(!$.browser.msie){
		$("#password").attr("placeholder","请输入密码");
		$("#prompt1").remove();
		$("#prompt2").remove();
		$("#newPassword").attr("placeholder","请输入密码6-15位字符");
		$("#newPassword2").attr("placeholder","请输入密码");
		$("#newPassword3").attr("placeholder","请再次输入密码");
		$("#prompt3").remove();
		$("#prompt4").remove();
		
	}
   
    // yanzhengmaLogin();
    setTimeout(function(){
        loadOk=true;
    },200);

    if($.browser.msie){
      if($.browser.version<9){
          $("#prompt1").show();

          $("#prompt2").show();
          $("#prompt3").show();
          $("#prompt4").show();

      }

   }

});
function guanbi() {
    parent.hiddenFrame();
}
var correctNum;
// 密码登录
function mimaLogin() {
    $(".login_list1").show();
    $(".login_list2").hide();
    $("#login_list3").hide();
	$(".login_list5").hide();
}

// 验证码登录
function yanzhengmaLogin() {
    $(".login_list2").show();
    $(".login_list1").hide();
    $("#login_list3").hide();
	$(".login_list5").hide();
}


// 忘记密码
function wangjimima() {
    $("#login_list3").show();
    $(".login_list1").hide();
    $(".login_list2").hide();
	$(".login_list5").hide();
}

//扫码登陆
function scanCodeLogin(){
	$(".login_list5").show();
    $("#login_list3").hide();
    $(".login_list1").hide();
    $(".login_list2").hide();
	getErweima();	
}








var loginTel;
//登录
function login(loginType) {

    var password = "";
    var photo = "";

    if (loginType == 1) {
        //账号密码登录
        if (Validation("photo") && Validation("password")) {
            photo = $("#photo").val();
            password = hex_md5($("#password").val());
        } else {
            return;
        }
    }

    if (loginType == 0) {
        //验证登录
        if (Validation2("photo") && Validation2("code")) {
            photo = $("#photo2").val();
            loginTel=photo; //记住号码
        } else {
            return;
        }
    }

    //alert("loginType:"+loginType);
    var getUrl = baseUrl + "login?tel=" + photo + "&loginType=" + loginType + "&password=" + password + getJSONPUrlSuffix();
    //alert(getUrl);
    if (loginType == 2) {
        //alert("loginType:"+loginType);
        //找回密码
        if (Validation3("photo") && Validation3("password") && Validation3("code")) {

            photo=$("#photo3").val();
            var newPassword = hex_md5($("#newPassword").val());
            getUrl = baseUrl + "changePassword?tel=" + photo + "&newPassword=" + newPassword + "&withPassword=0&password=" + getJSONPUrlSuffix();
            //alert(getUrl);
        } else {
            return;
        }
    }

    if (loginType == 3) {

        //找回密码
        if (Validation4(2) && Validation4(3)) {
            //photo=$("#photo3").val();
//            alert("密码:"+$("#newPassword2").val());
            var newPassword = hex_md5($("#newPassword2").val());
            getUrl = baseUrl + "changePassword?tel=" + loginTel + "&newPassword=" + newPassword + "&withPassword=0&password=" + getJSONPUrlSuffix();
            //alert(getUrl);
        } else {
            return;
        }
    }

  
    //alert(getUrl);

    
	

}




//账号密码验证
function Validation(logo) {

   

    if($("#"+logo).val()==''){

        $("#prompt1").show();
       // $(".passwordMsg").css("margin-top","24px");
    }
    if (logo == "photo") {

        var photo = $("#photo").val();
        var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        $(".photoMsg").html("");
        if (photo == "") {
            $(".photoMsg").html("手机号码不能为空！");
            return false;
        }
        if (!reg.test(photo)) {

            $(".photoMsg").html("您输入的手机号码有误！");
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
        if (password.length < 6 || 15 < password.length) {
            $(".passwordMsg").html("输入的密码必须为6~15位！");
            return false;
        }
        return true;
    }

}

//验证码登录验证
function Validation2(logo) {
  
    if (logo == "photo") {
 
        var photo = $("#photo2").val();
        var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        $(".photoMsg2").html("");
        if (photo == "") {
            $(".photoMsg2").html("手机号码不能为空！");
            return false;
        }
        if (!reg.test(photo)) {

            $(".photoMsg2").html("您输入的手机号码有误！");
            return false;
        }

        return true;
    }

    if (logo == "code") {

        var code = $("#code").val();
        $(".codeMsg").html("");
        if (code == "") {
            $(".codeMsg").html("验证码不能为空！");
            return false;
        }
        if (code.length != 4) {
            $(".codeMsg").html("验证码格式错误！");
            return false;
        }
        if (code != correctNum) {
            $(".codeMsg").html("验证码输入错误！");
            return false;
        }
        return true;
    }


}

//更改密码验证
function Validation3(logo) {
    //alert("验证字段:"+logo);
    
    if (logo == "photo") {

        var photo = $("#photo3").val();
        var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
        $(".photoMsg3").html("");
        if (photo == "") {
            $(".photoMsg3").html("手机号码不能为空！");
            return false;
        }
        if (!reg.test(photo)) {

            $(".photoMsg3").html("您输入的手机号码有误！");
            return false;
        }

        return true;
    }

 
    if (logo == "password") {
       
        var newPassword = $("#newPassword").val();
		if($.browser.msie){
		$(".newPasswordMsg").css({"margin-top":"24px"});
		}
        $(".newPasswordMsg").html("");
        if (newPassword == "") {
            $(".newPasswordMsg").html("密码不能为空！");
			 if($.browser.msie){
         $("#prompt2").show();
		 
		}
            return false;
        }
        //alert("newPassword.length:"+newPassword.length);
        if (newPassword.length < 6 || 15 < newPassword.length) {
            $(".newPasswordMsg").html("输入的密码必须为6~15位！");
            return false;
        }
        return true;
    }

    if (logo == "code") {

        var code2 = $("#code2").val();
        $(".code2Msg").html("");
        if (code2 == "") {
            $(".code2Msg").html("验证码不能为空！");
            return false;
        }
        if (code2.length != 4) {
            $(".code2Msg").html("验证码格式错误！");
            return false;
        }
        if (code2 != correctNum) {
            $(".code2Msg").html("验证码输入错误！");
            return false;
        }

        return true;
    }

}

function Validation4(logo) {
    
    if (logo == 2) {
        
        var newPassword = $("#newPassword2").val();
        $(".newPasswordMsg2").html("");
        if (newPassword == "") {
			 if($.browser.msie){
            $("#prompt3").show();
		 
		    }
            $(".newPasswordMsg2").html("密码不能为空！");
            return false;
        }
        if (newPassword.length < 6 || 15 < newPassword.length) {
            $(".newPasswordMsg2").html("输入的密码必须为6~15位！");
            return false;
        }
        return true;
    }
    if (logo == 3) {
       
        var pwd = $("#newPassword2").val();
        var newPassword = $("#newPassword3").val();
        $(".newPasswordMsg3").html("");
        if (newPassword == "") {
			if($.browser.msie){
            $("#prompt4").show();
		 
		    }
            $(".newPasswordMsg3").html("密码不能为空！");
            return false;
        }
        if (newPassword.length < 6 || 15 < newPassword.length) {
            $(".newPasswordMsg3").html("输入的密码必须为6~15位！");
            return false;
        }
		
		if(pwd!=newPassword){
            $(".newPasswordMsg3").html("密码与确认密码不一致！");
            return false;
        }
        return true;
    }

}

var lg;
//获取验证码
function TestGetCode(logo) {
    lg = logo;
  
    if (logo == 1) {
		
        if (Validation2("photo")) {//验证登录获取验证码
            
			var photo = $("#photo2").val();
            $('.huoquyanzhengma_btn').css({'background': '#e3e3e3', 'color': '#fff'});
            $('.huoquyanzhengma_btn').attr("disabled", "true");
            countdown = setInterval(CountDown, 1000);
          
			CountDown();
            var getUrl = baseUrl2 + "sms?tel=" + photo + getJSONPUrlSuffix();

            //document.write(getUrl);


            $.ajax({

                type: "get", //jquey是不支持post方式跨域的
                async: false,
                url: getUrl,
                dataType: "jsonp",
                //传递给请求处理程序，用以获得jsonp回调函数名的参数名(默认为:callback)
                jsonp: "callbackJSONP",
                //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
                jsonpCallback: "callbackJSONP",
                //成功获取跨域服务器上的json数据后,会动态执行这个callback函数
                success: function (data) {

                    if (data.resultCode == 0) {

                        num = data.num;
                        //alert(num);
                        correctNum = num;


                    } else {
                        alert("获取数据失败")
                    }

                }
            });


        }
    } else {
        if (Validation3("photo")) { //找回密码获取验证码
            var photo = $("#photo3").val();
            $('.huoquyanzhengma_btn2').css({'background': '#e3e3e3', 'color': '#fff'});
            $('.huoquyanzhengma_btn2').attr("disabled", "true");
            countdown = setInterval(CountDown, 1000);
            CountDown(logo);
            var getUrl = baseUrl2 + "sms?tel=" + photo + getJSONPUrlSuffix();

            //document.write(getUrl);


            $.ajax({

                type: "get", //jquey是不支持post方式跨域的
                async: false,
                url: getUrl,
                dataType: "jsonp",
                //传递给请求处理程序，用以获得jsonp回调函数名的参数名(默认为:callback)
                jsonp: "callbackJSONP",
                //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
                jsonpCallback: "callbackJSONP",
                //成功获取跨域服务器上的json数据后,会动态执行这个callback函数
                success: function (data) {

                    if (data.resultCode == 0) {

                        num = data.num;
                        //alert(num);
                        correctNum = num;

                    } else {
                        alert("获取数据失败");
                    }

                }
            });


        }
    }


}

//点击获取验证码后显示倒计时
var count = 60;
var countdown;
function CountDown() {
    if (lg == 1) {//验证登录获取验证码
        $('.huoquyanzhengma_btn').parent().attr("href", "#");
        $(".huoquyanzhengma_btn").html(count + "S");
        if (count == 0) {
            $(".huoquyanzhengma_btn").css({"background": "#f4f4f4", "color": "#333"})
            $(".huoquyanzhengma_btn").html("获取验证码");
            $('.huoquyanzhengma_btn').parent().attr("href", "javascript:TestGetCode(" + lg + ")");
            clearInterval(countdown);
            count = 60;
        }
        count--;
    } else {//找回密码获取验证码
        $('.huoquyanzhengma_btn2').parent().attr("href", "#");
        $(".huoquyanzhengma_btn2").html(count + "S");
        if (count == 0) {
            $(".huoquyanzhengma_btn2").css({"background": "#f4f4f4", "color": "#333"})
            $(".huoquyanzhengma_btn2").html("获取验证码");
            $('.huoquyanzhengma_btn2').parent().attr("href", "javascript:TestGetCode(" + lg + ")");
            clearInterval(countdown);
            count = 60;
        }
        count--;
    }

}


function alterPassword(logo){

    $(".passwordMsg").css({"margin-top":"7px","+margin-top":"22px"});
	$(".newPasswordMsg").css({"margin-top":"7px","+margin-top":"22px"});
	
    $("#prompt"+logo).hide();




}


