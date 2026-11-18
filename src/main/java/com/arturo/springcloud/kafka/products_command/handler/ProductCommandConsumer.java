package com.arturo.springcloud.kafka.products_command.handler;

import com.arturo.springcloud.kafka.products_command.model.Command;
import com.arturo.springcloud.kafka.products_command.model.Reply;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import com.arturo.springcloud.kafka.products_command.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProductCommandConsumer {
    private final ProductService productService;

    // Consumer recibe
    // Function recibe y produce

    @Bean
    public Function<Command<ProductDto>, Reply<?>> handleCommands() {
        return cmd -> {
            String type = cmd.type() == null ? "" : cmd.type().toUpperCase();

            switch (type) {
                case "CREATE" -> {
                    if (cmd.body() == null) {
                        log.warn("Create empty body");
                        return new Reply<>(
                            NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    }
                    ProductDto productSaved = productService.create(cmd.body());
                    log.info("Created product Product={}", productSaved);
                    return new Reply<>(OK.value(), OK.getReasonPhrase(), "Created product", productSaved);
                }
                case "UPDATE" -> {
                    if (cmd.body() == null) {
                        return new Reply<>(
                            NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    }
                    ProductDto productSaved = productService.update(cmd.body());
                    log.info("Updated product Product={}", productSaved);
                    return new Reply<>(OK.value(), OK.getReasonPhrase(), "Updates product", productSaved);
                }
                case "DELETE" -> {
                    if (cmd.body() == null) {
                        return new Reply<>(
                            NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    }
                    ProductDto productDeleted = productService.update(cmd.body());
                    log.info("Deleted product Product={}", productDeleted);
                    return new Reply<>(OK.value(), OK.getReasonPhrase(), "Deleted product", productDeleted);
                }
                case "GET_ALL" -> {
                    if (cmd.body() == null) {
                        return new Reply<>(
                            NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    }
                    List<ProductDto> productList = productService.getAll();
                    log.info("Get products products={}", productList);
                    return new Reply<>(OK.value(), OK.getReasonPhrase(), "Update product", productList);
                }
                case "FIND_BY_ID" -> {
                    if (cmd.body() == null) {
                        return new Reply<>(NOT_FOUND.value(), OK.getReasonPhrase(), "Empty body", null);
                    }
                    ProductDto productSearched = productService.findById(cmd.body().id());
                    log.info("Searching by id product={}", productSearched);
                    return new Reply<>(OK.value(), OK.getReasonPhrase(), "Update product", productSearched);
                }
                default -> {
                    log.info("Undefined operation...");
                    return new Reply<>(FORBIDDEN.value(), OK.getReasonPhrase(), "Undefined operation", null);
                }
            }
        };
    }
}