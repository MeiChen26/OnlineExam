<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
	.exercise-title{
		font-size: 25px;
    	font-weight: bold;
	}
	.exercise-options{
		margin-left: 40px;
    	font-size: 18px;
    	margin-bottom: 8px;
	}
</style>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div class="right-area">
		<h3>练习题 -
		<c:if test="${subject eq 1 }">科目一</c:if>
		<c:if test="${subject eq 2 }">科目二</c:if>
		<c:if test="${subject eq 3 }">科目三</c:if>
		<c:if test="${subject eq 4 }">科目四</c:if>
		</h3>
		<input type="hidden" name="subject" id="subject" value="${subject }">
		<input type="hidden" name="type" id="type" value="${question.type }">
		<table class="table">
			<tbody>
				<tr>
					<th class="first minw120"></th>
					<th class="second maxw300">
						<div class="text-input exercise-title">
							${reqPage }、<span style="color: red;font-size: 18px;"><c:if test="${question.type eq 1 }">[单选题]</c:if><c:if test="${question.type eq 2 }">[多选题]</c:if></span>${question.title }
							<c:if test="${not empty question.attachment}">
								<br>
								<img src="${pageContext.request.contextPath}/${question.attachment}" width="275px" 
									style="margin-left: 5%;margin-top: 1%;"/>
							</c:if>
						</div>
					</th>
				</tr>
				<tr>
					<th class="first minw120"></th>
					<th class="second maxw300" style="width: 100%;">
						<c:forEach items="${optionsList }" var="option">
							<div class="exercise-options">
								${option.opt }、${option.content }
								<c:if test="${question.type eq 1 }">
									<input type="radio" name="opt" value="${option.opt }" 
										data-rig="<c:if test='${option.rig }'>1</c:if><c:if test='${!option.rig }'>0</c:if>">
								</c:if>
								<c:if test="${question.type eq 2 }">
									<input type="checkbox" name="opt" value="${option.opt }" data-rig="<c:if test='${option.rig }'>1</c:if><c:if test='${!option.rig }'>0</c:if>">
								</c:if>
							</div>
						</c:forEach>
					</th>
				</tr>
				<tr id="analysis" style="display: none;">
					<th class="first minw120"></th>
					<th class="second maxw300">
						<div class="text-input" style="color:red;">
							正确答案为：${question.answer }
						</div>
						<div class="text-input" style="    color: lightseagreen;margin-top: 10px;font-size: 15px; width: 75%;">
							解析：<br/>${question.analysis }
						</div>
					</th>
				</tr>
			</tbody>
		</table>
		<div class="btn-box">
			<input type="button" value="上一题" onclick="javascript:history.go(-1);">
			<input class="active" onclick="exercise()" type="button" value="确定">
			<input type="button" value="下一题" onclick="goNext()">
		</div>
	</div>
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
		var path = '${pageContext.request.contextPath}';
		var reqPage = ${reqPage};
		var preId = ${question.id};
		var subject = ${subject};
		//判断对错
		function exercise() {
			var type = $("#type").val();
			var flag = -1;
			if(type == 1) { //单选
			 	$("input[name='opt']:radio").each(function(i){
			    	if($(this).is(":checked")) {
			    		flag = $(this).attr("data-rig");
			    	}
				});
			} else if(type == 2) {//复选
				flag = 1;
				$("input[name='opt']:checkbox").each(function(i){
					var rig = $(this).attr("data-rig");
			    	if($(this).is(":checked")) {
			    		if(rig == 0) { //选中，但不是标准答案，错误
			    			flag = 0;
			    			return;
			    		}
			    	} else {
			    		if(rig == 1) { //未选中，但为标准答案时，错误
			    			flag = 0;
			    			return;
			    		}
			    	}
				});
			}
			
			if(flag == 1) { //正确，跳转至下一题
				urlpmenu(path+"/exercise/doExercise?reqPage="+(parseInt(reqPage)+1)+"&preId="+preId+"&subject="+subject, 
						path+"/exercise/doExercise?subject="+subject);
			} else if(flag == 0) { //错误显示解析
				$("#analysis").show();
			} else {
				layer.msg("请选择选项");
			}
		}
		//下一题
		function goNext() {
			urlpmenu(path+"/exercise/doExercise?reqPage="+(parseInt(reqPage)+1)+"&preId="+preId+"&subject="+subject, 
					path+"/exercise/doExercise?subject="+subject);
		}
	</script>
</body>
</html>