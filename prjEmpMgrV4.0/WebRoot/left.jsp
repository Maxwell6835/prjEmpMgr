<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<style type="text/css">
	 body
	  {
		 background-image: url("img/content_bg.png");
		 background-repeat: no-repeat;
      }
     div
	{
	    margin: 10px 0px;
	    border: solid 1px #017ae3;
	    width: 125px;
	}
	ul
	{
	    list-style-type: none;
	    padding: 10px;
	    margin: 0px;
	}
	li
	{
	    margin-bottom: 13px;
	    background-color: #017ae3;
	}
	h4
	{
	    background-color: #017ae3;
	    padding: 5px;
	    margin: 0px;
	}
      div a {
      color:green;
      font-size:18px;
	text-decoration: none;
}
	</style>

  <script type="text/javascript" src="javascript/jquery-1.11.3.min.js"></script>
  <script type="text/javascript">
    $(function () {
       $("h4").bind("click", function () {
           if ($("#hidval").val() == 0) {
               $("ul").slideUp(300,function() {
                   $("#hidval").val(1);
               });
           } else {
              $("ul").slideDown(300,function(){
                   $("#hidval").val(0);
               });
           }
       });
   });
  </script>
  </head>
  
  <body>
   <div id="box">
     <h4>员工管理</h4>
     <ul>
       <li><a href="EmpServlet.do?op=1" target="right">员工列表</a></li>
       <li><a>按编号查询</a></li>
       <li><a>增加员工</a></li>
       <li><a>删除员工</a></li>
       <li><a>修改员工</a></li>
     </ul>
      <input id="hidval" type="hidden" value="0"/>
   </div>
  </body>
</html>
