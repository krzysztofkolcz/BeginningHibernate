package com.kkolcz;

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
import com.kkolcz.config.AppConfig;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
/* import com.github.springtestdbunit.annotation.DatabaseSetup; */
/* import com.github.springtestdbunit.annotation.ExpectedDatabase; */
/* import com.github.springtestdbunit.assertion.DatabaseAssertionMode; */


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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbUnitAppContext.class,DbUnitHibernateConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class DbUnitAdminControllerTest {

    @Autowired UserDao userDao;
    @Autowired UserProfileDao userProfileDao;

    public static final String FIRST_NAME         = "firstName";
    public static final String LAST_NAME          = "lastName";
    public static final String EMAIL              = "email";
    public static final String PASSWORD           = "password";
    public static final String MATCHING_PASSWORD  = "matchingPassword";

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 


    @Test
    public void adminUserListPageTest() throws Exception{
      System.out.println("++++++++++++++++++++++++++++++++=");
      System.out.println(AdminController.VIEW_USER_LIST);
      System.out.println("++++++++++++++++++++++++++++++++=");

      mockMvc.perform(get("/admin/user-list"))
      .andExpect(view().name(AdminController.VIEW_USER_LIST))
      .andExpect(forwardedUrl("/WEB-INF/views/admin/userList.jsp"))
      .andExpect(model().attribute(AdminController.MODEL_ATTRIBUTE_USER_LIST , hasSize(6)))
      .andExpect(model().attribute(AdminController.MODEL_ATTRIBUTE_USER_LIST , hasItem(
                              allOf(
                                      hasProperty(FIRST_NAME, is("Marian")),
                                      hasProperty(LAST_NAME, is("Zenoniusz")),
                                      hasProperty(EMAIL, is("marian.zenoniusz@gmail.com"))
                              )
                      )));
    }



}
