package com.emp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.dao.Emp_dao;
import com.entity.Emp;
import com.util.ConnectionUtil;
import com.util.Date_Util;

public class Emp_dao_impl implements Emp_dao
	{
		protected Connection conn;
		protected PreparedStatement ps;
		protected ResultSet rs;
		public int getEmpCountByStr(String field,String str)
			{
				int count=0;
				String sql="select count(*) from emp where "+field+" like'%"+str+"%'";
				conn=ConnectionUtil.getConn();
				try
					{
						ps=conn.prepareStatement(sql);
						rs=ps.executeQuery();
						while(rs.next())
							{
								count=rs.getInt(1);
							}
					} 
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return count;
			}
		/**
		 * 排序后按分页查询
		 * 1.根据要求查询全部的记录
		 * 2.要得到需要显示的范围
		 * 3.最重要的是拼接查询语句
		 * 例如：SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp order by sal DESC)e)  WHERE r>=1 AND r<=15
		 * 拆解分析：1.SELECT * FROM emp order by sal DESC得到排序后的记录
		 *        2.SELECT e.*,ROWNUM r FROM (SELECT * FROM emp order by sal DESC)e生成rownum r为了下一步实现分页控制
		 *        3.SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp order by sal DESC)e)  WHERE r>=1 AND r<=15实现分页
		 *        4.其中的变量是order by sal DESC和WHERE r>=1 AND r<=15
		 *        5.所以把SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp固定
		 *        6.对order by sal DESC进行判断，如果需要排序就拼接在SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp后面
		 *          如果不需要就不拼接
		 *        7.对)e )WHERE r>=1 AND r<=15进行拼接，根据传入的参数算出start和end，拼接为:)e )WHERE r>="+start+"AND r<="+end;
		 *        8.对deptno进行特殊处理，可以根据deptno进行排序，也可以只显示某一个deptno编号的记录，所以要对sort进行判断，这个判断sort
		 *          中是否包含10，20，30，40
		 */		  
		public List<Emp> queryEmpByPage(int page,int pageSize,String sort,String field,String str)
			{
				//计算要显示记录的范围
				int start=(page-1)*pageSize+1;
				int end=page*pageSize;
				String preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp";
				String sufSql=")e)  WHERE r>="+start+" AND r<="+end;
				//得到查询记录的条件
				if(sort!=null&& !sort.equals(""))
					{
						if(sort.contains("10"))
							{
								preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp where deptno="+10;
							}
						else if(sort.contains("20"))
							{
								preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp where deptno="+20;
							}
						else if(sort.contains("30"))
							{
								preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp where deptno="+30;
							}
						else if(sort.contains("40"))
							{
								preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp where deptno="+40;
							}
						else
							{
								preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp order by "+sort;
							}
					}
				if(field!=null && str!=null && !field.equals("") && !str.equals("") )
					{
						preSql="SELECT * FROM(SELECT e.*,ROWNUM r FROM (SELECT * FROM emp where "+field+" like'%"+str+"%'";
					}
				List<Emp> emps=new ArrayList<Emp>();
				conn=ConnectionUtil.getConn();
				String sql=preSql+sufSql;
				System.out.println(sql);
				try
					{
						ps=conn.prepareStatement(sql);
						rs=ps.executeQuery();
						while(rs.next())
							{
								Emp emp=new Emp();
								emp.setEmpno(rs.getShort("empno"));
								emp.setEname(rs.getString("ename"));
								emp.setJob(rs.getString("job"));
								emp.setMgr(rs.getShort("mgr"));
								emp.setHireDate(Date_Util.getUtilDateBySqlDate(rs.getDate("hiredate")));
								emp.setSal(rs.getDouble("sal"));
								emp.setComm(rs.getDouble("comm"));
								emp.setDeptno(rs.getByte("deptno"));
								emps.add(emp);
							}
					} 
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return emps;
			}
		/**
		 * 查询数据库中的记录中总数
		 * 1.判断deptno的值
		 */
		public int getEmpCount(int deptno)
			{
				int count=0;
				String sql="select count(*) from emp";
				if(deptno!=0)
					{
						sql="select count(*) from emp where deptno="+deptno;
					}
				conn=ConnectionUtil.getConn();
				try
					{
						ps=conn.prepareStatement(sql);
						rs=ps.executeQuery();
						while(rs.next())
							{
								count=rs.getInt(1);
							}
					} 
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return count;
			}
		
		/**
		 * 查询员工列表
		 */
		public List<Emp> queryEmps()
			{
				List<Emp> empList=new ArrayList<Emp>();
				conn=ConnectionUtil.getConn();
				try
					{
						ps=conn.prepareStatement("select * from emp");
						rs=ps.executeQuery();
						while(rs.next())
							{
								Emp emp=new Emp();
								emp.setEmpno(rs.getShort("empno"));
								emp.setEname(rs.getString("ename"));
								emp.setJob(rs.getString("job"));
								emp.setMgr(rs.getShort("mgr"));
								emp.setHireDate(Date_Util.getUtilDateBySqlDate(rs.getDate("hiredate")));
								emp.setSal(rs.getDouble("sal"));
								emp.setComm(rs.getDouble("comm"));
								emp.setDeptno(rs.getByte("deptno"));
								empList.add(emp);
							}
					}
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return empList;
			}
			/**
			 * 增加员工
			 */
		public boolean addEmp(Emp emp)
			{
				boolean flag=false;
				conn=ConnectionUtil.getConn();
				try
					{
						ps=conn.prepareStatement("insert into emp values(?,?,?,?,?,?,?,?,?)");
						ps.setShort(1,emp.getEmpno());
						ps.setString(2,emp.getEname());
						ps.setString(3,emp.getJob());
						ps.setShort(4,emp.getMgr());
						ps.setDate(5,Date_Util.getSqlDateByUtilDate(emp.getHireDate())); 
						ps.setDouble(6,emp.getSal());
						ps.setDouble(7,emp.getComm());
						ps.setByte(8,emp.getDeptno());
						ps.setString(9,emp.getPhoto());
						int count=ps.executeUpdate();
						if(count>0)
							{
								flag=true;
							}
					} 
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return flag;
			}
		/**
		 * 删除员工
		 */
		public boolean deleteEmp(short empno)
			{
				boolean flag=false;
				conn=ConnectionUtil.getConn();
				try {
					ps=conn.prepareStatement("delete from emp where empno=?");
					ps.setShort(1,empno);
					int count=ps.executeUpdate();
					if(count>0){
						flag=true;
					}
				} 
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return flag;
			}
		/**
		 * 修改员工
		 */
		public boolean updateEmp(Emp emp)
			{
				boolean flag=false;
				conn=ConnectionUtil.getConn();
				String sql="update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=?,photo=? where empno=?";
				try {
					ps=conn.prepareStatement(sql);
					
					ps.setString(1,emp.getEname());
					ps.setString(2,emp.getJob());
					ps.setShort(3,emp.getMgr());
					ps.setDate(4,Date_Util.getSqlDateByUtilDate(emp.getHireDate())); 
					ps.setDouble(5,emp.getSal());
					ps.setDouble(6,emp.getComm());
					ps.setByte(7,emp.getDeptno());
					ps.setString(8,emp.getPhoto());
					ps.setShort(9,emp.getEmpno());
					int count=ps.executeUpdate();
					if(count>0)
					{
						flag=true;
					}
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return flag;
			}
		/**
		 * 按编号查询员工
		 */
		public Emp queryEmpByEmpno(short empno)
			{
				Emp emp=null;
				conn=ConnectionUtil.getConn();
				String sql="select * from emp where empno=?";
				try {
					ps=conn.prepareStatement(sql);
					ps.setShort(1,empno);
					rs=ps.executeQuery();
					if(rs.next()){
						emp=new Emp();
						emp.setEmpno(rs.getShort("empno"));
						emp.setEname(rs.getString("ename"));
						emp.setJob(rs.getString("job"));
						emp.setMgr(rs.getShort("mgr"));
						emp.setHireDate(Date_Util.getUtilDateBySqlDate(rs.getDate("hiredate")));
						emp.setSal(rs.getDouble("sal"));
						emp.setComm(rs.getDouble("comm"));
						emp.setDeptno(rs.getByte("deptno"));
						emp.setPhoto(rs.getString("photo"));
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				finally
				{
					ConnectionUtil.close(conn, ps, rs);
				}
				return emp;
			}
		
		

	}
