<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%> 
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
		function	del(id){
				var	settings={
				url:'deleteTest.do',
				dataType:'json',
				type:'post',
				data:{id:id},
				success:function(data){
					window.location.href="testProduct.do";
					
				}
				};
				$.ajax(settings);
		}

	function	addTest(){
	$('#name').text("");
		var	settings={
		url:'testCheck.do',
		dataType:'json',
		type:'post',
		data:{testName:$('#testName').val()},
		success:function(data){
			if(!data.result){
				$('#name').text("考试已存在！");
			}
			else{
			$('#testAdd').submit();
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
	    <h3 class="panel-title">考试科目管理</h3>
	  </div>
	  <div class="panel-body">
	  			<a  href="javascript:;" class="btn btn-primary btn-default" data-toggle="modal" data-target="#proAdd"	>
	  			添加
	  			<span class="glyphicon glyphicon-plus"></span>
	  		</a>
	  		
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
						
						<a class="btn btn-primary btn-xs btn-success" href="javascript:;" onclick="del('${Test.id}')">
		  					删除
		  						
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
			        <form class="form-horizontal" role="form" action="testAdd.do"	id="testAdd"	method="post">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="proAddLabel">考试科目添加</h4>
					      </div>
					      <div class="modal-body">
						            <div class="form-group">
						              <label for="productName4Add" class="col-lg-2 control-label">科目名称</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="testName"	name="testName" placeholder="科目名称"><span	id="name"></span>
						              </div>
						             <label for="productName4Add" class="col-lg-2 control-label">开始时间</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="testStart" name="testStart"	placeholder="开始时间">
						              </div>
						              <label for="productName4Add" class="col-lg-2 control-label">结束时间</label>
						              <div class="col-lg-10">
						                <input type="text" class="form-control" id="testEnd" 	name="testEnd"	placeholder="结束时间">
						              </div>
						            </div>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="button" class="btn btn-primary" value="添加" onclick="addTest()">
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 产品添加Modal -->
  </body>
</html>