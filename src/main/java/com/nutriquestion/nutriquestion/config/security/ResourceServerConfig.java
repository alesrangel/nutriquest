package com.nutriquestion.nutriquestion.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.core.filter.TokenFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired 
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
	
	private static final String[] ADMIN = {"/usuario/nutricionista"};
			
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().disable()
		.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.anyRequest().authenticated();

//		http.cors().and().csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.authorizeRequests().anyRequest().permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC).permitAll();
	
//		 http.authorizeRequests()
//		    .antMatchers(HttpMethod.POST, "/auth").permitAll()
//		    .anyRequest().authenticated()
//		    .and().cors()
//		    .and().csrf().disable()
//		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		    .and().addFilterBefore(new TokenFilter(tokenService,nutricionistaRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
