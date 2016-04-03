package com.kkolcz;

import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
import org.springframework.format.FormatterRegistry;
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
import com.kkolcz.service.*;
import com.kkolcz.model.*;

import com.kkolcz.converter.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@WebAppConfiguration
public class AdminControllerMvcMockWithContextTest{


    @org.springframework.context.annotation.Configuration
    static class Configuration extends WebMvcConfigurerAdapter
    {
        @Bean(name="adminController")
        public AdminController controller()
        {
            return new AdminController();
        }

        @Bean
        public UserService userService()
        {
            return Mockito.mock(UserService.class);
        }

        @Bean
        public UserProfileService userProfileService()
        {
            return Mockito.mock(UserProfileService.class);
        }

        @Bean
        public RoleToUserProfileConverter roleToUserProfileConverter (){
            return new RoleToUserProfileConverter(); 
        }

        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(roleToUserProfileConverter());
        }
    }

    @Resource private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Autowired UserService userService;
    @Autowired UserProfileService userProfileService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());

        Mockito.when(userService.findById(16)).thenReturn(createUser());
        Mockito.when(userProfileService.findById(5)).thenReturn(createUserProfileAdmin());
        Mockito.when(userProfileService.findById(6)).thenReturn(createUserProfileRegistered());
    } 


    /*
    @Test
    public void adminUserEditPageTest() throws Exception{
      mockMvc.perform(get("/admin/edit-user-16"))
      .andExpect(view().name(AdminController.VIEW_USER_ADD))
      .andExpect(forwardedUrl(AdminController.VIEW_USER_ADD))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("firstName", equalTo("Def")) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("lastName", equalTo("Def")) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("email", equalTo("def@wp.pl")) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("password", equalTo("Power123")) ));

    }
    */

    @Test
    public void adminUserEditPostValidTest() throws Exception{
      System.out.println("============================");
      System.out.println("adminUserEditPostValidTest");
      System.out.println("============================");

      String eFirstName = "Stef";
      String eLastName = "K";
      String eEmailName = "stef.k@wp.pl";
      String ePassword = "Rower123";
      String eMatchingPassword = "Rower123";
      String eNotMatchingPassword = "agoirehapoiasg";

      /* Valid user edit form */
      mockMvc.perform(post("/admin/edit-user-16")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("firstName", eFirstName)
      .param("lastName", eLastName)
      .param("email", eEmailName)
      .param("password", ePassword)
      .param("matchingPassword", eMatchingPassword)
      .param("userProfiles", "5")
      .param("userProfiles", "6"))
      .andExpect(view().name(AdminController.VIEW_USER_LIST))
      .andExpect(forwardedUrl(AdminController.VIEW_USER_LIST))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("firstName", equalTo(eFirstName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("lastName", equalTo(eLastName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("email", equalTo(eEmailName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("password", equalTo(ePassword)) ));
    }

    @Test
    public void adminUserEditPostPasswordNotMatchingTest() throws Exception{
      System.out.println("============================");
      System.out.println("adminUserEditPostPasswordNotMatchingTest");
      System.out.println("============================");

      String eFirstName = "Stef";
      String eLastName = "K";
      String eEmailName = "stef.k@wp.pl";
      String ePassword = "Rower123";
      String eMatchingPassword = "Rower123";
      String eNotMatchingPassword = "agoirehapoiasg";
      
      /* Innalid user edit form - password not match */
      mockMvc.perform(post("/admin/edit-user-16")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("firstName", eFirstName)
      .param("lastName", eLastName)
      .param("email", eEmailName)
      .param("password", ePassword)
      .param("matchingPassword", eNotMatchingPassword)
      .param("userProfiles", "5"))
      .andExpect(view().name(AdminController.VIEW_USER_ADD))
      .andExpect(forwardedUrl(AdminController.VIEW_USER_ADD))
      /* .andExpect(model().attributeHasFieldErrors(AppController.MODEL_ATTRIBUTE_USER_COMMAND,"password" )) */
      .andExpect(model().hasErrors())
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("firstName", equalTo(eFirstName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("lastName", equalTo(eLastName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("email", equalTo(eEmailName)) ))
      .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("password", equalTo(ePassword)) ));

      /* TODO - not matching passwords */
      /* TODO - email existing */


    }

    public User createUser(){
      User user = new User();
      user.setPassword("Power123");
      user.setFirstName("Def");
      user.setLastName("Def");
      user.setEmail("def@wp.pl");
      return user;
    }

    public UserProfile createUserProfileAdmin(){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(5);
        userProfile.setType(UserProfileType.ADMIN.getUserProfileType());
        return userProfile;

    }

    public UserProfile createUserProfileRegistered(){
        UserProfile userProfile = new UserProfile();
        userProfile.setId(6);
        userProfile.setType(UserProfileType.REGISTERED.getUserProfileType());
        return userProfile;

    }
}
