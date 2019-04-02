<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"/>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div class="right-area">
		<h3>修改密码</h3>
            <form style="margin-left: 20%;" method="post" 
            id="inputForm" name="inputForm" action="${pageContext.request.contextPath}/system/setPwd" method="post">
				<input type="hidden" id="id" name="id" value="${user.id }"/>
				<table class="table">
					<tbody>
						<tr>
		                    <th class="first minw120">姓名：</th>
		                    <th class="second maxw300">
								<div class="text-input">
								<input type="text" name="realName" id="realName"
											value="${user.realName}" class="required form-control" 
											 readonly />			
		                    	</div>
		                    </th>
		                  </tr>  	
	                	<tr>
		                    <th class="first minw120">用户名：</th>
		                    <th class="second maxw300">
								<div class="text-input">
								<input type="text" name="userName" id="userName"
										value="${user.userName}" class="required form-control" 
										 readonly />			
	                    		</div>
	                    	</th>
	                    </tr>		
	                	<tr>
		                    <th class="first minw120">新密码：</th>
	                    	<th class="second maxw300">
								<div class="text-input">
						   			<input type="password" name="password" id="password" value=""
										class="required form-control" placeholder="设置密码" title="必选字段" />				 			
	                    		</div>
	                		</th>
	                	</tr>	
						<tr>
		                    <th class="first minw120">确认密码：</th>
	                    	<th class="second maxw300">
								<div class="text-input">
								<input type="password" name="passwordc" id="passwordc" value=""
										class="required form-control" equalTo='#password' placeholder="确认密码" title="不符合要求" />			
	                    		</div>
	                		</th>
	                	</tr>
	                </tbody>
                </table>		
                <div class="btn-box">
                        <input type="submit" class="active" value="保存">
                        <input type="button" onclick="javascript:history.go(-1);" value="取消">
                 </div>
              </form>
            </div>
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
		var path = '${pageContext.request.contextPath}';			
		
	</script>
	<script type="text/javascript">
	$(function(){
		$("#inputForm").validate({
			submitHandler:function(form){
				
		    	if(confirm("确定要提交吗？")){
		    		  var targetUrl = $("#inputForm").attr("action");    
		    		  var data = $("#inputForm").serialize();     
		    		   $.ajax({ 
		    		    type:'post',  
		    		    url:targetUrl, 
		    		    cache: false,
		    		    data:data,  
		    		    dataType:'json', 
		    		    success:function(data){      
		    		      layer.msg(data.message);
		    		    },
		    		    error:function(){ 
		    		    	layer.msg('请求失败');
		    		    }
		    		   })
					//form.submit(); 
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
</body>
</html>