package com.oguztasgin.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Product extends BaseEntity{
    @Id
    private String id;

    public UUID productID;
    public String name;
    public double price;
    public double rating;
    public String imageURL;
}
