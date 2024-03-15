package br.com.sas.travel.planner.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CorsGlobalConfiguration {

	@Value("${spring.cors.allowed-origins}")
	private List<String> allowedOrigins;

	@Bean
	CorsWebFilter corsWebFilter() {
		log.info("ALLOWED ORIGINS: " + allowedOrigins);
		var corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(allowedOrigins);
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedHeader(HttpHeaders.ACCEPT);
		corsConfig.addAllowedHeader(HttpHeaders.CONTENT_TYPE);

		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}