package br.com.sirius.sirius.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sirius.sirius.Model.Categoria;

@Repository //Cria um repositorio 
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{ // Pega a herança da interface JpaRepository
		
	public static List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao){ // Procura por descricao 
		return null;
	}
}

