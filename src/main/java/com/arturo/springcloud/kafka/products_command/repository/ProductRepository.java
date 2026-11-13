package com.arturo.springcloud.kafka.products_command.repository;

import com.arturo.springcloud.kafka.products_command.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
