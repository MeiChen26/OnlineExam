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
					<form action="${pageContext.request.contextPath}/exam/list" id="selectForm" method="post">
						<!-- 公用部分 -->
						<div class="header-box">
							<h2>考试成绩管理</h2>
							<ul class="clearfix" style="overflow: hidden;height:45px;margin-top: 10px;">
								<li class="fl input">
									姓名:
									<input type="text" name="keyword" value="${keyword}">
								</li>
								<li class="fl btn" style="margin-left: 10px;">
									<input class="active" type="submit" value="查询">
								</li>
								<li class="fr btn">
									<input class="active" type="button" value="导出成绩" onclick="exportData();">
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
								<td><fmt:formatDate value="${exam.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	
	$(".delete").click(function(){
		var id = $(this).attr("data-id");
		if(confirm("确定要删除吗？")){
			location.href = path+"/exam/delete?id=" + id;
		}
	});
	function exportData(){
		$("#selectForm").attr("action",path+"/exam/exportData").submit();
		
		/* $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: path+"/exam/exportData" ,//url
                data: $('#selectForm').serialize(),
                success: function (result) {
 //                   console.log(result);//打印服务端返回的数据(调试用)
 						alert(result);
                   
                },
                error : function() {
                    alert("异常！");
                }
            });*/
	} 
	
</script>
</body>
</html>