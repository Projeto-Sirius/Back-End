package com.example.demo.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {

	@Id // Definindo Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull // Definindo que email não pode ser nulo
	@Size(min = 15, max = 255, message = "O email deve ter no mínimo 15 e máximo 255")
	private String email;

	@NotNull // Definindo que senha não pode ser nulo
	@Size(min = 8, max = 255, message = "O atributo deve ter  no mínimo 20 e no máximo 255 caracteres")
	private String senha;

	@NotNull // Definindo que nomeCompleto
	@Size(min = 20, max = 255, message = "O atributo deve ter  no mínimo 20 e no máximo 255 caracteres ")
	private String nomeCompleto;

	@OneToOne(mappedBy = "tb_usuario", cascade = CascadeType.ALL) // Referenciando tb_usuario na tabela Postagem e
																	// colocando CascadeType para excluir produtos da
																	// categoria, quando ela for excluida
	@JsonIgnoreProperties("tb_usuario") // Utilizado para evitar looping no Postman.
	private List<ProdutosEntity> tb_produtos; // Instanciando Lista de ProdutosEntity

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public List<ProdutosEntity> getTb_produtos() {
		return tb_produtos;
	}

	public void setTb_produtos(List<ProdutosEntity> tb_produtos) {
		this.tb_produtos = tb_produtos;
	}

}
