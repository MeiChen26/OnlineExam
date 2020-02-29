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
		<h3>添加考试成绩</h3>
		<form id="inputForm" name="inputForm"
			action="${pageContext.request.contextPath}/exam/save"
			method="post">
			<input type="hidden" name="id" id="id" value="">
			<table class="table" id="table">
				<tbody>
					<tr>
						<th class="first minw120">学员</th>
						<th class="second maxw300">
							<div class="select-radio" >
								<select  name="studentId" id="stuId" onchange="getexam()">
								   <c:forEach var="stu" items="${stuList}">
									<option value="${stu.id }" <c:if test="${exam.studentId eq stu.id }">selected</c:if> >${stu.realName }</option>
								   </c:forEach>
								</select>
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
			getexam();
			$("#inputForm").validate({
				submitHandler:function(form){ 
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
		
		//获取成绩
		function getexam() {
			var stuId = $("#stuId").val();
			if(stuId != null && stuId != '') {
				$.ajax({
					url:path+"/exam/getExamByStuId",
					data:{stuId:stuId},
					dataType: 'json',
					success:function(data){
						if(data.code == "000") {//请求成功
							var exam = data.result.exam;
							$(".score").remove();
							if(exam != null && exam != '') {
								$("#id").val(exam.id);
								var score1 = (exam.score1==null || exam.score1=='')?"":exam.score1;
								var score2 = (exam.score2==null || exam.score2=='')?"":exam.score2;
								var score3 = (exam.score3==null || exam.score3=='')?"":exam.score3;
								var score4 = (exam.score4==null || exam.score4=='')?"":exam.score4;
								if(exam.score1 != null && exam.score1 != '' && parseInt(exam.score1) > 89) {
									$("#table").find("tbody").append('<tr class="score">\
										<th class="first minw120">科目一成绩</th>\
										<th class="second maxw300">\
											<div class="text-input">'+exam.score1+'</div>\
										</th>\
									</tr>');
									if(exam.score2 != null && exam.score2 != '' && parseInt(exam.score2) > 79) {
										$("#table").find("tbody").append('<tr class="score">\
												<th class="first minw120">科目二成绩</th>\
												<th class="second maxw300">\
													<div class="text-input">'+exam.score2+'</div>\
												</th>\
											</tr>');
										if(exam.score3 != null && exam.score3 != '' && parseInt(exam.score3) > 89) {
											$("#table").find("tbody").append('<tr class="score">\
													<th class="first minw120">科目三成绩</th>\
													<th class="second maxw300">\
														<div class="text-input">'+exam.score3+'</div>\
													</th>\
												</tr>');
											if(exam.score4 != null && exam.score4 != ''  && parseInt(exam.score4) > 89) {
												$("#table").find("tbody").append('<tr class="score">\
														<th class="first minw120">科目四成绩</th>\
														<th class="second maxw300">\
															<div class="text-input">'+exam.score4+'</div>\
														</th>\
													</tr>');
											} else {
												if(parseInt(exam.score3) > 89) { //科三成绩>=90时，才可以录入科四
													$("#table").find("tbody").append('<tr class="score">\
															<th class="first minw120">科目四成绩</th>\
															<th class="second maxw300">\
																<div class="text-input">\
																	<input type="text" name="score4" id="score4" value="'+score4+'" maxlength="20"\
																		class="required" placeholder="科目四成绩" title="必选字段" />\
																</div>\
															</th>\
														</tr>');
												}
											}
											
										} else {
											if(parseInt(exam.score2) > 79) { //科二成绩>=80时，才可以录入科三
												$("#table").find("tbody").append('<tr class="score">\
														<th class="first minw120">科目三成绩</th>\
														<th class="second maxw300">\
															<div class="text-input">\
																<input type="text" name="score3" id="score3" value="'+score3+'" maxlength="20"\
																	class="required" placeholder="科目三成绩" title="必选字段" />\
															</div>\
														</th>\
													</tr>');
											}
										}
									} else {
										if(parseInt(exam.score1) > 89) { //科一成绩>=90时，才可以录入科二
											$("#table").find("tbody").append('<tr class="score">\
													<th class="first minw120">科目二成绩</th>\
													<th class="second maxw300">\
														<div class="text-input">\
															<input type="text" name="score2" id="score2" value="'+score2+'" maxlength="20"\
																class="required" placeholder="科目二成绩" title="必选字段" />\
														</div>\
													</th>\
												</tr>');
										}
									}
									
								} else {
									$("#table").find("tbody").append('<tr class="score">\
											<th class="first minw120">科目一成绩</th>\
											<th class="second maxw300">\
												<div class="text-input">\
													<input type="text" name="score1" id="score1" value="'+score1+'" maxlength="20"\
														class="required" placeholder="科目一成绩" title="必选字段" />\
												</div>\
											</th>\
										</tr>');
								}
							} else { //无成绩数据
								$("#table").find("tbody").append('<tr class="score">\
									<th class="first minw120">科目一成绩</th>\
									<th class="second maxw300">\
										<div class="text-input">\
											<input type="text" name="score1" id="score1" value="" maxlength="20"\
												class="required" placeholder="科目一成绩" title="必选字段" />\
										</div>\
									</th>\
								</tr>');
							}
						} else { //请求失败
							layer.msg(data.reason);
						}
					}
				});
			}
		}
	</script>
</body>
</html>