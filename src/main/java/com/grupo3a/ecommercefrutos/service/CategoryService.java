package com.grupo3a.ecommercefrutos.service;

import com.grupo3a.ecommercefrutos.entity.Category;
import com.grupo3a.ecommercefrutos.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category getCategoryById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
