<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/front/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>成绩</title>
</head>
<body>
<div id="main" style="width: 90%;height:77%;">
		<table class="table">
            <tbody>
                <tr>
                    <th class="first minw120">用户名</th>
                    <th class="second maxw300">
                        <div class="text-input">
                        ${electricInfo.frontUserName }
                        </div>
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">用电量</th>
                    <th class="second maxw300">
                        <div class="text-input">
                            ${electricInfo.electricConsumption }度、kW.h
                        </div>
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">用电金额</th>
                    <th class="second maxw300">
                       ${electricInfo.electricAmount }元
                    </th>
                </tr>
                <tr>
                    <th class="first minw120">是否欠费</th>
                    <th class="second maxw300">
                        <div class="text-input">
                           <c:if test="${electricInfo.isowe==1}">是</c:if>
									<c:if test="${electricInfo.isowe==0}">否</c:if>
                        </div>
                    </th>
                </tr>
               
            </tbody>
        </table>
   </div> 
<script type="text/javascript">

</script>
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</body>
</html>