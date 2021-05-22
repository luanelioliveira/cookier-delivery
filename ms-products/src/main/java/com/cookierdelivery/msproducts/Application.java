package com.cookierdelivery.msproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
@EntityScan(basePackages = "com.cookierdelivery.msproducts")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
