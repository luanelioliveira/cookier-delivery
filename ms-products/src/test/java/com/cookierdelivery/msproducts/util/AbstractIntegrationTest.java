package com.cookierdelivery.msproducts.util;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import com.cookierdelivery.msproducts.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Application.class)
@RunWith(SpringRunner.class)
@Import(AbstractIntegrationTest.SnsConfig.class)
public abstract class AbstractIntegrationTest {

  @ClassRule
  public static PostgreSQLContainerIntegration dbContainer =
      PostgreSQLContainerIntegration.getInstance();

  @ClassRule
  public static LocalstackContainerIntegration localStackContainer =
      LocalstackContainerIntegration.getInstance();

  @TestConfiguration
  static class SnsConfig {

    private final String productEventsTopicArn;
    private final AmazonSNS snsClient;

    public SnsConfig() {
      this.snsClient =
          AmazonSNSClient.builder()
              .withCredentials(localStackContainer.getDefaultCredentialsProvider())
              .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(SNS))
              .build();

      CreateTopicRequest createTopicRequest = new CreateTopicRequest("product_events");
      this.productEventsTopicArn = snsClient.createTopic(createTopicRequest).getTopicArn();
    }

    @Bean
    public AmazonSNS snsClient() {
      return this.snsClient;
    }

    @Bean
    public Topic productEventsTopic() {
      return new Topic().withTopicArn(this.productEventsTopicArn);
    }
  }
}
