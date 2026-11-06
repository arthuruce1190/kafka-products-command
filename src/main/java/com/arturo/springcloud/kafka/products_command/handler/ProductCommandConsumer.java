package com.arturo.springcloud.kafka.products_command.handler;

import com.arturo.springcloud.kafka.products_command.model.Command;
import com.arturo.springcloud.kafka.products_command.model.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ProductCommandConsumer {

    @Bean
    public Consumer<Command<ProductDto>> handleCommands() {
        return command -> {
            log.info("Comando recibido y consumido con éxito: type={}, body={}", command.type(), command.body());

        };
    }
}