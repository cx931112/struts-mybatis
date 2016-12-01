<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script	type="text/javascript">
	function	cancle(){
		$("#username").val("");
		$("#password").val("");
	}
	function	check(){
		var	settings={
		url:'loginCheck.do',
		dataType:'json',
		type:'post',
		data:{username:$('#username').val(),password:$('#password').val()},
		success:function(data){
		if(data.result){
			$('#loginForm').submit();
		}
		else{
			alert("用户名或者密码错误！");
		}
		}
		};
		$.ajax(settings);
	}
</script>
  </head>
  
  <body>
  <div	style="background-image: url('${pageContext.request.contextPath }/images/68669.jpg');	height:100%;">
    <div	align="center"><h2>欢迎来到在线报名系统</h2></div>
    <center>
    <form	id="loginForm"	action="toLogin.do"	method="post">
    		<label>用户名：</label><input	id="username"	name="username"type="text"/><br/>
    		<label>密码&ensp;&ensp;：</label><input	id="password"	name="password"	type="text"/><br/>
    		<input	id="loginButton"	type="button"	value="登陆"		onclick="check()"	/>
    		<input	id="cancleButton"	type="button"	value="取消"		onclick="cancle()"/>
    		<a	href="${pageContext.request.contextPath }/regist.jsp">注册</a>
    	
    </form>
    </center>
    </div>
  </body>
</html>
