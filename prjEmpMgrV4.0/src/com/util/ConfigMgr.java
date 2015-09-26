package com.util;

/**
 * 数据库连接的配置文件
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigMgr
	{
		private static ConfigMgr configMgr;
		private static Properties properties;
		private ConfigMgr()
			{
				String configFile="dbConfig.properties";
				properties=new Properties();
				InputStream is=ConfigMgr.class.getClassLoader().getResourceAsStream(configFile);
				try
					{
						properties.load(is);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				finally
				{
					try
						{
							if(is!=null)
								{
									is.close();
								}
						} catch (Exception e2)
						{
							e2.printStackTrace();
						}
				}
			}
		public static ConfigMgr getInstance()
			{
				if(configMgr==null)
					{
						configMgr=new ConfigMgr();
					}
				return configMgr;
			}
		public String getValueByKey(String key)
			{
				return properties.getProperty(key);
			}
	}
