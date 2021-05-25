package com.cookierdelivery.msproducts.interactions.product;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositorie.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllProducts {

  private final ProductRepository repository;

  public List<Product> get() {
    return repository.findAll();
  }
}
