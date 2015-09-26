<%@page import="com.util.Date_Util"%>
<%@page import="com.entity.Emp"%>
<%@page import="com.emp.dao.Emp_dao"%>
<%@page import="com.emp.dao.impl.Emp_dao_impl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="javascript/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="javascript/common.js"></script>
	<link  type="text/css" href="css/common.css" rel="stylesheet"/>
	<style type="text/css">
	  body {
	     background-image: url("img/content_bg.png");
		 background-repeat: no-repeat;
          }
       a{
            text-decoration: none;
          }
      .slt{
            background-color: transparent;
            border: none;      
          }
	</style>
  </head>
  
  <body>
  
    <table class="tabStyle">
      <thead id="theadId">
       <tr>
           <td colspan="3" style="padding-top: 10px;">
    	      <input type="button" value="增加" onclick="add(this)" />
    	   </td>
    	   <td colspan="2" style="padding-top: 10px;">
    	      <input type="button" value="删除" onclick="deleteEmp(this)" />
    	   </td>
    	    <td colspan="2" style="padding-top: 10px;">
    	      <input type="button" value="修改" onclick="editer(this)" />
    	   </td>
    	   <td colspan="4" style="padding-top: 10px;">
    	   	  <select id="slt">
    	   	    <option value="">查询范围</option>
    	   	    <option value="empno">员工编号</option>
    	   	    <option value="ename">员工姓名</option>
    	   	    <option value="job">员工工作</option>
    	   	    <option value="mgr">员工领导</option>
    	   	    <option value="hiredate">入职日期</option>
    	   	    <option value="sal">员工工资</option>
    	   	    <option value="comm">员工津贴</option>
    	   	    <option value="deptno">部门编号</option>
    	   	  </select>中包含
    	   	  <input type="text" id="str" value=""/>
    	      <input type="button" value="查询" onclick="query('${pageSize}')" />
    	   </td>
      </tr>
        <tr>
         <th>全选<input type="checkbox" id="choice" class="choice" onclick="choice()"/></th>
         <th>序号</th>
         <th>
           <select class="slt"  onchange="sortByNo(this.value,'empno','${pageSize}')">
              <option selected="selected">员工编号</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
           </select>
         </th>
		 <th>
		   <select class="slt" onchange="sortByNo(this.value,'ename','${pageSize}')">
              <option value="" selected="selected" >员工姓名</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
           </select> 
		</th>
		 <th>
		   <select class="slt" onchange="sortByNo(this.value,'job','${pageSize}')">
              <option value="" selected="selected" >员工职位</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
           </select> 
		</th>
		 <th>
		   <select class="slt" onchange="sortByNo(this.value,'mgr','${pageSize}')">
              <option value="" selected="selected" >员工上司</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
           </select> 
		 </th>
		 <th>
		   <select class="slt" onchange="sortByNo(this.value,'hireDate','${pageSize}')">
              <option value="" selected="selected" >入职日期</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
           </select>
		 </th>
		 <th>
		  <select class="slt" onchange="sortByNo(this.value,'sal','${pageSize}')">
             <option value="" selected="selected" >员工工资</option>
             <option value="asc">升序</option>
             <option value="desc">降序</option>
          </select>
		 </th>
		 <th>
		   <select class="slt" onchange="sortByNo(this.value,'comm','${pageSize}')">
             <option value="" selected="selected" >员工津贴</option>
             <option value="asc">升序</option>
             <option value="desc">降序</option>
          </select>
		 </th>
		 <th>
		    <select class="slt" onchange="sortByNo(this.value,'deptno','${pageSize}')">
             <option value="" selected="selected" >部门编号</option>
             <option value="asc">升序</option>
             <option value="desc">降序</option>
             <option value="10">10</option>
             <option value="20">20</option>
             <option value="30">30</option>
             <option value="40">40</option>
          </select>
		 </th>
		 <th>个人档案</th>
        </tr>
      </thead>
      <tbody id="tbodyId">
      <c:forEach var="emp" items="${empList}" varStatus="vs">
      	<tr class="tab">
      		 <td style="width:4%"><input type="checkbox" name="choice" id="${vs.index+1}" style="margin-left: 1px;"  readonly="readonly" onclick="changeStyle(this)"/></td>
      	     <td style="width:4%"><input type="text" name="index" value="${vs.index+1}" readonly="readonly" /></td>
  			 <td class="wd"><input type="text" name="empno" value="${emp.empno}" readonly="readonly"/></td>
	         <td class="wd"><input type="text" name="ename" value="${emp.ename}" readonly="readonly"></td>
	         <td class="wd"><input type="text" name="job" value="${emp.job}" readonly="readonly"></td>
	         <td class="wd"><input type="text" name="mgr" value="${emp.mgr}" readonly="readonly"></td>
	         <td class="wd"><input type="text" name="HireDate" value="<fmt:formatDate value="${emp.hireDate}" pattern="yyyy-MM-dd"/>" id="hireDate" readonly="readonly"></td>
	         <td class="wd"><input type="text" name="sal" value="${emp.sal}" readonly="readonly"></td>
	         <td class="wd"><input type="text" name="comm" value="${emp.comm}" readonly="readonly"></td>
  			 <td class="wd">
  			   <select name="deptno" disabled="disabled" style="color:black">
  			     <c:forEach var="dept" items="${deptList}">
					<option value="${dept.deptno}" ${dept.deptno==emp.deptno?"selected":"" }>${dept.deptno}</option>     
  			     </c:forEach>
  			   </select>
  			 </td>
  			 <td><a  href="EmpServlet.do?op=5&empno=${emp.empno}">个人详情</a></td>
    	 </tr>
      </c:forEach>
      </tbody>
      <tfoot>
        <tr>
         <td colspan="11" style="height:50px">
          <span>查到${EmpCount}条记录,</span>
            <span>每页显示
            <select name="pageSize" onchange="pageSize()" id="pageSize">
                 <option value="5" ${pageSize==5?"selected":"" }>5</option>
                 <option value="10" ${pageSize==10?"selected":"" }>10</option>
                 <option value="15" ${pageSize==15?"selected":"" }>15</option>
              </select>
                       条记录,共${pageNum}页</span>
         	<span><a href="EmpServlet.do?op=1&page=1&pageSize=${pageSize}&sort=${sort}&field=${field}&str=${str}">首页</a></span>
         	<span><a href="EmpServlet.do?op=1&page=${page-1}&pageSize=${pageSize}&sort=${sort}&field=${field}&str=${str}">上一页</a></span>
         	<span><a href="EmpServlet.do?op=1&page=${page+1}&pageSize=${pageSize}&sort=${sort}&field=${field}&str=${str}">下一页</a></span>
         	<span><a href="EmpServlet.do?op=1&page=${pageNum}&pageSize=${pageSize}&sort=${sort}&field=${field}&str=${str}">尾页</a></span>
         	<span><input type="text" id="choicePage" style="width:50px" value="${page}" onchange="choicePage()">&nbsp;/${pageNum}</span>
         </td>
        </tr>
      </tfoot>
    </table>
  </body>
</html>
