package com.cookierdelivery.msproducts.controllers;

import com.cookierdelivery.msproducts.interactions.product.GetAllProducts;
import com.cookierdelivery.msproducts.interactions.product.GetProductById;
import com.cookierdelivery.msproducts.models.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final GetAllProducts getAllProducts;
  private final GetProductById getProductById;

  @GetMapping
  public List<Product> getAllProducts() {
    return getAllProducts.get();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable long id) {

    return getProductById
        .get(id)
        .map((product) -> ResponseEntity.ok(product))
        .orElse(ResponseEntity.notFound().build());
  }
}
