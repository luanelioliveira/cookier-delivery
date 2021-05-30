package com.cookierdelivery.msproducts.util;

import java.util.Objects;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
public class LocalstackContainerIntegration extends LocalStackContainer {

  private static final String DOCKER_IMAGE_VERSION = "localstack/localstack:0.12.11";
  private static LocalstackContainerIntegration container;

  public LocalstackContainerIntegration() {
    super(DockerImageName.parse(DOCKER_IMAGE_VERSION));
    this.withServices(Service.SNS);
    this.withEnv("DEFAULT_REGION", "us-east-1");
  }

  @ClassRule
  public static LocalstackContainerIntegration getInstance() {
    if (Objects.isNull(container)) {
      container = new LocalstackContainerIntegration();
    }
    return container;
  }

  @SneakyThrows
  @Override
  public void start() {
    super.start();
  }

  /*
   * By leaving this method empty, we allow the JVM to handle the container shutdown.
   */
  @Override
  public void stop() {
    log.info("JVM handles shut down");
  }
}
