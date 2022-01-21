package com.glanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfiguration {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("*"); // e.g. http://domain1.com
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");

      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}
