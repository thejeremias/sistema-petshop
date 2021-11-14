package br.edu.ifrn.petshop.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Pet {

	private int id;
	
	@Size(min = 3, message = "O campo Nome deve receber ao menos 3 caracteres.")
	@NotBlank(message = "O campo Nome não pode estar em branco.")
	private String nome;
	
	@Size(min = 3, message = "O campo Raça deve receber ao menos 3 caracteres.")
	@NotBlank(message = "O campo Raça não pode estar em branco.")
	private String raca;
	
	@Size(min = 3, message = "O campo Dono deve receber ao menos 3 caracteres.")
	@NotBlank(message = "O campo Dono não pode estar em branco.")
	private String dono;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
