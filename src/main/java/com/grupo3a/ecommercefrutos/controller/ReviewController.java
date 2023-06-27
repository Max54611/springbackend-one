package com.grupo3a.ecommercefrutos.controller;

import com.grupo3a.ecommercefrutos.entity.Product;
import com.grupo3a.ecommercefrutos.entity.Review;
import com.grupo3a.ecommercefrutos.exception.NotAuthorizedException;
import com.grupo3a.ecommercefrutos.model.User;
import com.grupo3a.ecommercefrutos.service.FetchService;
import com.grupo3a.ecommercefrutos.service.ProductService;
import com.grupo3a.ecommercefrutos.service.ReviewService;
import com.grupo3a.ecommercefrutos.utility.MappingJacksonValueBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReviewController {
    @Autowired
    private FetchService fetchService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews")
    public MappingJacksonValue addReview(@RequestHeader(name = "Authorization") String token, @RequestBody Review review) {
        User user = fetchService.validateToken(token);
        if (review.getUserId().intValue() != user.getUserId().intValue())
            throw new NotAuthorizedException();
        review.setUserName(user.getUserName());
        reviewService.addReview(review);
        Product product = productService.getProductById(review.getProduct().getProductId());
        product.setReviewCount(product.getReviewCount() + 1);
        product.setTotalRating(product.getTotalRating() + review.getRating());
        product = productService.updateProduct(product);
        return MappingJacksonValueBuilder.init(product)
                .addFilter(Product.FILTER)
                .build();
    }

    @DeleteMapping("/reviews")
    public MappingJacksonValue deleteReview(@RequestHeader(name = "Authorization") String token, @RequestBody Review review) {
        User user = fetchService.validateToken(token);
        if (review.getUserId().intValue() != user.getUserId().intValue())
            throw new NotAuthorizedException();
        Review deletedReview = reviewService.getReviewById(review.getReviewId());
        reviewService.deleteReviewById(review.getReviewId());
        Product product = productService.getProductById(review.getProduct().getProductId());
        product.setReviewCount(product.getReviewCount() - 1);
        product.setTotalRating(product.getTotalRating() - deletedReview.getRating());
        product = productService.updateProduct(product);
        return MappingJacksonValueBuilder.init(product)
                .addFilter(Product.FILTER)
                .build();
    }
}

