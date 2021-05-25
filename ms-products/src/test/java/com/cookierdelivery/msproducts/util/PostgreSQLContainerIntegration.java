package com.cookierdelivery.msproducts.util;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.testcontainers.containers.PostgreSQLContainer;

@Slf4j
public class PostgreSQLContainerIntegration
    extends PostgreSQLContainer<PostgreSQLContainerIntegration> {

  private static final String IMAGE_VERSION = "postgres:11.1";
  private static PostgreSQLContainerIntegration container;

  public PostgreSQLContainerIntegration() {
    super(IMAGE_VERSION);
  }

  @ClassRule
  public static PostgreSQLContainerIntegration getInstance() {
    if (Objects.isNull(container)) {
      container = new PostgreSQLContainerIntegration();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
    System.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
  }

  /*
   * By leaving this method empty, we allow the JVM to handle the container shutdown.
   */
  @Override
  public void stop() {
    log.info("JVM handles shut down");
  }
}
