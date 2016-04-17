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
        public ProductCategoryService productCategoryService()
        {
            return Mockito.mock(ProductCategoryService.class);
        }

    }

    @Resource private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;

    @Autowired ProductCategoryService productCategoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
    } 



    private ProductCategory createProductCategory16(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(16);
        productCategory.setName("Product category 16");
        return productCategory;
    }

    private ProductCategory createProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        productCategory.setName("Product category 1");
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

    private ResultActions postAddProductCategoryForm (String name, String price, String sku, String active, String state, String  productCategoryCat1, String  productCategoryCat2) throws Exception{
        return mockMvc.perform(post("/admin/add-product-category")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( NAME, name )
            /* .param( ACTIVE, active ) */
            /* .param( STATE, state ) */
        );
    }

    @Test
    public void adminProductCategoryListTest() throws Exception{

        Mockito.when(productCategoryService.findAllProductCategories()).thenReturn(createProductCategoryList());

        mockMvc.perform(get("/admin/product-category-list"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_LIST))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_LIST  , hasItem(
                allOf(
                        hasProperty(NAME, is("Schabowy zestaw")),
                        hasProperty(PRICE, is(new BigDecimal("17.50"))),
                        hasProperty(SKU, is("000-000-001")),
                        hasProperty(ACTIVE, is(true)),
                        hasProperty(STATE, is("Active"))
                )
          )));
    }

    @Test
    public void adminAddProductCategoryGETTest() throws Exception{
          mockMvc.perform(get("/admin/add-productCategory"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT));
    }

    @Test
    public void adminAddProductCategoryPOSTValidTest() throws Exception{

          String name     = "Filet z kurczaka zestaw";
          String price    = new BigDecimal("19.50").toString();
          String sku      = "000-000-002";
          String active   = "true";
          String state    = "Active";
          String productCategoryCat1 = "1";
          String productCategoryCat2 = "2";

          postAddProductCategoryForm (name, price, sku, active, state, productCategoryCat1, productCategoryCat2)
           .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_CAT_ADD ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( NAME, equalTo(name)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( PRICE, equalTo(price)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( SKU, equalTo(sku)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( ACTIVE, equalTo(true)) ))
           
           /* http://www.marcphilipp.de/downloads/posts/2013-01-02-hamcrest-quick-reference/Hamcrest-1.3.pdf */
           /* http://stackoverflow.com/questions/18919983/interrogation-about-spring-mvc-test-apis-model-attribute-method */
           /* public <T> ResultMatcher attribute(final String name, final Matcher<T> matcher) */

           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, 
                 hasProperty( PRODUCT_CATCATEGORIES  ,
                     hasItem(createProductCategoryCategory1())
                 )
           ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, 
                 hasProperty( PRODUCT_CATCATEGORIES  ,
                     hasItem(createProductCategoryCategory2())
                 )
           ))
           ;
    }


    @Test
    public void adminAddProductCategoryPOSTInvalidFieldsTest() throws Exception{
          String name ="";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002";
          String active = "true";
          String state ="Active";
          String productCategoryCat1 = "1";
          String productCategoryCat2 = "2";

          postAddProductCategoryForm (name, price, sku, active, state, productCategoryCat1, productCategoryCat2)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_CAT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , NAME ))
          ;
    }

    @Test
    public void adminAddProductCategoryPOSTInvalidSkuTest() throws Exception{

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000a9eih-000-002";
          String active = "true";
          String state ="Active";
          String productCategoryCat1 = "1";
          String productCategoryCat2 = "2";

          postAddProductCategoryForm (name, price, sku, active, state, productCategoryCat1, productCategoryCat2)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_CAT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , SKU ))
          ;

    }
    

    @Test
    public void adminEditProductCategoryGETTest() throws Exception{
          mockMvc.perform(get("/admin/edit-productCategory-16"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( ID , equalTo(16)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( NAME, equalTo("ProductCategory 16")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( PRICE, equalTo("16.50")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( SKU, equalTo("000-000-016")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( ACTIVE, equalTo(true)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty( STATE , equalTo("Active")) ));
    }

    @Test
    public void adminEditProductCategoryPOSTValidTest() throws Exception{

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCategoryCat1 = "1";
          String productCategoryCat2 = "2";
          String id = "16";
          postEditProductCategoryForm ( id, name,  price,  sku,  active,  state,   productCategoryCat1,   productCategoryCat2)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(NAME, equalTo(name)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(PRICE, equalTo(price)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(SKU, equalTo(sku)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(ACTIVE, equalTo(activeB)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, hasProperty(STATE, equalTo(state)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, 
                 hasProperty( PRODUCT_CATCATEGORIES  ,
                     hasItem(createProductCategoryCategory1())
                 )
                  
          ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, 
                 hasProperty( PRODUCT_CATCATEGORIES  ,
                     hasItem(createProductCategoryCategory2())
                 )
          ));
    }

    /*

    @Test
    public void adminEditProductCategoryPOSTInvalidFieldsTest(){
    }

    */

    @Test
    public void adminEditProductCategoryPOSTInvalidSkuTest() throws Exception {

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002agoiugh";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCategoryCat1 = "1";
          String productCategoryCat2 = "2";
          String id = "16";
          postEditProductCategoryForm ( id, name,  price,  sku,  active,  state,   productCategoryCat1,   productCategoryCat2)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , SKU ))
          ;
    }

    @Test
    public void adminEditProductCategoryPOSTExistingSkuTest() throws Exception {
      String id = "16";
      String sku = "000-000-002";
      String name ="Filet z kurczaka zestaw";
      String price = new BigDecimal("19.50").toString();
      String active = "true";
      boolean activeB = true;
      String state ="Active";
      String productCategoryCat1 = "1";
      String productCategoryCat2 = "2";

      Mockito.when(productCategoryService.checkSkuUniqueExceptId(sku,Integer.parseInt(id))).thenReturn(false);

      postEditProductCategoryForm ( id, name,  price,  sku,  active,  state,   productCategoryCat1,   productCategoryCat2)
      .andExpect(view().name(Const.A_VIEW_PRODUCT_CAT_EDIT))
      .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , SKU ))
      ;
    }

}
