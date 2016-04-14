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
public class AdminProductController001Test{
 
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
    } 


    private List<Product> createProductList(){
        List<Product> productList = new ArrayList<Product>();
        productList.add(createProduct());
        return productList;
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
      productCategories.add(createProductCategory()); 
      product.setProductCategories(productCategories);
      return product;
    }

    private ProductCategory createProductCategory(){
      ProductCategory productCategory = new ProductCategory();
      productCategory.setId(1); 
      productCategory.setName("4 jelenie"); 
      return productCategory;
    }


    @Test
    public void adminProductListTest() throws Exception{
      /* TODO */

      Mockito.when(productService.findAllProducts()).thenReturn(createProductList());

      mockMvc.perform(get("/admin/product-list"))
        .andExpect(view().name(Const.A_VIEW_PRODUCT_LIST))
        .andExpect(model().attribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_LIST  , hasItem(
                              allOf(
                                      hasProperty("name", is("Schabowy zestaw")),
                                      hasProperty("price", is(new BigDecimal("17.50"))),
                                      hasProperty("sku", is("000-000-001")),
                                      hasProperty("active", is(true)),
                                      hasProperty("state", is("Active"))
                              )
                      )));

    }

    @Test
    public void adminAddProductGETTest() throws Exception{
        mockMvc.perform(get("/admin/add-product"))
        .andExpect(view().name(Const.A_VIEW_PRODUCT_ADD));
    }

    /*
    @Test
    public void adminAddProductPOSTInvalidFieldsTest(){
    }

    @Test
    public void adminAddProductPOSTInvalidSkuTest(){
    }

    @Test
    public void adminAddProductPOSTValidTest(){
    }

    @Test
    public void adminEditProductGETTest(){
    }

    @Test
    public void adminEditProductPOSTInvalidFieldsTest(){
    }

    @Test
    public void adminEditProductPOSTInvalidSkuTest(){
    }

    @Test
    public void adminEditProductPOSTValidTest(){
    }
    */
}
