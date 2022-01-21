package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // Instanciando Tabela
@Table(name = "tb_produtos") // Nome da tabela
public class ProdutosEntity {

	@Id // Definindo chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
	private long id; // Definindo Id

	@NotNull // Definindo que não pode ficar nulo.
	@Size(min = 3, max = 255, message = "O atributo nome deve haver no mínimo três caracteres e no máximo 255")
	private String nome; // Nome do produto

	private double preco; // Definindo preço

	private int quantidade; // Definindo quantidade

	boolean disponivel; // Definindo disponivel
	
	

	@ManyToOne // Referencia Categoria
	@JsonIgnoreProperties("tb_produtos") // Evita Looping no postman
	public CategoriaEntity categoria_id; // referencia tb_categoria
	
	@OneToOne // Referencia Categoria
	@JsonIgnoreProperties("tb_produtos") // Evita Looping no postman
	public UsuarioEntity usuario_id; // referencia tb_usuario

	// Getters e Setters

	public CategoriaEntity getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(CategoriaEntity categoria_id) {
		this.categoria_id = categoria_id;
	}

	public long getId() {
		return id;
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

	public UsuarioEntity getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(UsuarioEntity usuario_id) {
		this.usuario_id = usuario_id;
	}
	

}
