package com.example.pedidos.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.pedidos.dto.ProdutoDTO;

@Component
public class ProdutoClientSync {
    
    private final RestTemplate restTemplate;
    private final String produtosUrl;
    
    @Autowired
    public ProdutoClientSync(@Value("${produtos.service.url}") String produtosUrl) {
        this.restTemplate = new RestTemplate();
        this.produtosUrl = produtosUrl;
    }
    
    public ProdutoDTO buscarProduto(Long id) {
        try {
            String url = produtosUrl + "/api/produtos/" + id;
            return restTemplate.getForObject(url, ProdutoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Produto não encontrado: " + id, e);
        }
    }
    
    public void atualizarEstoque(Long id, Integer quantidade) {
        try {
            String url = produtosUrl + "/api/produtos/" + id + "/estoque?quantidade=" + quantidade;
            restTemplate.put(url, null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar estoque do produto: " + id, e);
        }
    }
}