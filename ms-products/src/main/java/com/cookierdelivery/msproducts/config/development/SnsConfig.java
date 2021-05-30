package com.cookierdelivery.msproducts.config.development;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("development")
public class SnsConfig {

  private static final String LOCALSTACK_URL = "http://localhost:4566";

  @Value("${aws.region}")
  private String awsRegion;

  @Value("${aws.sns.topic.product.events.name}")
  private String productEventsTopic;

  private Map<String, String> topics = new HashMap<>();

  @Bean
  public AmazonSNS snsClient() {
    AmazonSNS snsClient =
        AmazonSNSClient.builder()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(LOCALSTACK_URL, awsRegion))
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();

    createTopics(snsClient);

    return snsClient;
  }

  @Bean
  public Topic productEventsTopic() {
    return new Topic().withTopicArn(topics.get(productEventsTopic));
  }

  private void createTopics(AmazonSNS sns) {
    CreateTopicRequest createTopicRequest = new CreateTopicRequest(productEventsTopic);
    var productEventsTopicArn = sns.createTopic(createTopicRequest).getTopicArn();
    topics.put(productEventsTopic, productEventsTopicArn);
  }
}
