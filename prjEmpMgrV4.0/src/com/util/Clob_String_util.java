package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;

public class Clob_String_util
	{
		/**
		 * 把 oracleClob转换成Str
		 * @param clob
		 * @return
		 */
		public static String oracleClob2Str(Clob clob) 
			{
				String getString=null;
	        try
				{
					getString=(clob != null ? clob.getSubString(1, (int) clob.length()) : null);
				} 
	        catch (SQLException e)
				{
					e.printStackTrace();
				}
			return getString;
			}
		/**
		 * 把string写入到数据库中的clob中
		 * @param str
		 * @return
		 * @throws Exception
		 */
		@SuppressWarnings("deprecation")
		public static Reader stringToClob(String str)
			{
				 Reader clobReader=new StringReader(str);
			     return clobReader;  
	       }  
	}
