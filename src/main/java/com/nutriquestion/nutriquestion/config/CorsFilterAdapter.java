package com.nutriquestion.nutriquestion.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsFilterAdapter {

	private final String[] clientUrls;
	private final String[] headers;
	private final String[] methods;

	public CorsFilterAdapter(String clientUrls, String headers, String methods) {
		this.clientUrls = clientUrls.split(",");
		this.headers = headers.split(",");
		this.methods = methods.split(",");
	}

	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		for (String clientUrl : clientUrls) {
			config.addAllowedOrigin(clientUrl);
		}
		for (String header : headers) {
			config.addAllowedHeader(header);
		}
		for (String method : methods) {
			config.addAllowedMethod(method);
		}
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
