package com.cookierdelivery.msproducts.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class SnsConfig {

  @Value("${aws.region}")
  private String awsRegion;

  @Value("${aws.sns.topic.product.events.name}")
  private String productEventsTopic;

  @Bean
  public AmazonSNS snsClient() {
    return AmazonSNSClientBuilder.standard()
        .withRegion(awsRegion)
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .build();
  }

  @Bean
  public Topic productEventsTopic() {
    return new Topic().withTopicArn(productEventsTopic);
  }
}
