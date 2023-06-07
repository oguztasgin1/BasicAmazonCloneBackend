package com.oguztasgin.repository.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;



@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseEntity {
    private Long createdate;
    private Long updatedate;
}
