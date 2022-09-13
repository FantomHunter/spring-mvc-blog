package com.codehunter.springmvcblog.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PostDto {

  private String id;
  private String title;
  private String content;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date createdDate;
}
