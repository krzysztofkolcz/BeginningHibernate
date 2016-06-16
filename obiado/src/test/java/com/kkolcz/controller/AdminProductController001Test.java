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
import com.kkolcz.fixture.Create;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@WebAppConfiguration
public class AdminProductController001Test extends AdminProductController000General{

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

    private Create create; 

    @Before
    public void setUp() {
        this.create = new Create();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());

        Mockito.when(productCategoryService.findById(1)).thenReturn(getProductCategory1());
        Mockito.when(productCategoryService.findById(2)).thenReturn(getProductCategory2());

        Mockito.when(productService.findById(1)).thenReturn(getProduct1());
        Mockito.when(productService.findById(2)).thenReturn(getProduct2());


        Mockito.when(productService.findAll()).thenReturn(createProductList());

        Mockito.when(productService.skuExistsExceptId("000-000-001",Integer.parseInt("2"))).thenReturn(true);
    } 

    protected ProductCategory getProductCategory1(){
        return create.getProductCategory1();
    }

    protected ProductCategory getProductCategory2(){
        return create.getProductCategory2();
    }

    protected Product getProduct1(){
        return create.getProduct1();
    }


    protected Product getProduct2(){
        return create.getProduct2();
    }


    protected List<Product> createProductList(){
        List<Product> productList = new ArrayList<Product>();
        productList.add(getProduct1());
        return productList;
    }


    /* protected ProductCategory getProductCategory1(){ */
    /*     ProductCategory productCategory = new ProductCategory(); */
    /*     productCategory.setId(1);  */
    /*     productCategory.setName("4 jelenie");  */
    /*     return productCategory; */
    /* } */
    /*  */
    /* protected ProductCategory getProductCategory2(){ */
    /*     ProductCategory productCategory = new ProductCategory(); */
    /*     productCategory.setId(2);  */
    /*     productCategory.setName("Samos");  */
    /*     return productCategory; */
    /* } */
    /*  */
    /* protected Product getProduct1(){ */
    /*     Product product = new Product(); */
    /*     product.setId(1); */
    /*     product.setName("Schabowy zestaw"); */
    /*     product.setPrice(new BigDecimal("17.50")); */
    /*     product.setActive(true); */
    /*     product.setState("Active"); */
    /*     product.setSku("000-000-001"); */
    /*     HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>(); */
    /*     productCategories.add(getProductCategory2());  */
    /*     product.setProductCategories(productCategories); */
    /*     return product; */
    /* } */
    /*  */
    /*  */
    /* protected Product getProduct2(){ */
    /*     Product product = new Product(); */
    /*     product.setId(2); */
    /*     product.setName("Polędwiczki grillowane"); */
    /*     product.setPrice(new BigDecimal("16.00")); */
    /*     product.setActive(true); */
    /*     product.setState("Active"); */
    /*     product.setSku("000-000-002"); */
    /*     HashSet<ProductCategory> productCategories = new HashSet<ProductCategory>(); */
    /*     productCategories.add(getProductCategory2());  */
    /*     product.setProductCategories(productCategories); */
    /*     return product; */
    /* } */


    @Test
    public void adminProductListTest() throws Exception{
        super.adminProductListTest();

    }

    @Test
    public void adminAddProductGETTest() throws Exception{
        super.adminAddProductGETTest();

    }

    @Test
    public void adminAddProductPOSTValidTest() throws Exception{
        super.adminAddProductPOSTValidTest();

    }


    @Test
    public void adminAddProductPOSTInvalidFieldsTest() throws Exception{
        super.adminAddProductPOSTInvalidFieldsTest();

    }

    @Test
    public void adminAddProductPOSTInvalidSkuTest() throws Exception{
        super.adminAddProductPOSTInvalidSkuTest();

    }
    

    @Test
    public void adminEditProductGETTest() throws Exception{
        super.adminEditProductGETTest();

    }

    @Test
    public void adminEditProductPOSTValidTest() throws Exception{
        super.adminEditProductPOSTValidTest();

    }


    @Test
    public void adminEditProductPOSTInvalidSkuTest() throws Exception {
        super.adminEditProductPOSTInvalidSkuTest();

    }

    @Test
    public void adminEditProductPOSTExistingSkuTest() throws Exception {
        super.adminEditProductPOSTExistingSkuTest();

    }

}
