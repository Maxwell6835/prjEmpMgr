<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人简历</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <style type="text/css">
     table {
    width:80%;
    margin:0 auto;
    border-collapse:collapse;
	border: 1px black solid;
}
table th,table td{
	border: 1px black solid;
	text-align: center;
	width:65px;
	padding: 15px;
	font-size: 18px;
}
  h1{
  	 margin-top: 15px;
  }
  input {
    width:60px;
	border: none;
}
   </style>
   
  <script type="text/javascript" src="javascript/ckeditor3/ckeditor.js"></script>
  <script type="text/javascript" src="javascript/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  <form action="EmpServlet.do?op=6" method="post" enctype="multipart/form-data">
   <table>
    <thead>
    <tr>
      <th colspan="6" text-align: center><h1>个 人 档 案</h1></th>
    </tr>
    </thead>
    <tbody>
      <tr>
        <td>员工编号:</td>
        <td><input type="text" name="empno" value="${emp.empno}" readonly="readonly"/></td>
        <td>员工姓名:</td>
        <td><input type="text" name="ename" value="${emp.ename}"/></td>
        <td rowspan="2" width="70px"><span>照片</span></td>
        <td rowspan="4" style="padding: 0px;">
          <img src="image/${emp.photo}" width="120px;">
        </td>
      </tr>
      <tr>
        <td>员工职位:</td>
        <td><input type="text" name="job" value="${emp.job}"/></td>
         <td>员工上司:</td>
        <td><input type="text" name="mgr" value="${emp.mgr}"/></td>
      </tr>
      <tr>
        <td>入职日期:</td>
        <td><input type="text" name="hireDate" value="<fmt:formatDate value="${emp.hireDate}" pattern="yyyy-MM-dd"/>" style="width:100%" onClick="WdatePicker()" class="Wdate"></td>
        <td>员工工资:</td>
        <td><input type="text" name="sal" value="${emp.sal}"/></td>
        <td rowspan="2" width="70px">
          <input type="file" name="photo" style="width:65px;" >
        </td>
      </tr>
      <tr>
        <td>员工津贴:</td>
        <td><input type="text" name="comm" value="${emp.comm}"/></td>
        <td>部门编号:</td>
        <td><input type="text" name="deptno" value="${emp.deptno}"/></td>
      </tr>
      <tr>
      <td>评价:</td>
       <td colspan="6">
         <textarea  class="ckeditor" name="editor1"></textarea>
       </td>
      </tr>
    </tbody>
    	<tr>
       <td colspan="6">
         <input type="submit" value="保存" style="height:30px">
       </td>
      </tr>
   </table>
  </form>
</html>













