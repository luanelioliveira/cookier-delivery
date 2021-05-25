package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductById {

  private final ProductRepository repository;

  public void delete(long id) {
    repository.findById(id);
  }
}
