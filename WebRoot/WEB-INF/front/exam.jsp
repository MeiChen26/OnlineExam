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
	<div style="width: 100%;height:77%;">
		<form action="${pageContext.request.contextPath}/f/saveScore" id="form" method="post">
			<input type="hidden" id="score" name="score" />
		</form>
		<table class="table">
			<tbody>
				<c:forEach items="${questionlist }" var="question" varStatus="vstatus">
				<tr>
					<th class="first minw120"></th>
					<th class="second maxw300">
						<span class="text-input exercise-title">
							${reqPage }、<span style="color: red;font-size: 18px;"><c:if test="${question.type eq 1 }">[单选题]</c:if><c:if test="${question.type eq 2 }">[多选题]</c:if></span>${question.title }
							<c:if test="${not empty question.attachment}">
								<br>
								<img src="${pageContext.request.contextPath}/${question.attachment}" width="275px" 
									style="margin-left: 5%;margin-top: 1%; float:left;"/>
							</c:if>
						</span>
					</th>
				</tr>
				<tr id="vstatus.count">
					<th class="first minw120"></th>
					<th class="second maxw300" style="width: 100%;">
						<c:forEach items="${question.list }" var="option">
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
				</c:forEach>
			</tbody>
		</table>
		<div class="btn-box">
			<span id="tTime"></span>
			<span id="examTimes" style="dispaly:none">5400</span>
			<input type="button" value="交卷" onclick="">
		</div>
	</div>
	<!-- PAGE CONTENT END -->
	<script type="text/javascript">
		 //JS实现倒计时，考试结束提交试卷
		    function startTime(){  
		        //定义考试剩余时间，时间为毫秒数
		        //examTime = "${examTime}";//对考试剩余时间赋值
		        //var exam = parseInt("${examTime}");
		        var examTime = parseInt(examTimes.innerHTML);  
		        var examTimeLength;//考试时长
		        examTimeLength = 90*60; //单位秒
		        if ((examTime)<0){
		            alert("考试时间到!\n即将提交试卷!");
		            hand();
		        }else{
		            var lm = Math.floor((examTimeLength - examTime) / 60);
		            var ls = (examTimeLength - examTime) % 60;
		            var ym = Math.floor(examTime / 60);
		            var ys = examTime % 60;
		           document.getElementById("tTime").innerHTML = "考试已经开始了" + lm + "分" + ls + "秒" + ",剩余"  + ym + "分" + ys + "秒";
		        }
		        examTime--;
		        examTimes.innerHTML=examTime;
		        setTimeout(startTime,1000);
		    }

		    var timer=null;
		    //当页面加载后，启动周期性定时器，每过1秒执行startTime
		    window.onload=function(){
		        startTime();
		    }
		    </script>
	<script type="text/javascript">
		//交卷
		function hand() {
			var score=0;
			for (var i=0;i<50;i++)
			{ 
				var type = $("."+i+" input[name='type']").val();
				var flag = -1;
				if(type == 1) { //单选
				 	$("."+i+" input[name='opt']:radio").each(function(i){
				    	if($(this).is(":checked")) {
				    		flag = $(this).attr("data-rig");
				    	}
					});
				} else if(type == 2) {//复选
					flag = 1;
					$("."+i+" input[name='opt']:checkbox").each(function(i){
						var rig = $(this).attr("data-rig");
				    	if($(this).is(":checked")) {
				    		if(rig == 0) { //选中，但不是标准答案，错误
				    			flag = 0;
				    		}
				    	} else {
				    		if(rig == 1) { //未选中，但为标准答案时，错误
				    			flag = 0;
				    		}
				    	}
					});
				}
				score=score+flag;
				$("#score").val(score);
				$("#form").submit();
			}
			
		}
		
	</script>
</body>
</html>