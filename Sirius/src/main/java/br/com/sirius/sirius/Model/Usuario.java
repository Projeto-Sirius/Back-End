package br.com.sirius.sirius.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Não permite nulo")
	@Size(min = 15, max = 255, message = "O email deve ter no mínimo 15 e máximo 255")
	private String email;

	@NotNull(message = "Não permite nulo")
	@Size(min = 8, max = 255, message = "O atributo deve ter  no mínimo 20 e no máximo 255 caracteres")
	private String senha;

	@NotNull(message = "Não permite nulo")
	@Size(min = 20, max = 255, message = "O atributo deve ter  no mánimo 20 e no máximo 255 caracteres ")
	private String nomeCompleto;

	
	
	
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



	
}