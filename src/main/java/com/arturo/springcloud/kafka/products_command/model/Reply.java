package com.arturo.springcloud.kafka.products_command.model;

public record Reply<T>(Integer status, String statusMessage, String message, T body) {
}
