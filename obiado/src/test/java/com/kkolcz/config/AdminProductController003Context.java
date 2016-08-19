package com.kkolcz.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.kkolcz.dao.*;
import com.kkolcz.service.*;
import com.kkolcz.controller.*;
import com.kkolcz.validator.ProductValidator;

import org.hibernate.SessionFactory;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class AdminProductController003Context{

    @Bean 
    public ProductCategoryDao productCategoryDao(){
      return new ProductCategoryDaoImpl();
    }

    @Bean 
    public ProductCategoryService productCategoryService(){
      return new ProductCategoryServiceImpl();
    }

    @Bean 
    public ProductDao productDao(){
      return new ProductDaoImpl();
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
    public ProductValidator productValidator(){
        return new ProductValidator();
    }

}
