package com.wrapper;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingWrapper extends HttpServletRequestWrapper
{

	private String encoding;
	public EncodingWrapper(HttpServletRequest request,String encoding) {
		super(request);
		this.encoding=encoding;
	}
	@Override
	public String getParameter(String name) {
		String v=getRequest().getParameter(name);
		if(v!=null)
		{
			try
			{
				v=new String(v.getBytes("ISO-8859-1"),encoding);
			} 
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return v;
	}

}
