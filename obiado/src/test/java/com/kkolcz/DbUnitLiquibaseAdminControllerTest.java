package com.kkolcz;

import liquibase.database.jvm.HsqlConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/* import org.springframework.web.servlet.view.InternalResourceView; */

/* import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; */
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
/* import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*; */
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/* import org.springframework.test.web.servlet.MockMvc; */
/* import org.testng.annotations.Test; */
/* import org.springframework.http.MediaType; */
/* import static org.mockito.Mockito.*; */
/* import static org.junit.matchers.JUnitMatchers.*; // for hasItem() */
/* import static org.junit.Assert.*; */
/* import org.junit.Test; */
/* import org.junit.runner.RunWith; */

import com.kkolcz.controller.*;
import com.kkolcz.dao.UserProfileDao;
import com.kkolcz.dao.UserDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.User;
import com.kkolcz.model.UserProfile;
import com.kkolcz.model.UserProfileType;
import com.kkolcz.config.DbUnitAppContext;
import com.kkolcz.config.DbUnitHibernateConfig;
import com.kkolcz.config.DbUnitHibernateLiquibaseConfig;
import com.kkolcz.config.AppConfig;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
/* import com.github.springtestdbunit.annotation.DatabaseSetup; */
/* import com.github.springtestdbunit.annotation.ExpectedDatabase; */
/* import com.github.springtestdbunit.assertion.DatabaseAssertionMode; */


import javax.annotation.Resource;
import javax.sql.DataSource;

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


import liquibase.Liquibase;
/* import liquibase.FileSystemFileOpener; *//* nie ma już, teraz jest FileSystemResourceAccessor */
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.database.jvm.JdbcConnection;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.changelog.DatabaseChangeLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbUnitAppContext.class, DbUnitHibernateLiquibaseConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class DbUnitLiquibaseAdminControllerTest {

    @Autowired UserDao userDao;
    @Autowired UserProfileDao userProfileDao;

    public static final String FIRST_NAME         = "firstName";
    public static final String LAST_NAME          = "lastName";
    public static final String EMAIL              = "email";
    public static final String PASSWORD           = "password";
    public static final String MATCHING_PASSWORD  = "matchingPassword";

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired ApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 

    /* http://www.liquibase.org/javadoc/liquibase/Liquibase.html */
    @Before
    public void setUpDatabase() throws Exception{
      /* DataSource dataSource = (DataSource)webApplicationContext.getBean("dataSource"); */
      FileSystemResourceAccessor fsra = new FileSystemResourceAccessor();
      DatabaseChangeLog changelog = new DatabaseChangeLog("src/main/resources/liquibase/changelog.xml");
      /* Liquibase lb = new Liquibase("src/main/resources/liquibase/changelog.xml", fsra, dataSource.getConnection()); */
      /* lb.update(""); */
      /* SpringLiquibase liquibase = new SpringLiquibase(dataSource,"src/main/resources/liquibase/changelog.xml","test"); */
      /* SpringLiquibase liquibase = new SpringLiquibase(); */
      /* liquibase.setChangeLog("src/main/resources/liquibase/changelog.xml"); */
      /* liquibase.setDataSource(dataSource); */
      /* liquibase.update(""); */
    
      /*
      SpringLiquibase liquibase = new SpringLiquibase();
      liquibase.setDataSource(dataSource);
      liquibase.setChangeLog("src/main/resources/liquibase/changelog.xml");
      liquibase.setContexts("test, development, production");
      //nie działa performUpdate
      liquibase.performUpdate(liquibase);
      */

      /*
      Liquibase lb = new Liquibase(changelog, fsra, dataSource.getConnection());
      lb.update("");
     */

      /* Liquibase lb = new Liquibase(); */


      DataSource dataSource = (DataSource)context.getBean("dataSource");
        HsqlConnection hsqlConnection = new HsqlConnection(dataSource.getConnection());
      //Liquibase liquibase = new Liquibase("src/main/resources/liquibase/changelog.xml", fsra, dataSource.getConnection());
        Liquibase liquibase;
        liquibase = new Liquibase("src/main/resources/liquibase/changelog.xml",
                fsra,
                hsqlConnection
        );


        liquibase.update("");
    }

    /* @After */
    /* protected void tearDown() throws Exception */
    /* { */
    /*   Liquibase liquibase = new Liquibase("src/main/resources/res/cp1/db.clear.xml", new FileSystemFileOpener(), dataSource.getConnection()); */
    /*   liquibase.update(""); */
    /*   super.tearDown(); */
    /* } */


    @Test
    public void adminUserListPageTest() throws Exception{
      System.out.println("++++++++++++++++++++++++++++++++=");
      System.out.println(AdminController.VIEW_USER_LIST);
      System.out.println("++++++++++++++++++++++++++++++++=");


      mockMvc.perform(get("/admin/user-list"))
      .andExpect(view().name(AdminController.VIEW_USER_LIST))
      .andExpect(forwardedUrl("/WEB-INF/views/admin/userList.jsp"))
      .andExpect(model().attribute(AdminController.MODEL_ATTRIBUTE_USER_LIST , hasSize(2)))
      .andExpect(model().attribute(AdminController.MODEL_ATTRIBUTE_USER_LIST , hasItem(
                              allOf(
                                      hasProperty(FIRST_NAME, is("Jacek")),
                                      hasProperty(LAST_NAME, is("Theys")),
                                      hasProperty(EMAIL, is("jacek@xyz.com"))
                              )
                      )));
    }



}
