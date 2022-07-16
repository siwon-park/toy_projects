package com.example.springboottodoapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS 요청 허락을 위한 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Origin이 localhost:3000에 대해 (전체를 허락하려면 "*")
                .allowedOrigins("http://localhost:3000")
                // GET, POST, PUT, DELETE 메서드를 허용함(PATCH, OPTIONS 등도 있음)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 모든 헤더에 대해 허용
                .allowedHeaders("*")
                // 모든 credential에 대해 허용
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
