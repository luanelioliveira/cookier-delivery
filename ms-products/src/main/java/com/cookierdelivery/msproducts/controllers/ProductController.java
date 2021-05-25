package com.cookierdelivery.msproducts.controllers;

import com.cookierdelivery.msproducts.interactions.product.CreateAProduct;
import com.cookierdelivery.msproducts.interactions.product.DeleteProductById;
import com.cookierdelivery.msproducts.interactions.product.GetAllProducts;
import com.cookierdelivery.msproducts.interactions.product.GetProductById;
import com.cookierdelivery.msproducts.interactions.product.UpdateProductById;
import com.cookierdelivery.msproducts.models.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final GetAllProducts getAllProducts;
  private final GetProductById getProductById;
  private final CreateAProduct createAProduct;
  private final UpdateProductById updateProductById;
  private final DeleteProductById deleteProductById;

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

  @PostMapping
  public ResponseEntity<Product> createAProduct(@RequestBody Product product) {
    var productCreated = createAProduct.create(product);

    return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProductById(
      @PathVariable long id, @RequestBody Product product) {

    return updateProductById
        .update(id, product)
        .map((productUpdated) -> ResponseEntity.ok(productUpdated))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductById(@PathVariable long id) {

    deleteProductById.delete(id);
    return ResponseEntity.noContent().build();
  }
}
