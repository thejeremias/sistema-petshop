package br.edu.ifrn.petshop.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
	
}
