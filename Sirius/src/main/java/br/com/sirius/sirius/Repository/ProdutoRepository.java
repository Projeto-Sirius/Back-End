package br.com.sirius.sirius.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sirius.sirius.Model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
	
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);

	
	

}
