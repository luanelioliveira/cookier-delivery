package com.cookierdelivery.msproducts.controllers;

import com.cookierdelivery.msproducts.interactions.status.CheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

  @Autowired private CheckStatus checkStatus;

  @GetMapping()
  public String status() {
    return checkStatus.check();
  }
}
