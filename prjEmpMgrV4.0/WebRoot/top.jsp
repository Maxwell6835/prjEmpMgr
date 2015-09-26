<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	  body{
	background-image:url(img/content_bg.png);
	background-repeat:no-repeat;
        }
	</style>
  </head>
  
  <%
     String name=(String)session.getAttribute("realName");
     session.invalidate(); 
  %>
  <body>
    <h1 style="margin-top: 30px;margin-left: 20px;">欢迎，<span style="color:red;"><%="Jack"%></span>成功登录</h1>
  </body>
</html>
