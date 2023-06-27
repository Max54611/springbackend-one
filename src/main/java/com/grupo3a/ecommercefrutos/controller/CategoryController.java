package com.grupo3a.ecommercefrutos.controller;

import com.grupo3a.ecommercefrutos.entity.Category;
import com.grupo3a.ecommercefrutos.entity.Product;
import com.grupo3a.ecommercefrutos.service.CategoryService;
import com.grupo3a.ecommercefrutos.utility.MappingJacksonValueBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/{id}")
    public MappingJacksonValue getCategory(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        return MappingJacksonValueBuilder.init(category)
                .addFilter(Category.FILTER)
                .addFilter(Product.FILTER)
                .build();
    }
}
