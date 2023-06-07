package com.oguztasgin.repository;

import com.oguztasgin.repository.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findOptionalByProductID(UUID productId);
}
