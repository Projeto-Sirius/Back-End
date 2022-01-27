package br.com.sirius.sirius.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sirius.sirius.Model.Produto;
import br.com.sirius.sirius.Repository.ProdutoRepository;
@RequestMapping("/produto")
@RestController // Cria um Controller
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite conexão com o front-end
public class ProdutoController {
	
	@Autowired //Injeta as dependencias em repositorio
	private ProdutoRepository repository; //instancia repositório

	@GetMapping  // Pesquisa todos os dados
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{id}") // Pesquisa por ID
	public ResponseEntity<Produto> GetByID(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")//Pesquisa por nome
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping //Insere informações no BD
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping //Altera as informações
	public ResponseEntity<Produto> put( @RequestBody Produto produto) {
		return repository.findById(produto.getId()).map(resposta -> ResponseEntity.ok().body(repository.save(produto))) //Altera a informação
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}") //Deleta as informações 
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		return repository.findById(id).map(resposta -> {
			repository.deleteById(id); //Deleta a informação no BD
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build()); // Se não conter as informações no BD, retorna 404
	}

}