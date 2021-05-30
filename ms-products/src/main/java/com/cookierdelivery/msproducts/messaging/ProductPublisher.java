package com.cookierdelivery.msproducts.messaging;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.cookierdelivery.msproducts.enums.EventType;
import com.cookierdelivery.msproducts.models.Event;
import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.models.ProductEvent;
import com.cookierdelivery.msproducts.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductPublisher {

  private final AmazonSNS sns;
  private final Topic topic;

  public void publishProductEvent(EventType eventType, Product product, String username) {
    ProductEvent productEvent = ProductEvent.of(product.getId(), product.getCode(), username);
    Event event = Event.from(eventType, JsonUtils.toJson(productEvent));

    sns.publish(topic.getTopicArn(), JsonUtils.toJson(event));
  }
}
