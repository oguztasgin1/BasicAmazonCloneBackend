package com.oguztasgin.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CartDetails {
    private UUID userId;
    private List<Product> list;
}
