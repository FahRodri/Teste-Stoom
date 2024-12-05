package com.testestoom.loja.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.testestoom.loja.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByMarcaAndAtivoTrue(String marca);
	List<Produto> findByMarcaAndAtivoFalse(String marca);
	List<Produto> findByCategoriaAndAtivoTrue(String categoria);
	List<Produto> findByCategoriaAndAtivoFalse(String categoria);
    List<Produto> findByMarcaIgnoreCaseAndAtivoTrue(String marca);
    List<Produto> findByCategoriaIgnoreCaseAndAtivoTrue(String categoria);
    List<Produto> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
}
