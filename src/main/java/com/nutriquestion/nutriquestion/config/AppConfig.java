package com.nutriquestion.nutriquestion.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean(new JwtAuthFilter());
	    registrationBean.setName("CORS FIlter");
	    registrationBean.addUrlPatterns("/*");
	    registrationBean.setOrder(1);
	    return registrationBean;
	}
	
}
