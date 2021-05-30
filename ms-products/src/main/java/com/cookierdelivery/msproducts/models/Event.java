package com.cookierdelivery.msproducts.models;

import com.cookierdelivery.msproducts.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "from")
public class Event {

  private EventType eventType;
  private String data;
}
