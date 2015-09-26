package com.emp.dao;

import java.util.List;

import com.entity.Emp;

public interface Emp_dao
	{
		
		/**
		 * 查询全部员工
		 * @return
		 */
		public List<Emp> queryEmps();
		/**
		 * 按员工编号查询
		 * @return
		 */
		public Emp queryEmpByEmpno(short empno);
		/**
		 * 增加员工
		 * @param emp
		 * @return
		 */
		public boolean addEmp(Emp emp);
		/**
		 * 删除员工
		 * @param empno
		 * @return
		 */
		public boolean deleteEmp(short empno);
		/**
		 * 修改员工信息
		 * @param emp
		 * @return
		 */
		public boolean updateEmp(Emp emp);
		/**
		 * 得到数据库中记录的总数
		 * @return
		 */
		public int getEmpCount(int deptno);
		/**
		 * 得到模糊查询的总记录数
		 * @param field
		 * @param str
		 * @return
		 */
		public int getEmpCountByStr(String field,String str);
		/**
		 * 按分页查询
		 * @param start
		 * @param end
		 * @return
		 */
		public List<Emp> queryEmpByPage(int start,int pageSize,String sort,String field,String str);
	}
