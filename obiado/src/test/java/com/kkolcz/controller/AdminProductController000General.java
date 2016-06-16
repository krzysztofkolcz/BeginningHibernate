package com.kkolcz.controller;

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

public abstract class AdminProductController000General{

    protected static final String ID = "id";
    protected static final String NAME = "name";
    protected static final String STATE = "state";
    protected static final String PRICE = "price";
    protected static final String ACTIVE = "active";
    protected static final String SKU = "sku";
    protected static final String PRODUCTCATEGORIES = "productCategories";
 
    @Resource protected WebApplicationContext wac;

    protected MockMvc mockMvc;
    protected MockHttpSession mockHttpSession;

    @Autowired 
    protected ProductService productService;
    @Autowired 
    protected ProductCategoryService productCategoryService;

    protected abstract List<Product> createProductList();

    protected abstract ProductCategory getProductCategory1();
    protected abstract ProductCategory getProductCategory2();

    protected abstract Product getProduct1();
    protected abstract Product getProduct2();

            
    protected ResultActions postEditProductForm (String id,String name, String price, String sku, String active, String state, String  productCat1) throws Exception{
        return mockMvc.perform(post("/admin/edit-product-"+id)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( ID , id)
            .param( NAME, name )
            .param( PRICE, price )
            .param( SKU, sku )
            .param( ACTIVE, active )
            .param( STATE, state )
            .param( PRODUCTCATEGORIES , productCat1 )
            /* .param( PRODUCTCATEGORIES , productCat2 ) */
        );
    }

    protected ResultActions postAddProductForm (String name, String price, String sku, String active, String state, String  productCat1) throws Exception{
        return mockMvc.perform(post("/admin/add-product")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( NAME, name )
            .param( PRICE, price )
            .param( SKU, sku )
            .param( ACTIVE, active )
            .param( STATE, state )
            .param( PRODUCTCATEGORIES , productCat1 )
            /* .param( PRODUCTCATEGORIES , productCat2 ) */
        );
    }

    /* test */
    protected void adminProductListTest() throws Exception{

        /* Mockito.when(productService.findAll()).thenReturn(createProductList()); */

        mockMvc.perform(get("/admin/product-list"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_LIST))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_LIST  , hasItem(
                allOf(
                        hasProperty(NAME, is("Schabowy zestaw")),
                        hasProperty(PRICE, is(new BigDecimal("17.50"))),
                        hasProperty(SKU, is("000-000-001")),
                        hasProperty(ACTIVE, is(true)),
                        hasProperty(STATE, is("Active"))
                )
          )));
    }

    /* test */
    public void adminAddProductGETTest() throws Exception{
          mockMvc.perform(get("/admin/add-product"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT));
    }

    /* test */
    public void adminAddProductPOSTValidTest() throws Exception{

          String name     = "Filet z kurczaka zestaw";
          String price    = new BigDecimal("19.50").toString();
          String sku      = "000-000-003";
          String active   = "true";
          String state    = "Active";
          String productCat1 = "1";
          String productCat2 = "2";

          System.out.println("General000");
          postAddProductForm (name, price, sku, active, state, productCat2/* productCat1 */)
           .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_ADD ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( NAME, equalTo(name)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( PRICE, equalTo(price)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( SKU, equalTo(sku)) ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( ACTIVE, equalTo(true)) ))
           
           /* http://www.marcphilipp.de/downloads/posts/2013-01-02-hamcrest-quick-reference/Hamcrest-1.3.pdf */
           /* http://stackoverflow.com/questions/18919983/interrogation-about-spring-mvc-test-apis-model-attribute-method */
           /* public <T> ResultMatcher attribute(final String name, final Matcher<T> matcher) */

           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(getProductCategory2())
                 )
           ))
           /*
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(getProductCategory2())
                 )
           ))
           */
           ;
          System.out.println("General000");
    }


    /* test */
    public void adminAddProductPOSTInvalidFieldsTest() throws Exception{
          String name ="";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-003";
          String active = "true";
          String state ="Active";
          String productCat1 = "1";
          /* String productCat2 = "2"; */

          postAddProductForm (name, price, sku, active, state, productCat1 /* productCat2 */)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , NAME ))
          ;
    }

    /* test */
    public void adminAddProductPOSTInvalidSkuTest() throws Exception{

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000a9eih-000-002";
          String active = "true";
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";

          postAddProductForm (name, price, sku, active, state, productCat1/* , productCat2 */ )
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
          ;

    }

    /* test */
    public void adminEditProductGETTest() throws Exception{
          mockMvc.perform(get("/admin/edit-product-2"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( ID , equalTo(2)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( NAME, equalTo("Polędwiczki grillowane")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( PRICE, equalTo("16.00")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( SKU, equalTo("000-000-002")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( ACTIVE, equalTo(true)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( STATE , equalTo("Active")) ));
    }

    /* test */
    public void adminEditProductPOSTValidTest() throws Exception{
          String id = "2";
          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-003";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";
          postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1/* ,   productCat2 */ )
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(NAME, equalTo(name)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(PRICE, equalTo(price)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(SKU, equalTo(sku)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(ACTIVE, equalTo(activeB)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(STATE, equalTo(state)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(getProductCategory1())
                 )
          ))
          /*
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(getProductCategory2())
                 )
          ))
          */
          ;
    }

    /*

    @Test
    public void adminEditProductPOSTInvalidFieldsTest(){
    }

    */

    /* test */
    public void adminEditProductPOSTInvalidSkuTest() throws Exception {

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002agoiugh";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";
          String id = "2";
          postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1/* ,   productCat2 */ )
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
          ;
    }

    /* test */
    public void adminEditProductPOSTExistingSkuTest() throws Exception {
      String id = "2";
      String sku = "000-000-001";
      String name ="Filet z kurczaka zestaw";
      String price = new BigDecimal("19.50").toString();
      String active = "true";
      boolean activeB = true;
      String state ="Active";
      String productCat1 = "1";
      String productCat2 = "2";

      postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1/* ,   productCat2 */ )
      .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
      .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
      ;
    }

}
