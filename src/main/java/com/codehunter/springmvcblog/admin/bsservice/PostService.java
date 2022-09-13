package com.codehunter.springmvcblog.admin.bsservice;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codehunter.springmvcblog.PostRepository;
import com.codehunter.springmvcblog.dto.PostDto;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataIn;
import com.codehunter.springmvcblog.dto.admin.DisplayAllPostDataOut;
import com.codehunter.springmvcblog.entity.Post;
import com.codehunter.springmvcblog.util.UUIDUtil;

import lombok.RequiredArgsConstructor;

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
    dto.setId(e.getId().toString());
    dto.setContent(new String(e.getContent(), StandardCharsets.UTF_8));
    dto.setTitle(e.getTitle());
    dto.setCreatedDate(new Date(e.getCreatedDate().toEpochMilli()));
    return dto;
  }

  public PostDto createPost(PostDto post) {
    var postDao = new Post();
    postDao.setTitle(post.getTitle());
    postDao.setContent(post.getContent().getBytes(StandardCharsets.UTF_8));
    return this.convertPostDaoToPostDto(postRepository.save(postDao));
  }

  public PostDto getPost(String id) {
    if (!UUIDUtil.getInstance().isValidUUID(id)) {
      throw new EntityNotFoundException();
    }
    return postRepository
        .findById(UUID.fromString(id))
        .map(this::convertPostDaoToPostDto)
        .orElseThrow(EntityNotFoundException::new);
  }

  public PostDto updatePost(PostDto post) {
    if (!StringUtils.hasText(post.getId())) {
      throw new EntityNotFoundException();
    }
    return postRepository
        .findById(UUID.fromString(post.getId()))
        .map(
            postDao -> {
              postDao.setTitle(post.getTitle());
              postDao.setContent(post.getContent().getBytes(StandardCharsets.UTF_8));
              return postRepository.save(postDao);
            })
        .map(this::convertPostDaoToPostDto)
        .orElseThrow(EntityNotFoundException::new);
  }

  public void deletePost(String id) {
    postRepository.deleteById(UUID.fromString(id));
  }
}
