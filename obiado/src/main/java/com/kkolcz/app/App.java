package com.kkolcz.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import com.kkolcz.controller.*;
import com.kkolcz.dao.*;
import com.kkolcz.model.*;
import com.kkolcz.service.*;
import com.kkolcz.config.AppConfig;
import com.kkolcz.config.AppInitializer;
import com.kkolcz.config.HibernateConfig;
import com.kkolcz.config.SecurityConfig;

import javax.annotation.Resource;
import javax.sql.DataSource;


import java.util.HashSet;

/* @ContextConfiguration(classes = {AppConfig.class, AppInitializer.class, HibernateConfig.class, SecurityConfig.class, SecurityWebAppInitializer.class}) */
public class App{

    public static void main(String[] args) throws Exception{
      ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, AppInitializer.class, HibernateConfig.class, SecurityConfig.class );
      ProductCategoryServiceImpl pcsi = context.getBean(ProductCategoryServiceImpl.class);
      ProductCategory pc = pcsi.findByName("Samos");
      System.out.println(pc.getId());
    }

}
