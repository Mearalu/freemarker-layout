package com.github.mearalu.freemarker.layout.config;

import com.github.mearalu.freemarker.layout.interceptor.FreemarkerHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author meara
 * @date 2018/6/27
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPage;

    @Bean
    public FreemarkerHandlerInterceptor freemarkerInterceptor() {
        FreemarkerHandlerInterceptor freemarkerHandlerInterceptor = new FreemarkerHandlerInterceptor();
        //设置默认布局文件
        freemarkerHandlerInterceptor.setDefaultLayout("layout/main");
        freemarkerHandlerInterceptor.setErrorPage(errorPage);
        return freemarkerHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(freemarkerInterceptor()).addPathPatterns("/**");
    }
}
