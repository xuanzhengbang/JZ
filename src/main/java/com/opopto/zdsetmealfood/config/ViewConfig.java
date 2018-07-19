package com.opopto.zdsetmealfood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/list").setViewName("list");
        registry.addViewController("/edit").setViewName("edit");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("404");
    }

}
