package com.kkolcz.config;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkolcz.dao.*;
import com.kkolcz.service.*;
import com.kkolcz.controller.*;
import com.kkolcz.converter.CategoriesToProductCategoryConverter;
import com.kkolcz.validator.ProductValidator;


import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
public class AdminProductController002InMemoryDaoTestContext extends WebMvcConfigurerAdapter{

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
    public ProductDao productDao(){
      return new ProductInMemoryDao();
    }

    @Bean 
    public ProductService productService(){
      return new ProductServiceImpl(productDao());
    }

    @Bean 
    public AdminProductController adminProductController(){
      return new  AdminProductController();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }


    @Bean
    public CategoriesToProductCategoryConverter categoriesToProductCategoryConverter (){
        return new CategoriesToProductCategoryConverter(); 
    }

    @Autowired CategoriesToProductCategoryConverter categoriesToProductCategoryConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoriesToProductCategoryConverter);
    }

    @Bean
    public ProductValidator productValidator(){
        return new ProductValidator();
    }
}
