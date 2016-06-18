package com.kkolcz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
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
import org.springframework.mock.web.MockHttpSession;

import com.kkolcz.controller.*;
import com.kkolcz.service.*;
import com.kkolcz.dao.ProductCategoryDao;
import com.kkolcz.dao.ProductDao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.dao.UserDao;
import com.kkolcz.model.ProductCategory;
import com.kkolcz.model.Product;
import com.kkolcz.model.User;
import com.kkolcz.config.AdminProductController003Context;
import com.kkolcz.config.DbUnitHibernateLiquibaseConfig;
import com.kkolcz.config.ViewResolverContext;
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

import org.springframework.context.ApplicationContext;
import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.database.jvm.JdbcConnection;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.jvm.HsqlConnection;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdminProductController003Context.class, DbUnitHibernateLiquibaseConfig.class, ViewResolverContext.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@WebAppConfiguration
public class  AdminProductController004DbUnitLiquibaseTest extends AdminProductController000General{
    /* public static final String ID = "id"; */
    /* public static final String NAME = "name"; */
    /* public static final String PRICE = "price"; */
    /* public static final String ACTIVE = "active"; */
    /* public static final String SKU = "sku"; */
    /* public static final String PRODUCTCATEGORY = "productCategory"; */

    @Autowired ApplicationContext context;
    @Autowired ProductCategoryService productCategoryService; 
    @Autowired ProductService productService; 

    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /* mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); */
        mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
    } 

    @Before
    public void setUpDatabase() throws Exception{
      FileSystemResourceAccessor fsra = new FileSystemResourceAccessor();
      DatabaseChangeLog changelog = new DatabaseChangeLog("src/main/resources/liquibase/changelog.xml");
      DataSource dataSource = (DataSource)context.getBean("dataSource");
      HsqlConnection hsqlConnection = new HsqlConnection(dataSource.getConnection());
      Liquibase liquibase;
      liquibase = new Liquibase("src/main/resources/liquibase/changelog.xml",
              fsra,
              hsqlConnection
      );
      liquibase.update("");
    }


    protected ProductCategory getProductCategory1(){
        return productCategoryService.findById(1);
    }

    protected ProductCategory getProductCategory2(){
        return productCategoryService.findById(2);
    }

    protected Product getProduct1(){
        return productService.findById(1);
    }

    protected Product getProduct2(){
        return productService.findById(2);
    }

    protected List<Product> createProductList(){
        return productService.findAll();
    }


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
