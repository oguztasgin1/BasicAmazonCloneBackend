package com.oguztasgin.repository;

import com.oguztasgin.repository.entity.Cart;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAddToCartRepository extends JpaRepository<Cart, Long> {
    Cart save(Cart cart);
    void delete(Cart cart);
    List<Cart> findByUserId(UUID userId);
}
