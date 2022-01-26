package br.com.sirius.sirius.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sirius.sirius.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
		
	public static List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao){
		return null;
	}
}
