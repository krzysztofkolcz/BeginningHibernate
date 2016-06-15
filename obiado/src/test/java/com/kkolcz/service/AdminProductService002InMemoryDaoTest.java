package com.kkolcz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.kkolcz.config.AdminProductService002InMemoryDaoTestContext;
import com.kkolcz.command.ProductCommand;
import com.kkolcz.model.Product;
import com.kkolcz.service.ProductService;
import com.kkolcz.dao.ProductDao;
import com.kkolcz.dao.ProductDaoImpl;
import com.kkolcz.fixture.Create;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AdminProductService002InMemoryDaoTestContext.class})
public class AdminProductService002InMemoryDaoTest{

    @Autowired ProductService productService;
    @Autowired ProductDao productDao;
    /* @Autowired AbstractService<Product,ProductCommand,ProductDao> productService; */
    private Create create; 

    @Before
    public void setUp() {
      this.create = new Create();
    }

    @Test
    public void productServcieAddTest(){
      ProductCommand productCommand = create.createFilledProductCommand();
      Product product = new Product();
      productService.add(productCommand,product);

      System.out.println("--------------------productServcieAddTest--------------------");
      List<Product> products = productDao.findAll();
      for(Product p : products){
        System.out.println(p.getName());
      }
      System.out.println("--------------------productServcieAddTest--------------------");
      assertEquals(products.size(),1);

    }

}
