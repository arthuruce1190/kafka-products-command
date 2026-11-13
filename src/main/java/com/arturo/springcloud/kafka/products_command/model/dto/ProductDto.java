package com.arturo.springcloud.kafka.products_command.model.dto;

public record ProductDto(String id, String name, Double price, Boolean isActive) {
}
