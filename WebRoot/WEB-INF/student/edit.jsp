<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div class="right-area">
		<h3>编辑学生</h3>
		<form id="inputForm" name="inputForm"
			action="${pageContext.request.contextPath}/student/save"
			method="post">
			<input type="hidden" name="id" value="${student.id}">
			<table class="table">
				<tbody>
					<tr>
						<th class="first minw120">姓名</th>
						<th class="second maxw300">
							<div class="text-input">
								<input type="text" name="name" id="name" maxlength="20"
									value="${student.name}" class="required" placeholder="姓名"
									title="必选字段" />
							</div>
						</th>
					</tr>
					<tr>
						<th class="first minw120">学号</th>
						<th class="second maxw300">
							<div class="text-input">
								<input type="text" class="required" name="studentNo" value="${student.studentNo}" maxlength="20"
									placeholder="手机号" />
							</div>
						</th>
					</tr>
					<tr>
						<th class="first minw120">密码</th>
						<th class="second maxw300">
							<div class="text-input">
								<input type="password" name="password" id="password" value="${student.password}"
									class="required" placeholder="设置密码" title="必选字段" /><br /> <input
									type="password" name="rpassword" id="rpasswordc" value=""
									class="required" placeholder="确认密码" title="必选字段" />
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
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
	var path = '${pageContext.request.contextPath}';
	$(function(){
		$("#inputForm").validate({
			submitHandler:function(form){ 
		    	if(confirm("确定要提交吗？")){
					form.submit();
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