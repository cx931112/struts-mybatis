<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'productTypeManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/bootstrap/css/bootstrap.min.css"
		type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/FlatUI/dist/css/flat-ui.css"
		type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/bootstrap/js/messager.js"></script>
	<script type="text/javascript">
	function	apply(testName){
			var	userName="<%=session.getAttribute("userName")%>"; 
			
			var	settings={
			url:'applyCheck.do',
			dataType:'json',
			type:'post',
			data:{testName:testName,userName:userName},
			success:function(data){
				if(!data.result){
					alert("报名成功");
					
				}
				else{
				alert("不能重复报名");
				
				}
			}
			};
			$.ajax(settings);
			
	}

	

		
	</script>

  </head>
  
  <body style="padding: 5px 10px;">
    <div class="panel panel-primary">
	  <div class="panel-heading">
	    <h3 class="panel-title">考试报名</h3>
	  </div>
	  <div class="panel-body">

	  		
	  		<table class="table table-hover table-bordered">
	  			<thead>
	  				<tr>
	  					<th>考试科目ID</th>
	  					<th>科目名称</th>
	  					<th>开始日期</th>
	  					<th>结束日期</th>
	  					<th>操作</th>
	  				</tr>
	  			</thead>
	  			<tbody>

	  			<c:forEach	items="${requestScope.allTest}"	var="Test"	varStatus="status">
	  			<tr> 	 	 	 	 	 
		  				<td>${Test.id}</td>
		  				<td>${Test.testName}</td>
		  				<td>
		  					${Test.testStart}
		  				</td>
						<td>${Test.testEnd}</td>
						<td>
						<a class="btn btn-primary btn-xs btn-success" href="javascript:;" onclick="apply('${Test.testName}')">
		  						报名
		  						
		  					</a>
		  				<span	id=""></span>
		  				</td>
		  			</tr>
	  			</c:forEach>
	  			</tbody>
	  		</table> 
	  </div>
	</div>
	
	
	
			<!-- 产品修改Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="proModify" tabindex="-1" role="dialog" aria-labelledby="proModifyLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" action="">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">产品类型修改</h4>
					      </div>
					      <div class="modal-body">
					      			<div class="form-group">
						              <label for="productId4Modify" class="col-lg-2 control-label">类型Id</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productId4Modify" readonly="readonly"/>
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="productName4Modify" class="col-lg-2 control-label">类型名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productName4Modify" />
						              </div>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="button" class="btn btn-primary" value="修改" onclick="changeTypeName()"/>
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 产品修改Modal -->
			
			<!-- 产品类型添加Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="proAdd" tabindex="-1" role="dialog" aria-labelledby="proAddLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form" action="">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="proAddLabel">产品类型添加</h4>
					      </div>
					      <div class="modal-body">
						            <div class="form-group">
						              <label for="productName4Add" class="col-lg-2 control-label">类型名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="productName4Add" placeholder="类型名称">
						              </div>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="button" class="btn btn-primary" value="添加" onclick="addProductType()">
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 产品添加Modal -->
  </body>
</html>