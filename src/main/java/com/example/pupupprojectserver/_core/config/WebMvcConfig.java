package com.example.pupupprojectserver._core.config;

import com.example.pupupprojectserver._core.interceptor.AppLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:./upload/")
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppLoginInterceptor())
                .addPathPatterns("/api/**");
//        registry.addInterceptor(new SSRLoginInterceptor())
//                .addPathPatterns("/stores/**")
//                .excludePathPatterns("/stores/login", "/stores/login-form", "/stores/join", "/stores/join-form");
    }
}