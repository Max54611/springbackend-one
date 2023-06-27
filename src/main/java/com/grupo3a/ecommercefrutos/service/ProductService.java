package com.grupo3a.ecommercefrutos.service;

import com.grupo3a.ecommercefrutos.entity.Product;
import com.grupo3a.ecommercefrutos.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    public Product[] getProductsWithText(String text) {
        return repository.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(text, text);
    }
}

