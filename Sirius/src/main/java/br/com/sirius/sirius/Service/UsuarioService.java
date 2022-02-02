package br.com.sirius.sirius.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.sirius.sirius.Model.Usuario;
import br.com.sirius.sirius.Model.UsuarioLogin;
import br.com.sirius.sirius.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repositorio;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {

		if (repositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(repositorio.save(usuario));
	}

	public Optional<UsuarioLogin> autentificarUsuario(Optional<UsuarioLogin> usuarioLogin){
		Optional<Usuario> usuario = repositorio.findByUsuario(usuarioLogin.get().getUsuario());
		if(usuario.isPresent()) {
			if(compararSenha(usuarioLogin.get().getSenha(),usuario.get().getSenha())) {
				
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setUsuario(usuario.get().getUsuario());
				usuarioLogin.get().setToken(gerartoken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;
			}
		}
		return Optional.empty();
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(repositorio.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = repositorio.findByUsuario(usuario.getUsuario());
				

				if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

				usuario.setSenha(criptografarSenha(usuario.getSenha()));

				return Optional.ofNullable(repositorio.save(usuario));
			}
			return null;
		}
		
		
	
	
	public String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	public boolean compararSenha(String SenhaDigitada, String senhadoBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(SenhaDigitada, senhadoBanco);
		
	}
	
	public String gerartoken(String usuario, String senha) {
		
		String token = usuario+":"+senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
	
	
	
	

}
