<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
</head>
<body>
<div  style="width: 100%;height:77%;">
    <!-- 表格 -->
			<div class="table-outer" style="height: 65%;">
				<table class="table-data" border="1" bordercolor="#d9ddde"
					width="300" cellspacing="0" style="width: 100%">
					<thead>
						<tr class="header">
							<td width="5%">序号</td>
							<td width="20%">学号</td>
							<td width="10%">姓名</td>
							<td width="10%">成绩</td>
							<td width="20%">考试时间</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="exam" items="${pageinfo.resultList}" varStatus="vstatus">
							<tr>
								<td>${(pageinfo.currentPage-1)*pageinfo.paginationSize+vstatus.count}</td>
								<td>${exam.studentNo}</td>
								<td>${exam.name}</td>
								<td>${exam.score}</td>
								<td>${exam.createTime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
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
	
</script>	
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</body>
</html>