package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.enums.EventType;
import com.cookierdelivery.msproducts.messaging.ProductPublisher;
import com.cookierdelivery.msproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductById {

  private final ProductRepository repository;
  private final ProductPublisher publisher;

  public void delete(long id) {

    var product = repository.findById(id).orElseThrow(NoSuchFieldError::new);
    repository.delete(product);

    publisher.publishProductEvent(EventType.PRODUCT_DELETED, product, "zeze");
  }
}
