package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.emp.dao.Dept_dao;
import com.emp.dao.Emp_dao;
import com.emp.dao.impl.Dept_dao_impl;
import com.emp.dao.impl.Emp_dao_impl;
import com.entity.Dept;
import com.entity.Emp;
import com.util.Date_Util;

public class EmpServlet extends HttpServlet
	{
		private Emp_dao emp_dao;
		private Dept_dao dept_dao;
		public void init() throws ServletException
		{
			emp_dao=new Emp_dao_impl();
			dept_dao=new Dept_dao_impl();
		}
		
		public void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,
				IOException
			{
				this.doPost(request, response);
			}

		public void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,
				IOException
			{
				String opStr=request.getParameter("op");
				if(opStr!=null)
					{
						int op=Integer.parseInt(opStr);
						switch(op)
						{
							case 1:
								//分页查询员工
								queryEmpByPage(request,response);
								break;
							case 2:
								//增加员工
								addEmp(request,response);
								break;
							case 3:
								//删除员工
								deleteEmp(request,response);
								break;
							case 4:
								//更新员工
								updateEmp(request,response);
								break;
							case 5:
								//按编号查询员工详情
								queryEmpByEmpno(request,response);
								break;
							case 6:
								//按编号更新员工详情
								upload(request,response);
								break;
							default:
								break;
						}
					}
			}

		/**
		 * 分页查询所有员工
		 * 1.得到所有记录总数EmpCount，保存在session中，已备在queryEmp.jsp中使用
		 * 2.从queryEmp.jsp中接受每页显示的记录条数pageSize默认是pageSize=5;以及显示第几页page，默认是page=1
		 *   然后还要把pageSize和page保存在session中，已备在queryEmp.jsp中使用,最后用EmpCount/pageSize=pageNum得到总页数
		 *   当page小于pageNUm时就把1赋值给page，如果page大于pageNum,就把pageNum赋值给page
		 * 3.对deptno进行特殊处理，在筛选某一个部门编号下的员工是，比如只要10部门下的员工，那么查询的EmpCount，也就是记录的总数就会发生变化，
		 *   所以要首先对deptno的sort进行判断，要把判断sort放在得到EmpCount之前判断是否包含10，20，30，40，如果有的话就要得到相应编号的记录数
		 * @param request
		 * @param response
		 */
		private void queryEmpByPage(HttpServletRequest request,HttpServletResponse response)
					{
						String sort=null;
						int deptno=0;
						int EmpCount=0;
						String field=null;
						String str=null;
					  	if(request.getParameter("sort")!=null && !request.getParameter("sort").equals(""))
					  		{
					  			sort=request.getParameter("sort");
					  			if(sort.contains("10"))
					  				{
					  					 deptno=10;
					  				}
					  			else if(sort.contains("20"))
					  				{
					  					 deptno=20;
					  				}
					  			else if(sort.contains("30"))
					  				{
					  					 deptno=30;
					  				}
					  			else if(sort.contains("40"))
					  				{
					  					 deptno=40;
					  				}
					  		request.setAttribute("sort", sort);
					  		//得到员工的总记录数
							EmpCount=emp_dao.getEmpCount(deptno);
					  		}
					  	else if(request.getParameter("field")!=null && !request.getParameter("field").equals("")&&request.getParameter("field")!=null && !request.getParameter("field").equals(""))
					  		{
					  			field=request.getParameter("field");
					  			str=request.getParameter("str");
					  			EmpCount=emp_dao.getEmpCountByStr(field, str);
					  			request.setAttribute("field", field);
					  			request.setAttribute("str", str);
					  		}
					  	else
					  		{
					  			EmpCount=emp_dao.getEmpCount(0);
					  		}
						request.setAttribute("EmpCount", EmpCount);
						//获取pageSize，
						int pageSize=5;
						if(request.getParameter("pageSize")!=null)
							{
								pageSize=Integer.parseInt(request.getParameter("pageSize"));
							}
						//获取pageNum
						int pageNum=0;
						if(EmpCount%pageSize==0)
							{
								pageNum=EmpCount/pageSize;
							}
						else
							{
								pageNum=EmpCount/pageSize+1;
							}
						//获取page
						int page=1;
						if(request.getParameter("choicePage")!=null)
							{
								page=Integer.parseInt(request.getParameter("choicePage"));
								if(page<=0)
									{
										page=1;
									}
								if(page>pageNum)
									{
										page=pageNum;
									}
							}
						else
							{
								if(request.getParameter("page")!=null)
									{
										page=Integer.parseInt(request.getParameter("page"));
										if(page<=0)
											{
												page=1;
											}
										if(page>pageNum)
											{
												page=pageNum;
											}
									}
							}
					    request.setAttribute("page", page);
					  	request.setAttribute("pageNum", pageNum);
					  	request.setAttribute("pageSize", pageSize);
					  	
					  	System.out.println(sort+"sort");
					  	System.out.println(page);
					  	System.out.println(pageSize);
						List<Emp>empList=emp_dao.queryEmpByPage(page,pageSize,sort,field,str);
						List<Dept>deptList=dept_dao.queryDept();
				  	    request.setAttribute("empList", empList);
				  	    request.setAttribute("deptList", deptList);
				  	    try
							{
								request.getRequestDispatcher("/queryEmp.jsp").forward(request, response);
							}
				  	    catch (ServletException e)
							{
								e.printStackTrace();
							} 
				  	    catch (IOException e)
							{
								e.printStackTrace();
							}
					}
		//按编号查询员工详情
		private void queryEmpByEmpno(HttpServletRequest request,HttpServletResponse response)
			{
				short empno=0;
				String empnoStr=request.getParameter("empno");
				if(empnoStr!=null)
					{
						empno=Short.parseShort(empnoStr);
						Emp emp=emp_dao.queryEmpByEmpno(empno);
						request.setAttribute("emp",emp);
						try
							{
								request.getRequestDispatcher("/Resume.jsp").forward(request, response);
							}
						catch (IOException e)
							{
								e.printStackTrace();
							} 
						catch (ServletException e)
							{
								e.printStackTrace();
							}
					}
				else
					{
						try
							{
								response.sendRedirect("EmpServlet.do?op=1");
							} 
						catch (IOException e)
							{
								e.printStackTrace();
							}
					}
			}
		//详情更新
		private void upload(HttpServletRequest request,HttpServletResponse response)
			{
					//实例化对象
					Emp emp=new Emp();
					// 判断是否是多部请求
					boolean isMul = ServletFileUpload.isMultipartContent(request);
					if (isMul) {
						// 创建FileItemFactory工厂对象
						FileItemFactory factory = new DiskFileItemFactory();
						// 创建ServletFileUpload对象
						ServletFileUpload upload = new ServletFileUpload(factory);
						// 解析
						List<FileItem> all = null;
						try {
							all = upload.parseRequest(request);
							for (FileItem fileItem : all) {
								if (fileItem.isFormField()) {
									// 普通项
									// 接受普通项中name的值
									String filedName = fileItem.getFieldName();
									if (filedName != null) {
										if ("empno".equals(filedName)) {
											emp.setEmpno(Short.parseShort(fileItem.getString("UTF-8")));
										}
										if ("ename".equals(filedName)) {
											emp.setEname(fileItem.getString("UTF-8"));
										}
										if ("job".equals(filedName)) {
											emp.setJob(fileItem.getString("UTF-8"));
										}
										if ("mgr".equals(filedName)) {
											emp.setMgr(Short.parseShort(fileItem.getString("UTF-8")));
										}
										if ("hireDate".equals(filedName)) {
											emp.setHireDate((Date_Util.getDateByStr(fileItem.getString("UTF-8"),"yyyy-MM-dd")));
										}
										if ("sal".equals(filedName)) {
											emp.setSal(Double.parseDouble(fileItem.getString("UTF-8")));
										}
										if ("comm".equals(filedName)) {
											emp.setComm(Double.parseDouble(fileItem.getString("UTF-8")));
										}
										if ("deptno".equals(filedName)) {
											emp.setDeptno(Byte.parseByte(fileItem.getString("UTF-8")));
										}
									}
								}
								else 
								{
									//获取服务器上图片文件的路径
									String imagePath=request.getSession().getServletContext().getRealPath("/image");
									String imageFilePath=Date_Util.createFileName(fileItem.getName());
									fileItem.write(new File(imagePath+"/"+imageFilePath));
									emp.setPhoto(imageFilePath);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					boolean flag=emp_dao.updateEmp(emp);
					if(flag)
						{
							try
							{
								response.sendRedirect("EmpServlet.do?op=5&empno="+emp.getEmpno());
							}
							catch (IOException e)
							{
								e.printStackTrace();
						    }
					    }
				}
		//更新员工
		private void updateEmp(HttpServletRequest request,HttpServletResponse response)
			{
				  
			      String empSet=request.getParameter("empSet");
			      String[] empnoList=empSet.split(",");
			      Emp emp=new Emp();
			      emp.setEmpno(Short.parseShort(empnoList[0]));
			      emp.setEname(empnoList[1]);
				  emp.setJob(empnoList[2]);
			      emp.setMgr(Short.parseShort(empnoList[3]));
			      emp.setHireDate(Date_Util.getDateByStr(empnoList[4], "yyyy-MM-dd"));
			      emp.setSal(Double.parseDouble(empnoList[5]));
			      emp.setComm(Double.parseDouble(empnoList[6]));
			      emp.setDeptno(Byte.parseByte(empnoList[7]));
			      boolean flag=emp_dao.updateEmp(emp);
			      try
					{
						response.sendRedirect("EmpServlet.do?op=1");
					}
			      catch (IOException e)
					{
						e.printStackTrace();
					}
			}
		//删除员工
		private void deleteEmp(HttpServletRequest request,HttpServletResponse response)
			{
				 
			      String empnostr=request.getParameter("empList");
			      String[] empnoList=empnostr.split(",");
			      Emp_dao emp_dao=new Emp_dao_impl();
			      for(int i=0;i<empnoList.length;i++)
			      {
			      	 boolean flag=emp_dao.deleteEmp(Short.parseShort(empnoList[i]));
			      	 
			      }
			      
				    try
						{
							response.sendRedirect("EmpServlet.do?op=1");
						} 
				    catch (IOException e)
						{
							e.printStackTrace();
						}
			}

		//	增加员工
		private void addEmp(HttpServletRequest request,HttpServletResponse response)
			{
			   	  String addEmpStr=request.getParameter("addEmp");
			   	  String[] empnoList=addEmpStr.split(",");
			      Emp emp=new Emp();
			      emp.setEmpno(Short.parseShort(empnoList[0]));
			      emp.setEname(empnoList[1]);
				  emp.setJob(empnoList[2]);
			      emp.setMgr(Short.parseShort(empnoList[3]));
			      emp.setHireDate(Date_Util.getDateByStr(empnoList[4], "yyyy-MM-dd"));
			      emp.setSal(Double.parseDouble(empnoList[5]));
			      emp.setComm(Double.parseDouble(empnoList[6]));
			      emp.setDeptno(Byte.parseByte(empnoList[7]));
			      emp.setPhoto(null);
			      Emp_dao emp_dao=new Emp_dao_impl();
			      System.out.println(emp);
			      boolean flag=emp_dao.addEmp(emp);
			   	  try
					{
						response.sendRedirect("EmpServlet.do?op=1");
					}
			   	  catch (IOException e)
					{
						e.printStackTrace();
					}
			}

	}
