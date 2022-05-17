package com.codehunter.springmvcblog.dto;

import lombok.Data;

@Data
public class PostDto {
  private String title;
  private byte[] content;
}
