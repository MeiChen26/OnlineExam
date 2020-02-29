<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>完整demo</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
    var path = '${pageContext.request.contextPath }';
    function more(){
    	var contenthtml = "<form id='importForm' action = '"+path+"/duty/importExcel' method='post' enctype='multipart/form-data'>"+
        "<div style='padding:10px;'><input type='file' id='file' name='file' /></div></form>";
    	var buttonst = ['导入','取消'];
    	layer.open({
    		  type: 1,
    		  shade: false,
    		  btn: buttonst,
    		  content: contenthtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    		  yes:function(index, layero){
    			  if($("#file").val()=='' || $("#file").val()==null){
    				  tip('请选择文件');
    			  }else{
    				  $("#importForm").attr("action", path+"/duty/importExcel").submit();
    			  }
    		  },btn2: function(index, layero){
    			  layer.close(index);
    		  }
    	});
    }
	</script>
</head>
<body>
	<div class="right-area">
		<div class="edit-container">
		    <h1>完整demo</h1>
		    <a target="_self" href="${pageContext.request.contextPath }/assets/template/duty.xlsx" download="值班表.xlsx">下载模板</a>
		    <a href="javascript:void(0);" onclick="more()">导入</a>
		</div>
		<div>
		    
		
		</div>
	</div>
</body>
</html>