//package com.nutriquestion.nutriquestion.config;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.nutriquestion.nutriquestion.services.AuthService;
//
//public class JwtAuthFilter extends GenericFilterBean {
//	
//	private JwtAuthService authService;
//	
//	public JwtAuthFilter(AuthService authService) {
//		this.authService = authService;
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		Authentication authentication = authService.authenticated();
//		
//		SecurityContextHolder.getContext().setAuthentication(null);
//		chain.doFilter(request, response);
//		
//	}
//	
//
//}
