package com.sh.wxapp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * GlobalInterceptorAdaptor
 *
 * @author xuqie
 * @version 1.0.0
 **/
@Configuration
public class GlobalInterceptorAdaptor implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    private final static String[] EXCLUDE_PATH = {"/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/api-docs", "/login", "/error"};
    private final static List<String> excludePath = Arrays.asList(EXCLUDE_PATH);

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns(excludePath);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
