package com.cwsoft.multi_tenant.repository;

import com.cwsoft.multi_tenant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // No need for custom methods; Spring Data will handle it
}
