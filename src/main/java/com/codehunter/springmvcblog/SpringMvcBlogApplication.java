package com.codehunter.springmvcblog;

import com.codehunter.springmvcblog.entity.Post;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.IntStream;
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
      IntStream.range(1,12).forEach(i -> {
        Post post = new Post();
        post.setTitle("title " + i);
        String content = "Content " + i;
        post.setContent(content.getBytes(StandardCharsets.UTF_8));
        postRepository.save(post);
      });
    };
  }
}
