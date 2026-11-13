package com.arturo.springcloud.kafka.products_command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ProductsCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsCommandApplication.class, args);
    }

}
