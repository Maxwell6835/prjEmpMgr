package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Date_Util
	{
		/**
		 * 把Util下的时间转换成SQL下的时间
		 * @param date
		 * @return
		 */
		public static java.sql.Date getSqlDateByUtilDate(java.util.Date date)
			{
				Long longTime=date.getTime();
				java.sql.Date sqlDate=new java.sql.Date(longTime);
				return sqlDate;
			}
		/**
		 * 把SQL下的时间转换成Util下的时间
		 * @param date
		 * @return
		 */
		public static java.util.Date getUtilDateBySqlDate(java.sql.Date date)
			{
				Long longTime=date.getTime();
				java.util.Date utilDate=new java.util.Date(longTime);
				return utilDate;
			}
		/**
		 * 把时间字符串转化成时间
		 * @param str
		 * @return
		 */
		public static Date getDateByStr(String str, String pattern)
			{
				SimpleDateFormat sdf=new SimpleDateFormat(pattern);
				Date date=null;
				try
					{
						date=sdf.parse(str);
					} catch (ParseException e)
					{
						e.printStackTrace();
					}
				return date;
			}
		
		/**
		 * 把时间转化为字符串
		 * @param date
		 * @param pattern
		 * @return
		 */
		public static String getStrByDate(Date date,String pattern)
			{
				SimpleDateFormat sdf=new SimpleDateFormat(pattern);
				return sdf.format(date);
			}
		/**
		 * 根据时间戳创建文件名
		 * @param name
		 * @return
		 */
		public static String createFileName(String name)
			{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String prfix=sdf.format(new Date());
			Random random=new Random();
			int middle=random.nextInt(10);
			String sufix=name.substring(name.lastIndexOf(".")+1);
			return prfix+middle+"."+sufix;
		}
	}
