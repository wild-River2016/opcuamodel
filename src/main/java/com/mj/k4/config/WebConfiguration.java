package com.mj.k4.config;



import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * Created by oukingtim
 */
@Slf4j
@Aspect
@Configuration
public class WebConfiguration{

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addAllowedOrigin("*");
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/assets/**", config);
        source.registerCorsConfiguration("/management/**", config);
        source.registerCorsConfiguration("/websocket/", config);
        source.registerCorsConfiguration("/websocket/**", config);
        return new CorsFilter(source);
    }

}
