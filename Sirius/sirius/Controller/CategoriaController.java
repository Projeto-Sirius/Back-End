package br.com.sirius.sirius.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sirius.sirius.Model.Categoria;
import br.com.sirius.sirius.Repository.CategoriaRepository;

@RequestMapping("/categoria") // Gera o EndPoint
@RestController // Cria um Controller
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite que o Front-End se comunique com o Back-End
public class CategoriaController {

	@Autowired // Faz a injeção das dependências na Interface
	CategoriaRepository repository;

	@GetMapping // Responde com todos os dados de Categoria
	ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(repository.findAll()); // Abstrai todos os dados de Categoria
	}

	@GetMapping("/{id}") // Responde com todos os dados que contem o id
	ResponseEntity<Categoria> getById(@PathVariable Long id) { // Procura o id atravez da url
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build()); // Devolve
																														// todos
																														// os
																														// dados
																														// com
																														// o
																														// id
	}

	@GetMapping("/nome/{nome}") // Procura pelo nome no Banco de dados
	ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) { // abstrai o nome apartir da url
		return ResponseEntity.ok(CategoriaRepository.findAllByNomeContainingIgnoreCase(nome)); // Procura no BD atravez
																								// do nome
	}

	@PostMapping // Envia as informações para o Banco de dados
	ResponseEntity<Categoria> post(@RequestBody @Validated Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));

	}

	@PutMapping // Alterar dados na tabela categoria
	ResponseEntity<Categoria> put(@RequestBody @Validated Categoria categoria) {// Faz a alteração a apartir do corpo da
																				// requisição
		return repository.findById(categoria.getId()) // Procurar pelo Id
				.map(resp -> ResponseEntity.ok().body(repository.save(categoria))) // Caso exista ele modifica os dados
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}") // Deletar dados do BD atravez do ID
	ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		return repository.findById(id) // Procura pelo Id
				.map(resp -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}).orElse(ResponseEntity.notFound().build());

	}

}
