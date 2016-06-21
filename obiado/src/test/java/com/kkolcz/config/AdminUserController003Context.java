package com.kkolcz.config;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.kkolcz.dao.*;
import com.kkolcz.service.*;
import com.kkolcz.controller.*;


import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@EnableWebMvc
public class AdminUserController003Context extends WebMvcConfigurerAdapter{

    /* private static final String JDBC_DRIVER = org.h2.Driver.class.getName(); */
    /* private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; */
    /* private static final String USER = "sa"; */
    /* private static final String PASSWORD = ""; */

    @Bean 
    public UserDao userDao(){
      return new UserDaoImpl();
    }

    @Bean 
    public UserProfileDao userProfileDao(){
      return new UserProfileDaoImpl();
    }


    @Bean 
    public UserService userService(){
      return new UserServiceImpl();
    }

    @Bean 
    public UserProfileService userProfileService(){
      return new UserProfileServiceImpl();
    }


    @Bean 
    public AppController appController(){
      return new  AppController();
    }

    @Bean 
    public AdminController adminController(){
      return new  AdminController();
    }

    /* @Bean */
    /* public ViewResolver viewResolver() { */
    /*     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); */
    /*     viewResolver.setViewClass(JstlView.class); */
    /*     viewResolver.setPrefix("/WEB-INF/views/"); */
    /*     viewResolver.setSuffix(".jsp"); */
    /*     return viewResolver; */
    /* } */
    /*  */
    /* @Bean */
    /* public MessageSource messageSource() { */
    /*     ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); */
    /*     messageSource.setBasename("messages"); */
    /*     return messageSource; */
    /* } */
    /*  */

    /* http://www.marcphilipp.de/blog/2012/03/13/database-tests-with-dbunit-part-1/ */
    /* http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-controllers/ */
    /* Brakuje session factory */
    /* @Bean(destroyMethod = "shutdown") */
    /* public EmbeddedDatabase dataSource() { */
    /*     return new EmbeddedDatabaseBuilder(). */
    /*             setType(EmbeddedDatabaseType.H2). */
    /*             addScript("dbunit/db-schema.sql"). */
    /*             addScript("dbunit/db-test-data.sql"). */
    /*             build(); */
    /* } */
}
