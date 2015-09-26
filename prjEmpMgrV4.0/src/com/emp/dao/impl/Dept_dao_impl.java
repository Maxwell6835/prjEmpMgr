package com.emp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.dao.Dept_dao;
import com.entity.Dept;
import com.util.ConnectionUtil;

public class Dept_dao_impl implements Dept_dao
	{
		protected Connection conn;
		protected PreparedStatement pstmt;
		protected ResultSet rs;
		public List<Dept> queryDept()
			{
					List<Dept> deptList=new ArrayList<Dept>();
					conn=ConnectionUtil.getConn();
					try {
						pstmt=conn.prepareStatement("select * from dept");
						rs=pstmt.executeQuery();
						while(rs.next())
						{
							Dept dept=new Dept(rs.getByte(1),rs.getString(2),rs.getString(3));
							deptList.add(dept);
						}
					}
					catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally
					{
						ConnectionUtil.close(conn, pstmt, rs);
					}
					return deptList;
			}
			

	}
