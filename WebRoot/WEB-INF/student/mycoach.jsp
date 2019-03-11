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
		<h3>我的教练</h3>
		<table class="table">
			<tbody>
				<tr>
					<th class="first minw120">教练名称</th>
					<th class="second maxw300">
						<div class="text-input">
							${coach.name }
						</div>
					</th>
				</tr>
				<tr>
					<th class="first minw120">车牌号</th>
					<th class="second maxw300">
						<div class="text-input">
							${coach.carno }
						</div>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>