package com.example.pedidos.controller;

import com.example.pedidos.client.ProdutoClientSync;
import com.example.pedidos.dto.PedidoRequestDTO;
import com.example.pedidos.dto.ProdutoDTO;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoClientSync produtoClient;
    
    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRequestDTO request) {
        try {
            // 1. Buscar o produto
            ProdutoDTO produto = produtoClient.buscarProduto(request.getProdutoId());
            
            // 2. Calcular valor total
            double valorTotal = produto.getPreco() * request.getQuantidade();
            
            // 3. Criar o pedido
            Pedido pedido = new Pedido(
                request.getProdutoId(),
                request.getQuantidade(),
                valorTotal
            );
            
            // 4. Atualizar o estoque
            produtoClient.atualizarEstoque(request.getProdutoId(), request.getQuantidade());
            
            // 5. Salvar o pedido
            Pedido pedidoSalvo = pedidoRepository.save(pedido);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar pedido: " + e.getMessage());
        }
    }
}