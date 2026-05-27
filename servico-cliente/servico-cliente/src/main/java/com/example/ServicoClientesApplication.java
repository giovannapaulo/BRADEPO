package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicoClientesApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServicoClientesApplication.class, args);

        System.out.println("🚀 Serviço de Clientes rodando na porta 8083");
    }
}