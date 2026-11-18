package com.austshop.austshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 写拦截器配置文件主类 WebMvcConfigurer
 * zpwan
 * 2019/3/18
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截除登录外的所有请求
        registry.addInterceptor(new Myinterceptor()).addPathPatterns("/**").excludePathPatterns("/blogManage/token/getById")
                .excludePathPatterns("/blogManage/admin/login").excludePathPatterns("/blogManage/filemodule/upload").excludePathPatterns("/blogManage/music/update")
                .excludePathPatterns("/blogManage/music/add").excludePathPatterns("/blogManage/music/delete");
        super.addInterceptors(registry);
    }
}
