<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
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
		function	check(){
		$("#name").text("");
		$("#pass").text("");
		$("#mail").text("");
		$("#identitynumber").text("");
		var	email=$("#email").val();
		var	identity=$("#identity").val();
		var	regidentify=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		var	regmail= /^([a-zA-Z0-9_\.-]+)@(([a-zA-Z0-9_-]+)\.)+[a-zA-Z]{2,3}$/;
		if($("#password").val()!=$("#passwordagin").val()){
			$("#pass").text("密码不一致！");
			
		}
		else	if(!regmail.test(email)){
			$("#mail").text("邮箱格式不正确！");
		}
		else	if(!regidentify.test(identity)){
			$("#identifynumber").text("身份证格式不正确！");
		}
			
		else		{var	settings={
				url:'checkUsername.do',
				dataType:'json',
				type:'post',
				data:{username:$('#username').val()},
				success:function(data){
					if(data.result){
						$('#form').submit();
					}
					else{
						$('#name').text('用户名已存在！');
					}
				}
				
		};
		$.ajax(settings);
		
		
			
		}
		}
	</script>
  </head>
  
  <body>
    <div	style="background-image: url('${pageContext.request.contextPath }/images/regist.jpg');	height:100%;">
    	<h1	align="center"><label>欢迎进入注册界面</label></h1>
    	<div	align="center">
    	<form	id="form"	action="toRegist.do">
    		<table>
    		<tr>
    		<td	align="left">
    		<label>用户名：</label>
    		</td>
    		<td>
    		<input	id="username"	name="username"	type="text"/><span	id="name"></span>
    		</td>
    		</tr>
    		<tr>
    		<td>
    		<label>密码：</label>
    		</td>
    		<td><input	id="password"	name="password"	type="password"/><br/>
    		</td>
    		</tr>
    		<tr>
    		<td>
    		<label>确认密码：</label>
    		</td>
    		<td>
    		<input	id="passwordagin"	type="password"/><span	id="pass"></span><br/>
    		</td>
    		</tr>
    		<tr>
    			<td>电子邮箱：</td>
    			<td><input	id="email"	name="email"	type="text"/><span	id="mail"></span></td>
    		</tr>
    		<tr><td>身份证号：</td>
    		<td><input	id="identity"	name="identity"	type="text"/><span	id="identitynumber"></span></td>
    		</tr>
    		<tr><td><input	type="button"	value="确认"		onclick="check()"/></td><td><input	type="button"	value="取消"/></td></tr>
    		</table>
    	</form>
    </div>
    </div>
  </body>
</html>
