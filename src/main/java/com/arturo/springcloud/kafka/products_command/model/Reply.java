package com.arturo.springcloud.kafka.products_command.model;

import org.springframework.http.HttpStatus;

public record Reply<T>(HttpStatus status, String statusMessage, String message, T body) {
}
