package br.com.sirius.sirius.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name = "tb_categoria") 
public class Categoria {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;

	@NotNull
	@Size(min = 3, max = 255, message = "O atributo nome deve haver no mínimo três caracteres e no máximo 255")
	private String nome; // Definindo o nome da categoria

	@NotNull 
	@Size(min = 20, max = 255, message = "O atributo descricao deve ter no mínimo 20 caracteres e no máximo 255")
	private String descricao; 

	@NotNull 
	@Size(min = 3, max = 255, message = "O atributo zona_preferencial deve haver no mínimo três caracteres e no máximo 255")
	private String zona_preferencial; 

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)												
	@JsonIgnoreProperties("categoria") 
	private List<Produto> produto; 

	// Criando Getters e Setters
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getZona_preferencial() {
		return zona_preferencial;
	}

	public void setZona_preferencial(String zona_preferencial) {
		this.zona_preferencial = zona_preferencial;
	}

}
