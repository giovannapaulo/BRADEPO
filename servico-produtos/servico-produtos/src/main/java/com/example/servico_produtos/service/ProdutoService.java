package com.example.servico_produtos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servico_produtos.model.Produto;
import com.example.servico_produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarEstoque(Long id, Integer quantidade) {

        Produto produto = buscarPorId(id);

        produto.setEstoque(produto.getEstoque() - quantidade);

        return repository.save(produto);
    }
}