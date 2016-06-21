package com.kkolcz.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkolcz.converter.CategoriesToProductCategoryConverter;

@Configuration
@EnableWebMvc
public class ConverterCategoryContext extends WebMvcConfigurerAdapter{

    @Bean
    public CategoriesToProductCategoryConverter categoriesToProductCategoryConverter (){
        return new CategoriesToProductCategoryConverter(); 
    }

    @Autowired CategoriesToProductCategoryConverter categoriesToProductCategoryConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoriesToProductCategoryConverter);
    }

}
