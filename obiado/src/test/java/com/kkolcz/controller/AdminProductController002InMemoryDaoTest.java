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


import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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

    private Create create; 

    @Before
    public void setUp() {
        this.create = new Create();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        ProductCategory pc1 = getProductCategory1();
        ProductCategory pc2 = getProductCategory2();
        productCategoryDao.saveProductCategory(pc1);
        productCategoryDao.saveProductCategory(pc2);
        Product p1 = getProduct1();
        System.out.println(p1.getName());
        Product p2 = getProduct2();
        System.out.println(p2.getName());
        productDao.save(p1);
        productDao.save(p2);
        System.out.println("--------------------setUp--------------------");
        List<Product> prds = productDao.findAll();
        for(Product p : prds){
          System.out.println(p.getName());
        }
        System.out.println("--------------------setUp--------------------");
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


    @Test
    public void adminAddProductPOSTValidTest() throws Exception{
          String name = "Filet z kurczaka zestaw";
          String sku      = "000-000-003";
          super.adminAddProductPOSTValidTest();
          System.out.println("--------------------test--------------------");
          List<Product> prds = productDao.findAll();
          for(Product p : prds){
            System.out.println(p);
          }
          System.out.println("--------------------test--------------------");
          List<Product> products = productDao.findBySku(sku);
          System.out.println(products.size());

          System.out.println("--------------------test--------------------");
          prds = productDao.findAll();
          for(Product p : prds){
            System.out.println(p);
          }
          System.out.println("--------------------test--------------------");
          assertEquals(products.size(),1);
          Product product = products.get(0);
          assertThat(product.getName(),equalTo(name));
    }
}
