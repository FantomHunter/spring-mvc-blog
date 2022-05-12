package com.codehunter.springmvcblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AHomeController {
  @GetMapping("")
  public String getAdminHome(Model model) {
    return "admin/home.html";
  }

}
