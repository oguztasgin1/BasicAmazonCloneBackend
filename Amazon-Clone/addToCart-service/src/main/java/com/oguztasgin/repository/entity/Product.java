package com.oguztasgin.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class Product {
    private Long id;
    public UUID productID;
    public String name;
    public double price;
    public double rating;
    public String imageURL;
}
