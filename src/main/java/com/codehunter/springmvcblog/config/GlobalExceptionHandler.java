package com.codehunter.springmvcblog.config;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ModelAndView handleException() {
    ModelAndView view = new ModelAndView();
    view.setViewName("public/404.html");
    return view;
  }
}
