package com.cookierdelivery.msproducts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductEvent {
  private long productId;
  private String code;

  private String username;
}
