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
import com.kkolcz.config.InMemoryDaoAppContext;
import com.kkolcz.config.AppConfig;


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
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryDaoAppContext.class})
@WebAppConfiguration
public class InMemoryDaoControllerTest {

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

    @Before
    public void setUpUserProfile() {
      UserProfile userProfileReg = new UserProfile();
      userProfileReg.setType(UserProfileType.REGISTERED.getUserProfileType());
      userProfileDao.save(userProfileReg);
      UserProfile userProfileAdm = new UserProfile();
      userProfileAdm.setType(UserProfileType.ADMIN.getUserProfileType());
      userProfileDao.save(userProfileAdm);
    } 

    /* private MockHttpServletRequestBuilder postRegisterForm (String firstName, String lastName, String email, String password, String matchingPassword){ */
    private ResultActions postRegisterForm (String firstName, String lastName, String email, String password, String matchingPassword) throws Exception{
      /* return mockMvc.post(CONTROLLER_URL)  */
            // User is logged in
            /* .principal(CURRENT_USER)  */
        return mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(FIRST_NAME, firstName)
                .param(LAST_NAME, lastName)
                .param(EMAIL, email)
                .param(PASSWORD, password)
                .param(MATCHING_PASSWORD, password)
        );
    }



/*
    @Test 
    public void testAppControllerRegistrationPost() throws Exception{
        String firstName = "Marian";
        String lastName = "Zenoniusz";
        String email = "marian.zenoniusz@gmail.com";
        String password = "Power123";
        postRegisterForm(firstName,lastName,email,password,password)
                .andExpect(view().name(AppController.VIEW_SUCCESS_REGISTER));

        User user = userDao.findByEmail("marian.zenoniusz@gmail.com");
        assertThat(user.getFirstName(), is(equalTo(firstName)));
        assertThat(user.getLastName(), is(equalTo(lastName)));
        assertThat(user.getEmail(), is(equalTo(email)));
        assertThat(user.getPassword(), is(equalTo(password)));
        UserProfile userProfileReg = userProfileDao.findByType(UserProfileType.REGISTERED.getUserProfileType());
        UserProfile userProfileAdm = userProfileDao.findByType(UserProfileType.ADMIN.getUserProfileType());
        assertThat(user.getUserProfiles(), contains(userProfileReg));
        assertThat(user.getUserProfiles(), not(contains(userProfileAdm)));

    }
*/

    @Test 
    public void testAppControllerRegistrationPost_EmptyFirstName() throws Exception{
        String firstName = "";
        String lastName = "Zenoniusz";
        String email = "marian.zenoniusz@gmail.com";
        String password = "Power123";
        /* mockMvc.perform(post("/register") */
        /*         .contentType(MediaType.APPLICATION_FORM_URLENCODED) */
        /*         .param(FIRST_NAME, firstName) */
        /*         .param(LAST_NAME, lastName) */
        /*         .param(EMAIL, email) */
        /*         .param(PASSWORD, password) */
        /*         .param(MATCHING_PASSWORD, password) */
        /* ) */
        postRegisterForm(firstName,lastName,email,password,password)
                .andExpect(view().name(AppController.VIEW_REGISTER))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/register.jsp"))
                .andExpect(model().attributeHasFieldErrors(AppController.MODEL_ATTRIBUTE_USER_COMMAND,"firstName" ))
                .andExpect(model().attribute(AppController.MODEL_ATTRIBUTE_USER_COMMAND, hasProperty("firstName", isEmptyOrNullString()) ));
    }

}

