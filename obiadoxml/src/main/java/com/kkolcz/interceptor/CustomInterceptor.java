package com.kkolcz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {

  private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);
  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.debug("Custom Interceptor before handling the request - logger");
    System.out.println("Custom Interceptor before handling the request");
    return super.preHandle(request, response, handler);
  }
  
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.debug("Custom Interceptor after handling the request - logger");
    System.out.println("Custom Interceptor after handling the request");
    response.addHeader("dummy-header", "dummy-value");
    super.postHandle(request, response, handler, modelAndView);
  }
  
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    logger.debug("Custom Interceptor After rendering the view");
    System.out.println("Custom Interceptor After rendering the view");
    super.afterCompletion(request, response, handler, ex);
  }
}
