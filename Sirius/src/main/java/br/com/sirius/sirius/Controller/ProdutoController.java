package br.com.sirius.sirius.Controller;

import java.math.BigDecimal;
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
@RestController 
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class ProdutoController {
	
	@Autowired 
	private ProdutoRepository repository; 

	@GetMapping 
	public ResponseEntity<List<Produto>> GetAll() {
		
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{id}") 
	public ResponseEntity<Produto> GetByID(@PathVariable long id) {
		
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome) {
		
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaiorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(repository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(repository.findByPrecoLessThanOrderByPrecoDesc(preco));
	}
	
	@PostMapping 
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping 
	public ResponseEntity<Produto> put( @RequestBody Produto produto) {
		
		return repository.findById(produto.getId()).map(resposta -> ResponseEntity.ok().body(repository.save(produto))) 
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return repository.findById(id).map(resposta -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

}