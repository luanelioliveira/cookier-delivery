package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositorie.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAProduct {

  private final ProductRepository repository;

  public Product create(Product product) {

    return repository.save(product);
  }
}
