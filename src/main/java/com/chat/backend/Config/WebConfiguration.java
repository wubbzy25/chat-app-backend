package com.chat.backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://chat-app-sable-pi-20.vercel.app")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
    }
}
