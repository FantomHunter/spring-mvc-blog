package com.codehunter.springmvcblog.admin.controller;

import com.codehunter.springmvcblog.admin.bsservice.PostService;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataIn;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AHomeController {
  private final PostService postService;

  @GetMapping("")
  public String getAdminHome(Model model) {
    model.addAttribute("allPostDataOut", null);
    return "admin/home.html";
  }

  @GetMapping("/all-post")
  public String getAllPost(Model model, @RequestParam int page, @RequestParam int size) {
    var in = new DisplayAllPostDataIn(page, size);
    var out = postService.displayAllPostDataOut(in);
    model.addAttribute("allPostDataOut", out);
    return "admin/home.html";
  }
}
