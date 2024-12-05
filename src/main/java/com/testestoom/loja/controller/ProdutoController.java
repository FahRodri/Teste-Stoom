package com.testestoom.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testestoom.loja.model.Produto;
import com.testestoom.loja.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // criar produtos
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoCriado = produtoService.criarProduto(produto);
        return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }
    
    // atualizar produtos
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        try {
            Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // deletar produtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // listar produtos ativos
    @GetMapping("/ativos")
    public List<Produto> listarProdutosAtivos() {
        return produtoService.listarProdutosAtivos();
    }
    
    // listar todos produtos
    @GetMapping("/todos")
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }
    
    // buscar produtos (por nome)
    @GetMapping("/nome/{nome}")
    public List<Produto> buscarProdutos(@PathVariable String nome) {
            return produtoService.buscarPorNome(nome);
    }

    // listar produtos por marca
    @GetMapping("/marca/{marca}")
    public List<Produto> listarProdutosPorMarca(@PathVariable String marca) {
        return produtoService.listarPorMarca(marca);
    }

    // listar produtos por categoria
    @GetMapping("/categoria/{categoria}")
    public List<Produto> listarProdutosPorCategoria(@PathVariable String categoria) {
        return produtoService.listarPorCategoria(categoria);
    }

    // inativar um produto
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarProduto(@PathVariable Long id) {
        produtoService.inativarProduto(id);
        return ResponseEntity.noContent().build();
    }

    // reativar um produto
    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativarProduto(@PathVariable Long id) {
        produtoService.reativarProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    // Inativar Produtos por Marca
    @PutMapping("/inativar/marca/{marca}")
    public ResponseEntity<Void> inativarProdutosPorMarca(@PathVariable String marca) {
        produtoService.inativarProdutosPorMarca(marca);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Reativar Produtos por Marca
    @PutMapping("/reativar/marca/{marca}")
    public ResponseEntity<Void> reativarProdutosPorMarca(@PathVariable String marca) {
        produtoService.reativarProdutosPorMarca(marca);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Inativar Produtos por Categoria
    @PutMapping("/inativar/categoria/{categoria}")
    public ResponseEntity<Void> inativarProdutosPorCategoria(@PathVariable String categoria) {
        produtoService.inativarProdutosPorCategoria(categoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Reativar Produtos por Categoria
    @PutMapping("/reativar/categoria/{categoria}")
    public ResponseEntity<Void> reativarProdutosPorCategoria(@PathVariable String categoria) {
        produtoService.reativarProdutosPorCategoria(categoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
