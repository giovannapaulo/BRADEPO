package com.example.servico_produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicoProdutosApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServicoProdutosApplication.class, args);

        System.out.println("🚀 Serviço de Produtos rodando na porta 8081");
    }
}