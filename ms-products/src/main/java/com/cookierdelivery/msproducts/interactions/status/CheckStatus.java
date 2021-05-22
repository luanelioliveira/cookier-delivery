package com.cookierdelivery.msproducts.interactions.status;

import com.cookierdelivery.msproducts.properties.EnvironmentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckStatus {

  @Autowired private EnvironmentProperties environment;

  public String check() {
    return environment.getStatus();
  }
}
