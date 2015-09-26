package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wrapper.EncodingWrapper;

public class EncodingFilter implements Filter {

	private String encoding;
	public void init(FilterConfig config) throws ServletException
	{
		encoding=config.getInitParameter("encoding");
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		if("GET".equalsIgnoreCase(request.getMethod()))
		{ 
			request=new EncodingWrapper(request, encoding);
		}
		else if("POST".equalsIgnoreCase(request.getMethod()))
		{ 
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request,response);
	}
	 public void destroy() {
		// TODO Auto-generated method stub

	}
}
