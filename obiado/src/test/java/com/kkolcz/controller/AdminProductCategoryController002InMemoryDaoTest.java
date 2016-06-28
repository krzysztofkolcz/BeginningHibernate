package com.kkolcz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

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
import com.kkolcz.dao.ProductCategoryDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.config.AdminProductCategoryController002InMemoryDaoTestContext ;
import com.kkolcz.constants.Const;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/*  
Run this test as single test: 
mvn -Dtest=AdminProductCategoryController002TestInMemoryDao test 
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdminProductCategoryController002InMemoryDaoTestContext.class})
@WebAppConfiguration
public class  AdminProductCategoryController002InMemoryDaoTest{
    public static final String ID = "id";
    public static final String NAME = "name";

    @Autowired ProductCategoryDao productCategoryDao; 

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 

    private ResultActions postEditProductCategoryForm (String id,String name) throws Exception{
        return mockMvc.perform(post("/admin/edit-product-category-"+id)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( ID , id)
            .param( NAME, name )
        );
    }

    private ResultActions postAddProductCategoryForm (String name) throws Exception{
        return mockMvc.perform(post("/admin/add-product-category")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( NAME, name )
        );
    }



    @Test
    public void adminAddProductCategoryPOSTValidTest() throws Exception{
          String name     = "Category XX";
          postAddProductCategoryForm (name)
           .andExpect(view().name( Const.A_VIEW_PRODUCT_CAT_EDIT ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( NAME, equalTo(name)) ));
          /* ProductCategory productCategory = productCategoryDao.findByName(name); */
          ProductCategory productCategory = productCategoryDao.findByNaturalKey(name);
          assertThat(productCategory.getName(),equalTo(name));
    }
}
