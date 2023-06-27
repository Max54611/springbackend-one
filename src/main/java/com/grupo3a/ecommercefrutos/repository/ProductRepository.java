package com.grupo3a.ecommercefrutos.repository;

import com.grupo3a.ecommercefrutos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE product_sequence SET next_val = 1", nativeQuery = true)
    void resetSequence();

    Product[] findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(String text1, String text2);
}
