package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil
	{
		private static final String DRIVER=ConfigMgr.getInstance().getValueByKey("DRIVER");
		private static final String URL=ConfigMgr.getInstance().getValueByKey("URL");
		private static final String USER=ConfigMgr.getInstance().getValueByKey("USER");
		private static final String PASSWORD=ConfigMgr.getInstance().getValueByKey("PASSWORD");
		/**
		 * 获取连接
		 * @return
		 */
		public static Connection getConn()
			{
				Connection conn=null;
				try
					{
						Class.forName(DRIVER);
						conn=DriverManager.getConnection(URL,USER,PASSWORD);
					} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					} catch (SQLException e)
						{
							e.printStackTrace();
						}
				return conn;
			}
		/**
		 * 关闭资源
		 * @param conn
		 * @param ps
		 * @param rs
		 */
		public static void close(Connection conn,PreparedStatement ps,ResultSet rs)
			{

				try
					{
						
						if(rs!=null)
							{
								rs.close();
							}
						if(ps!=null)
							{
								ps.close();
							}
						if(conn!=null)
							{
								conn.close();
							}
						
					}
				catch (SQLException e)
					{
						e.printStackTrace();
					}
			}
	}
