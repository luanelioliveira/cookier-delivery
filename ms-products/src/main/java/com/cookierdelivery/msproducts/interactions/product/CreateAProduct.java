package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.enums.EventType;
import com.cookierdelivery.msproducts.messaging.ProductPublisher;
import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAProduct {

  private final ProductRepository repository;
  private final ProductPublisher publisher;

  public Product create(Product product) {
    var productCreated = repository.save(product);

    publisher.publishProductEvent(EventType.PRODUCT_CREATED, productCreated, "zeze");

    return productCreated;
  }
}
