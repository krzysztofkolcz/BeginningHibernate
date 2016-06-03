package com.kkolcz.controller;

/*
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.kkolcz.service.ProductService; 
import com.kkolcz.service.ProductCategoryService; 
import com.kkolcz.model.Product; 
import com.kkolcz.model.ProductCategory; 
import com.kkolcz.command.ProductCommand;
import com.kkolcz.exception.SkuExistsException;
import com.kkolcz.constants.Const;

import java.util.List;
import java.util.ArrayList;
*/
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
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.kkolcz.controller.*;
import com.kkolcz.service.*;
import com.kkolcz.model.*;
import com.kkolcz.converter.*;

import com.kkolcz.service.ProductService; 
import com.kkolcz.service.ProductCategoryService; 
import com.kkolcz.model.Product; 
import com.kkolcz.model.ProductCategory; 
import com.kkolcz.command.ProductCommand;
import com.kkolcz.exception.SkuExistsException;
import com.kkolcz.constants.Const;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;

/*
oduct;oductCategory 
PRODUCT;PRODUCT_CAT
A_MODEL_ATTRIBUTE_PRODUCT_COMMAND;A_MODEL_PRODUCT_CAT_COMMAND
 
Categorys;Categories

 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@WebAppConfiguration
public class AdminProductCategoryController001Test{

    public static final String ID = "id";
    public static final String NAME = "name";
 
    @org.springframework.context.annotation.Configuration
    @EnableWebMvc /* bez tej annotacji nie działał RoleToUserProvileConverter*/
    static class Configuration extends WebMvcConfigurerAdapter
    {
        @Bean(name="adminProductCategoryController")
        public AdminProductCategoryController controller()
        {
            return new AdminProductCategoryController();
        }

        @Bean
        public ProductService productService()
        {
            return Mockito.mock(ProductService.class);
        }

        @Bean
        public ProductCategoryService productCategoryService()
        {
            return Mockito.mock(ProductCategoryService.class);
        }

    }

    @Resource private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Autowired ProductService productService;
    @Autowired ProductCategoryService productCategoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());

        Mockito.when(productCategoryService.findAllProductCategories()).thenReturn(createProductCategoryList());

        Mockito.when(productCategoryService.findById(16)).thenReturn(createProductCategory16());
        Mockito.when(productCategoryService.findById(1)).thenReturn(createProductCategory());
    } 

    String P_CAT_NAME = "Product category 1";
    String P_CAT_16_NAME = "Product category 16";

    List<ProductCategory> createProductCategoryList(){
      List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
      ProductCategory cat16 = createProductCategory16();
      ProductCategory cat = createProductCategory();
      productCategoryList.add(cat16);
      productCategoryList.add(cat);
      return productCategoryList;
    }


    private ProductCategory createProductCategory16(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(16);
        productCategory.setName(P_CAT_16_NAME);
        return productCategory;
    }

    private ProductCategory createProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        productCategory.setName(P_CAT_NAME);
        return productCategory;
    }


            
    private ResultActions postEditProductCategoryForm (String id,String name) throws Exception{
        return mockMvc.perform(post("/admin/edit-product-category-"+id)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( ID , id)
            .param( NAME, name )
            /* .param( ACTIVE, active ) */
            /* .param( STATE, state ) */
        );
    }

    private ResultActions postAddProductCategoryForm (String name) throws Exception{
        return mockMvc.perform(post("/admin/add-product-category")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( NAME, name )
            /* .param( ACTIVE, active ) */
            /* .param( STATE, state ) */
        );
    }

    @Test
    public void adminProductCategoryListTest() throws Exception{


        mockMvc.perform(get("/admin/product-category-list"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_LIST))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_LIST  , hasItem(
                allOf(
                        hasProperty(NAME, is(P_CAT_16_NAME)) 
                )
          )));
    }

    @Test
    public void adminAddProductCategoryGETTest() throws Exception{
          mockMvc.perform(get("/admin/add-product-category"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT));
    }

    @Test
    public void adminAddProductCategoryPOSTValidTest() throws Exception{
          String name     = "Category XX";
          postAddProductCategoryForm (name)
           .andExpect(view().name( Const.A_VIEW_PRODUCT_CAT_EDIT ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( NAME, equalTo(name)) ));
    }


    @Test
    public void adminAddProductCategoryPOSTInvalidNameTest() throws Exception{
          String name ="";
          postAddProductCategoryForm (name)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_CAT_EDIT))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , NAME ))
          ;
    }

    @Test
    public void adminEditProductCategoryGETTest() throws Exception{
          mockMvc.perform(get("/admin/edit-product-category-16"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( ID , equalTo(16)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( NAME, equalTo(P_CAT_16_NAME)) ));
    }

    @Test
    public void adminEditProductCategoryPOSTValidTest() throws Exception{
          String name ="Category Oxo";
          String id = "16";
          postEditProductCategoryForm (id, name)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(NAME, equalTo(name)) ));
    }

    @Test
    public void adminEditProductCategoryPOSTInvalidNameTest() throws Exception {
          String name ="";
          String id = "16";
          postEditProductCategoryForm(id, name)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , NAME ))
          ;
    }

    @Test
    public void adminEditProductCategoryPOSTExistingNameTest() throws Exception {
      String id = "16";
      String name = P_CAT_NAME;

      Mockito.when(productCategoryService.checkNameUniqueExceptId(name,Integer.parseInt(id))).thenReturn(false);

      postEditProductCategoryForm ( id, name )
      .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
      .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , NAME ))
      ;
    }


}
