package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositorie.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductById {

  private final ProductRepository repository;

  public Optional<Product> update(long id, Product product) {
    if (!repository.existsById(id)) return Optional.empty();

    product.setId(id);

    return Optional.of(repository.save(product));
  }
}
