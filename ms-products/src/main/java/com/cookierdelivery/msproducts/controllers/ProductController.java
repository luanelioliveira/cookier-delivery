package com.cookierdelivery.msproducts.controllers;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositories.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductRepository repository;

  @GetMapping
  public List<Product> getAllProducts() {
    return repository.findAll();
  }
}
