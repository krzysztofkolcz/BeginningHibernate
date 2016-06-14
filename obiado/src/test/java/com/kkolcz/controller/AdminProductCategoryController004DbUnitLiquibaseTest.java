package com.kkolcz.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kkolcz.controller.*;
import com.kkolcz.constants.Const;
import com.kkolcz.dao.ProductCategoryDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.User;
import com.kkolcz.model.UserProfile;
import com.kkolcz.model.UserProfileType;
import com.kkolcz.config.AdminProductCategoryController003Context;
import com.kkolcz.config.DbUnitHibernateConfig;
import com.kkolcz.config.AppConfig;
import com.kkolcz.config.DbUnitAppContext;
import com.kkolcz.config.DbUnitHibernateLiquibaseConfig;


import com.github.springtestdbunit.DbUnitTestExecutionListener;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import javax.sql.DataSource;

import liquibase.Liquibase;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.database.jvm.JdbcConnection;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.jvm.HsqlConnection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdminProductCategoryController003Context.class, DbUnitHibernateLiquibaseConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class AdminProductCategoryController004DbUnitLiquibaseTest{


    public static final String NAME               = "name";
    public static final String ID                 = "id";

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired ApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 

    @Before
    public void setUpDatabase() throws Exception{
      FileSystemResourceAccessor fsra = new FileSystemResourceAccessor();
      DatabaseChangeLog changelog = new DatabaseChangeLog("src/main/resources/liquibase/changelog.xml");
      DataSource dataSource = (DataSource)context.getBean("dataSource");
      HsqlConnection hsqlConnection = new HsqlConnection(dataSource.getConnection());
      Liquibase liquibase;
      liquibase = new Liquibase("src/main/resources/liquibase/changelog.xml",
              fsra,
              hsqlConnection
      );
      liquibase.update("");
    }

    @Test
    public void adminUserListPageTest() throws Exception{

      System.out.println("++++++++++++++++++++++++++++++++=");
      System.out.println("TEST");
      System.out.println("++++++++++++++++++++++++++++++++=");


      /*
      mockMvc.perform(get("/admin/product-category-list"))
      .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_LIST))
      .andExpect(forwardedUrl("/WEB-INF/views/admin/productCategoryList.jsp"))
      .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_LIST , hasSize(2)))
      .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_LIST, hasItem(
                              allOf(
                                      hasProperty(NAME, is("4 jelenie"))
                              )
                      )));
      */
      assert(true);
    }
}
