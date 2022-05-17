package com.codehunter.springmvcblog;

import com.codehunter.springmvcblog.entity.Post;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringMvcBlogApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMvcBlogApplication.class, args);
  }

  @Bean
  public CommandLineRunner demoData(PostRepository postRepository) {
    return args -> {
      Post post1 = new Post();
      post1.setTitle("title 1");
      post1.setContent("content1".getBytes(StandardCharsets.UTF_8));
      Post post2 = new Post();
      post2.setTitle("title 2");
      post2.setContent("content2".getBytes(StandardCharsets.UTF_8));
      postRepository.saveAll(List.of(post1, post2));
    };
  }
}
