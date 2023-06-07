package com.oguztasgin.controller;

import com.oguztasgin.repository.entity.Product;
import com.oguztasgin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.oguztasgin.constants.ApiUrls.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveData(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveDataToDB(product));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProductDetails(productId));
    }

}
