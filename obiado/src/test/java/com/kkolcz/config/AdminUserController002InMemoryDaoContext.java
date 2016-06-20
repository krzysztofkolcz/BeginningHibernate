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


@Configuration
public class AdminUserController002InMemoryDaoContext {

    @Bean 
    public UserDao userDao(){
      return new UserInMemoryDao();
    }

    @Bean 
    public UserProfileDao userProfileDao(){
      return new UserProfileInMemoryDao();
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

}
