<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>成绩查询</title>
	<link href="${ctxAssets}/front/css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/plugin/zxf_page/zxf_page.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugin/zxf_page/zxf_page.js"></script>
	<style type="text/css">
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
<div  style="width: 100%;height:70%; border-top:solid 1px #ddd;" >
			<form action="" id="selectForm" style="float:right;padding-right:50px;padding-top:20px;">
				<input name="studentId" type="hidden" value="${frontUser.id }" >
				<input class="buttonactive" type="button" value="导出成绩" onclick="exportData();">
			</form>
    		<!-- 表格 -->
				<table class="table">
					<thead>
						<tr>
							<td width="5%">序号</td>
							<td width="20%">学号</td>
							<td width="10%">姓名</td>
							<td width="10%">成绩</td>
							<td width="20%">考试时间</td>
							<td width="20%">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="exam" items="${pageinfo.resultList}" varStatus="vstatus">
							<tr>
								<td>${(pageinfo.currentPage-1)*pageinfo.paginationSize+vstatus.count}</td>
								<td>${exam.studentNo}</td>
								<td>${exam.name}</td>
								<td>${exam.score}</td>
								<td><fmt:formatDate value="${exam.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<a href="${pageContext.request.contextPath}/f/examInfo?examId=${exam.id}" class="buttonactive">答题详情</a>
									<a href="${pageContext.request.contextPath}/f/examInfo?examId=${exam.id}&isRight=0" class="buttonactive" >错题</a>
									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<!-- 分页 -->
			<div class="toleft"
				style="box-sizing: border-box; width: 95%; padding: 40px 50px;">
				<div class="zxf_pagediv"></div>
			</div>
	
</div>

<script type="text/javascript">
	var path = '${pageContext.request.contextPath}';
	var totalPageNum = ${pageinfo.totalPageNum};
	var currentPage = ${pageinfo.currentPage};
	var paginationSize = ${pageinfo.paginationSize};
	// 分页
    $(".zxf_pagediv").createPage({
        pageNum: totalPageNum, //总页码
        current: currentPage, //当前页
        shownum: paginationSize, //每页显示个数
        //activepage: "",//当前页选中样式
        activepaf: "",//下一页选中样式
        backfun: function(e) {
            console.log(e);
            var formEle = $("#selectForm");
            var inputEle = $("<input type='hidden' name='reqPage' value='" + e.current + "'/>")
            formEle.append(inputEle);
            formEle.submit();
        }
    });
	
    $(document).ready(function() {
		$("#score").addClass("active");
	});
    function exportData(){
		$("#selectForm").attr("action",path+"/f/exportData").submit();
    }	
</script>	
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</body>
</html>