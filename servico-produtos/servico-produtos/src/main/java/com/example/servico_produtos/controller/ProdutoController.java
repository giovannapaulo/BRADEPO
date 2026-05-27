package com.example.servico_produtos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.servico_produtos.model.Produto;
import com.example.servico_produtos.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}/estoque")
    public Produto atualizarEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        return service.atualizarEstoque(id, quantidade);
    }
}