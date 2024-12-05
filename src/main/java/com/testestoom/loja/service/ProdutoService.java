package com.testestoom.loja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testestoom.loja.exception.ResourceNotFoundException;
import com.testestoom.loja.model.Produto;
import com.testestoom.loja.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
	
	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }
	
	public void deletarProduto(Long id) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        
        if (produtoExistente.isPresent()) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
    }
	
	// Listar todos os produtos ativos
	public List<Produto> listarProdutosAtivos() {
		return produtoRepository.findAll().stream().filter(Produto::isAtivo).toList();
	}

	// Buscar produtos por nome
	public List<Produto> buscarPorNome(String nome) {
		return produtoRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome);
	}

	// Listar produtos por marca
	public List<Produto> listarPorMarca(String marca) {
		return produtoRepository.findByMarcaIgnoreCaseAndAtivoTrue(marca);
	}

	// Listar produtos por categoria
	public List<Produto> listarPorCategoria(String categoria) {
		return produtoRepository.findByCategoriaIgnoreCaseAndAtivoTrue(categoria);
	}

	// Inativar produto
	public void inativarProduto(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));
		produto.setAtivo(false);
		produtoRepository.save(produto);
	}

	// Reativar produto
	public void reativarProduto(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));
		produto.setAtivo(true);
		produtoRepository.save(produto);
	}
	
    // Inativar Produtos por Marca
    public void inativarProdutosPorMarca(String marca) {
        List<Produto> produtos = produtoRepository.findByMarcaAndAtivoTrue(marca);
        for (Produto produto : produtos) {
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }

    // Reativar Produtos por Marca
    public void reativarProdutosPorMarca(String marca) {
        List<Produto> produtos = produtoRepository.findByMarcaAndAtivoFalse(marca);
        for (Produto produto : produtos) {
            produto.setAtivo(true);
            produtoRepository.save(produto);
        }
    }

    // Inativar Produtos por Categoria
    public void inativarProdutosPorCategoria(String categoria) {
        List<Produto> produtos = produtoRepository.findByCategoriaAndAtivoTrue(categoria);
        for (Produto produto : produtos) {
            produto.setAtivo(false);
            produtoRepository.save(produto);
        }
    }

    // Reativar Produtos por Categoria
    public void reativarProdutosPorCategoria(String categoria) {
        List<Produto> produtos = produtoRepository.findByCategoriaAndAtivoFalse(categoria);
        for (Produto produto : produtos) {
            produto.setAtivo(true);
            produtoRepository.save(produto);
        }
    }
    // Listar todos os produtos
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

}
