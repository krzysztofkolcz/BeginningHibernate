package com.kkolcz.config;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.kkolcz.dao.*;
import com.kkolcz.service.*;
import com.kkolcz.controller.*;
import com.kkolcz.converter.CategoriesToProductCategoryConverter;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.format.FormatterRegistry;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebMvc
public class AdminProductCategoryController002InMemoryDaoTestContext extends WebMvcConfigurerAdapter{


    @Bean 
    public ProductCategoryDao productCategoryDao(){
      return new ProductCategoryInMemoryDao();
    }


    @Bean 
    public ProductCategoryService productCategoryService(){
      return new ProductCategoryServiceImpl();
    }

    @Bean 
    public AdminProductCategoryController adminProductCategoryController(){
      return new  AdminProductCategoryController();
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
