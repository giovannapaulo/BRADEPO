package com.exemple.pedidos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.pedidos.dto.ProdutoDTO;

import reactor.core.publisher.Mono;

@Component
public class ProdutoClient {
    
    private final WebClient webClient;
    
    public ProdutoClient(@Value("${produtos.service.url}") String produtosUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(produtosUrl)
                .build();
    }
    
    public Mono<ProdutoDTO> buscarProduto(Long id) {
        return webClient.get()
                .uri("/api/produtos/{id}", id)
                .retrieve()
                .bodyToMono(ProdutoDTO.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Produto não encontrado: " + id)));
    }
    
    public Mono<Void> atualizarEstoque(Long id, Integer quantidade) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/produtos/{id}/estoque")
                    .queryParam("quantidade", quantidade)
                    .build(id))
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> Mono.error(new RuntimeException("Erro ao atualizar estoque do produto: " + id)));
    }
}