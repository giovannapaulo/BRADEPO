package com.example.servico_produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servico_produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}