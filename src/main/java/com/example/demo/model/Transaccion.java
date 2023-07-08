package com.example.demo.model;

public record Transaccion(String sourceAccount, String password, String destinationAccount, int amount) {

}
