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
					<form action="${pageContext.request.contextPath}/student/list" id="selectForm" method="post">
						<!-- 公用部分 -->
						<div class="header-box">
							<h2>学生管理</h2>
							<ul class="clearfix" style="overflow: hidden;height:45px;margin-top: 10px;">
								<li class="fl input">
									姓名:
									<input type="text" name="keyword" value="${keyword}" placeholder="姓名">
								</li>
								<li class="fl btn" style="margin-left: 10px;">
									<input class="active" type="submit" value="查询">
								</li>
								<li class="fr btn">
									<input class="active" type="button" value="添加" onclick="url('${pageContext.request.contextPath}/student/addOrUpdate')">
	                            </li>
							</ul>
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
							<td width="10%">姓名</td>
							<td width="10%">学号</td>
							<td width="10%">状态</td>
							<td width="8%">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="student" items="${pageinfo.resultList}" varStatus="vstatus">
							<tr>
								<td>${(pageinfo.currentPage-1)*pageinfo.paginationSize+vstatus.count}</td>
								<td>${student.name}</td>
								<td>${student.studentNo}</td>
								<td>
									<c:if test="${student.deleted==true}">已删除</c:if>
									<c:if test="${student.deleted==false}">正常</c:if>
								</td>
								<td>
									<a href="javascript:;" onclick="url('${pageContext.request.contextPath}/student/addOrUpdate?id=${student.id}')" class="edit" >编辑</a>
									<a href="javascript:" class="delete user-delete" data-id="${student.id}" >删除</a>
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
	
	$(".user-delete").click(function(){
		var id = $(this).attr("data-id");
		if(confirm("确定要删除吗？")){
			location.href = path+"/student/delete?id=" + id;
		}
	});
	
</script>
</body>
</html>