<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<link href="${ctxAssets}/front/css/bootstrap.css" rel='stylesheet' type='text/css' />
<head>
<style type="text/css">
	.exercise-title{
		font-size: 16px;
    	font-weight: bold;
	}
	.exercise-options{
		margin-left: 40px;
    	font-size: 14px;
    	margin-bottom: 8px;
	}
	.buttonactive{
	 	background-color: #1f78ed;
		border: 1px solid #1f78ed;
		color: #fff;
		font-size: 12px;
		padding: 5px 10px;
		line-height: 20px;
	 }
</style>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div style="width: 100%;height:70%;">
		<div style="width:100%;height:90%;overflow:auto;">
			<input type="hidden" id="examId" name="examId" value="${examId }"/>
			<table class="table">
				<tbody>
					<c:forEach items="${examQuestionList}" var="question" varStatus="vstatus">
						<input type="hidden"  name="questionId" value="${question.id}"/>
					<tr>
						<th class="first minw120"></th>
						<th class="second maxw300">
							<span class="text-input exercise-title">
								${question.sort }、<span style="color: red;font-size: 18px;"><c:if test="${question.type eq 1 }">[单选题]</c:if><c:if test="${question.type eq 2 }">[多选题]</c:if></span>${question.title }
								<c:if test="${not empty question.attachment}">
									<br>
									<img src="${pageContext.request.contextPath}/${question.attachment}" width="275px" 
										style="margin-right: 5%;margin-top: 1%; float:right;"/>
								</c:if>
							</span>
						</th>
					</tr>
					<tr class="${vstatus.count}">
						<th class="first minw120"></th>
						<th class="second maxw300" style="width: 100%;">
							<c:forEach items="${question.options }" var="option">
								<div class="exercise-options" <c:if test='${option.opt eq question.myanswer && !option.rig }'>style="color: red;"</c:if>>
									${option.opt }、${option.content }
									<c:if test="${question.type eq 1 }">
										<input type="radio" disabled name="opt${vstatus.count}" value="${option.opt }" <c:if test="${option.opt eq question.myanswer }">checked</c:if> >
									</c:if>
									<c:if test="${question.type eq 2 }">
										<input type="checkbox" disabled name="opt${vstatus.count}" value="${option.opt }" <c:if test="${option.opt eq question.myanswer }">checked</c:if>>
									</c:if>
								</div>
							</c:forEach>
						</th>
					</tr>
					<tr>
						<th class="first minw120"></th>
						<th>
							正确答案：${question.answer }
							<div>
							解析：${question.analysis}
							</div>
						</th>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("#score").addClass("active");
		});
		
	</script>
</body>
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</html>