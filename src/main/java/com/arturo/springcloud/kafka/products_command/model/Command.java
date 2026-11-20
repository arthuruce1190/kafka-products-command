package com.arturo.springcloud.kafka.products_command.model;

public record Command<T>(String type, String id, Boolean isActive, T body) {
}
