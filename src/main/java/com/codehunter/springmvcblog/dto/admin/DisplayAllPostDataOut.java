package com.codehunter.springmvcblog.dto.admin;

import com.codehunter.springmvcblog.dto.PostDto;
import java.util.List;
import lombok.Data;

@Data
public class DisplayAllPostDataOut {
  private List<PostDto> postDtoList;
  private Integer totalElement;
}
