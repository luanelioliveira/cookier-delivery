package com.cookierdelivery.msproducts.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "environment")
@Getter
@Setter
public class EnvironmentProperties {

  private String status;
}
