package com.kkolcz.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.kkolcz.service.ProductService; 
//import com.kkolcz.service.ProductServiceImpl; /* TODO */
import com.kkolcz.service.ProductCategoryService; 
//import com.kkolcz.service.ProductCategoryServiceImpl; /* TODO */
import com.kkolcz.model.Product; 
import com.kkolcz.model.ProductCategory; 
import com.kkolcz.command.ProductCommand;
import com.kkolcz.exception.SkuExistsException;
import com.kkolcz.constants.Const;
import com.kkolcz.validator.ProductValidator;

import com.kkolcz.model.State; 

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
@SessionAttributes("categories")
public class AdminProductController extends BaseController{
  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminProductController.class);

  @Autowired private ProductService productService;
  @Autowired private ProductCategoryService productCategoryService;
  @Autowired private ProductValidator productValidator;

  @Autowired private MessageSource messageSource; /* na potrzeby wypisania binding errors */

  /* TODO - jak to działa */
  @ModelAttribute("categories")
  public List<ProductCategory> initializeProfiles() {
      return productCategoryService.findAllProductCategories();
  }

  @ModelAttribute("states")
  public List<State> initializeStates() {
      /* State.values(); returns State[] */
      return Arrays.asList(State.values());
  }


  @RequestMapping(value = "/product-list", method = RequestMethod.GET)
  public String productListPage(ModelMap model){
      List<Product> productList = productService.findAll();
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_LIST,productList);
      /* model.addAttribute("states",State.values()); */
      return Const.A_VIEW_PRODUCT_LIST;
  }

  @RequestMapping(value = "/add-product", method = RequestMethod.GET)
  public String addProduct(ModelMap model){
      ProductCommand productCommand = new ProductCommand();
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND,productCommand);
      logger.error("add product get");
      return Const.A_VIEW_PRODUCT_EDIT;
  }

  @RequestMapping(value = "/add-product", method = RequestMethod.POST)
  public ModelAndView addProductPost( 
      @ModelAttribute("productCommand") @Valid ProductCommand productCommand, 
      BindingResult result, 
      WebRequest request) {

      logger.error("add product post");
      Product product = new Product();

      productValidator.validate(productCommand,result);

      if (result.hasErrors()) {
          logger.error("result errors");
          return new ModelAndView(Const.A_VIEW_PRODUCT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      }else{
          productService.add(productCommand,product);
          return new ModelAndView(Const.A_VIEW_SUCCESS_PRODUCT_ADD,Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , productCommand);
      } 
  }

  @RequestMapping(value = "/edit-product-{productId}", method = RequestMethod.GET)
  public ModelAndView editProduct(@PathVariable String productId,ModelMap model) {
      int id = Integer.parseInt(productId);
      Product product = productService.findById(id);
      ProductCommand productCommand = new ProductCommand(product);
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND,productCommand);
      return new ModelAndView(Const.A_VIEW_PRODUCT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
  }

  @RequestMapping(value = "/edit-product-{productId}", method = RequestMethod.POST)
  public ModelAndView editProduct(
      @ModelAttribute("productCommand") @Valid ProductCommand productCommand, 
      BindingResult result, 
      WebRequest request,
      @PathVariable String productId) {

      int id = Integer.parseInt(productId);

      boolean skuExistsExceptId = productService.skuExistsExceptId(productCommand.getSku(),id);
      if(skuExistsExceptId){
          result.rejectValue("sku", "message.skuError");
      }

      if(result.hasErrors()){
          return new ModelAndView(Const.A_VIEW_PRODUCT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      }else{
          productCommand.setId(id);
          productService.update(productCommand);
          return new ModelAndView(Const.A_VIEW_PRODUCT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      }
  }
}
