package com.oguztasgin.manager;

import com.oguztasgin.repository.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(
        name = "product-profile-service-feign",
        url = "http://localhost:9092/api/v1/product",
        decode404 = true
)
public interface IProductManager {
    @GetMapping("/search/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable UUID productId);
}
