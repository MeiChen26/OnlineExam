<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<!-- PAGE CONTENT BEGIN -->
	<div class="content map-content-bg">
		<div class="content-bg-div content-bg-edit">
			<div style="width: 100%; overflow: auto">
				<div class="map-top-div">
					<form action="${pageContext.request.contextPath}/exercise/list?subject=${subject}" id="selectForm" method="post">
						<!-- 公用部分 -->
						<%-- <input type="hidden" name="subject" value="${subject }"/> --%>
						<div class="header-box">
							<h2>练习题 -
							<c:if test="${subject eq 3 }">科目三</c:if>
							<c:if test="${subject eq 2 }">科目二</c:if> </h2>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="showMap-bg-div">
			<!-- 表格 -->
			<div class="table-outer" style="height: 65%;">
				<table class="table-data" border="1" bordercolor="#d9ddde"
					width="300" cellspacing="0" style="width: 100%">
					<thead>
						<tr class="header">
							<td width="5%">序号</td>
							<td width="10%">题目</td>
							<td width="10%">视频地址</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="question" items="${pageinfo.resultList}" varStatus="vstatus">
							<tr>
								<td>${(pageinfo.currentPage-1)*pageinfo.paginationSize+vstatus.count}</td>
								<td>${question.title}</td>
								<td>
									<a href="${pageContext.request.contextPath }/${question.attachment}" target="_blank">查看视频</a>
								</td>
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
	</div>
	<!-- PAGE CONTENT END -->
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
</body>
</html>