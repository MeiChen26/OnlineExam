<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="${ctxAssets}/front/css/form.css">
	<script type="text/javascript" src="${ctxAssets}/plugin/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${ctxAssets}/plugin/jquery-validation/js/jquery.validate.min.js"></script>
</head>
<body>
<div id="main" style="width: 90%;height:77%;">
	<form id="inputForm" name="inputForm"  method="post" >
		<table class="table">
            <tbody>
                <tr>
                    <th class="first minw120">用户名</th>
                    <th class="second maxw300">
                        <div class="text-input">
                            <input type="text" name="loginName" id="loginName" value="" class="required" placeholder="用户名" title="必填字段"/>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">密码</th>
                    <th class="second maxw300">
                        <div class="text-input">
                            <input type="password" name="password" id="password" value="" class="required" placeholder="设置密码" title="必填字段"/><br/>
							<input type="password" name="rpassword" id="rpasswordc" value="" class="required" placeholder="确认密码" title="必填字段"/>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">城市</th>
                    <th class="second maxw300">
                        <div class="text-input">
                            <select name="city">
                              <option value="北京">北京</option>
                              <option value="上海">上海</option>
                              <option value="广州">广州</option>
                            </select>
                            
                        </div>
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">地址</th>
                    <th class="second maxw300">
                        <div class="text-input">
                            <input type="text" name="address" value="" placeholder="地址" />
                        </div>
                    </th>
                </tr>
               
            </tbody>
        </table>
        <div class="btn-box">
            <input class="active" type="submit" value="确定">
            <input type="button" value="取消" onclick="javascript:history.go(-1);">
        </div>
    </form>
   </div> 
<script type="text/javascript">
var path = '${pageContext.request.contextPath}';
$(function(){
	
	$("#inputForm").validate({
		
		submitHandler:function(form){ 
	    	if(confirm("确定要提交吗？")){
	    			    $.ajax({  
	    			        url: '${ctx}/register',  
	    			        type: 'POST',  
	    			        data: $("#inputForm").serialize(),  
	    			        beforeSend: function ()  
	    			        {  
	    			            //$("#ajax-loader").show(); 
	    			        },  
	    			        error: function ()  
	    			        {  
	    			  
	    			        },  
	    			        complete:function () {  
	    			           // $("#ajax-loader").hide();  
	    			        },  
	    			        success: function (data)  
	    			        {  
	    			            //$("#article").html(data);
	    			            if("success"==data){
	    			            	alert("注册成功");
	    			            setTimeout("location.href='${ctx}/toLogin'",2000);
	    			            }else{
	    			                alert("注册失败");
	    			            	
	    			            }
	    			        }  
	    			    });  
	    		  
	    	}
		},
		errorPlacement: function (error, element) { //指定错误信息位置
			if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
				var eid = element.attr('name'); //获取元素的name属性
				error.appendTo(element.parent().parent().parent()); //将错误信息添加当前元素的父结点后面
			} else if(element.is('input:text') || element.is('textarea')){
				error.insertAfter(element);
			}else{
				error.insertAfter(element);
			}
		}
	});
});
</script>
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</body>
</html>