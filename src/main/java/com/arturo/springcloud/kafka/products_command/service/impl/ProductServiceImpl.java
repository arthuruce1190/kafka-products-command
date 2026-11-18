package com.arturo.springcloud.kafka.products_command.service.impl;

import com.arturo.springcloud.kafka.products_command.entity.Product;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import com.arturo.springcloud.kafka.products_command.model.mapper.ProductMapper;
import com.arturo.springcloud.kafka.products_command.repository.ProductRepository;
import com.arturo.springcloud.kafka.products_command.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDto create(final ProductDto product) {
        final Product productSaved = productRepository.insert(productMapper.toEntity(product));
        return productMapper.toDto(productSaved);
    }

    @Override
    @Transactional
    public ProductDto update(final ProductDto product) {
        Product productUpdated = productRepository.save(productMapper.toEntity(product));
        return productMapper.toDto(productUpdated);
    }

    @Override
    public List<ProductDto> getAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto findById(final String id) {
        return productRepository.findById(id)
            .map(productMapper::toDto)
            .orElse(null);
    }
}
