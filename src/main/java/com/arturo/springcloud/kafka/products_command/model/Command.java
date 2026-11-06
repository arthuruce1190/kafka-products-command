package com.arturo.springcloud.kafka.products_command.model;

public record Command<T>(String type, Long id, T body) {
}
