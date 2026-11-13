package com.arturo.springcloud.kafka.products_command.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("price")
    private Double price;

    @Field("cratedAt")
    @CreatedDate
    private LocalDateTime createdAt;

    @Field("updatedAt")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Field("isActive")
    private Boolean isActive;
}
