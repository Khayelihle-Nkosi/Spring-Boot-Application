package com.enviro.assessment.grad001.KhayelihleNkosi.api.repositories.productRepository;

import com.enviro.assessment.grad001.KhayelihleNkosi.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
