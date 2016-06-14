package com.kkolcz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.math.BigDecimal;

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
import com.kkolcz.service.*;
import com.kkolcz.dao.ProductCategoryDao;
import com.kkolcz.dao.ProductDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.config.AdminProductController002InMemoryDaoTestContext;
import com.kkolcz.constants.Const;
import com.kkolcz.fixture.Create;

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
import static org.junit.Assert.*;

/*  
Run this test as single test: 
mvn -Dtest=AdminProductCategoryController002TestInMemoryDao test 
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdminProductController002InMemoryDaoTestContext.class})
@WebAppConfiguration
public class  AdminProductController002InMemoryDaoTest extends AdminProductController000General{
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String ACTIVE = "active";
    public static final String SKU = "sku";
    public static final String PRODUCTCATEGORY = "productCategory";

    @Autowired ProductCategoryDao productCategoryDao; 
    @Autowired ProductDao productDao; 
    @Autowired ProductCategoryService productCategoryService;

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private Create create; 
    public AdminProductController002InMemoryDaoTest (){
        this.create = new Create();
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    } 


    protected ProductCategory getProductCategory1(){
        return create.getProductCategory1();
    }

    protected ProductCategory getProductCategory2(){
        return create.getProductCategory2();
    }

    protected Product getProduct1(){
        return create.getProduct2();
    }


    protected Product getProduct2(){
        return create.getProduct2();
    }

    @Before
    public void setUpProductCategories() {
        ProductCategory pc1 = getProductCategory1();
        ProductCategory pc2 = getProductCategory2();
        /* pc1.setId(1); */
        /* pc1.setName("Category 1"); */
        /* pc2.setId(2); */
        /* pc2.setName("Category 2"); */
        productCategoryDao.saveProductCategory(pc1);
        productCategoryDao.saveProductCategory(pc2);
    } 

    @Before
    public void setUpProducts() {
        Product p1 = getProduct1();
        Product p2 = getProduct2();
        /* p1.setName("kotlet"); */
        /* p1.setSku("aaa-aaa-aaa"); */
        /* p1.setPrice(new BigDecimal("180.20")); */
        /* p1.setActive(true); */
        /* Set<ProductCategory> set = new HashSet<ProductCategory>(); */
        /* set.add(productCategoryService.findById(1)); */
        /* p1.setProductCategories(set); */
        productDao.save(p1);
        productDao.save(p2);
    } 

    /* private ResultActions postEditProductForm (String id,String name,String price,String sku,String productCategoryId) throws Exception{ */
    /*     return mockMvc.perform(post("/admin/edit-product-"+id) */
    /*         .contentType(MediaType.APPLICATION_FORM_URLENCODED) */
    /*         .param( ID , id) */
    /*         .param( NAME, name ) */
    /*         .param( PRICE, price) */
    /*         .param( SKU, sku) */
    /*         .param( PRODUCTCATEGORY, productCategoryId) */
    /*     ); */
    /* } */

    /* private ResultActions postAddProductForm (String name,String price, String sku,String productCategoryId) throws Exception{ */
    /*     return mockMvc.perform(post("/admin/add-product") */
    /*         .contentType(MediaType.APPLICATION_FORM_URLENCODED) */
    /*         .param( NAME, name ) */
    /*         .param( PRICE, price) */
    /*         .param( SKU, sku) */
    /*         .param( PRODUCTCATEGORY, productCategoryId) */
    /*     ); */
    /* } */

    @Test
    public void adminAddProductPOSTValidTest() throws Exception{
          /* String name     = "kotlet"; */
          /* postAddProductForm (name,"290","aaa-aaa-aaa","1") */
          /*  .andExpect(view().name( Const.A_VIEW_PRODUCT_EDIT )) */
          /*  .andExpect(model().attribute( Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, hasProperty( NAME, equalTo(name)) )); */
          /* List<Product> products = productDao.findAll(); */
          /* for(Product p: products) { */
          /*   System.out.println(p.getName()); */
          /* } */
          String name = "Filet z kurczaka zestaw";
          String sku      = "000-000-003";
          super.adminAddProductPOSTValidTest();
          List<Product> products = productDao.findBySku(sku);
          assertEquals(products.size(),1);
          Product product = products.get(0);
          assertThat(product.getName(),equalTo(name));
    }
}
