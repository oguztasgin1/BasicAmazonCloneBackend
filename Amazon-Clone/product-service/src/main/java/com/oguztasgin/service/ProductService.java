package com.oguztasgin.service;

import com.oguztasgin.exception.EErrorType;
import com.oguztasgin.exception.ProductException;
import com.oguztasgin.repository.IProductRepository;
import com.oguztasgin.repository.entity.Product;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService extends ServiceManager<Product, String> {
    private final IProductRepository productRepository;
    public ProductService(IProductRepository productRepository) {
        super(productRepository);
        this.productRepository=productRepository;
    }

    public Product saveDataToDB(Product product) {
        product.setProductID(UUID.randomUUID());
        return save(product);
    }

    public Product getProductDetails(UUID productId) {
        Optional<Product> product = productRepository.findOptionalByProductID(productId);
        if (product.isEmpty()){
            throw new ProductException(EErrorType.PRODUCT_NOT_FOUND);
        }
        return product.get();
    }
}
