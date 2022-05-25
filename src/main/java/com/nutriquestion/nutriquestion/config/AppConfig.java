package com.nutriquestion.nutriquestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/oauth/token").permitAll().and().cors()
				.and().csrf().disable();
		System.out.println("APPCONFIG==> CONFIGURE ");
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT",
				"DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
		System.out.println("APPCONFIG==> ODD ");
	}
}
