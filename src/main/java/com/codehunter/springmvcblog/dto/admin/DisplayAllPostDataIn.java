package com.codehunter.springmvcblog.dto.admin;

import com.codehunter.springmvcblog.dto.OrderType;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class DisplayAllPostDataIn {
  @NonNull
  private Integer page;
  @NonNull
  private Integer size;
  private String orderBy;
  private OrderType orderType;
}
