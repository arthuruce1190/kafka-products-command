package com.arturo.springcloud.kafka.products_command.repository;

import com.arturo.springcloud.kafka.products_command.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'isActive': ?0}")
    List<Product> findAll(Boolean isActive);
}
