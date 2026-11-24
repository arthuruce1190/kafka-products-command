package com.arturo.springcloud.kafka.products_command.handler;

import com.arturo.springcloud.kafka.products_command.model.Command;
import com.arturo.springcloud.kafka.products_command.model.Reply;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import com.arturo.springcloud.kafka.products_command.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

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
    public Function<Message<Command<ProductDto>>, Message<Reply<?>>> handleCommands() {
        return msg -> {
            final Command<ProductDto> cmd = msg.getPayload();
            Reply<?> reply = switch (cmd.type()) {
                case CREATE -> {
                    if (cmd.body() == null) {
                        log.warn("Create empty body");
                        yield new Reply<>(
                            NOT_FOUND, NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    } else {
                        ProductDto productSaved = productService.create(cmd.body());
                        log.info("Created product Product={}", productSaved);
                        yield new Reply<>(OK, OK.getReasonPhrase(), "Created product", productSaved);
                    }
                }
                case UPDATE -> {
                    if (cmd.body() == null) {
                        yield new Reply<>(
                            NOT_FOUND, NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    } else {
                        ProductDto productSaved = productService.update(cmd.body());
                        log.info("Updated product Product={}", productSaved);
                        yield new Reply<>(OK, OK.getReasonPhrase(), "Updates product", productSaved);
                    }
                }
                case DELETE -> {
                    if (cmd.body() == null) {
                        yield new Reply<>(
                            NOT_FOUND, NOT_FOUND.getReasonPhrase(), "Empty body", null);
                    } else {
                        ProductDto productDeleted = productService.update(cmd.body());
                        log.info("Deleted product Product={}", productDeleted);
                        yield new Reply<>(OK, OK.getReasonPhrase(), "Deleted product", productDeleted);
                    }
                }
                case GET_ALL -> {
                    List<ProductDto> productList = productService.getAll(cmd.isActive());
                    log.info("Get products products={}", productList);
                    yield new Reply<>(OK, OK.getReasonPhrase(), "Update product", productList);
                }
                case FIND_BY_ID -> {
                    ProductDto productSearched = productService.findById(cmd.id());
                    yield new Reply<>(OK, OK.getReasonPhrase(), "Update product", productSearched);
                }
                default -> {
                    log.info("Undefined operation...");
                    yield new Reply<>(FORBIDDEN, OK.getReasonPhrase(), "Undefined operation", null);
                }
            };

            String correlationId = msg.getHeaders().get("correlationId", String.class);
            log.info("Recibiendo correlationId={}", correlationId);

            MessageBuilder<Reply<?>> out = MessageBuilder.withPayload(reply);
            if (correlationId != null) {
                out.setHeader("correlationId", correlationId);
            }
            return out.build();

        };
    }
}