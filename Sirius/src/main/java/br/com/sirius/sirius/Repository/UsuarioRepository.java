package br.com.sirius.sirius.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sirius.sirius.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	
	public List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);

}
