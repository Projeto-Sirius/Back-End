package br.com.sirius.sirius.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sirius.sirius.Model.Usuario;
import br.com.sirius.sirius.Repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		
		Optional <Usuario> username = repository.findByUsuario(usuario);
		username.orElseThrow(() -> new UsernameNotFoundException(usuario + " not found."));
		

		return username.map(UserDetailsImpl::new).get();
		
	}

}
