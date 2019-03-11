<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>编辑教练信息</title>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div class="right-area">
		<h3>编辑
		<c:if test="${subject eq 1 }">科目一</c:if>
		<c:if test="${subject eq 2 }">科目二</c:if>
		<c:if test="${subject eq 3 }">科目三</c:if>
		<c:if test="${subject eq 4 }">科目四</c:if>
		</h3>
		<form id="inputForm" name="inputForm"
			action="${pageContext.request.contextPath}/question/save"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${question.id}">
			<input type="hidden" name="subject" value="${subject }">
			<table class="table">
				<tbody>
					<tr>
						<th class="first minw120">题目</th>
						<th class="second maxw300">
							<div class="text-input">
								<input type="text" name="title" id="title" value="${question.title }" maxlength="1000"
									class="required" placeholder="题目" title="必选字段" />
							</div>
						</th>
					</tr>
					<c:if test="${subject eq 1 || subject eq 4 }">
						<tr>
							<th class="first minw120">图片</th>
							<th class="second maxw300">
								<div class="text-input">
									<c:if test="${not empty question.attachment }">
										<a href="${pageContext.request.contextPath}/${question.attachment}" target="_blank">查看图片</a>
									</c:if>
									<input type="file" id="image" name="multipartFile" >
								</div>
							</th>
						</tr>
						<tr>
							<th class="first minw120">题型</th>
							<th class="second maxw300">
								<div class="text-input select-radio">
									<select name="type">
										<option value="1" <c:if test="${question.type eq 1 }">selected</c:if> >单选</option>
										<option value="2" <c:if test="${question.type eq 2 }">selected</c:if>>多选</option>
									</select>
								</div>
							</th>
						</tr>
						<tr>
							<th class="first minw120">选项</th>
							<th class="second maxw300" style="width: 100%;">
								<c:forEach items="${optionsList }" var="options" varStatus="i">
									<div class="text-input">
										<select name="options[${i.index }].opt">
											<option value="${options.opt }">${options.opt }</option>
										</select>
										<input type="text" name="options[${i.index }].content" value="${options.content }" maxlength="500"
											placeholder="选项内容" title="必选字段" />
										<select name="options[${i.index }].rig">
											<option value="1" <c:if test="${options.rig }">selected</c:if> >正确答案</option>
											<option value="0" <c:if test="${!options.rig }">selected</c:if> >错误答案</option>
										</select>
									</div>
								</c:forEach>
							</th>
						</tr>
						<tr>
							<th class="first minw120">题目解析</th>
							<th class="second maxw300">
								<div class="text-input">
									<textarea name="analysis" rows="8" cols="30">${question.analysis }</textarea>
								</div>
							</th>
						</tr>
					</c:if>
					<c:if test="${subject eq 2 || subject eq 3 }">
						<tr>
							<th class="first minw120">上传视频</th>
							<th class="second maxw300">
								<div class="text-input">
									<c:if test="${not empty question.attachment }">
										<a href="${pageContext.request.contextPath}/${question.attachment}" target="_blank">查看视频</a>
									</c:if>
									<input type="file" id="attachment" name="multipartFile" >
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
		$("#attachment").change(function(){  
			var s = $('#attachment').val();  
		    var start = s.indexOf(".") + 1;  
		    var name = s.substring(start, s.length).toLowerCase();  
		    if (name != "mp4" && name != "avi") {  
		    	layer.msg("视频格式必须为mp4、avi中的一种");
		        $('#attachment').val('');
		        return;
		    }  
		});
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
				if(s == '' && (subject == 2 || subject == 3)) {
					layer.msg("请上传视频");
					return;
				}
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