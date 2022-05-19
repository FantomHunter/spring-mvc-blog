package com.codehunter.springmvcblog.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

  @GetMapping("/404")
  String get404(Model model) {
    return "public/404.html";
  }


}