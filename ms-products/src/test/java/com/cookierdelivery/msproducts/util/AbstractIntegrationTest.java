package com.cookierdelivery.msproducts.util;

import com.cookierdelivery.msproducts.Application;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Application.class)
@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {

  @ClassRule
  public static PostgreSQLContainerIntegration container =
      PostgreSQLContainerIntegration.getInstance();
}
