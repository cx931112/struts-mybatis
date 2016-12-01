<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>在线报名系统-后台首页</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/bootstrap/css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/FlatUI/dist/css/flat-ui.css" type="text/css"></link>
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/plugins/FlatUI/dist/img/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/plugins/FlatUI/dist/js/flat-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/base.js"></script>
<script	type="text/javascript">

		function passChange(){
		var settings={
				
				url:'passChange.do',
				dataType:'json',
				type:'post',
				data:{oldPwd:$('#oldPwd').val(),newPwd:$('#newPwd').val(),
					loginName:$('#label').html()
					},
				success:function(data){
						
						window.location.href="loginAgin.do";
						
				}

				};			
			
					$.ajax(settings);
		}
	function check(){
		var oldPwd=$("#oldPwd").val();
		var newPwd=$("#newPwd").val();
		var reNewPwd=$("#reNewPwd").val();
		if(newPwd!=reNewPwd){
			$("#span").text("密码不一致");
			
		}
		else{
			$("#span").text("");
			var settings={
				
				url:'selectUserByName.do',
				dataType:'json',
				type:'post',
				data:{oldPwd:$('#oldPwd').val(),newPwd:$('#newPwd').val(),
					loginName:$('#label').html()
					},
				success:function(data){
					if(data.result){
						 $("#span2").text("");
						 
							passChange();
						
						
					}
					else{
						$("#span2").text("原密码不正确");
						
					}
				}				
			};
			$.ajax(settings);
		}
	}
</script>
  </head>
  
  <body class="page-header-fixed">

		<!-- 	头部logo开始 -->
		<div class="header navbar navbar-inverse navbar-fixed-top">
			<div class="header-inner" style="padding: 2px 40px;">
				
				<div class="nav navbar-nav pull-right">
					<div class="btn-group">
		            <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button">
		            	<img alt="" src="${pageContext.request.contextPath }/images/admin.png">
		            	<span>欢迎您:管理员<label id="label">${sessionScope.userName}</label></span>
		             <span class="caret"></span>
		             </button>
		            <ul role="menu" class="dropdown-menu">
		              <li><a href="javascript:;" data-toggle="modal" data-target="#modifyPwd">修改密码</a></li>
		              <li class="divider"></li>
		              <li><a href="#">退出系统</a></li>
		            </ul>
		          </div> 
				</div>
			</div>
		</div>
		<div class="clearfix">
		</div>	
		<!-- 	头部logo结束 -->
		
		
		<div class="page-container">
			<!-- 菜单导航开始 -->
			<div class="container menu" >
				<div class="panel-group" id="accordion">
	
					
					

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseThree">
									考试管理
								</a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body">
								<ul>
									<li><a href="javascript:;" onclick="changePage('testProduct.do')">考试科目管理</a></li>
									
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse"
									data-parent="#accordion" href="#collapseOne">
									用户管理
								</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse">
							<div class="panel-body">
								<ul>
									<li><a href="javascript:;" onclick="changePage('userProduct.do')">用户管理</a></li>
									
								</ul>
							</div>
						</div>
					</div>
					
				
				</div>
			</div>
			
			<!-- 菜单导航结束 -->
			
			<!--主界面开始 -->
			<iframe id="mainFrm" style="border: none;width: 73%;height: 80%;" src="testProduct.do"></iframe>
			<!-- 主界面结束 -->
			
			<!-- 文档尾部开始 -->
			<div class="footer">Copyright &copy; 2016南京晓庄  版权所有   <a href="mailto:1140885639@qq.com">1140885639@qq.com</a></div>
			<!-- 文档尾部结束 -->
		</div>
		
		
		
		
		
		<!-- 修改密码Modal -->
			<div class="modal fade" style="margin-top: 200px;" id="modifyPwd" tabindex="-1" role="dialog" aria-labelledby="modifyPwd" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			        <form class="form-horizontal" role="form"  id="myForm" >
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
					      </div>
					      <div class="modal-body">
					      
					      			<div class="form-group">
					      			
						              <label for="oldPwd" class="col-lg-2 control-label">原密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="oldPwd" /><span id="span2"></span>
						              </div>
						            </div>
						            <div class="form-group">
						              <label for="newPwd" class="col-lg-2 control-label">新密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="newPwd"  />
						              </div>
						            </div>
						             <div class="form-group">
						              <label for="reNewPwd" class="col-lg-2 control-label">重复密码</label>
						              <div class="col-lg-10">
						                <input type="password" class="form-control" id="reNewPwd"  /><span id="span"></span>
						              </div>
						              
						            </div>
						          
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					        <input type="button" class="btn btn-primary" value="修改" onclick="check()"/>
					      </div>
           			</form>
			    </div>
			  </div>
			</div>
			<!-- 修改密码Modal -->
</body>
</html>
