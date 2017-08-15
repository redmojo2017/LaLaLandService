package com.cjl.ljz.basedemo.core.intercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 暂时用不着，用来直接跳转的
 * @author 49594
 *
 */
@Configuration
@EnableWebMvc
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/showDemo");;
    }
}
