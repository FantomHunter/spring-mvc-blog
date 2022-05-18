package com.codehunter.springmvcblog.admin.bsservice;

import com.codehunter.springmvcblog.PostRepository;
import com.codehunter.springmvcblog.dto.PostDto;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataIn;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataOut;
import com.codehunter.springmvcblog.entity.Post;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public DisplayAllPostDataOut displayAllPostDataOut(DisplayAllPostDataIn in) {
    var listPost =
        postRepository.findAll(
            PageRequest.of(
                in.getPage(), in.getSize(), Sort.by(Order.by("createdDate").with(Direction.DESC))));
    DisplayAllPostDataOut out = new DisplayAllPostDataOut();
    out.setPostDtoList(
        listPost.get().map(this::convertPostDaoToPostDto).collect(Collectors.toList()));
    out.setTotalElement((int) listPost.getTotalElements());
    out.setTotalPage(listPost.getTotalPages());
    out.setCurrentPage(listPost.getNumber() + 1);
    out.setHasNext(listPost.hasNext());
    out.setHasPrevious(listPost.hasPrevious());
    return out;
  }

  private PostDto convertPostDaoToPostDto(Post e) {
    var dto = new PostDto();
    dto.setContent(new String(e.getContent(), StandardCharsets.UTF_8));
    dto.setTitle(e.getTitle());
    dto.setCreatedDate(e.getCreatedDate());
    return dto;
  }

  public PostDto createPost(PostDto post) {
    var postDao = new Post();
    postDao.setTitle(post.getTitle());
    postDao.setContent(post.getContent().getBytes(StandardCharsets.UTF_8));
    return this.convertPostDaoToPostDto(postRepository.save(postDao));
  }
}
