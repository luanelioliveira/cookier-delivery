package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositorie.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductById {

  private final ProductRepository repository;

  public Optional<Product> get(long id) {
    return repository.findById(id);
  }
}
