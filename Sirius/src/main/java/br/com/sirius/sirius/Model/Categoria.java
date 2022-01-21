package br.com.sirius.sirius.Model;

//Bibliotecas
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //Cria tabela
@Table(name = "tb_categoria") //Da nome para a tabela
public class Categoria {
	
	@Id // Criando a primary key de tb_categoria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Fazendo auto-increment
	private long id;

	@NotNull //Não permite valores nulos
	@Size(min = 3, max = 255, message = "O atributo nome deve haver no mínimo três caracteres e no máximo 255")
	private String nome; // Definindo o nome da categoria

	@NotNull //Não permite valores nulos
	@Size(min = 20, max = 255, message = "O atributo descricao deve ter no mínimo 20 caracteres e no máximo 255")
	private String descricao; // Definindo nome da descrição

	@NotNull //Não permite valores nulos
	@Size(min = 3, max = 255, message = "O atributo zona_preferencial deve haver no mínimo três caracteres e no máximo 255")
	private String zona_preferencial; // Definindo zona preferencial da categoria por estado.


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

	


