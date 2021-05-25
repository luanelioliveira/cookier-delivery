package com.cookierdelivery.msproducts.controllers;

import com.cookierdelivery.msproducts.interactions.product.GetAllProducts;
import com.cookierdelivery.msproducts.models.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final GetAllProducts getAllProducts;

  @GetMapping
  public List<Product> getAllProducts() {
    return getAllProducts.get();
  }
}
