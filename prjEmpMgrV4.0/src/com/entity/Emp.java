package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.util.Date_Util;

/**
 * 员工的实体类
 * @author hp
 *
 */
public class Emp implements Serializable
	{
		private short empno;
		private String ename;
		private String job;
		private short mgr;
		private Date hireDate;
		private double sal;
		private double comm;
		private byte deptno;
		private String photo;
		public Emp()
			{
				super();
			}
		
		public Emp(short empno, String ename, String job, short mgr,Date hireDate, double sal, double comm, byte deptno,String photo)
				
			{
				super();
				this.empno = empno;
				this.ename = ename;
				this.job = job;
				this.mgr = mgr;
				this.hireDate = hireDate;
				this.sal = sal;
				this.comm = comm;
				this.deptno = deptno;
				this.photo = photo;
			}

		public short getEmpno()
			{
				return empno;
			}
		public void setEmpno(short empno)
			{
				this.empno = empno;
			}
		public String getEname()
			{
				return ename;
			}
		public void setEname(String ename)
			{
				this.ename = ename;
			}
		public String getJob()
			{
				return job;
			}
		public void setJob(String job)
			{
				this.job = job;
			}
		public short getMgr()
			{
				return mgr;
			}
		public void setMgr(short mgr)
			{
				this.mgr = mgr;
			}
		public Date getHireDate()
			{
				return hireDate;
			}
		public void setHireDate(Date hireDate)
			{
				this.hireDate = hireDate;
			}
		public double getSal()
			{
				return sal;
			}
		public void setSal(double sal)
			{
				this.sal = sal;
			}
		public double getComm()
			{
				return comm;
			}
		public void setComm(double comm)
			{
				this.comm = comm;
			}
		public byte getDeptno()
			{
				return deptno;
			}
		public void setDeptno(byte deptno)
			{
				this.deptno = deptno;
			}
		public String getPhoto()
			{
					return photo;
			}

		public void setPhoto(String photo)
			{
					this.photo = photo;
			}
		public String toString()
			{
				return empno+"\t"+"\t"+ename+"\t"+"\t"+job+"\t"+"\t"+mgr+"\t"+"\t"+Date_Util.getStrByDate(hireDate, "yyyy-MM-dd")+"\t"+"\t"+sal+"\t"+"\t"+comm+"\t"+"\t"+deptno+"\t"+photo;
			}
		
	}
