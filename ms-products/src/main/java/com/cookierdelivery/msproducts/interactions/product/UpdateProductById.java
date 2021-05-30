package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.enums.EventType;
import com.cookierdelivery.msproducts.messaging.ProductPublisher;
import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositories.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductById {

  private final ProductRepository repository;
  private final ProductPublisher publisher;

  public Optional<Product> update(long id, Product product) {
    if (!repository.existsById(id)) return Optional.empty();

    product.setId(id);
    var productUpdated = repository.save(product);

    publisher.publishProductEvent(EventType.PRODUCT_UPDATED, productUpdated, "zeze");

    return Optional.of(productUpdated);
  }
}
