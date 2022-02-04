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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired 
	CategoriaRepository repository;

	@GetMapping
	ResponseEntity<List<Categoria>> getAll() {
		
		return ResponseEntity.ok(repository.findAll()); 
	}

	@GetMapping("/{id}")
	ResponseEntity<Categoria> getById(@PathVariable Long id) { 
		
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}") 
	ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) { 
		
		return ResponseEntity.ok(CategoriaRepository.findAllByNomeContainingIgnoreCase(nome)); 
	}
	
	

	@PostMapping 
	ResponseEntity<Categoria> post(@RequestBody @Validated Categoria categoria) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));

	}

	@PutMapping 
	ResponseEntity<Categoria> put(@RequestBody @Validated Categoria categoria) {
		
		return repository.findById(categoria.getId())
				.map(resp -> ResponseEntity.ok().body(repository.save(categoria)))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}") 
	ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		
		return repository.findById(id)
				.map(resp -> {
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}).orElse(ResponseEntity.notFound().build());

	}

}
