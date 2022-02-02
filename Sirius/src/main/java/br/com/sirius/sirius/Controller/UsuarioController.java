package br.com.sirius.sirius.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.sirius.sirius.Model.Usuario;
import br.com.sirius.sirius.Model.UsuarioLogin;
import br.com.sirius.sirius.Repository.UsuarioRepository;
import br.com.sirius.sirius.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository repositorioUsuario;
	@Autowired
	UsuarioService repositorioService;
	
	@GetMapping("/all")
	ResponseEntity<List<Usuario>> getAll(){
		
		return ResponseEntity.ok(repositorioUsuario.findAll());
		
	}
	
	@PostMapping("/cadastrar")
	ResponseEntity <Usuario> cadastrar(@RequestBody @Valid Usuario usuario){
		return repositorioService.CadastrarUsuario(usuario)
		
		.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
		.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/logar")
	ResponseEntity <UsuarioLogin> logar(@RequestBody @Valid Optional<UsuarioLogin> user){
		return repositorioService.autentificarUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/atualizar")
	ResponseEntity <Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
		return repositorioService.atualizarUsuario(usuario)
		.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	
	
	
		
}
