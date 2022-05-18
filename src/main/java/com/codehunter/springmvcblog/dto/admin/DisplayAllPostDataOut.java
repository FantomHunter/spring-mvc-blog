package com.codehunter.springmvcblog.dto.admin;

import com.codehunter.springmvcblog.dto.PostDto;
import java.util.List;
import lombok.Data;

@Data
public class DisplayAllPostDataOut {
  private List<PostDto> postDtoList;
  private Integer totalElement;
  private Integer totalPage;
  private Integer currentPage;
  private Boolean hasNext;
  private Boolean hasPrevious;
}
