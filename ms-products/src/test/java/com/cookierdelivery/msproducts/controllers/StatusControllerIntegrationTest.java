package com.cookierdelivery.msproducts.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.cookierdelivery.msproducts.util.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class StatusControllerIntegrationTest extends AbstractIntegrationTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testStatus() {
    String body = restTemplate.getForObject("/status", String.class);
    assertThat(body).isEqualTo("OK");
  }
}
