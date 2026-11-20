package com.arturo.springcloud.kafka.products_command.service;

import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto product);

    ProductDto update(ProductDto product);

    ProductDto delete(ProductDto product);

    List<ProductDto> getAll(Boolean isActive);

    ProductDto findById(String id);
}
