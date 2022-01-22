package io.github.thejeremias.petshop.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_pet")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
