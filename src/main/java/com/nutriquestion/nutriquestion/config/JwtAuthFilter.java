package com.nutriquestion.nutriquestion.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthFilter extends GenericFilterBean implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	        throws IOException, ServletException {

	    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
	    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
	    httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
	    httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
	    httpServletResponse.setHeader("Access-Control-Allow-Credentials", "*");
	    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
	    filterChain.doFilter(servletRequest, servletResponse);

	}
}