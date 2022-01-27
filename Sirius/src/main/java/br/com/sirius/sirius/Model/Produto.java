package br.com.sirius.sirius.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RequestMapping("/produto")
@Entity // Instanciando Tabela
@Table(name = "tb_produtos") // Nome da tabela
public class Produto {

	@Id // Definindo chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
	private long id; // Definindo Id

	@NotNull // Definindo que não pode ficar nulo.
	@Size(min = 3, max = 255, message = "O atributo nome deve haver no mÃ­nimo trÃªs caracteres e no mÃ¡ximo 255")
	private String nome; // Nome do produto.

	private double preco; // Definindo preço.

	private int quantidade; // Definindo quantidade.

	boolean disponivel; // Definindo disponivel.

	@ManyToOne // Referência Categoria
	@JsonIgnoreProperties("tb_produtos") // Evita Looping no postman
	public Categoria categoria; // referência tb_categoria

	// Getters e Setters

	public long getId() {
		return id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

}
