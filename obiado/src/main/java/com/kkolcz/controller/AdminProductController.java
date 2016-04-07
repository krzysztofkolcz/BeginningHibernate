package com.kkolcz.controller;

import java.util.List;
import java.util.ArrayList;
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

import com.kkolcz.service.ProductService; /* TODO */
import com.kkolcz.service.ProductServiceImpl; /* TODO */
import com.kkolcz.service.ProductCategoryService; /* TODO */
import com.kkolcz.service.ProductCategoryServiceImpl; /* TODO */
import com.kkolcz.model.Product; /* TODO */
import com.kkolcz.model.ProductCategory; /* TODO */
import com.kkolcz.command.ProductCommand;
import com.kkolcz.exception.SkuExistsException;
import com.kkolcz.constants.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminProductController extends BaseController{
  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminProductController.class);

  @Autowired private ProductService userService;
  @Autowired private ProductCategoryService userCategoryService;

  @Autowired private MessageSource messageSource; /* na potrzeby wypisania binding errors */

  @RequestMapping(value = "/product-list", method = RequestMethod.GET)
  public String productListPage(ModelMap model){
    List<Product> productList = productService.findAllProducts();
    model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_LIST,productList);
    return Const.A_VIEW_PRODUCT_LIST;
  }

  @RequestMapping(value = "/add-product", method = RequestMethod.GET)
  public String addProduct(ModelMap model){
    ProductCommand productCommand = new ProductCommand();
    model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND,productCommand);
    return Const.A_VIEW_PRODUCT_ADD;
  }

  @RequestMapping(value = "/add-product", method = RequestMethod.POST)
  public ModelAndView register( 
      @ModelAttribute("productCommand") @Valid ProductCommand productCommand, 
      BindingResult result, 
      WebRequest request) {
      Product product = new Product();
      if (!result.hasErrors()) {
          try {
              product = productService.addProduct(productCommand);
          } catch (SkuExistsException e) {
              result.rejectValue("sku", "message.skuError");
          }
      }
      if (result.hasErrors()) {
          return new ModelAndView(Const.A_VIEW_PRODUCT_ADD, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      } 
      else {
          return new ModelAndView(Const.A_VIEW_SUCCESS_PRODUCT_ADD,Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND , productCommand);
      }
  }

  @RequestMapping(value = "/edit-product-{productId}", method = RequestMethod.GET)
  public ModelAndView editProduct(@PathVariable String productId,ModelMap model) {
      int id = Integer.parseInt(productId);
      Product product = productService.findById(id);
      ProductCommand productCommand = new ProductCommand(product);
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND,productCommand);
      return new ModelAndView(Const.A_VIEW_PRODUCT_ADD, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
  }

  @RequestMapping(value = "/edit-product-{productId}", method = RequestMethod.POST)
  public ModelAndView editProduct(
      @ModelAttribute("productCommand") @Valid ProductCommand productCommand, 
      BindingResult result, 
      WebRequest request,
      @PathVariable String productId) {

      productService.updateProduct(productCommand);

      result.rejectValue("sku", "message.skuNotUniqueError");
      if(result.hasErrors()){
        return new ModelAndView(Const.A_VIEW_PRODUCT_ADD, Const.A_MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      }

      try {
          productCommand.setId(Integer.parseInt(productId));
          productService.updateProduct(productCommand);
      } catch (SkuExistsException e) {
          /* nie powinno wystąpić - powyżej sprawdzenie maila */
          result.rejectValue("email", "message.regError");
          /* return VIEW_PRODUCT_ADD; */
          return new ModelAndView(VIEW_PRODUCT_ADD, MODEL_ATTRIBUTE_PRODUCT_COMMAND, productCommand);
      }

      /* return VIEW_PRODUCT_LIST; */
      return new ModelAndView(VIEW_PRODUCT_LIST);
  }
}
