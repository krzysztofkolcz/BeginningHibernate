package com.kkolcz.controller;

import java.net.URL;
import java.net.URLClassLoader;

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


/* import com.github.springtestdbunit.DbUnitTestExecutionListener; */
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
@ContextConfiguration("classpath:web.xml")
@WebAppConfiguration
public class BaseControllerTest {

    /*
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 
    */


    @Test
    public void adminUserListPageTest() throws Exception{
      System.out.println("++++++++++++++++++++++++++++++++=");
      System.out.println(BaseController.VIEW_INDEX);
      System.out.println("++++++++++++++++++++++++++++++++=");

      /* classpath */
      ClassLoader cl = ClassLoader.getSystemClassLoader();
      URL[] urls = ((URLClassLoader)cl).getURLs();
      for(URL url: urls){
        System.out.println(url.getFile());
      }
      /*
      mockMvc.perform(get("/kris"))
      .andExpect(view().name(BaseController.VIEW_INDEX))
      .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
      .andExpect(model().attribute("message", equalTo("Welcome kris")))
      .andExpect(model().attribute("counter", equalTo(1)));
      */
    }



}
