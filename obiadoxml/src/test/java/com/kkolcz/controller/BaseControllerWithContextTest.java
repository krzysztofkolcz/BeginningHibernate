package com.kkolcz.controller;

import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.context.annotation.Bean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.UUID;

import com.kkolcz.controller.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@WebAppConfiguration
public class BaseControllerWithContextTest {

    @org.springframework.context.annotation.Configuration
    static class Configuration
    {
        @Bean(name="baseController")
        public BaseController controller()
        {
            return new BaseController();
        }
    }

    @Resource private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
    } 


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
      mockMvc.perform(get("/kris"))
      .andExpect(view().name(BaseController.VIEW_INDEX))
      /* WebApplicationContext nie ma ustawionego view resolvera, dlatego zwracane bez prefix i suffix
      .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp")) 
      */
      .andExpect(forwardedUrl("index"))
      .andExpect(model().attribute("message", equalTo("Welcome kris")))
      .andExpect(model().attribute("counter", equalTo(1)));
    }
}
