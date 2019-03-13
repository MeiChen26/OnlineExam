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
    <div id="main" style="width: 100%;height:77%;"></div>
	<section class="pinlun_wap">
				<c:if test="${frontUser!=null }">
					<input type="hidden" id="frontUserId" name="frontUserId" value="${frontUser.id }"/>
					<div class="pinlun_xie">
						<div class="pinlun_xie_row2">写评论：</div>
						<input type="text" placeholder="随便说说你的想法，或者有什么问题？和大家一起探讨吧" id="content" maxlength="200">
						<div class="pinlun_xie_row4">
							<div class="pinlun_xie_row4_right">
								<span>还可以输入200字</span>
								<div onclick="saveComment();">发布</div>
							</div>
						</div>
					</div>
				</c:if>
				
				<!-- 已有评论 -->
				<div class="yipinglun">
					<c:if test="${commentList==null||commentList.size()==0 }">
					<h4>暂无评论</h4>
					</c:if>
				 	<c:if test="${commentList!=null||commentList.size()>0 }">
				 		<ul class="yipinglun_list">评论：
				 		<c:forEach items="${commentList }" var="comment">
					 		 <li><p>${comment.frontUserName }：
			            		<span><fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
			            		</p>
			            		${comment.content}
			                 </li>
				 		</c:forEach>
				 		</ul>
				 	</c:if>
				</div>
	</section>
</div>	
<script src="${pageContext.request.contextPath}/assets/plugin/echarts/echarts.min.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
	    title : {
	        text: '各城市用户用电量',
	        //subtext: '纯属虚构',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ${statisticJsonStr}
	    },
	    series : [
	        {
	            name: '城市',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:${statisticJsonStr},
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

//发表评论
function saveComment(){
	var commentText=$().val();
	$.ajax({
		url:'${ctx}/saveComment',
		type:"POST",
		dataType:"json",
		data:{'frontUserId':$("#frontUserId").val(),'content':$("#content").val()},
		success:function(data){
			if("fail"==data){
				alert("评论失败");
			}else{
			$("#content").val("");
			var html='<li><p>'+${frontUser.loginName}+'：';
			html+='<span>'+data.createTime+'</span></p>';
			html+=data.content+'</li>';
			$(".yipinglun_list").append(html);
			}
		}
	});
}
</script>
<%@ include file="/WEB-INF/front/include/footer.jsp"%>
</body>
</html>