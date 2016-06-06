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

import com.kkolcz.service.ProductService; 
/* import com.kkolcz.service.ProductServiceImpl; #<{(| TODO |)}># */
import com.kkolcz.service.ProductCategoryService; 
import com.kkolcz.service.ProductCategoryServiceImpl; /* TODO */
import com.kkolcz.model.Product; 
import com.kkolcz.model.ProductCategory; 
import com.kkolcz.command.ProductCommand;
import com.kkolcz.command.ProductCategoryCommand;
import com.kkolcz.exception.SkuExistsException;
import com.kkolcz.constants.Const;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminProductCategoryController extends BaseController{
  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminProductCategoryController.class);

  @Autowired private ProductCategoryService productCategoryService;

  @Autowired private MessageSource messageSource; /* na potrzeby wypisania binding errors */


  @RequestMapping(value = "/product-category-list", method = RequestMethod.GET)
  public String productCategoryListPage(ModelMap model){
      List<ProductCategory> productCategoryList = productCategoryService.findAllProductCategories();
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_LIST,productCategoryList);
      return Const.A_VIEW_PRODUCT_CAT_LIST;
  }

  @RequestMapping(value = "/add-product-category", method = RequestMethod.GET)
  public String addProductCategory(ModelMap model){
      ProductCategoryCommand productCategoryCommand = new ProductCategoryCommand();
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND,productCategoryCommand);
      return Const.A_VIEW_PRODUCT_CAT_EDIT;
  }

  @RequestMapping(value = "/add-product-category", method = RequestMethod.POST)
  public ModelAndView addProductCategoryPost( 
      @ModelAttribute("productCategoryCommand") @Valid ProductCategoryCommand productCategoryCommand, 
      BindingResult result, 
      WebRequest request) {
      ProductCategory productCategory = new ProductCategory();

      boolean nameExist = productCategoryService.nameExist(productCategoryCommand.getName());
      if(nameExist){
          result.rejectValue("name", "message.categoryNameError");
      }

      if (!result.hasErrors()) {
           productCategoryService.addProductCategory(productCategoryCommand);
      }
      return new ModelAndView(Const.A_VIEW_PRODUCT_CAT_EDIT,Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND , productCategoryCommand);
  }

  @RequestMapping(value = "/edit-product-category-{productCategoryId}", method = RequestMethod.GET)
  public ModelAndView editProductCategory(@PathVariable String productCategoryId,ModelMap model) {
      int id = Integer.parseInt(productCategoryId);
      ProductCategory productCategory = productCategoryService.findById(id);
      ProductCategoryCommand productCategoryCommand = new ProductCategoryCommand(productCategory);
      model.addAttribute(Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND,productCategoryCommand);
      return new ModelAndView(Const.A_VIEW_PRODUCT_CAT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, productCategoryCommand);
  }

  @RequestMapping(value = "/edit-product-category-{productCategoryId}", method = RequestMethod.POST)
  public ModelAndView editProductCategory(
      @ModelAttribute("productCategoryCommand") @Valid ProductCategoryCommand productCategoryCommand, 
      BindingResult result, 
      WebRequest request,
      @PathVariable String productCategoryId) {

      int id = Integer.parseInt(productCategoryId);

      boolean nameExist = productCategoryService.nameExistExceptId(productCategoryCommand.getName(),productCategoryCommand.getId());
      if(nameExist){
          result.rejectValue("name", "message.categoryNameError");
      }

      if(!result.hasErrors()){
          productCategoryCommand.setId(id);
          productCategoryService.updateProductCategory(productCategoryCommand);
      }
      return new ModelAndView(Const.A_VIEW_PRODUCT_CAT_EDIT, Const.A_MODEL_ATTRIBUTE_PRODUCT_CAT_COMMAND, productCategoryCommand);
  }
}
