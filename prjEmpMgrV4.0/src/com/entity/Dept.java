package com.entity;

public class Dept
	{
		private byte deptno;
		private String dname;
		private String loc;
		
	public Dept()
		{
			super();
		}
		
	public Dept(byte deptno, String dname, String loc)
		{
			super();
			this.deptno = deptno;
			this.dname = dname;
			this.loc = loc;
		}

	public byte getDeptno()
		{
			return deptno;
		}
	public void setDeptno(byte deptno)
		{
			this.deptno = deptno;
		}
	public String getDname()
		{
			return dname;
		}
	public void setDname(String dname)
		{
			this.dname = dname;
		}
	public String getLoc()
		{
			return loc;
		}
	public void setLoc(String loc)
		{
			this.loc = loc;
		}
		
	}
