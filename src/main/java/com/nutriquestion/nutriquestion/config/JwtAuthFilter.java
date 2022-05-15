//package com.nutriquestion.nutriquestion.config;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.nutriquestion.nutriquestion.services.AuthService;
//
//public class JwtAuthFilter extends GenericFilterBean {
//	
//	private AuthService jwtAuthService;
//	
//	public JwtAuthFilter(AuthService jwtAuthService) {
//		this.jwtAuthService = jwtAuthService;
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		SecurityContextHolder.getContext().setAuthentication(null);
//		chain.doFilter(request, response);
//		
//	}
//	
//
//}
