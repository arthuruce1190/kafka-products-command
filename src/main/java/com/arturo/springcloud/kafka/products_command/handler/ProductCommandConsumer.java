package com.arturo.springcloud.kafka.products_command.handler;

import com.arturo.springcloud.kafka.products_command.model.Command;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import com.arturo.springcloud.kafka.products_command.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProductCommandConsumer {
    private final ProductService productService;

    @Bean
    public Consumer<Command<ProductDto>> handleCommands() {
        return cmd -> {
            String type = cmd.type() == null ? "" : cmd.type().toUpperCase();

            switch (type) {
                case "CREATE" -> {
                    if (cmd.body() == null) {
                        return;
                    }
                    ProductDto productDto = cmd.body();
                    ProductDto productDtoSaved = productService.create(productDto);
                    log.info("Created product Product={}", productDtoSaved);
                }
                case "UPDATE" -> {
                    if (cmd.body() == null) {
                        return;
                    }
                    ProductDto productDto = cmd.body();
                    ProductDto productDtoSaved = productService.update(productDto);
                    log.info("Updated product Product={}", productDtoSaved);
                }
                case "DELETE" -> {
                    if (cmd.body() == null) {
                        return;
                    }
                    ProductDto productDto = cmd.body();
                    ProductDto productDtoSaved = productService.update(productDto);
                    log.info("Deleted product Product={}", productDtoSaved);
                }
                case "GET_ALL" -> {
                    List<ProductDto> productDtoList = productService.getAll();
                    log.info("Get products products={}", productDtoList);
                }
                case "FIND_BY_ID" -> {
                    if (cmd.body() == null) {
                        return;
                    }
                    ProductDto productDto = cmd.body();
                    ProductDto productDtoSearched = productService.findById(productDto.id());
                    log.info("Searching by id product={}", productDtoSearched);
                }
                default -> {
                    log.info("Undefined operation...");
                }
            }
        };
    }
}