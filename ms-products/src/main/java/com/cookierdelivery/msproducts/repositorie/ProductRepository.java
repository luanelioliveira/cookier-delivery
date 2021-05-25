package com.cookierdelivery.msproducts.repositorie;

import com.cookierdelivery.msproducts.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
