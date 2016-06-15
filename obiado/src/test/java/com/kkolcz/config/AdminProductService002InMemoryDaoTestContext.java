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


import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class AdminProductService002InMemoryDaoTestContext extends WebMvcConfigurerAdapter{

    @Bean 
    public ProductCategoryDao productCategoryDao(){
      return new ProductCategoryInMemoryDao();
    }

    @Bean 
    public ProductCategoryService productCategoryService(){
      return new ProductCategoryServiceImpl();
    }

    @Bean 
    public ProductDao productDao(){
      return new ProductInMemoryDao();
    }

    /* @Bean  */
    /* public ProductService productService(){ */
    /*   return new ProductServiceImpl(); */
    /* } */

    @Bean 
    public AbstractService productService(){
      return new ProductServiceImpl(productDao());
    }


    /*
    @Bean
    public CategoriesToProductCategoryConverter categoriesToProductCategoryConverter (){
        return new CategoriesToProductCategoryConverter(); 
    }

    @Autowired CategoriesToProductCategoryConverter categoriesToProductCategoryConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoriesToProductCategoryConverter);
    }
    */
}
