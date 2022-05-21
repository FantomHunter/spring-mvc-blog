package com.codehunter.springmvcblog.admin.controller;

import com.codehunter.springmvcblog.admin.bsservice.PostService;
import com.codehunter.springmvcblog.dto.PostDto;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataIn;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AHomeController {

  private final PostService postService;

  @GetMapping("")
  public String getAdminHome(Model model) {
    return "admin/home.html";
  }

  @GetMapping("/all-post")
  public String getAllPost(Model model, @RequestParam int page, @RequestParam int size) {
    var in = new DisplayAllPostDataIn(page, size);
    var out = postService.displayAllPostDataOut(in);
    model.addAttribute("allPostDataOut", out);
    return "admin/home.html";
  }

  @GetMapping("/create-new-post")
  public String showCreateNewPostPage(Model model) {
    model.addAttribute("post", new PostDto());
    return "admin/create-post.html";
  }

  @GetMapping("/update-post/{id}")
  public String showUpdatePostPage(@PathVariable String id, Model model) {
    model.addAttribute("post", postService.getPost(id));
    return "admin/update-post.html";
  }

  @PostMapping("/create-post")
  public String createPost(@ModelAttribute("post") PostDto post, BindingResult error, Model model) {
    log.info("createPost" + post);
    var newPost = postService.createPost(post);
    return "redirect:/admin/post-details/" + newPost.getId();
  }

  @PostMapping("/update-post")
  public String updatePost(@ModelAttribute("post") PostDto post, BindingResult error, Model model) {
    log.info("updatePost" + post);
    var updatedPost = postService.updatePost(post);
    return "redirect:/admin/post-details/" + updatedPost.getId();
  }

  @GetMapping("/post-details/{id}")
  public String showPost(@PathVariable String id, Model model) {
    var post = postService.getPost(id);
    model.addAttribute("post", post);
    return "admin/display-post.html";
  }

  @GetMapping("/delete-post/{id}")
  public String deletePost(
      @PathVariable String id,
      Model model,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("alert", "Delete post successfully!!");
    postService.deletePost(id);
    String referer = request.getHeader("Referer");
    return "redirect:" + referer;
  }
}
