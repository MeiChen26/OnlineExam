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
		<h3>添加 </h3>
		<form id="inputForm" name="inputForm"
			action="${pageContext.request.contextPath}/question/save"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="subject" id="subject" value="${subject }">
			<table class="table">
				<tbody>
					<tr>
						<th class="first minw120">题目</th>
						<th class="second maxw300">
							<div class="text-input">
								<input type="text" name="title" id="title" value="" maxlength="1000"
									class="required" placeholder="题目" title="必选字段" />
							</div>
						</th>
					</tr>
					<c:if test="${subject eq 1 || subject eq 4 }">
						<tr>
							<th class="first minw120">图片</th>
							<th class="second maxw300">
								<div class="text-input">
									<input type="file" id="image" name="multipartFile" >
								</div>
							</th>
						</tr>
						<tr>
							<th class="first minw120">题型</th>
							<th class="second maxw300">
								<div class="text-input select-radio">
									<select name="type">
										<option value="1">单选</option>
										<option value="2">多选</option>
									</select>
								</div>
							</th>
						</tr>
						<tr>
							<th class="first minw120">选项</th>
							<th class="second maxw300" style="width: 100%;">
								<div class="text-input">
									<select name="options[0].opt">
										<option value="A">A</option>
									</select>
									<input type="text" name="options[0].content" value="" maxlength="500"
										class="required" placeholder="选项内容" title="必选字段" />
									<select name="options[0].rig">
										<option value="1">正确答案</option>
										<option value="0">错误答案</option>
									</select>
								</div>
								<div class="text-input">
									<select name="options[1].opt">
										<option value="B">B</option>
									</select>
									<input type="text" name="options[1].content" value="" maxlength="500"
									   class="required"	placeholder="选项内容" title="必选字段"/>
									<select name="options[1].rig">
										<option value="1">正确答案</option>
										<option value="0">错误答案</option>
									</select>
								</div>
								<div class="text-input">
									<select name="options[2].opt">
										<option value="C">C</option>
									</select>
									<input type="text" name="options[2].content" value="" maxlength="500"
										placeholder="选项内容" />
									<select name="options[2].rig">
										<option value="1">正确答案</option>
										<option value="0">错误答案</option>
									</select>	
								</div>
								<div class="text-input">
									<select name="options[3].opt">
										<option value="D">D</option>
									</select>
									<input type="text" name="options[3].content" value="" maxlength="500"
										placeholder="选项内容" />
									<select name="options[3].rig">
										<option value="1">正确答案</option>
										<option value="0">错误答案</option>
									</select>
								</div>
							</th>
						</tr>
						<tr>
							<th class="first minw120">题目解析</th>
							<th class="second maxw300">
								<div class="text-input">
									<textarea name="analysis" rows="8" cols="30"></textarea>
								</div>
							</th>
						</tr>
					</c:if>
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
			$("#image").change(function(){  
				var s = $('#image').val();
			    var start = s.indexOf(".") + 1;
			    var name = s.substring(start, s.length).toLowerCase();
			    if (name != "gif" && name != "jpg" && name != "png") {
			    	layer.msg("图片格式必须为gif、jpg、png中的一种");
			        $('#image').val('');
			        return;
			    }  
			});
			
			$("#inputForm").validate({
				submitHandler:function(form){
					var subject = $("#subject").val();
					var s = $('#attachment').val();
			    	if(confirm("确定要提交吗？")){
						form.submit(); 
			    	}
				},
				errorPlacement: function (error, element) { //指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
						var eid = element.attr('name'); //获取元素的name属性
						error.appendTo(element.parent().parent()); //将错误信息添加当前元素的父结点后面
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