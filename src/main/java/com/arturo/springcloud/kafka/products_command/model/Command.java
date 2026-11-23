package com.arturo.springcloud.kafka.products_command.model;

public record Command<T>(CommandType type, String id, Boolean isActive, T body) {
}
