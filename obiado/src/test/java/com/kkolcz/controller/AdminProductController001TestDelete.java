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



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@WebAppConfiguration
public class AdminProductController001TestDelete{

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STATE = "state";
    public static final String PRICE = "price";
    public static final String ACTIVE = "active";
    public static final String SKU = "sku";
    public static final String PRODUCTCATEGORIES = "productCategories";
 
    @org.springframework.context.annotation.Configuration
    @EnableWebMvc /* bez tej annotacji nie działał RoleToUserProvileConverter*/
    static class Configuration extends WebMvcConfigurerAdapter
    {
        @Bean(name="adminProductController")
        public AdminProductController controller()
        {
            return new AdminProductController();
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

        @Bean
        public CategoriesToProductCategoryConverter categoriesToProductCategoryConverter (){
            return new CategoriesToProductCategoryConverter(); 
        }

        @Autowired CategoriesToProductCategoryConverter categoriesToProductCategoryConverter;

        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(categoriesToProductCategoryConverter);
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

        Mockito.when(productCategoryService.findById(1)).thenReturn(createProductCategory1());
        Mockito.when(productCategoryService.findById(2)).thenReturn(createProductCategory2());

        Mockito.when(productService.findById(16)).thenReturn(createProduct16());
    } 


    private List<Product> createProductList(){
        List<Product> productList = new ArrayList<Product>();
        productList.add(createProduct());
        return productList;
    }

    private Product createProduct16(){
        Product product = new Product();
        product.setId(16);
        product.setName("Product 16");
        product.setPrice(new BigDecimal("16.50"));
        product.setActive(true);
        product.setState("Active");
        product.setSku("000-000-016");
        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        productCategories.add(createProductCategory1()); 
        product.setProductCategories(productCategories);
        return product;
    }

    private Product createProduct(){
        Product product = new Product();
        product.setId(1);
        product.setName("Schabowy zestaw");
        product.setPrice(new BigDecimal("17.50"));
        product.setActive(true);
        product.setState("Active");
        product.setSku("000-000-001");
        HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>();
        productCategories.add(createProductCategory1()); 
        product.setProductCategories(productCategories);
        return product;
    }


    private ProductCategory createProductCategory1(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1); 
        productCategory.setName("4 jelenie"); 
        return productCategory;
    }

    private ProductCategory createProductCategory2(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(2); 
        productCategory.setName("Samos"); 
        return productCategory;
    }

            
    private ResultActions postEditProductForm (String id,String name, String price, String sku, String active, String state, String  productCat1, String  productCat2) throws Exception{
        return mockMvc.perform(post("/admin/edit-product-"+id)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( ID , id)
            .param( NAME, name )
            .param( PRICE, price )
            .param( SKU, sku )
            .param( ACTIVE, active )
            .param( STATE, state )
            .param( PRODUCTCATEGORIES , productCat1 )
            .param( PRODUCTCATEGORIES , productCat2 )
        );
    }

    private ResultActions postAddProductForm (String name, String price, String sku, String active, String state, String  productCat1, String  productCat2) throws Exception{
        return mockMvc.perform(post("/admin/add-product")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param( NAME, name )
            .param( PRICE, price )
            .param( SKU, sku )
            .param( ACTIVE, active )
            .param( STATE, state )
            .param( PRODUCTCATEGORIES , productCat1 )
            .param( PRODUCTCATEGORIES , productCat2 )
        );
    }

    @Test
    public void adminProductListTest() throws Exception{

        Mockito.when(productService.findAll()).thenReturn(createProductList());

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

    @Test
    public void adminAddProductGETTest() throws Exception{
          mockMvc.perform(get("/admin/add-product"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT));
    }

    @Test
    public void adminAddProductPOSTValidTest() throws Exception{

          String name     = "Filet z kurczaka zestaw";
          String price    = new BigDecimal("19.50").toString();
          String sku      = "000-000-002";
          String active   = "true";
          String state    = "Active";
          String productCat1 = "1";
          String productCat2 = "2";

          postAddProductForm (name, price, sku, active, state, productCat1, productCat2)
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
                     hasItem(createProductCategory1())
                 )
           ))
           .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(createProductCategory2())
                 )
           ))
           ;
    }


    @Test
    public void adminAddProductPOSTInvalidFieldsTest() throws Exception{
          String name ="";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002";
          String active = "true";
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";

          postAddProductForm (name, price, sku, active, state, productCat1, productCat2)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , NAME ))
          ;
    }

    @Test
    public void adminAddProductPOSTInvalidSkuTest() throws Exception{

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000a9eih-000-002";
          String active = "true";
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";

          postAddProductForm (name, price, sku, active, state, productCat1, productCat2)
          .andExpect(view().name( Const.A_VIEW_SUCCESS_PRODUCT_ADD ))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
          ;

    }
    

    @Test
    public void adminEditProductGETTest() throws Exception{
          mockMvc.perform(get("/admin/edit-product-16"))
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( ID , equalTo(16)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( NAME, equalTo("Product 16")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( PRICE, equalTo("16.50")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( SKU, equalTo("000-000-016")) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( ACTIVE, equalTo(true)) ))
          .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( STATE , equalTo("Active")) ));
    }

    @Test
    public void adminEditProductPOSTValidTest() throws Exception{

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";
          String id = "16";
          postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1,   productCat2)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(NAME, equalTo(name)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(PRICE, equalTo(price)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(SKU, equalTo(sku)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(ACTIVE, equalTo(activeB)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty(STATE, equalTo(state)) ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(createProductCategory1())
                 )
                  
          ))
          .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, 
                 hasProperty( PRODUCTCATEGORIES  ,
                     hasItem(createProductCategory2())
                 )
          ));
    }

    /*

    @Test
    public void adminEditProductPOSTInvalidFieldsTest(){
    }

    */

    @Test
    public void adminEditProductPOSTInvalidSkuTest() throws Exception {

          String name ="Filet z kurczaka zestaw";
          String price = new BigDecimal("19.50").toString();
          String sku = "000-000-002agoiugh";
          String active = "true";
          boolean activeB = true;
          String state ="Active";
          String productCat1 = "1";
          String productCat2 = "2";
          String id = "16";
          postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1,   productCat2)
          .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
          .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
          ;
    }

    @Test
    public void adminEditProductPOSTExistingSkuTest() throws Exception {
      String id = "16";
      String sku = "000-000-002";
      String name ="Filet z kurczaka zestaw";
      String price = new BigDecimal("19.50").toString();
      String active = "true";
      boolean activeB = true;
      String state ="Active";
      String productCat1 = "1";
      String productCat2 = "2";

      Mockito.when(productService.skuExistsExceptId(sku,Integer.parseInt(id))).thenReturn(true);

      postEditProductForm ( id, name,  price,  sku,  active,  state,   productCat1,   productCat2)
      .andExpect(view().name(Const.A_VIEW_PRODUCT_EDIT))
      .andExpect(model().attributeHasFieldErrors( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , SKU ))
      ;
    }

}
