<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <frameset rows="15%,75%,10%" frameborder="yes" border="1" >
   <frame src="top.jsp" noresize="noresize" scrolling="no">
   <frameset cols="12%,*">
     <frame src="left.jsp"  noresize="noresize" >
     <frame src="EmpServlet.do?op=1" name="right" noresize="noresize">
   </frameset>
   <frame src="footer.jsp"  noresize="noresize" scrolling="no">
   
   <noframes>
     <body>您的浏览器无法处理框架！</body>
   </noframes>
 </frameset>
</html>
